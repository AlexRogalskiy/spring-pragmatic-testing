package org.pgramatictesting;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pgramatictesting.service.PokemonService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author tolkv
 * @version 11/09/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class PokemonControllerTest extends MockMvcBaseTest {
  @MockBean
  PokemonService pokemonService;

  @Test
  public void should_return_power() throws Exception {
    //given
    String bulbasaur = "Bulbasaur";
    given(pokemonService.getPokemonPower(any()))
        .willReturn(15.0d);

    //expect
    mockMvc.perform(
        get("/pokemon/" + bulbasaur)
            .accept(MediaType.APPLICATION_JSON)
    )
        .andExpect(status().isOk())
        .andExpect(
            jsonPath("$.name", equalTo(bulbasaur))
        )
        .andExpect(
            jsonPath("$.power", Matchers.closeTo(15.0d, 1.0d))
        );
  }

}