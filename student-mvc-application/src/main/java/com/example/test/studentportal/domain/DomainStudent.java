package com.example.test.studentportal.domain;

import javax.persistence.*;

@Entity
@Table(name= "students")
public class DomainStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "batch")
    private String batch;


    public DomainStudent(long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public DomainStudent() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getBatch() {
        return batch;
    }
}
