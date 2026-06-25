package com.zephyr.api.massaging;

import com.zephyr.api.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;


    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send (String message) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.FAVORITE_CITY_QUEUE,
                message
        );

        System.out.println("MENSAGEM RECEBIDA:");
        System.out.println(message);

    }
}
