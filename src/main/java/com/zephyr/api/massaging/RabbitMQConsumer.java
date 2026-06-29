package com.zephyr.api.massaging;

import com.zephyr.api.dto.FavoriteCityEventDTO;
import com.zephyr.api.entity.FavoriteCity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues =  "favorite-city-queue")
    public void receiveMessage(FavoriteCityEventDTO event) {

        System.out.println("MENSAGEM RECEBIDA:");
        System.out.println(event);
    }
}
