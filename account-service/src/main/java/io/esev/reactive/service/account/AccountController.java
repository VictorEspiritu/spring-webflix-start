package io.esev.reactive.service.account;

import io.esev.reactive.service.account.model.AccountDTO;
import io.esev.reactive.service.account.model.AccountEntity;
import io.esev.reactive.service.account.repository.AccountRepository;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class.getName());


    @Autowired
    private AccountRepository repository;

    @GetMapping("/customer/{customerId}")
    public Flux<AccountDTO> findByCustomer(@PathVariable("customerId") String customerId) {
        logger.info("[SERVICE][ACCOUNT] findByCustomer: {}", customerId);

        return repository.findByCustomerId(customerId)
                .map(a -> new AccountDTO(a.getId(), a.getNumber(), a.getCustomerId(), a.getAmount()));
    }

    @GetMapping("/")
    public Flux<AccountDTO> findAll() {
        logger.info("[SERVICE][ACCOUNT] findAll");

        return repository.findAll()
                .map(a -> new AccountDTO(a.getId(), a.getNumber(), a.getCustomerId(), a.getAmount()));
    }

    @GetMapping("/{id}")
    public Mono<AccountDTO> findById(@PathVariable("id") String id){
        logger.info("[SERVICE][ACCOUNT] findById: {}", id);

        return repository.findById(id)
                .map(a -> new AccountDTO(a.getId(), a.getNumber(), a.getCustomerId(), a.getAmount()));
    }

    @PostMapping("/customer")
    public Mono<AccountDTO> create(@RequestBody Publisher<AccountDTO> account){
        logger.info("[SERVICE][ACCOUNT] create: {}", account);

        return repository.save(Mono.from(account).map(a -> new AccountEntity(a.getNumber(), a.getCustomerId(), a.getAmount())))
                .map(a -> new AccountDTO(a.getId(), a.getNumber(), a.getCustomerId(), a.getAmount()));
    }

}
