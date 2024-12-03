package com.team2ed8back.santas_dashboard_backend.service.reindeer;

import com.team2ed8back.santas_dashboard_backend.entity.reindeer.Reindeer;
import com.team2ed8back.santas_dashboard_backend.entity.reindeer.ReindeerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReindeerService {

    private final ReindeerRepository reindeerRepository;

    public ReindeerService(ReindeerRepository reindeerRepository) {
        this.reindeerRepository = reindeerRepository;
    }

    public void saveAllReindeers(List<Reindeer> reindeer) {
        reindeerRepository.saveAll(reindeer);
    }

    public List<Reindeer> findAllReindeers() {
        return reindeerRepository.findAll();
    }
}
