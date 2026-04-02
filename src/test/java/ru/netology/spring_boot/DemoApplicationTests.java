package ru.netology.spring_boot;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    static final GenericContainer<?> devApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @BeforeAll
    static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void devProfileTest() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" +
                devApp.getMappedPort(8080) + "/profile", String.class);

        String expected = "Current profile is dev";
        String actual = forEntity.getBody();

        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    void prodProfileTest() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" +
                prodApp.getMappedPort(8081) + "/profile", String.class);

        String expected = "Current profile is production";
        String actual = forEntity.getBody();

        System.out.println(actual);
        assertEquals(expected, actual);
    }
}