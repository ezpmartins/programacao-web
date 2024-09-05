package school.sptech.sprint1_nota1.ex2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ExercicioMedioController {

    @GetMapping("/ex-02/{numero}")
    public Boolean exercicioMedio(@PathVariable int numero) {
        if (numero <= 1) {
            return false;
        } else if (numero == 2) {
            return true;
        } else if (numero % 2 == 0) {
            return false;
        } else {
            for (int i = 3; i <= numero / 2; i += 2) {
                if (numero % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
