package br.com.cadastro.simples.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${userPostQueue.name}")
    private String userPostQueue;

    @Value("${userPostDLX.name}")
    private String userPostDLX;

    @Value("${userPostDLQ.name}")
    private String userPostDLQ;

    @Bean
    public Queue userPostQueue() {
        return QueueBuilder.durable(userPostQueue)
            .withArgument("x-dead-letter-exchange", userPostDLX)
            .withArgument("x-dead-letter-routing-key", userPostDLQ)
            .build();
    }

    @Bean
    public Queue userPostDLQ() {
        return QueueBuilder.durable(userPostDLQ).build();
    }

    @Bean
    public DirectExchange userPostDLX() {
        return new DirectExchange(userPostDLX);
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(userPostDLQ())
            .to(userPostDLX())
            .with(userPostQueue);
    }
}
