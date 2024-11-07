package com.team2ed8back.santas_dashboard_backend.controller;

import com.team2ed8back.santas_dashboard_backend.entity.ChristmasLetter;
import com.team2ed8back.santas_dashboard_backend.service.ChristmasLetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/christmas-cards")
@RequiredArgsConstructor
public class ChristmasLetterController {

    private final ChristmasLetterService christmasLetterService;

    @GetMapping("/all")
    public ResponseEntity<List<ChristmasLetter>> getAllCards() {
        return ResponseEntity.ok(christmasLetterService.readAllCard());
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateStateReadCard (@PathVariable Long id){
        christmasLetterService.updateStateReadCard(id);
        return ResponseEntity.ok().build();
    }

    public void insertChristmasLetters() {
        List<ChristmasLetter> letters = Arrays.asList(
                new ChristmasLetter(1L, "Carta a Papá Noel", "Querido Papá Noel, este año he sido muy bueno y me gustaría un juguete nuevo.", false, null),
                new ChristmasLetter(2L, "Deseos Navideños", "Este año quiero que todos tengan una Navidad feliz y en paz.", false, null),
                new ChristmasLetter(3L, "Lista de Regalos", "Papá Noel, aquí va mi lista: una bicicleta, una pelota, y un juego de mesa.", false, null),
                new ChristmasLetter(4L, "Carta a los Reyes Magos", "Queridos Reyes Magos, he sido un buen niño, espero me traigan un buen regalo.", false, null),
                new ChristmasLetter(5L, "Carta a Santa", "Hola Santa, ¿podrías traerme el libro que tanto quiero leer?", false, null),
                new ChristmasLetter(6L, "Agradecimiento Navideño", "Gracias, Santa, por todos los regalos del año pasado.", false, null),
                new ChristmasLetter(7L, "Petición Especial", "Querido Papá Noel, me gustaría que mi familia esté unida y feliz.", false, null),
                new ChristmasLetter(8L, "Cartita de Paz", "Santa, este año deseo que haya paz en el mundo.", false, null),
                new ChristmasLetter(9L, "Carta para mis Amigos", "Santa, por favor, dale un buen regalo a cada uno de mis amigos.", false, null),
                new ChristmasLetter(10L, "Carta con Amor", "Querido Papá Noel, gracias por siempre estar ahí para todos los niños.", false, null)
        );

        // Insertar las cartas en la base de datos
        christmasLetterService.saveAllCards(letters);
    }


}
