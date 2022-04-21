package codurance.academyfinalboy.backend.model.token;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
  private final TokenRepository tokenRepository;
  private final TokenIdProvider tokenIdProvider;
  private final TimeProvider timeProvider;

  public TokenService(TokenRepository tokenRepository, TokenIdProvider tokenIdProvider, TimeProvider timeProvider) {
    this.tokenRepository = tokenRepository;
    this.tokenIdProvider = tokenIdProvider;
    this.timeProvider = timeProvider;
  }

  public UUID generateToken(long teamId) {
    var tokenExpiryDate = timeProvider.getCurrentTime().plusMinutes(5);
    var tokenId = tokenIdProvider.random();
    tokenRepository.save(new Token(teamId, tokenId, tokenExpiryDate));
    return tokenId;
  }
}
