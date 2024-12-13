package com.team2ed8back.santas_dashboard_backend.entity.childs;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Integer> {
    List<Child> findAllBySuitableForGiftTrue();
    List<Child> findAllBySuitableForGiftFalse();
    Optional<Child> findById(Integer id);
}
