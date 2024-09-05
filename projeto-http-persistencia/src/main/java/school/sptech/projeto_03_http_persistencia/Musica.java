package school.sptech.projeto_03_http_persistencia;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Sinaliza que a classe música será uma entidade no BD
public class Musica {
    @Id// esse atributo será o nosso "id"
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String artista;
    private int anoLancamento;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Ao ter o prefixo "Get" ele vira um atributo do json. Campo virtual/Campo calculado
    public String getDescricao() {
        return "A música " + nome + " é cantada/tocada por " + artista;
    }
}
