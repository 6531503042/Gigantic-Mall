package dev.bengi.authservice.exception;

public class RefreshTokenExpiredException extends RuntimeException {

    public RefreshTokenExpiredException() {
        super("Refresh token expired");
    }
    public RefreshTokenExpiredException(String message) {
        super(message);
    }
}
