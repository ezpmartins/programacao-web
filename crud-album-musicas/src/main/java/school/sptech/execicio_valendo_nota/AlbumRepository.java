package school.sptech.execicio_valendo_nota;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findByArtistaContainingIgnoreCase(String nome);

    Album findTopByOrderByTotalVendasDesc();

    Album findTopByOrderByTotalVendas();

    List<Album> findByDataLancamentoBetween(LocalDate inicio, LocalDate fim);

}
