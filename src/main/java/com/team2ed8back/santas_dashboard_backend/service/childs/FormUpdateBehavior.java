package com.team2ed8back.santas_dashboard_backend.service.childs;

import lombok.Builder;

@Builder
public record FormUpdateBehavior(
        Integer idChild,
        boolean isFemale,
        int kindness,
        int respectful,
        int patience,
        int effort,
        int teamWork
) {
}
