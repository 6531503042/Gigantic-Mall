package dev.bengi.authservice.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

/**
 * @author bengi
 */
@Service
public class TokenService {

    private static final String ISSUED = "mall-backend";
    private static final String ROLES_CLAIM = "roles";
    private static final int TIME_FOR_ROTATE_SEC = 120;

    // TODO: Move to config
    private final JwtEncoder jwtEncoder;
    private long accessTokenExpiredInSeconds = 36;
    private long refreshTokenExpiredInSeconds = 86400L;

    public TokenService(JwtEncoder jwtEncoder, long accessTokenExpiredInSeconds, long refreshTokenExpiredInSeconds) {
        this.jwtEncoder = jwtEncoder;
        this.accessTokenExpiredInSeconds = accessTokenExpiredInSeconds;
        this.refreshTokenExpiredInSeconds = refreshTokenExpiredInSeconds;
    }

//    public String generateToken(AuthenticatedUser auth, Instant issueDate, long expiredInSeconds) {
//        return generateToken(auth.userId(), auth.getAuthorities(), issueDate, expiredInSeconds);
//    }
}
