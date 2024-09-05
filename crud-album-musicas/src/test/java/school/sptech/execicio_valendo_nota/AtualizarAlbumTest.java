package school.sptech.execicio_valendo_nota;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.CompareOperation;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DBUnit(allowEmptyFields = true, cacheConnection = false, alwaysCleanAfter = true, alwaysCleanBefore = true)
@DisplayName("Dado que o endpoint PUT /albuns/{id} seja chamado")
public class AtualizarAlbumTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(
          cleanBefore = true,
          cleanAfter = true,
          executeStatementsBefore = "TRUNCATE TABLE album RESTART IDENTITY",
          value = "/listar/listar_25_albuns.json")
    @ExpectedDataSet(value = "/atualizar/album_atualizado.json", compareOperation = CompareOperation.CONTAINS)
    @DisplayName("[1] - Quando o album existir, então deve retornar status 200")
    void atualizarAlbum() throws Exception {
        var body = """
              {
                  "nome": "Um sonhador",
                  "artista": "Leandro e Leonardo",
                  "dataLancamento": "1996-01-01",
                  "quantidadeMusicas": 10,
                  "totalVendas": 1000000.0
              }
              """;

        mockMvc.perform(put("/albuns/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.id").isNumber())
              .andExpect(jsonPath("$.nome").value("Um sonhador"))
              .andExpect(jsonPath("$.artista").value("Leandro e Leonardo"));
    }

    @Test
    @DataSet(
          cleanBefore = true,
          cleanAfter = true,
          executeStatementsBefore = "TRUNCATE TABLE album RESTART IDENTITY",
          value = "/listar/listar_25_albuns.json")
    @ExpectedDataSet("/listar/listar_25_albuns.json")
    @DisplayName("[2] - Quando o album não existir, então deve retornar status 404")
    void atualizarAlbumNaoExistente() throws Exception {
        var body = """
              {
                  "nome": "Um sonhador",
                  "artista": "Leandro e Leonardo",
                  "dataLancamento": "1996-01-01",
                  "quantidadeMusicas": 10,
                  "totalVendas": 1000000.0
              }
              """;

        mockMvc.perform(put("/albuns/42")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
              .andExpect(status().isNotFound());
    }

    @Test
    @DataSet(
          cleanBefore = true,
          cleanAfter = true,
          executeStatementsBefore = "TRUNCATE TABLE album RESTART IDENTITY",
          value = "/listar/listar_25_albuns.json")
    @ExpectedDataSet(value = "/atualizar/album_atualizado.json", compareOperation = CompareOperation.CONTAINS)
    @DisplayName("[3] - Quando o id do album for enviado diferente do id da URL, entao deve considerar o id da URL")
    void atualizarAlbumComIdDiferente() throws Exception {
        var body = """
              {
                  "id": 42,
                  "nome": "Um sonhador",
                  "artista": "Leandro e Leonardo",
                  "dataLancamento": "1996-01-01",
                  "quantidadeMusicas": 10,
                  "totalVendas": 1000000.0
              }
              """;

        mockMvc.perform(put("/albuns/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.id").isNumber())
              .andExpect(jsonPath("$.id").value(1));
    }
}
