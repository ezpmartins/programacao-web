package school.sptech.primeira_aplicacao;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frases")

public class FraseController {
    //frases no /
    @GetMapping
    public String ola() {
        return "Olá";
    }

    //frases/boa
    @GetMapping("/boa")
    public String ola2() {
        return "Boa pá nois";
    }

    //frases/boa2
    @GetMapping("/boa2")
    public String legal() {
        return "Frase bacana";
    }

    // frases/nome/{nome da pessoa}

    @GetMapping("/nome/{nomeDaPessoa}")
    public String retornaNome(@PathVariable String nomeDaPessoa) {
        return "Seja bem vindo ! Seu nome é: " + nomeDaPessoa;
    }

    @GetMapping("/nome/{nomeDaPessoa}/{sobrenome}")

    public String retornaSobrenome(@PathVariable String nomeDaPessoa, @PathVariable String sobrenome) {
        return "Seja bem vindo ! Seu nome é: " + nomeDaPessoa + "" + sobrenome;
    }

}





