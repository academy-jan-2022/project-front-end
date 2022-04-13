package codurance.academyfinalboy.backend.model.team;

import java.util.Optional;

public interface TeamRepository {
  Optional<Team> findById(Long teamId);
}