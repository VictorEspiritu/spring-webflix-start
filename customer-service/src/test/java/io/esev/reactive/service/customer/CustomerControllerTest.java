package io.esev.reactive.service.customer;

import io.esev.reactive.service.customer.model.AccountDTO;
import io.esev.reactive.service.customer.model.CustomerDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(CustomerControllerTest.class);

    @Autowired
    private WebTestClient webTestClient;

//     @Before
//    public void setup(){
//        this.webClient = WebClient.create("http://localhost:" + this.port);
//    }


    @Test
    public void findById() {
        this.webTestClient.get().uri("/customer/4365af54j4534j523")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(CustomerDTO.class);
    }

    @Test
    public void findAll() {
        this.webTestClient.get().uri("/customer/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(CustomerDTO.class);
    }

    @Test
    public void findByDocumentWithAccounts() {

        this.webTestClient.get().uri("/customer/account/3456799")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(CustomerDTO.class);

    }

    @Test
    public void create() {

        CustomerDTO customerDTO = new CustomerDTO(UUID.randomUUID().toString(), "Steve", "Hawking", "656454767");

        this.webTestClient.post().uri("/customer/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(customerDTO), CustomerDTO.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.firstName").isEqualTo("Steve");
    }
}