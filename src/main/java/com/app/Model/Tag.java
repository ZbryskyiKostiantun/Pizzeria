package com.app.Model;

public class Tag {
    Long id;
    String name;
    String discription;

    public Tag(Long id, String name, String discription) {
        this.id = id;
        this.name = name;
        this.discription = discription;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
