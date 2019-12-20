package de.maik.mpcache;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/users")
@ApplicationScoped
public class UsersController {

    private UserService userService;

    @Inject
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GET
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GET
    @Path("{userId}")
    public User getById(int userId) {
        return userService.getById(userId);
    }
}
