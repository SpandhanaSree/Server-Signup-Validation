
package com.challenge.backend.model;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private Integer age;

    public User(String id, String name, String email, String password, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }
}
