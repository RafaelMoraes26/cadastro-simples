package br.com.cadastro.simples.msg.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserPostsConsumer {

    @RabbitListener(queues = "${queue.name}")
    public void receive(@Payload String message) {
        System.out.println(message);
    }
}
