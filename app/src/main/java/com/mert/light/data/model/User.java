package com.mert.light.data.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class User extends RealmObject {
    @Required
    @PrimaryKey
    private long id;
    @Required
    private String name;
    @Required
    private String surname;
    @Required
    private String username;
    @Required
    private String password;

    private Date createdAt;
    private Date updatedAt;

    public User() {

        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
    }

    public void setId(long id) {
        this.id = id;
        this.updatedAt = new Date();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.updatedAt = new Date();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        this.updatedAt = new Date();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.updatedAt = new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        this.updatedAt = new Date();
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        this.updatedAt = new Date();
    }
}
