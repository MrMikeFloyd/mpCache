package de.maik.mpcache.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.UncheckedExecutionException;
import de.maik.mpcache.control.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InterceptorBinding;
import javax.interceptor.InvocationContext;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Interceptor Class that wraps around method
 * invokations to provide caching functionality.
 */
@Cached
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class CacheInterceptor {
    private Cache<Object, Object> cache;
    private static final long VALIDITY_PERIOD = 10;
    Logger log = LoggerFactory.getLogger(UserService.class);

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
        log.info("Invoking Cache.");
        try {
            return cache.get(
                    ic.getParameters(),
                    ic::proceed);
        } catch (UncheckedExecutionException runtimeException) {
            throw runtimeException.getCause();
        } catch (ExecutionException checkedException) {
            throw new CacheInvocationException(checkedException.getCause());
        }
    }
}
