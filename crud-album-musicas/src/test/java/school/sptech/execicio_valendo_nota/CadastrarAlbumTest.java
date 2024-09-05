package school.sptech.execicio_valendo_nota;

import com.github.database.rider.core.api.configuration.DBUnit;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DBUnit(allowEmptyFields = true, cacheConnection = false, alwaysCleanAfter = true, alwaysCleanBefore = true)
@DisplayName("Dado que o endpoint POST /albuns seja chamado")
public class CadastrarAlbumTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(
          cleanBefore = true,
          cleanAfter = true,
          executeStatementsBefore = "TRUNCATE TABLE album RESTART IDENTITY",
          value = "/datasets/listar/listar_25_albuns.json")
    @ExpectedDataSet({
          "/datasets/listar/listar_25_albuns.json",
          "/datasets/cadastrar/novo_album.json"})
    @DisplayName("[1] - Quando o album for cadastrado com sucesso, então deve retornar status 201 e id do album")
    void cadastrarAlbum() throws Exception {
        var body = """
              {
                  "nome": "Mr. Morale & the Big Steppers",
                  "artista": "Kendrick Lamar",
                  "dataLancamento": "2022-05-13",
                  "quantidadeMusicas": 18,
                  "totalVendas": 500000.0
              }
              """;

        mockMvc.perform(post("/albuns")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
              .andExpect(status().isCreated())
              .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    @DataSet(
          cleanBefore = true,
          cleanAfter = true,
          executeStatementsBefore = "TRUNCATE TABLE album RESTART IDENTITY",
          value = "/datasets/listar/listar_25_albuns.json")
    @ExpectedDataSet({
          "/datasets/listar/listar_25_albuns.json",
          "/datasets/cadastrar/novo_album.json"
    })
    @DisplayName("[2] - Caso o id seja informado, então deve ser ignorado e gerado um novo id")
    void cadastrarAlbumComId() throws Exception {
        var body = """
              {
                  "id": 1,
                  "nome": "Mr. Morale & the Big Steppers",
                  "artista": "Kendrick Lamar",
                  "dataLancamento": "2022-05-13",
                  "quantidadeMusicas": 18,
                  "totalVendas": 500000.0
              }
              """;

        mockMvc.perform(post("/albuns")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
              .andExpect(status().isCreated())
              .andExpect(jsonPath("$.id").isNumber());
    }
}
