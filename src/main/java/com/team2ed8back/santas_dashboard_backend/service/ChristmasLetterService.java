package com.team2ed8back.santas_dashboard_backend.service;

import com.team2ed8back.santas_dashboard_backend.entity.christmasLetter.ChristmasLetter;
import com.team2ed8back.santas_dashboard_backend.entity.christmasLetter.ChristmastLetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChristmasLetterService {

    private final ChristmastLetterRepository christmastLetterRepository;

    //metodo para update el estado de una carta a leida cuando el cleinte me envie que fue leida
    public List<ChristmasLetter> readAllCard() {
        return christmastLetterRepository.findAll();
    }

    public ChristmasLetter findCardById(Long id) {
        return christmastLetterRepository.findById(id).orElseThrow(() -> new RuntimeException("ChristmasLetter not found"));
    }

    public boolean isEmptyCards (){
        return christmastLetterRepository.findAll().isEmpty();
    }

    public void updateStateReadCard(Long idCardRead){
        ChristmasLetter cardRead = christmastLetterRepository.findById(idCardRead).get();
        cardRead.generateDateReadCard();
        cardRead.setWasRead(true);
        christmastLetterRepository.save(cardRead);
    }

    public void saveAllCards(List<ChristmasLetter> cardsList){
        christmastLetterRepository.saveAll(cardsList);
    }

}
