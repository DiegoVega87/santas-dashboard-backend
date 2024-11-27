package com.team2ed8back.santas_dashboard_backend.service.calorie;

import lombok.Builder;

@Builder
public record FormSavedCookieAndCalorie(int quantityCookies, int quantityCalories) {
}
