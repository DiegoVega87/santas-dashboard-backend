package com.team2ed8back.santas_dashboard_backend.entity.calorie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "calorie_counter")
public class CalorieCounter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int totalCookies;
    private int totalCalories;
    private String date_last_updated;

    public CalorieCounter(int totalCookies, int totalCalories) {
        this.totalCookies = totalCookies;
        this.totalCalories = totalCalories;
        this.date_last_updated = LocalDateTime.now().toString();
    }

    public void setTotalCookies(int totalCookies) {
        this.totalCookies += totalCookies;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories += totalCalories;
    }

    public void setDate_last_updated() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date_last_updated = LocalDateTime.now().format(dateFormatter);
    }

    public void setCounterAndCaloriesToZero() {
        this.totalCalories = 0;
        this.totalCookies = 0;
        this.date_last_updated = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
