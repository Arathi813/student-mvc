package com.example.test.studentportal.domain;

public class DomainPost {

    private String title;
    private String body;
    private long id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
