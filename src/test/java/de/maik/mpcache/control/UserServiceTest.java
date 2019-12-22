package de.maik.mpcache.control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void invokesCacheOnFirstInvocation() {
        // TODO: Test cache invocation
    }

}