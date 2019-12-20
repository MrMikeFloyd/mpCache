package de.maik.mpcache;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserService {

    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        allUsers.add(new User(1, "Jane", "Doe", "Munich"));
        allUsers.add(new User(2, "John", "Doe", "Paris"));
        allUsers.add(new User(3, "Julia", "Doe", "London"));
        allUsers.add(new User(4, "James", "Doe", "Rome"));

        return allUsers;
    }

    public User getById(int userId) {
        return new User(userId, "Jeremy", "Doe", "Madrid");
    }
}
