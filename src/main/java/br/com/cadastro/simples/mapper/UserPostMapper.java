package br.com.cadastro.simples.mapper;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

import br.com.cadastro.simples.dto.user.post.UserPostEventDTO;
import br.com.cadastro.simples.dto.user.post.UserPostResponse;
import br.com.cadastro.simples.repository.document.UserPostDocument;

@Component
public class UserPostMapper {

    public UserPostDocument fromEventToDocument(UserPostEventDTO dto) {
        LocalDateTime createdDate = dto.getDate() != null ? LocalDateTime.parse(dto.getDate()) : LocalDateTime.now();
        UserPostDocument document = new UserPostDocument();
        document.setUsername(dto.getUsername());
        document.setTitle(dto.getTitle());
        document.setContent(dto.getContent());
        document.setCreatedDate(createdDate);
        document.setUpdatedDate(createdDate);
        return document;
    }

    public UserPostResponse fromDocumentToResponse(UserPostDocument document) {
        UserPostResponse response = new UserPostResponse();
        response.setId(document.getId());
        response.setUsername(document.getUsername());
        response.setTitle(document.getTitle());
        response.setContent(document.getContent());
        response.setCreatedDate(document.getCreatedDate().toString());
        response.setUpdatedDate(document.getUpdatedDate().toString());
        return response;
    }
}
