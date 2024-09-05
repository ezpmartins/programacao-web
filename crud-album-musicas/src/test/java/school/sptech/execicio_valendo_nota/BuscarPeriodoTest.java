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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DBUnit(allowEmptyFields = true, cacheConnection = false, alwaysCleanAfter = true, alwaysCleanBefore = true)
@DisplayName("Dado que o endpoint GET /periodos seja chamado")
public class BuscarPeriodoTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE album RESTART IDENTITY",
            value = "/listar/listar_25_albuns.json")
    @DisplayName("[1] - Quando houver periodos, então deve retornar status 200")
    void buscarPeriodos() throws Exception {

        var expected = 2;

        mockMvc.perform(get("/albuns/periodo")
                        .param("inicio", "1990-01-01")
                        .param("fim", "2000-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(expected))
                .andExpect(jsonPath("$[0].artista").value("Alanis Morissette"))
                .andExpect(jsonPath("$[1].artista").value("Nirvana"));
    }

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE album RESTART IDENTITY",
            value = "/listar/listar_25_albuns.json")
    @DisplayName("[2] - Quando não houver periodos, então deve retornar status 204")
    void buscarPeriodosNaoExistente() throws Exception {
        mockMvc.perform(get("/albuns/periodo")
                        .param("inicio", "2024-01-01")
                        .param("fim", "2025-12-01"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE album RESTART IDENTITY",
            value = "/listar/listar_25_albuns.json")
    @DisplayName("[3] - Quando a da data de inicio for maior que a data de fim, então deve retornar status 400")
    void buscarPeriodosDataInicioMaiorQueFim() throws Exception {
        mockMvc.perform(get("/albuns/periodo")
                        .param("inicio", "2025-01-01")
                        .param("fim", "2024-12-01"))
                .andExpect(status().isBadRequest());
    }
}
