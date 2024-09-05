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
@DisplayName("Dado que o endpoint GET /artista seja chamado")
public class BuscarPorArtistaTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE album RESTART IDENTITY",
            value = "/listar/listar_25_albuns.json")
    @DisplayName("[1] - Quando o artista existir, então deve retornar status 200")
    void buscarPorArtista() throws Exception {
        var artista = "Kendrick Lamar";

        mockMvc.perform(get("/albuns/artistas")
                        .param("nome", artista))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].artista").value(artista));
    }

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE album RESTART IDENTITY",
            value = "/listar/listar_25_albuns.json")
    @DisplayName("[2] - Quando o artista não existir, então deve retornar status 204")
    void buscarPorArtistaNaoExistente() throws Exception {
        var artista = "Gilberto Barros";

        mockMvc.perform(get("/albuns/artistas")
                        .param("nome", artista))
                .andExpect(status().isNoContent());
    }

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE album RESTART IDENTITY",
            value = "/listar/listar_25_albuns.json")
    @DisplayName("[3] - Quando o artista for parcial, então deve retornar status 200 com resultados aproximados")
    void buscarPorArtistaNaoCapitalizado() throws Exception {
        var artista = "bea";

        mockMvc.perform(get("/albuns/artistas")
                        .param("nome", artista))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].artista").value("The Beatles"));
    }

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            executeStatementsBefore = "TRUNCATE TABLE album RESTART IDENTITY",
            value = "/listar/listar_25_albuns.json")
    @DisplayName("[4] - Quando o artista estiver com maiúsculas e minúsculas, então deve retornar status 200 com resultados aproximados")
    void buscarPorArtistaCapitalizado() throws Exception {
        var artista = "kEnDrIcK lAmAr";

        mockMvc.perform(get("/albuns/artistas")
                        .param("nome", artista))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].artista").value("Kendrick Lamar"));
    }
}
