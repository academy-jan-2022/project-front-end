package codurance.academyfinalboy.backend.model.team;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class TeamShould {

    @Test void
    add_a_user_to_a_team() {
        long teamCreatorId = 1L;
        long newMemberId = 30L;
        Team team = new Team("Name", "Description", teamCreatorId);

        team.addMember(newMemberId);

        assertThat(team.getMembers(), contains(new UserRef(newMemberId)));
    }
}
