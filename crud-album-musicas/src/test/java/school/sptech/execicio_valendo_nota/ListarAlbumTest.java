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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DBUnit(allowEmptyFields = true, cacheConnection = false, alwaysCleanAfter = true, alwaysCleanBefore = true)
@DisplayName("Dado que o endpoint GET /albuns seja chamado")
class ListarAlbumTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            value = "/listar/listar_4_albuns.json")
    @DisplayName("[1] - Quando houver 3 albuns, então deve retornar status 200")
    void listarAlbuns() throws Exception {

        var expected = """
                [
                    {
                       "id": 1,
                       "nome": "Mr. Morale & the Big Steppers",
                       "artista": "Kendrick Lamar",
                       "dataLancamento": "2022-05-13",
                       "quantidadeMusicas": 18,
                       "totalVendas": 500000.0
                     },
                     {
                       "id": 2,
                       "nome": "DAMN.",
                       "artista": "Kendrick Lamar",
                       "dataLancamento": "2017-04-14",
                       "quantidadeMusicas": 14,
                       "totalVendas": 3200000.0
                     },
                     {
                       "id": 3,
                       "nome": "To Pimp a Butterfly",
                       "artista": "Kendrick Lamar",
                       "dataLancamento": "2015-03-15",
                       "quantidadeMusicas": 16,
                       "totalVendas": 2500000.0
                     },
                     {
                       "id": 4,
                       "nome": "good kid, m.A.A.d city",
                       "artista": "Kendrick Lamar",
                       "dataLancamento": "2012-10-22",
                       "quantidadeMusicas": 12,
                       "totalVendas": 3500000.0
                     }
                ]
                """;

        mockMvc.perform(get("/albuns"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    @DisplayName("[2] - Quando não houver albuns, então deve retornar status 204")
    void listarAlbunsVazio() throws Exception {
        mockMvc.perform(get("/albuns"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DataSet(
            cleanBefore = true,
            cleanAfter = true,
            value = "/listar/listar_25_albuns.json")
    @DisplayName("[3] - Quando houver 25 albuns, então deve retornar status 200")
    void listarAlbuns25() throws Exception {
        mockMvc.perform(get("/albuns"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(25));
    }
}