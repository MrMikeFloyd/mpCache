package de.maik.mpcache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.UncheckedExecutionException;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Array;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Interceptor Class that wraps around method
 * invokations to provide caching functionality.
 */
public class CacheInterceptor {
    private Cache<Object, Object> cache;
    private static final long VALIDITY_PERIOD = 10;

    public CacheInterceptor() {
        initializeCache();
    }

    private void initializeCache() {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(VALIDITY_PERIOD, TimeUnit.MINUTES)
                .build();
    }

    @AroundInvoke
    public Object cache(final InvocationContext ic) throws Throwable {
        try {
            if (ic.getParameters().length == 1) {
                return cache.get(
                        Array.get(ic.getParameters(), 0),
                        ic::proceed);
            } else {
                return ic.proceed();
            }
        } catch (UncheckedExecutionException runtimeException) {
            throw runtimeException.getCause();
        } catch (ExecutionException checkedException) {
            throw new CacheInvocationException(checkedException.getCause());
        }
    }
}
