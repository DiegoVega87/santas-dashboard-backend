package com.team2ed8back.santas_dashboard_backend.entity.address;


import jakarta.persistence.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;

@CrossOrigin("*")
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name="";
    @Column(nullable = false)
    private String address;
    @Column(name = "lat", nullable = false)
    private BigDecimal latitude;
    @Column(name = "lng", nullable = false)
    private BigDecimal longitude;

    public Address() {
    }

    public Address(long id, String name, String address, BigDecimal latitude, BigDecimal longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Address(String name, String address, BigDecimal latitude, BigDecimal longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
