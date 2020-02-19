package io.esev.reactive.service.account.repository;

import io.esev.reactive.service.account.model.AccountDTO;
import io.esev.reactive.service.account.model.AccountEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class AccountRepository {

    private static final Logger logger = LoggerFactory.getLogger(AccountRepository.class.getName());


    @Autowired
    private ReactiveMongoTemplate template;

    public Flux<AccountEntity> findByCustomerId(String customerId) {
        logger.info("[REPOSITORY][ACCOUNT] findByCustomerId: {}", customerId);

        return template.find(Query.query(Criteria.where("customerId").is(customerId)), AccountEntity.class);
    }

    public Flux<AccountEntity> findAll() {
        logger.info("[REPOSITORY][ACCOUNT] findAll");

        return template.findAll(AccountEntity.class);
    }

    public Mono<AccountEntity> findById(String id) {
        logger.info("[REPOSITORY][ACCOUNT] findById: {}", id);

        return template.findById(id, AccountEntity.class);
    }

    public Mono<AccountEntity> save(Mono<AccountEntity> account) {
        logger.info("[REPOSITORY][ACCOUNT] save: {}", account);

        return template.save(account);
    }
}
