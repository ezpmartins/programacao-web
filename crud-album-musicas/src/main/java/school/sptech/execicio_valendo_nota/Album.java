package school.sptech.execicio_valendo_nota;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Album {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String artista;
    private LocalDate dataLancamento;
    private Integer quantidadeMusicas;
    private Double totalVendas;

    public Album(Integer id, String nome, String artista, LocalDate dataLancamento, Integer quantidadeMusicas, Double totalVendas) {
        this.id = id;
        this.nome = nome;
        this.artista = artista;
        this.dataLancamento = dataLancamento;
        this.quantidadeMusicas = quantidadeMusicas;
        this.totalVendas = totalVendas;
    }

    public  Album(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Integer getQuantidadeMusicas() {
        return quantidadeMusicas;
    }

    public void setQuantidadeMusicas(Integer quantidadeMusicas) {
        this.quantidadeMusicas = quantidadeMusicas;
    }

    public Double getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(Double totalVendas) {
        this.totalVendas = totalVendas;
    }

}
