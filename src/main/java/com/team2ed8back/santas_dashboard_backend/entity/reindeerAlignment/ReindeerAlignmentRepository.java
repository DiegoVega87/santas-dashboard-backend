package com.team2ed8back.santas_dashboard_backend.entity.reindeerAlignment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReindeerAlignmentRepository extends JpaRepository<ReindeerAlignment, Long> {

    ReindeerAlignment findByName(String snowyAlignment);

    ReindeerAlignment saveByAlignment(ReindeerAlignment existingAlignment, ReindeerAlignment alignment);

}
