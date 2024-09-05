package school.sptech.sprint1_nota1.ex1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ExercicioFacilController {

    @GetMapping("/ex-01/{palavra}")
    public Boolean exercicioFacil(@PathVariable String palavra) {
        String palavraNormal = palavra.toLowerCase();
        String palavraReversa = new StringBuilder(palavraNormal).reverse().toString();
        return palavraNormal.equalsIgnoreCase(palavraReversa);
    }
}
