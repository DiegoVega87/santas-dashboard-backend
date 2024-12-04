package com.team2ed8back.santas_dashboard_backend.entity.reindeerAlignment;

import com.team2ed8back.santas_dashboard_backend.entity.reindeer.Reindeer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

@Data
@Entity
@Table(name = "reindeer_alignment")
public class ReindeerAlignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    //------------> For Rudolph in case weather = snow. <----------
    private Reindeer lead;
    //------------> For Rudolph in case weather = snow. <----------

    private Reindeer front1;

    private Reindeer front2;

    private Reindeer middle1;

    private Reindeer middle2;

    private Reindeer middle3;

    private Reindeer back1;

    private Reindeer back2;

    private Reindeer back3;
}
