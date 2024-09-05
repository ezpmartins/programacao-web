package school.sptech.sprint1_nota1.ex3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ExercicioDificilController {


    @GetMapping("/ex-03/{n}")
    public ExercicioDificilResponse exercicioDificil(@PathVariable int n) {
        ExercicioDificilResponse response = new ExercicioDificilResponse(0, 0);
        if (n > 0) {
            response.setEnesimoTermo(calcularFibonacci(n));
            response.setSoma(somarFibonacci(n));
        }
        return response;
    }

    private int calcularFibonacci(int termo) {
        if (termo == 1 || termo == 2) {
            return 1;
        }
        int primeiroTermo = 1;
        int segundoTermo = 1;
        int termoAtual = 0;
        for (int i = 3; i <= termo ; i++) {
            termoAtual = primeiroTermo + segundoTermo;
            primeiroTermo = segundoTermo;
            segundoTermo = termoAtual;
        }


        return termoAtual;
    }

    private int somarFibonacci ( int termos){
        if (termos == 1) {
            return 1;
        }
        int soma = 2;
        int primeiroTermo = 1;
        int segundoTermo = 1;
        int termoAtual = 0;

        for (int i = 3; i <= termos; i++) {
            termoAtual = primeiroTermo + segundoTermo;
            soma += termoAtual;
            primeiroTermo = segundoTermo;
            segundoTermo = termoAtual;
        }
        return soma;
    }
}
