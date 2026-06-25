package com.zephyr.api.massaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues =  "favorite-city-queue")
    public void receiveMessage(String message){

        System.out.println("MENSAGEM RECEBIDA:");
        System.out.println(message);
    }
}
