package com.salekseev.booksmarketclient.model;

import java.util.Objects;

public class Publisher {

    private Long id;

    private String name;
    private String phone;
    private String email;
    private String information;

    public Publisher() {
    }

    public Publisher(Long id, String name, String phone, String email, String information) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.information = information;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(id, publisher.id) && Objects.equals(name, publisher.name)
                && Objects.equals(phone, publisher.phone) && Objects.equals(email, publisher.email)
                && Objects.equals(information, publisher.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, email, information);
    }
}
