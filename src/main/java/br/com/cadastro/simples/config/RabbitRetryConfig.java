package br.com.cadastro.simples.config;

import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitRetryConfig {

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAdviceChain(RetryInterceptorBuilder.stateless()
            .maxAttempts(3)
            .backOffOptions(4000, 2.0, 20000) // Intervalo inicial de 4 segundos, multiplicador de 2, intervalo máximo de 20 segundos
            .recoverer(new RejectAndDontRequeueRecoverer()) // Envia para DLQ após o número máximo de tentativas
            .build());
        return factory;
    }
}
