package io.esev.reactive.service.customer.repository;

import io.esev.reactive.service.customer.model.CustomerDTO;
import io.esev.reactive.service.customer.model.CustomerEntity;
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
public class CustomerRepository {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class.getName());

    @Autowired
    private ReactiveMongoTemplate template;

    public Mono<CustomerEntity> findById(String id) {
        logger.info("[REPOSITORY][CUSTOMER] findById: {}", id);

        return template.findById(id, CustomerEntity.class);
    }

    public Flux<CustomerEntity> findAll() {
        logger.info("[REPOSITORY][CUSTOMER] findAll");

        return template.findAll(CustomerEntity.class);
    }

    public Mono<CustomerEntity> findByDocumentNumber(String documentNumber) {
        logger.info("[REPOSITORY][CUSTOMER] findByDocumentNumber: {}", documentNumber);

        return template.findOne(Query.query(Criteria.where("documentNumber").is(documentNumber)), CustomerEntity.class);
    }

    public Mono<CustomerEntity> save(Mono<CustomerEntity> customerEntity) {
        return template.insert(customerEntity);
    }
}
