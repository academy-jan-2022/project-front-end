package codurance.academyfinalboy.backend.infrastructure.repositories.team;

import codurance.academyfinalboy.backend.BaseSpringTest;
import codurance.academyfinalboy.backend.builders.ActivityBuilder;
import codurance.academyfinalboy.backend.model.team.Activity;
import codurance.academyfinalboy.backend.model.team.ActivityMember;
import codurance.academyfinalboy.backend.model.team.Team;
import codurance.academyfinalboy.backend.model.team.TeamRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class TeamRepositoryShould extends BaseSpringTest {

  @Autowired TeamRepository repository;

  @Test
  void save_team() {
    Team team = new Team("team name", "team description", 3L);

    Long teamId = repository.save(team);

    assertThat(teamId).isNotNull();
  }

  @Test
  void save_team_with_activity() {
    var activity = new ActivityBuilder().build();

    Team team = new Team("team name", "team description", 3L);
    team.addActivity(activity);

    Long teamId = repository.save(team);

    var foundTeam = repository.findById(teamId).orElseThrow();

    Activity foundActivity = foundTeam.getActivities().get(0);

    assertThat(foundActivity).usingRecursiveComparison().isEqualTo(activity);
  }

  @Test
  void find_by_id() {
    Team team = new Team("team name", "team description", 3L);
    Long teamId = repository.save(team);

    Optional<Team> foundTeam = repository.findById(teamId);

    team.setId(teamId);
    assertThat(foundTeam).hasValue(team);
  }

  @Test
  void clear() {
    Team team = new Team("team name", "team description", 3L);
    Long teamId = repository.save(team);

    repository.clear();

    assertThat(repository.findById(teamId)).isEmpty();
  }

  @AfterEach
  void tearDown() {
    repository.clear();
  }
}
