package br.com.cadastro.simples.msg.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cadastro.simples.exception.MapperFromJsonException;
import br.com.cadastro.simples.repository.UserPostRepository;
import br.com.cadastro.simples.repository.document.UserPostDocument;

@Component
public class UserPostsConsumer {

    private final UserPostRepository userPostRepository;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public UserPostsConsumer(UserPostRepository userPostRepository) {
        this.userPostRepository = userPostRepository;
    }

    @RabbitListener(queues = "${queue.name}")
    public void receive(@Payload String message) {
        userPostRepository.save(mapToUserPostDocument(message));
    }

    private UserPostDocument mapToUserPostDocument(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, UserPostDocument.class);
        } catch (JsonProcessingException e) {
            String exceptionMessage = String.format("Error occurred while converting message >>>%s<<< to object: %s", jsonString, e.getMessage());
            throw new MapperFromJsonException(exceptionMessage, e);
        }
    }
}
