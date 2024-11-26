package com.team2ed8back.santas_dashboard_backend.service.calorie;

import lombok.Builder;

@Builder
public record FormResponseCounterCalorie(int quantityTotalCookies, int quantityTotalCalories) {
}
