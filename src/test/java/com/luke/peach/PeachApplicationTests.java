package com.luke.peach;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class PeachApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("da");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(20);

        String pw = passwordEncoder.encode("java");
        System.out.println(passwordEncoder.matches("java",pw));

    }

}
