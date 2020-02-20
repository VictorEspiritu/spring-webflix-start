package io.esev.reactive.service.customer;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public @Bean MongoClient mongoClient(){
        return MongoClients.create("mongodb://192.168.0.102");
    }

    public @Bean ReactiveMongoTemplate reactiveMongoTemplate(){
        return new ReactiveMongoTemplate(mongoClient(), "customer");
    }

    public @Bean
    WebClient webClient(){
        return WebClient.builder().clientConnector(new ReactorClientHttpConnector()).baseUrl("http://127.0.0.1:2222").build();
    }


}
