package pers.wilson.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import pers.wilson.demo.model.Bill;

import java.net.URI;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = DemoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class DemoApplicationTest {
    @LocalServerPort
    private int port;
    private String base;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() {
        this.base = String.format("http://localhost:%d", port);
    }

    @Test
    public void testBadRequest() throws Exception {
        ResponseEntity<Bill> response = this.restTemplate.postForEntity(
                new URI(this.base + "/api/v1/bill"),
                Arrays.asList("Apple", "Banana", "Melon", "Lime", "Phone"),
                Bill.class
        );

        assertEquals(HttpStatusCode.valueOf(400), response.getStatusCode());
    }

    @Test
    public void testOKRequest() throws Exception {
        ResponseEntity<Bill> response = this.restTemplate.postForEntity(
                new URI(this.base + "/api/v1/bill"),
                Arrays.asList("Apple", "Banana", "Melon", "Lime"),
                Bill.class
        );

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(120, response.getBody().getTotal(), 0.01);
    }
}