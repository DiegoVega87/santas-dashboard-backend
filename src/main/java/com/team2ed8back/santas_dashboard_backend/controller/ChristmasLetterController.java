package com.team2ed8back.santas_dashboard_backend.controller;

import com.team2ed8back.santas_dashboard_backend.entity.christmasLetter.ChristmasLetter;
import com.team2ed8back.santas_dashboard_backend.service.ChristmasLetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*") // This means that the controller will accept requests from any origin
@RestController
@RequestMapping("api/v1/christmas-cards")
@RequiredArgsConstructor
public class ChristmasLetterController {

    private final ChristmasLetterService christmasLetterService;

    @GetMapping
    public ResponseEntity<List<ChristmasLetter>> getAllCards() {
        return ResponseEntity.ok(christmasLetterService.readAllCard());
    }

    @GetMapping("{id}")
    public ResponseEntity<ChristmasLetter> getCardById(@PathVariable Long id) {
        return ResponseEntity.ok(christmasLetterService.findCardById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateStateReadCard (@PathVariable Long id){
        christmasLetterService.updateStateReadCard(id);
        return ResponseEntity.ok().build();
    }

    public void insertChristmasLetters() {
        List<ChristmasLetter> letters = Arrays.asList(
                new ChristmasLetter(1L, "Carta al Viejo Noel", "Querido Santa, este año he sido tan bueno que hasta yo estoy impresionado. Por eso, creo que merezco unas vacaciones contigo en el Caribe. ¿Qué dices?", false, null),
                new ChristmasLetter(2L, "Sueños Navideños", "Santa, este año no pido paz mundial, pero al menos que el WiFi no se caiga durante las fiestas, ¿vale?", false, null),
                new ChristmasLetter(3L, "Lista de Cosas que no Necesito", "Papá Noel, te dejo mi lista: una membresía al gimnasio (que nunca usaré), una cafetera nueva (porque siempre pido café fuera) y un calendario de gatitos.", false, null),
                new ChristmasLetter(4L, "Carta a los Reyes Magos", "Queridos Reyes Magos, si este año me traen calcetines otra vez, les juro que les mando a sus camellos a dieta.", false, null),
                new ChristmasLetter(5L, "Querido Santa", "Hola Santa, ¿podrías traerme paciencia para aguantar las preguntas incómodas de la cena familiar? Gracias.", false, null),
                new ChristmasLetter(6L, "Agradecimiento con Sabor", "Santa, gracias por los regalos del año pasado, aunque creo que el sweater de renos fue una indirecta para que cambie mi estilo.", false, null),
                new ChristmasLetter(7L, "Petición del Corazón", "Santa, por favor, tráeme un robot que lave los platos, saque la basura y que además sepa hacer café... ¡lo necesito!", false, null),
                new ChristmasLetter(8L, "Santa, Piensa en Mí", "Santa, este año deseo que mi jefe olvide mi correo electrónico por unas semanas. ¿Puedes hacer que eso pase?", false, null),
                new ChristmasLetter(9L, "Carta para mis Amigos (y Enemigos)", "Santa, dale a mis amigos el regalo que merecen... y a mis enemigos un combo navideño de carbón, pero con estilo.", false, null),
                new ChristmasLetter(10L, "Carta con un Poco de Sarcasmo", "Querido Santa, gracias por estar ahí para todos nosotros. Especialmente para los adultos que seguimos creyendo que un tipo con barba nos va a solucionar la vida.", false, null),
                new ChristmasLetter(11L, "Papá Noel de Emergencia", "Santa, tráeme algo para mi dolor de espalda. Supongo que entiendes, con todo ese saco de regalos y yo con esta vida sedentaria.", false, null),
                new ChristmasLetter(12L, "Carta sin Filtros", "Querido Santa, este año he sido como un WiFi en el sótano: inconsistente. Pero igual, mándame algo chévere, porfa.", false, null),
                new ChristmasLetter(13L, "Operación Navidad", "Santa, ¿qué tal un GPS para encontrar el espíritu navideño? Creo que lo perdí hace un par de años entre facturas y obligaciones.", false, null),
                new ChristmasLetter(14L, "Fan del Grinch", "Santa, no te lo tomes personal, pero este año quiero ser más como el Grinch. Su estilo de vida parece más relajado.", false, null),
                new ChristmasLetter(15L, "Carta con Café en Mano", "Santa, mándame café ilimitado y el superpoder de no caer en coma después de las fiestas. Eso sería genial.", false, null),
                new ChristmasLetter(16L, "Propósitos Ambiciosos", "Santa, tráeme la motivación para empezar mi lista de propósitos... del 2023. Sí, todavía estoy atrasado.", false, null),
                new ChristmasLetter(17L, "Crisis Navideña", "Santa, ¿tienes algún descuento en existencialismo? Este año podría usar un poco menos de introspección y más vino.", false, null),
                new ChristmasLetter(18L, "¿Dónde Está la Magia?", "Santa, este año solo pido magia: hacer desaparecer mis deudas, teletransportarme al Caribe, y convertir el agua en vino. Fácil, ¿no?", false, null)
        );
        if(christmasLetterService.isEmptyCards()){
            christmasLetterService.saveAllCards(letters);
        };
    }


}
