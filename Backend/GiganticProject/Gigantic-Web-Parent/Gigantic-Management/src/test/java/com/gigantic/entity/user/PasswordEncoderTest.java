package com.gigantic.entity.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordEncoderTest {

    @Test
    public void testEncoderPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);

        boolean matches = encoder.matches(rawPassword, encodedPassword);
        assertThat(matches).isTrue();
    }
}
