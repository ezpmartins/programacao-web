package school.sptech.execicio_valendo_nota;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/albuns")
public class AlbumController {
    @Autowired
    private AlbumRepository albumRepository;

    // 1. Listar Álbuns
    @GetMapping
    public ResponseEntity<List<Album>> listar() {
        List<Album> albums = this.albumRepository.findAll();
        if (albums.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(albums);
    }

    // 2. Criar Álbuns
    @PostMapping
    public ResponseEntity<Album> cadastrarAlbuns(
            @RequestBody Album novoAlbum) {
        novoAlbum.setId(null); // não importa o que chegar vira nulo e cria
        return ResponseEntity.status(201).body(this.albumRepository.save(novoAlbum));
    }

    // 3. Buscar Albúms por Id
    @GetMapping("/{id}")

    public ResponseEntity<Album> buscarAlbunsPorId(
            @PathVariable Integer id) {
        Optional<Album> albumOpt = albumRepository.findById(id);
        if (albumOpt.isPresent()) {
            Album albumEncontrado = albumOpt.get();
            return ResponseEntity.status(200).body(albumEncontrado);
        }
        return ResponseEntity.status(404).build();
    }

    // 4. Atualizar Album

    @PutMapping("/{id}")
    public ResponseEntity<Album> atualizar(
            @PathVariable Integer id,
            @RequestBody Album albumAtualizado) {
        if (!albumRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }
        albumAtualizado.setId(id);
        return ResponseEntity.status(200).body(albumRepository.save(albumAtualizado));
    }

    // 5. Deletar Album

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Integer id) {
        if (albumRepository.existsById(id)) {
            albumRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    // 6. Buscar Álbuns por Artistas

    @GetMapping("/artistas")
    public ResponseEntity<List<Album>> buscarAlbunsPorArtista(
            @RequestParam String nome) {
        List<Album> albums = albumRepository.findByArtistaContainingIgnoreCase(nome);
        if (albums.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(albums);
    }

    // 7. Buscar Álbuns mais Vendido
    @GetMapping("/mais-vendido")
    public ResponseEntity<Album> buscarAlbumMaisVendido() {
        Album albums = albumRepository.findTopByOrderByTotalVendasDesc();
        if (albums == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(albums);
    }

    // 8. Buscar Álbuns mais Vendido

    @GetMapping("/menos-vendido")

    public ResponseEntity<Album> buscarAlbumMenosVendido() {
        Album albuns = albumRepository.findTopByOrderByTotalVendas();
        if (albuns == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(albuns);
    }

    // 9. Buscar Álbuns por periodo
    @GetMapping("/periodo")
    public ResponseEntity<List<Album>> buscaPorPeriodo(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim) {
        List<Album> albuns = albumRepository.findByDataLancamentoBetween(inicio, fim);
        if (inicio.isAfter(fim)) {
            return ResponseEntity.status(400).build();
        } else if (albuns.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(albuns);
    }

}
