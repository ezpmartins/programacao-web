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
@DisplayName("Dado que o endpoint GET /albuns/menos-vendido seja chamado")
public class MenosVendidoTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE album RESTART IDENTITY",
            value = "/listar/listar_25_albuns.json")
    @DisplayName("[1] - Quando houver albuns, então deve retornar status 200 e o menos vendido")
    void listarMenosVendido() throws Exception {

        var expected = """
                {
                  "nome": "good kid, m.A.A.d city",
                  "artista": "Kendrick Lamar",
                  "dataLancamento": "2012-10-22",
                  "quantidadeMusicas": 12,
                  "totalVendas": 300000.0
                }
                """;

        mockMvc.perform(get("/albuns/menos-vendido"))
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
    void listarMenosVendidoSemAlbum() throws Exception {
        mockMvc.perform(get("/albuns/menos-vendido"))
                .andExpect(status().isNotFound());
    }
}
