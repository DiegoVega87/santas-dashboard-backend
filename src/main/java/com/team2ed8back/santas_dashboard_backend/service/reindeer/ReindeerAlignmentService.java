package com.team2ed8back.santas_dashboard_backend.service.reindeer;

import com.team2ed8back.santas_dashboard_backend.entity.reindeerAlignment.ReindeerAlignment;
import com.team2ed8back.santas_dashboard_backend.entity.reindeerAlignment.ReindeerAlignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReindeerAlignmentService {

    @Autowired
    private ReindeerAlignmentRepository reindeerAlignmentRepository;

    public List<ReindeerAlignment> getAllAlignments() {
        return reindeerAlignmentRepository.findAll();
    }

    public ReindeerAlignment getAlignmentById(Long id) {
        return reindeerAlignmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Alignment not found"));
    }

    public ReindeerAlignment createAlignment(ReindeerAlignment alignment) {
        return reindeerAlignmentRepository.save(alignment);
    }

    public ReindeerAlignment updateAlignment(Long id, ReindeerAlignment alignment) {
        ReindeerAlignment existingAlignment = getAlignmentById(id);
        // Update fields of existingAlignment with values from alignment
        return reindeerAlignmentRepository.save(existingAlignment);
    }

    public void deleteAlignment(Long id) {
        reindeerAlignmentRepository.deleteById(id);
    }
}
