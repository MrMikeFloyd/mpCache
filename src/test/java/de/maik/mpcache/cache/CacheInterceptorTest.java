package de.maik.mpcache.cache;

import de.maik.mpcache.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.interceptor.InvocationContext;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class CacheInterceptorTest {

    private CacheInterceptor cacheInterceptor;
    @Mock
    private InvocationContext ic;

    @BeforeEach
    void setUp() {
        cacheInterceptor = new CacheInterceptor();
    }

    @Test
    void callsTargetMethodOnlyOnFirstInvocation() throws Throwable {
        // GIVEN - a target method with a single Integer parameter
        Integer[] params = new Integer[1];
        params[0] = 1;
        when(ic.getParameters()).thenReturn(params);
        // that returns a User object
        when(ic.proceed()).thenReturn(new User(1, "Last", "First", "Somewhere Over There"));

        // WHEN - calling that method twice with identical parameters
        cacheInterceptor.cache(ic);
        cacheInterceptor.cache(ic);

        // THEN - the target method will be called only once
        verify(ic, times(1)).proceed();
    }

    @Test
    void rethrowOriginalRuntimeException() throws Exception {
        Integer[] params = new Integer[1];
        params[0] = 1;
        when(ic.getParameters()).thenReturn(params);
        when(ic.proceed()).thenThrow(new IllegalArgumentException());

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> cacheInterceptor.cache(ic));
    }

    @Test
    void convertToCacheInvocationException() throws Exception {
        Integer[] params = new Integer[1];
        params[0] = 1;
        when(ic.getParameters()).thenReturn(params);
        when(ic.proceed()).thenThrow(new IOException());

        assertThatExceptionOfType(CacheInvocationException.class).isThrownBy(() -> cacheInterceptor.cache(ic));
    }

}