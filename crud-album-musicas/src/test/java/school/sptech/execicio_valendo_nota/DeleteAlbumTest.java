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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DBUnit(allowEmptyFields = true, cacheConnection = false, alwaysCleanAfter = true, alwaysCleanBefore = true)
@DisplayName("Dado que o endpoint DELETE /albuns/{id} seja chamado")
public class DeleteAlbumTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE album RESTART IDENTITY",
            value = "/listar/listar_25_albuns.json")
    @ExpectedDataSet(value = "/listar/listar_24_albuns.json")
    @DisplayName("[1] - Quando o album existir, então deve retornar status 204")
    void deletarAlbum() throws Exception {
        mockMvc.perform(delete("/albuns/5"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE album RESTART IDENTITY",
            value = "/listar/listar_25_albuns.json")
    @ExpectedDataSet(value = "/listar/listar_25_albuns.json")
    @DisplayName("[2] - Quando o album não existir, então deve retornar status 404")
    void deletarAlbumNaoExistente() throws Exception {
        mockMvc.perform(delete("/albuns/42"))
                .andExpect(status().isNotFound());
    }
}
