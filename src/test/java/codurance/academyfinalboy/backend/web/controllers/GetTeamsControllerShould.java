package codurance.academyfinalboy.backend.web.controllers;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.actions.CreateTeam;
import codurance.academyfinalboy.backend.actions.GetTeams;
import codurance.academyfinalboy.backend.configurations.InterceptorConfiguration;
import codurance.academyfinalboy.backend.infrastructure.services.GoogleTokenValidator;
import codurance.academyfinalboy.backend.model.team.Team;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GetTeamsController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class GetTeamsControllerShould {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    GetTeams getTeams;

    @MockBean
    InterceptorConfiguration interceptorConfiguration;
    @MockBean
    GoogleTokenValidator googleTokenValidator;

    @Test
    void return_team_id() throws Exception {
        var expectedTeam1 = new Team("Team 1", "Team 1 description");
        var expectedTeam2 = new Team("Team 2", "Team 2 description");

        List<Team> expectedTeams = new ArrayList<Team>(Arrays.asList(expectedTeam1, expectedTeam2));

        when(getTeams.execute()).thenReturn(expectedTeams);

        var response = mockMvc
                .perform(
                        get("/teams")
                                .contentType(MediaType.APPLICATION_JSON)
                                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(response, containsString("Team 1"));
        assertThat(response, containsString("Team 1 description"));

        assertThat(response, containsString("Team 2"));
        assertThat(response, containsString("Team 2 description"));

    }

}
