package school.sptech.projeto_http_code;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

    //dynamic finders

    // like
    List<Livro> findByNome(String nome);

    List<Livro> findByNomeContainsIgnoreCase(String nome);

    List<Livro> findByNomeContainsIgnoreCaseOrderClassificaoDesc(String nome);

    List<Livro> findByDataLancamentoBeetwen(LocalDate data1, LocalDate data2);

    List<Livro> findByOrderByClassificao();


}
