package org.example.todojpa.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {
    /**
     * 주어진 평문 비밀번호를 인코딩합니다.
     *
     * @param rawPassword 평문 비밀번호
     * @return 인코딩된 비밀번호
     */
    public String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    /**
     * 평문 비밀번호와 인코딩된 비밀번호가 일치하는지 검증합니다.
     *
     * @param rawPassword 평문 비밀번호
     * @param encodedPassword 인코딩된 비밀번호
     * @return 비밀번호가 일치하면 true, 그렇지 않으면 false
     */
    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }
}