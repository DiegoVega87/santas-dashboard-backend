package com.team2ed8back.santas_dashboard_backend.service.calorie;


import com.team2ed8back.santas_dashboard_backend.entity.CalorieCounter;
import com.team2ed8back.santas_dashboard_backend.entity.calorie.CalorieCounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalorieCounterService {

    private final CalorieCounterRepository calorieCounterRepository;

    public FormResponseCounterCalorie addCookie(FormSavedCookieAndCalorie formSavedCookieAndCalorie){
        if(formSavedCookieAndCalorie.quantityCalories() < 1 || formSavedCookieAndCalorie.quantityCookies() < 1
        || formSavedCookieAndCalorie.quantityCalories() > 1000 || formSavedCookieAndCalorie.quantityCookies() > 18){
            throw new RuntimeException("You cannot add exaggerated amounts of cookies or calories");
        }
        CalorieCounter counter = calorieCounterRepository.findById(1L)
                .orElse(new CalorieCounter());
        counter.setTotalCookies(formSavedCookieAndCalorie.quantityCookies());
        counter.setTotalCalories(formSavedCookieAndCalorie.quantityCalories());
        calorieCounterRepository.save(counter);
        return new FormResponseCounterCalorie(counter.getTotalCookies(), counter.getTotalCalories());
    }

    public FormResponseCounterCalorie getCounterCalorie(){
        CalorieCounter calorieCounter =  calorieCounterRepository.findById(1L)
                .orElse(new CalorieCounter(0,0));
        return new FormResponseCounterCalorie(calorieCounter.getTotalCookies(), calorieCounter.getTotalCalories());
    }

    public FormResponseCounterCalorie updateCounter(){
        CalorieCounter counter = calorieCounterRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("CalorieCounter is empty"));
        counter.setCounterAndCaloriesToZero();
        calorieCounterRepository.save(counter);
        return new FormResponseCounterCalorie(counter.getTotalCookies(), counter.getTotalCalories());
    }

}
