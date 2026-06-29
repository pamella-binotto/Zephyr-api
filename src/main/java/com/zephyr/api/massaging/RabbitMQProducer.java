package com.zephyr.api.massaging;

import com.zephyr.api.config.RabbitMQConfig;
import com.zephyr.api.dto.FavoriteCityEventDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;


    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send (FavoriteCityEventDTO event) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.FAVORITE_CITY_QUEUE,
                event
        );

        System.out.println("MENSAGEM ENVIADA:");
        System.out.println(event);

    }
}
