package com.zephyr.api.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {

    public static final String FAVORITE_CITY_QUEUE = "favorite-city-queue";

    @Bean
    public Queue favoriteCityQueue() {

        System.out.println("Criando uma fila favorite city");
        return new Queue(FAVORITE_CITY_QUEUE);
    }

    @PostConstruct
    public void init() {
        System.out.println("RABBIT CONFIG CARREGADO");
    }
}
