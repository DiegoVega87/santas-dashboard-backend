package com.team2ed8back.santas_dashboard_backend.controller;

import com.team2ed8back.santas_dashboard_backend.service.calorie.CalorieCounterService;
import com.team2ed8back.santas_dashboard_backend.service.calorie.FormResponseCounterCalorie;
import com.team2ed8back.santas_dashboard_backend.service.calorie.FormSavedCookieAndCalorie;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/counter-calories")
@RequiredArgsConstructor
public class CalorieCounterController {

    private final CalorieCounterService calorieCounterService;

    @PostMapping
    public FormResponseCounterCalorie addCookies(@RequestBody FormSavedCookieAndCalorie formSavedCookieAndCalorie) {
        return calorieCounterService.addCookie(formSavedCookieAndCalorie);
    }

    @GetMapping
    public FormResponseCounterCalorie getCounter() {
        return calorieCounterService.getCounterCalorie();
    }

    @PutMapping()
    public FormResponseCounterCalorie updateCounter() {
        return calorieCounterService.updateCounter();
    }


}
