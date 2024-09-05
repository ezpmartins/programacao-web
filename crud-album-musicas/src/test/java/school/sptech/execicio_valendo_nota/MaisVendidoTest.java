package school.sptech.execicio_valendo_nota;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DBUnit(allowEmptyFields = true, cacheConnection = false, alwaysCleanAfter = true, alwaysCleanBefore = true)
@DisplayName("Dado que o endpoint GET /albuns/mais-vendido seja chamado")
public class MaisVendidoTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE album RESTART IDENTITY",
            value = "/listar/listar_25_albuns.json")
    @DisplayName("[1] - Quando houver albuns, então deve retornar status 200 e o mais vendido")
    void listarMaisVendido() throws Exception {

        var expected = """
                {
                  "id": 7,
                  "nome": "Thriller",
                  "artista": "Michael Jackson",
                  "dataLancamento": "1982-11-30",
                  "quantidadeMusicas": 9,
                  "totalVendas": 66000000.0
                }
                """;

        mockMvc.perform(get("/albuns/mais-vendido"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(expected));
    }

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE album RESTART IDENTITY")
    @DisplayName("[2] - Quando não houver albuns, então deve retornar status 404")
    void listarMaisVendidoSemAlbuns() throws Exception {
        mockMvc.perform(get("/albuns/mais-vendido"))
                .andExpect(status().isNotFound());
    }
}
