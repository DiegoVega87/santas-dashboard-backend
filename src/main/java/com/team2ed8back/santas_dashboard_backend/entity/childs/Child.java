package com.team2ed8back.santas_dashboard_backend.entity.childs;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "child")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    boolean isFemale;
    String linkImgProfile;
    private int kindnessLevel;
    private int respectfulnessLevel;
    private int patienceLevel;
    private int effortLevel;
    private int teamworkLevel;
    @Enumerated(EnumType.STRING)
    private BehaviorLevel behaviorLevel;
    @Column(name = "suitable_for_gift")
    private boolean suitableForGift;

    public Child(String name, int age, boolean isFemale, String linkImgProfile, int kindnessLevel, int respectfulnessLevel, int patienceLevel, int effortLevel, int teamworkLevel) {
        this.name = name;
        this.age = age;
        this.isFemale = isFemale;
        this.linkImgProfile = linkImgProfile;
        this.kindnessLevel = kindnessLevel;
        this.respectfulnessLevel = respectfulnessLevel;
        this.patienceLevel = patienceLevel;
        this.effortLevel = effortLevel;
        this.teamworkLevel = teamworkLevel;
    }

    @PrePersist
    public void setBehaviorLevel() {
        int sum = this.patienceLevel + this.respectfulnessLevel + this.effortLevel + this.kindnessLevel + this.teamworkLevel;
        if(sum >= 33) {
            this.behaviorLevel = BehaviorLevel.EXCELENTE;
            this.setSuitableForGift(true);
        }
        else if(sum >= 18) {
            this.behaviorLevel = BehaviorLevel.REGULAR;
            this.setSuitableForGift(true);
        }
        else if(sum >= 5){
            this.behaviorLevel = BehaviorLevel.MALO_OJALA_CAMBIES;
            this.setSuitableForGift(false);
        }
        else{
            this.behaviorLevel = BehaviorLevel.BASURA_DE_LA_SOCIEDAD;
            this.setSuitableForGift(false);
        }
    }

}
