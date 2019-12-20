package de.maik.mpcache.boundary;

import de.maik.mpcache.entity.User;
import de.maik.mpcache.control.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@ApplicationScoped
public class UsersController {

    private UserService userService;

    UsersController() {
    }

    @Inject
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GET
    @Path("{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public User getById(@PathParam("userId") int userId) {
        return userService.getById(userId);
    }
}
