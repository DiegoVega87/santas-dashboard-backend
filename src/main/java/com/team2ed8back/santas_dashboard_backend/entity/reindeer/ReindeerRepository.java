package com.team2ed8back.santas_dashboard_backend.entity.reindeer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReindeerRepository extends JpaRepository<Reindeer, Long> {
    
}
