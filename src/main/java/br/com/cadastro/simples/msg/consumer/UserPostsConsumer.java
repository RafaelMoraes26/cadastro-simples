package br.com.cadastro.simples.msg.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cadastro.simples.service.ValidationService;
import br.com.cadastro.simples.dto.user.post.UserPostEventDTO;
import br.com.cadastro.simples.exception.MapperFromJsonException;
import br.com.cadastro.simples.mapper.UserPostMapper;
import br.com.cadastro.simples.repository.UserPostRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserPostsConsumer {

    private final UserPostRepository userPostRepository;
    private final ObjectMapper objectMapper;
    private final UserPostMapper userPostMapper;
    private final ValidationService validator;

    public UserPostsConsumer(UserPostRepository userPostRepository, ObjectMapper objectMapper, UserPostMapper userPostMapper, ValidationService validator) {
        this.userPostRepository = userPostRepository;
        this.objectMapper = objectMapper;
        this.userPostMapper = userPostMapper;
        this.validator = validator;
    }

    @RabbitListener(queues = "${userPostQueue.name}", containerFactory = "rabbitListenerContainerFactory")
    public void handleMessage(@Payload String message) {
        try {
            UserPostEventDTO eventDTO = objectMapper.readValue(message, UserPostEventDTO.class);
            validator.validate(eventDTO, message);
            userPostRepository.save(userPostMapper.fromEventToDocument(eventDTO));
            log.info("Message processed successfully");
        } catch (Exception e) {
            log.error("Error processing message: {}", message, e);
            throw new MapperFromJsonException("Message processing failed", e);
        }
    }
}
