package com.werun.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author werun
 * @version 1.0
 * @date 2022年01月23日 下午2:00
 * @description
 */
public class Sha512PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }
}
