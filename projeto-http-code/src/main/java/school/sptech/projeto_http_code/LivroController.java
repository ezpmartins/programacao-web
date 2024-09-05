package school.sptech.projeto_http_code;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public ResponseEntity<List<Livro>> listar() {
        List<Livro> livros = livroRepository.findAll();

        if (livros.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(livros);
    }

    //GET = /{id} - endpoint de busca por id
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscaPorId(@PathVariable int id) {
        Optional<Livro> livroOpt = livroRepository.findById(id);
        if (livroOpt.isPresent()) {
            Livro livroEncontrado = livroOpt.get();
            return ResponseEntity.status(200).body(livroEncontrado);
        }
        return ResponseEntity.status(400).build();
    }

    //POST = / cadastro de Livro
    @PostMapping
    public ResponseEntity<Livro> cadastrar(@RequestBody Livro novoLivro) {
        novoLivro.setId(null); // não importa e que chegar , vira null e cria
        return ResponseEntity.status(201).body(livroRepository.save(novoLivro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(
            @PathVariable int id,
            @RequestBody Livro livroAtualizado) {
        if (!livroRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }
        livroAtualizado.setId(id);
        Livro livroRetorno = livroRepository.save(livroAtualizado);
        return ResponseEntity.status(200).body(livroRetorno);
    }

    //DELETE = /{id} - endpoint de remoção por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        if (livroRepository.existsById(id)) {
            livroRepository.existsById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/filtro-nome")
    public ResponseEntity<List<Livro>> porNome(@RequestParam String nome) {

        List<Livro> livros = livroRepository.findByNome(nome);
        if (livros.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(livros);
    }

}
