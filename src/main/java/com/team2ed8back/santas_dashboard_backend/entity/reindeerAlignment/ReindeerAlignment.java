package com.team2ed8back.santas_dashboard_backend.entity.reindeerAlignment;

import com.team2ed8back.santas_dashboard_backend.entity.reindeer.Reindeer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "reindeer_alignment", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"lead", "front1", "front2",
                "middle1", "middle2", "middle3",
                "back1", "back2", "back3"})
})
public class ReindeerAlignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Name is mandatory")
    private String name;

    //------------> For Rudolph in case weather = snow. <----------
    @ManyToOne
    @JoinColumn(name = "lead", referencedColumnName = "id")
    private Reindeer lead;
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
    @JoinColumn(name = " back1", referencedColumnName = "id")
    private Reindeer back1;

    @ManyToOne
    @JoinColumn(name = "back2", referencedColumnName = "id")
    private Reindeer back2;

    @ManyToOne
    @JoinColumn(name = "back3", referencedColumnName = "id")
    private Reindeer back3;
}
