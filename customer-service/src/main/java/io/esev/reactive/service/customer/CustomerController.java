package io.esev.reactive.service.customer;

import io.esev.reactive.service.customer.model.AccountDTO;
import io.esev.reactive.service.customer.model.CustomerDTO;
import io.esev.reactive.service.customer.model.CustomerEntity;
import io.esev.reactive.service.customer.repository.CustomerRepository;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class.getName());

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private WebClient webClient;

    @GetMapping("/{id}")
    public Mono<CustomerDTO> findById(@PathVariable("id") String id){
        logger.info("[SERVICE][CUSTOMER] findById: {}", id);

        return repository.findById(id)
                .map(c -> new CustomerDTO(c.getId(), c.getFirstName(), c.getLastName(), c.getDocumentNumber()));
    }

    @GetMapping("/")
    public Flux<CustomerDTO> findAll(){
        logger.info("[SERVICE][CUSTOMER] findAll");

        return repository.findAll()
                .map(c -> new CustomerDTO(c.getId(), c.getFirstName(), c.getLastName(), c.getDocumentNumber()));
    }

    @GetMapping("/account/{documentNumber}")
    public Mono<CustomerDTO> findByDocumentWithAccounts(@PathVariable("documentNumber") String documentNumber){
        logger.info("[SERVICE][CUSTOMER] findByDocumentWithAccounts: {}", documentNumber);

        Mono<CustomerEntity> customerDTOMono = repository.findByDocumentNumber(documentNumber);

        return customerDTOMono
                .flatMap(c -> webClient
                                .get()
                                .uri("/account/customer/{customerId}", c.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .exchange()
                                .log()
                                .flatMap(r -> r.bodyToFlux(AccountDTO.class).collectList())
                                .map(l -> { return new CustomerDTO(c.getId(), c.getFirstName(), c.getLastName(), c.getDocumentNumber(), l); })
                );
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CustomerDTO> create(@RequestBody Publisher<CustomerDTO> customerStream){
        logger.info("[SERVICE][CUSTOMER] create: {}", customerStream);

        return repository.save(Mono.from(customerStream)
                                    .map(c -> new CustomerEntity(c.getFirstName(), c.getLastName(), c.getDocumentNumber())))
                        .map(c -> new CustomerDTO(c.getId(), c.getFirstName(), c.getLastName(), c.getDocumentNumber()));
    }
}
