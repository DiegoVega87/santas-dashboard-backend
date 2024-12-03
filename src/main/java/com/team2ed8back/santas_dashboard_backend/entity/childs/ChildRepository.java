package com.team2ed8back.santas_dashboard_backend.entity.childs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildRepository extends JpaRepository<Child, Integer> {
    List<Child> findAllBySuitableForGiftTrue();
    List<Child> findAllBySuitableForGiftFalse();
}
