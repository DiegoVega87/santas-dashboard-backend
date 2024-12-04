package com.team2ed8back.santas_dashboard_backend.service.reindeer;

import com.team2ed8back.santas_dashboard_backend.entity.reindeer.Reindeer;
import com.team2ed8back.santas_dashboard_backend.entity.reindeerAlignment.ReindeerAlignment;
import com.team2ed8back.santas_dashboard_backend.entity.reindeerAlignment.ReindeerAlignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReindeerAlignmentService {

    @Autowired
    private ReindeerAlignmentRepository reindeerAlignmentRepository;

    @Autowired
    ReindeerService reindeerService;

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

    public void insertDefaultAlignments() {
        List<Reindeer> reindeers = reindeerService.findAllReindeers();

        // Snowy alignment
        ReindeerAlignment snowyAlignment = new ReindeerAlignment();
        snowyAlignment.setName("Snowy alignment");

        Reindeer rudolph = reindeers.stream()
                .filter(r -> "Rudolph".equalsIgnoreCase(r.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Rudolph not found"));
        snowyAlignment.setLead(rudolph);

        List<Reindeer> strongReindeers = reindeers.stream()
                .filter(r -> "Strongest".equalsIgnoreCase(r.getType()) || "Strong".equalsIgnoreCase(r.getType()))
                .collect(Collectors.toList());
        List<Reindeer> fastReindeers = reindeers.stream()
                .filter(r -> "Fastest".equalsIgnoreCase(r.getType()) || "Fast".equalsIgnoreCase(r.getType()))
                .collect(Collectors.toList());

        snowyAlignment.setFront1(strongReindeers.get(0));
        snowyAlignment.setFront2(strongReindeers.get(1));
        snowyAlignment.setMiddle1(strongReindeers.get(2));
        snowyAlignment.setMiddle2(strongReindeers.get(3));
        snowyAlignment.setMiddle3(strongReindeers.get(4));
        snowyAlignment.setBack1(fastReindeers.get(0));
        snowyAlignment.setBack2(fastReindeers.get(1));
        snowyAlignment.setBack3(fastReindeers.get(2));

        reindeerAlignmentRepository.save(snowyAlignment);

        // Default alignment
        ReindeerAlignment defaultAlignment = new ReindeerAlignment();
        defaultAlignment.setName("Default alignment");

        Reindeer fastest = reindeers.stream()
                .filter(r -> "Fastest".equalsIgnoreCase(r.getType()) || "Fast".equalsIgnoreCase(r.getType()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Fastest reindeer not found"));
        defaultAlignment.setLead(fastest);

        defaultAlignment.setFront1(fastReindeers.get(0));
        defaultAlignment.setFront2(fastReindeers.get(1));
        defaultAlignment.setMiddle1(fastReindeers.get(2));
        defaultAlignment.setMiddle2(strongReindeers.get(0));
        defaultAlignment.setMiddle3(strongReindeers.get(1));
        defaultAlignment.setBack1(strongReindeers.get(2));
        defaultAlignment.setBack2(strongReindeers.get(3));
        defaultAlignment.setBack3(strongReindeers.get(4));

        reindeerAlignmentRepository.save(defaultAlignment);
    }
}
