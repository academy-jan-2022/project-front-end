package codurance.academyfinalboy.backend.infrastructure.repositories.user;

import codurance.academyfinalboy.backend.model.team.UserRef;
import codurance.academyfinalboy.backend.model.user.User;
import codurance.academyfinalboy.backend.model.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public class SpringUserRepository implements UserRepository {

  private final SpringDataJdbcUserRepository repository;

  public SpringUserRepository(SpringDataJdbcUserRepository repository) {

    this.repository = repository;
  }

  @Override
  public Optional<User> findByExternalId(String externalId) {
    return repository.findByExternalId(externalId);
  }

  @Override
  public User save(User user) {
    return repository.save(user);
  }

  @Override
  public void clear() {
    repository.deleteAll();
  }

  @Override
  public List<User> findAllById(Set<UserRef> userIds) {
    throw new UnsupportedOperationException();
  }
}
