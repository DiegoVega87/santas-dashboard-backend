package com.team2ed8back.santas_dashboard_backend.service.childs;

import lombok.Builder;

@Builder
public record FormSaveChild(
        String name,
        int bondad,
        int respeto,
        int paciencia,
        int esfuerzo,
        int trabajoEnEquipo
) {
}
