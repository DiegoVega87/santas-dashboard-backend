package com.team2ed8back.santas_dashboard_backend.entity.reindeer;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reindeer")
@Data
public class Reindeer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String type;

    public Reindeer() {
    }

    public Reindeer( String name, String type) {
        this.name = name;
        this.type = type;
    }

}
