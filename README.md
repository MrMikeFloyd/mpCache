# mpCache Demo application

This minimalistic sample application demonstrates the concept of decorating arbitrary method calls transparently by using custom annotations and interceptors. The example showcases an on-demand read-through cache that can be linked to whatever method calls the developer would like cached. In this example, these would be calls to a service retrieving user records.
The sample application is a Microprofile REST app powered by an OpenLiberty application server. The application stub was generated using the [MicroProfile Starter](https://start.microprofile.io).
In the showcase, the `getById` method in the `UserService` class is being cached. All that is needed is the `@Cached` annotation. The logic for the cache decorator resides in the `CacheInterceptor` class. Google's Guava Cache is used as a cache implementation.

## How to run

The generation of the executable jar file can be performed by issuing the following command

    mvn clean package

This will create an executable jar file **mpcache.jar** within the _target_ maven folder. This can be started by executing the following command

    java -jar target/mpcache.jar


### Liberty Dev Mode

During development, you can use Liberty's development mode (dev mode) to code while observing and testing your changes on the fly.
With the dev mode, you can code along and watch the change reflected in the running server right away; 
unit and integration tests are run on pressing Enter in the command terminal; you can attach a debugger to the running server at any time to step through your code.

    mvn liberty:dev


To explore the REST endpoints exposed by the application, open your browser at the following URL

    http://localhost:8181/openapi/ui/















