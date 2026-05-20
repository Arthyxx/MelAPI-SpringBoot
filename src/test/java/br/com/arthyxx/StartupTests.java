package br.com.arthyxx;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")   // <-- ADICIONE ESTA LINHA
class StartupTests {

    @Test
    void contextLoads() {
    }
}