package de.maik.mpcache.entity;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String homeCity;

    public User(int id, String firstName, String lastName, String homeCity) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeCity = homeCity;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }
}
