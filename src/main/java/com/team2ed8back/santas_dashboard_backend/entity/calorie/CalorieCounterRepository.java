package com.team2ed8back.santas_dashboard_backend.entity.calorie;

import com.team2ed8back.santas_dashboard_backend.entity.CalorieCounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalorieCounterRepository extends JpaRepository<CalorieCounter, Long> {
}
