package com.team2ed8back.santas_dashboard_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "christmas_letter")
public class ChristmasLetter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "title_card")
    private String titleCard;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false, name = "was_read")
    private boolean wasRead = false;
    @Column(name = "date_read")
    private String date_read;

    public void generateDateReadCard(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        date_read = LocalDateTime.now().format(formatter);
    }

}
