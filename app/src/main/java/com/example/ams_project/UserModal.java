package com.example.ams_project;

public class UserModal {

    private int id;
    private String email, firstName, lastName, profilePicture;

    public UserModal(int id, String email, String firstName, String lastName, String profilePicture) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
