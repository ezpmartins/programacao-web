package school.sptech.projeto_03_http_persistencia;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/*
CRUD

CREATE - PostMaping
READ - GetMaping
UPDATE - PutMaping
DELETE - DeleteMaping

CREATE - POST
READ - GET
UPDATE - PUT
DELETE - DELETE

*/
@RestController
@RequestMapping("/musicas")

public class MusicController {

    List<Musica> musicas = new ArrayList<>();
    private MusicaRepository musicaRepository;

//    @GetMapping("/favorita") // musicas/favorita
//    public Musica favorita() {
//        return new Musica("Hells Bells",
//                "ACDC",
//                1980);
//    }

    @GetMapping // musicas
    public List<Musica> listar() {
        return musicaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Musica buscarPorIndice(@PathVariable int id) {
        Optional<Musica> musicaOpt = musicaRepository.findById(id);

        if (musicaOpt.isPresent()) {
            return musicaOpt.get();
        }
        return null;
    }

    //POST -
    @PostMapping
    public Musica criar(@RequestBody Musica novaMusica) {
        // Recebendo um objeto a partir de um JSON
        Musica musicaCriada = musicaRepository.save(novaMusica);
        return musicaCriada;
    }

    // PUT -
    @PutMapping("{id}")
    public Musica atualizar(@PathVariable int id, @RequestBody Musica musicaAtualizada) {
        if (musicaRepository.existsById(id)) {
            return musicaRepository.save(musicaAtualizada);
        }
        return musicaAtualizada;
    }

    //DELETE -
    @DeleteMapping("{indice}")
    public void remover(@PathVariable int id) {
        if (musicaRepository.existsById(id)) {
            musicaRepository.deleteById(id);
        }
    }

}


