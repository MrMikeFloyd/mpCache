package de.maik.mpcache.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.UncheckedExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Array;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Interceptor Class that wraps around method
 * invocations to provide caching functionality.
 */
@Cached
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class CacheInterceptor {
    private Cache<Object, Object> cache;
    // Invalidate the cache after 10 minutes
    private static final long VALIDITY_PERIOD = 10;
    private Logger log = LoggerFactory.getLogger(CacheInterceptor.class);

    public CacheInterceptor() {
        initializeCache();
    }

    private void initializeCache() {
        log.info("Inizializing Cache.");
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(VALIDITY_PERIOD, TimeUnit.MINUTES)
                .build();
    }

    @AroundInvoke
    public Object cache(final InvocationContext ic) throws Throwable {
        log.info("Invoking Cache.");
        try {
            // Retrieve the annotated method's return object from the cache
            return cache.get(
                    // Use the method's (first) param as a key
                    Array.get(ic.getParameters(), 0),
                    // Delegate to Original method if there's nothing
                    ic::proceed);
        } catch (UncheckedExecutionException runtimeException) {
            // Be transparent and throw the target's original unchecked Exceptions
            throw runtimeException.getCause();
        } catch (ExecutionException checkedException) {
            // Let's stick to RuntimeExceptions and wrap checked exceptions
            throw new CacheInvocationException(checkedException.getCause());
        }
    }
}
