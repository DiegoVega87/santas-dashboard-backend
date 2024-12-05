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
    @ManyToOne
    @JoinColumn(name = "leader", referencedColumnName = "id")
    private Reindeer leader;
    //------------> For Rudolph in case weather = snow. <----------

    @ManyToOne
    @JoinColumn(name = "front1", referencedColumnName = "id")
    private Reindeer front1;

    @ManyToOne
    @JoinColumn(name = "front2", referencedColumnName = "id")
    private Reindeer front2;

    @ManyToOne
    @JoinColumn(name = "middle1", referencedColumnName = "id")
    private Reindeer middle1;

    @ManyToOne
    @JoinColumn(name = "middle2", referencedColumnName = "id")
    private Reindeer middle2;

    @ManyToOne
    @JoinColumn(name = "middle3", referencedColumnName = "id")
    private Reindeer middle3;

    @ManyToOne
    @JoinColumn(name = "back1", referencedColumnName = "id")
    private Reindeer back1;

    @ManyToOne
    @JoinColumn(name = "back2", referencedColumnName = "id")
    private Reindeer back2;

    @ManyToOne
    @JoinColumn(name = "back3", referencedColumnName = "id")
    private Reindeer back3;
}
