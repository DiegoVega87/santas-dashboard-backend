
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

    public boolean listReindeerIsEmpty() {
        return reindeerRepository.findAll().isEmpty();
    }

    public void saveReindeerList(){
        if(listReindeerIsEmpty()){
            saveAllReindeers(List.of(
                    new Reindeer("Dasher", "Fast"),
                    new Reindeer("Dancer", "Fast"),
                    new Reindeer("Comet", "Fast"),
                    new Reindeer("Prancer", "Strong"),
                    new Reindeer("Vixen", "Strong"),
                    new Reindeer("Cupid", "Strong"),
                    new Reindeer("Donner", "Strong"),
                    new Reindeer("Blitzen", "Strong"),
                    new Reindeer("Rudolph", "Leader")
            ));
        }
    }
}
