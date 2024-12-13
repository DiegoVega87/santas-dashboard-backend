package com.team2ed8back.santas_dashboard_backend.controller;

import com.team2ed8back.santas_dashboard_backend.entity.reindeerAlignment.ReindeerAlignment;
import com.team2ed8back.santas_dashboard_backend.service.reindeer.ReindeerAlignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/reindeer-alignment")
public class ReindeerAlignmentController {

    @Autowired
    private ReindeerAlignmentService reindeerAlignmentService;

    @GetMapping
    public List<ReindeerAlignment> getAllAlignments() {
        return reindeerAlignmentService.getAllAlignments();
    }

    @PostMapping
    public ReindeerAlignment createAlignment(@RequestBody ReindeerAlignment alignment) {
        return reindeerAlignmentService.createAlignment(alignment);
    }

    @PutMapping("/{id}")
    public ReindeerAlignment updateAlignment(@PathVariable Long id, @RequestBody ReindeerAlignment alignmentDetails) {
        return reindeerAlignmentService.updateAlignment(id,alignmentDetails);
    }

    // ASK front: Do you want this method to return anything?What should it return?
    @DeleteMapping("/{id}")
    public void deleteAlignment(@PathVariable long id) {
        reindeerAlignmentService.deleteAlignment(id);
    }

    @GetMapping("/weather-alignment")
    public ReindeerAlignment getAlignmentByWeather() {
        return reindeerAlignmentService.getAlignmentByWeather();
    }

}
