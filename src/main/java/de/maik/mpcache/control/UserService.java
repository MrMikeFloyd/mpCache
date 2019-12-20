package de.maik.mpcache.control;

import de.maik.mpcache.cache.Cached;
import de.maik.mpcache.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserService {

    Logger log = LoggerFactory.getLogger(UserService.class);

    @Cached
    public List<User> getAll() {
        log.info("Invoking getAll.");
        List<User> allUsers = new ArrayList<>();
        allUsers.add(new User(1, "Jane", "Doe", "Munich"));
        allUsers.add(new User(2, "John", "Doe", "Paris"));
        allUsers.add(new User(3, "Julia", "Doe", "London"));
        allUsers.add(new User(4, "James", "Doe", "Rome"));

        return allUsers;
    }

    @Cached
    public User getById(int userId) {
        log.info("Invoking getById.");
        return new User(userId, "Jeremy", "Doe", "Madrid");
    }
}
