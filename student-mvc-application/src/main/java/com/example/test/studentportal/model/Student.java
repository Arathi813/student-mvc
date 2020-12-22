package com.example.test.studentportal.model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class Student {

    private Long id;
    @NotNull(message="First name cannot be missing or empty")
    @Size(min=2, message="First name must not be less than 2 characters")
    private String firstName;
    private String lastName;
    private String batch;
    @Email
    @NotNull(message="email cannot be missing or empty")
    private String email;

    public Student(String firstName, String lastName, String email, Long id, String batch) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.batch = batch;
        this.email = email;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
