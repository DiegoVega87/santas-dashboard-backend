package com.team2ed8back.santas_dashboard_backend.service.childs;

import lombok.Builder;

@Builder
public record FormSaveChild(
        String name,
        int age,
        boolean isFemale,
        int kindness,
        int respectful,
        int patience,
        int effort,
        int teamWork
) {
}
