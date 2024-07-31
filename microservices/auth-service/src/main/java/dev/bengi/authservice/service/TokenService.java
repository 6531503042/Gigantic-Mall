package dev.bengi.authservice.service;

import dev.bengi.authservice.dto.AuthenticatedUser;
import dev.bengi.authservice.model.RefreshToken;
import dev.bengi.authservice.model.UserLogin;
import dev.bengi.authservice.repository.RefreshTokenRepository;
import dev.bengi.authservice.service.Impl.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.time.Instant;
import java.util.*;

/**
 * @author bengi
 */
@Service
public class TokenService {

    private static final String ISSUER = "mall-backend";
    private static final String ROLES_CLAIM = "roles";
    private static final int TIME_FOR_ROTATE_SECONDS = 120;

    // TODO: Move to config
    private final JwtEncoder jwtEncoder;
    private final long accessTokenExpiredInSeconds;
    private final long refreshTokenExpiredInSeconds;
    private final CustomerUserDetailsService customerUserDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public TokenService(JwtEncoder jwtEncoder, long accessTokenExpiredInSeconds, long refreshTokenExpiredInSeconds, CustomerUserDetailsService customerUserDetailsService, RefreshTokenRepository refreshTokenRepository) {
        this.jwtEncoder = jwtEncoder;
        this.accessTokenExpiredInSeconds = accessTokenExpiredInSeconds;
        this.refreshTokenExpiredInSeconds = refreshTokenExpiredInSeconds;
        this.customerUserDetailsService = customerUserDetailsService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String issueAccessToken(Authentication auth, Instant issueDate) {
        return generateToken(auth, issueDate, accessTokenExpiredInSeconds);
    }

    public String issueAccessToken(UserLogin userLogin, Instant issueDate) {
        AuthenticatedUser userDetails = (AuthenticatedUser) customerUserDetailsService
                .loadUserByUsername(userLogin.email());
        return generateToken(userDetails, issueDate, accessTokenExpiredInSeconds);
    }

    /**
     *
     * wait for store - service ready
     */
//    public String issueAccessToken(Store tourCompanyLogin, Instant issueDate) {
//        AuthenticatedUser userDetails = (AuthenticatedUser) customUserDetailsService
//                .loadUserByUsername(tourCompanyLogin.username());
//        return generateToken(userDetails, issueDate, accessTokenExpiredInSeconds);
//    }

    public String issueRefreshToken() {
        return UUID.randomUUID().toString();
    }

    public String generateToken(AuthenticatedUser auth, Instant issueDate, long expiredInSeconds) {
        return generateToken(auth.userId(), auth.getAuthorities(), issueDate, expiredInSeconds);
    }

    public String generateToken(Authentication auth, Instant issueDate, long expiredInSeconds) {
        var authenticatedUser = (AuthenticatedUser) auth.getPrincipal();
        return generateToken(authenticatedUser.userId(), auth.getAuthorities(), issueDate, expiredInSeconds);
    }

    private String generateToken(
            Integer userId,
            Collection<? extends GrantedAuthority> authorities,
            Instant issueDate,
            long expiredInSeconds) {

        Instant expire = issueDate.plusSeconds(expiredInSeconds);
        String scope = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(ISSUER)
                .issuedAt(issueDate)
                .subject(String.valueOf(userId))
                .claim(ROLES_CLAIM, scope)
                .expiresAt(expire)
                .build();

        return encodeClaimToJwt(claims);
    }

    public String encodeClaimToJwt(JwtClaimsSet claims) {
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String rotateRefreshTokenIfNeed(RefreshToken refreshTokenEntity) {
        var issuedDate = refreshTokenEntity.issuedAt();
        var expireDate = issuedDate.plusSeconds(refreshTokenExpiredInSeconds);
        var thresholdToRotateDate = expireDate.minusSeconds(TIME_FOR_ROTATE_SECONDS);
        var now = Instant.now();
        if (now.isAfter(thresholdToRotateDate)) {
            return issueRefreshToken();
        }
        return refreshTokenEntity.token();
    }

    public void cleanupRefreshTokenThatNotExpired() {
        var now = Instant.now();
        // Assume life of refresh token = 1 day
        // Token issued on 202407132216
        // Token expired on 202407142216
        // Cron start at 202407142216
        // If we want to check expired token from issuedDate -> minus seconds
        var thresholdDate = now.minusSeconds(refreshTokenExpiredInSeconds);
        refreshTokenRepository.updateRefreshTokenThatExpired(true, thresholdDate);
    }

}
