package com.team2ed8back.santas_dashboard_backend.entity.reindeerAlignment;

import com.team2ed8back.santas_dashboard_backend.entity.reindeer.Reindeer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "reindeer_alignment", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"left1", "right1", "left2",
                "right2", "left3", "right3",
                "left4", "rigth4", "back3"})
})
public class ReindeerAlignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Name is mandatory")
    private String name;

    //------------> For Rudolph in case weather = snow. <----------
    @ManyToOne
    @JoinColumn(name = "left1", referencedColumnName = "id")
    private Reindeer left1;
    //------------> For Rudolph in case weather = snow. <----------

    @ManyToOne
    @JoinColumn(name = "right1", referencedColumnName = "id")
    private Reindeer right1;

    @ManyToOne
    @JoinColumn(name = "left2", referencedColumnName = "id")
    private Reindeer left2;

    @ManyToOne
    @JoinColumn(name = "right2", referencedColumnName = "id")
    private Reindeer right2;

    @ManyToOne
    @JoinColumn(name = "left3", referencedColumnName = "id")
    private Reindeer left3;

    @ManyToOne
    @JoinColumn(name = "right3", referencedColumnName = "id")
    private Reindeer right3;

    @ManyToOne
    @JoinColumn(name = " left4", referencedColumnName = "id")
    private Reindeer left4;

    @ManyToOne
    @JoinColumn(name = "rigth4", referencedColumnName = "id")
    private Reindeer rigth4;


}
