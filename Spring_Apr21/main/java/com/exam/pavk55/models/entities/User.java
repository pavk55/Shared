package com.exam.pavk55.models.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(nullable = false, unique = true)
    @Size(min = 5, max = 20)
    private String username;
    @Column(nullable = true, name = "first_name")
    private String firstName;
    @Column(nullable = false, name = "last_name")
    @Size(min = 5, max = 20)
    private String lastName;
    @Column(nullable = false)
    @Size(min = 3)
    private String password;
    @Column(nullable = false)
    @Email(regexp = "\\w+@\\w+\\.\\w+")
    private String email;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
