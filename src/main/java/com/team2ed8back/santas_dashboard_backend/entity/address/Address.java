package com.team2ed8back.santas_dashboard_backend.entity.address;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name="";
    @Column(nullable = false)
    private String address;
    @Column( nullable = false)
    private long lat;
    @Column(nullable = false)
    private long lng;

}
