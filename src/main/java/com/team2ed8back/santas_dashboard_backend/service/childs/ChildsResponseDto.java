package com.team2ed8back.santas_dashboard_backend.service.childs;


import lombok.Builder;

import java.util.List;
@Builder
public record ChildsResponseDto(String name, List<Behavior> characteristics, String classification) {
}
