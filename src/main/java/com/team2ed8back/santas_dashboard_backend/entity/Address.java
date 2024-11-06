package com.team2ed8back.santas_dashboard_backend.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String street;
    private int number;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    private String zip;
    private String country;

    public Address() {
    }

    public Address(long id, String street, int number, String city, String state, String zip, String country) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public Address(String city, String street, int number, String state, String zip, String country) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
