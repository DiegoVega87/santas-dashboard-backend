package com.team2ed8back.santas_dashboard_backend.controller;

import com.team2ed8back.santas_dashboard_backend.entity.reindeer.Reindeer;
import com.team2ed8back.santas_dashboard_backend.service.reindeer.ReindeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/v1/reindeers")
public class ReindeerController {

    @Autowired
    private ReindeerService reindeerService;

    @GetMapping
    public List<Reindeer> getReindeers() {
        return reindeerService.findAllReindeers();
    }
}
