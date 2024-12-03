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

    public List<ReindeerAlignment> findAllAlignments() {
        return reindeerAlignmentRepository.findAll();
    }

    public ReindeerAlignment saveAlignment(ReindeerAlignment alignment) {
        return reindeerAlignmentRepository.save(alignment);
    }

    public ReindeerAlignment updateAlignment(Long id, ReindeerAlignment alignmentDetails ){

        ReindeerAlignment alignment = reindeerAlignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alignment not found"));
        alignment.setLead(alignmentDetails.getLead());
        alignment.setFront1(alignmentDetails.getFront1());
        alignment.setFront2(alignmentDetails.getFront2());
        alignment.setMiddle1(alignmentDetails.getMiddle1());
        alignment.setMiddle2(alignmentDetails.getMiddle2());
        alignment.setBack1(alignmentDetails.getBack1());
        alignment.setBack2(alignmentDetails.getBack2());
        alignment.setBack3(alignmentDetails.getBack3());
        return reindeerAlignmentRepository.save(alignment);
    }

    public void deleteAlignment(Long id) {
        if(!reindeerAlignmentRepository.existsById(id)){
            throw new RuntimeException("Alignment not found");
        }
        reindeerAlignmentRepository.deleteById(id);
    }
}
