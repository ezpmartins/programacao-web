package school.sptech.prova_ac1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Usuario> cadastro(
            @RequestBody Usuario novoUsuario) {
        Optional<Usuario> usuarioExistentePorEmail = usuarioRepository.findByEmail(novoUsuario.getEmail());
        Optional<Usuario> usuarioExistentePorCpf = usuarioRepository.findByCpf(novoUsuario.getCpf());

        if (usuarioExistentePorEmail.isPresent() || usuarioExistentePorCpf.isPresent()) {
            return ResponseEntity.status(409).build();
        }
        novoUsuario.setId(null);
        return ResponseEntity.status(201).body(usuarioRepository.save(novoUsuario));
    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> buscarUsuariosPorId(
            @PathVariable int id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuarioEncontrado = usuarioOpt.get();
            return ResponseEntity.status(200).body(usuarioOpt);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/filtro-data")
    public ResponseEntity<List<Usuario>> buscarPorDataMaior(
            @RequestParam LocalDate dataNascimento) {
        List<Usuario> usuarios = usuarioRepository.findByDataNascimentoGreaterThan(dataNascimento);
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarios);
    }

}
