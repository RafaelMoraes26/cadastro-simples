package br.com.cadastro.simples.mapper;

import org.springframework.stereotype.Component;

import br.com.cadastro.simples.dto.user.post.UserPostEventDTO;
import br.com.cadastro.simples.repository.document.UserPostDocument;

@Component
public class UserPostMapper {

    public UserPostDocument fromEventToDocument(UserPostEventDTO dto) {
        UserPostDocument document = new UserPostDocument();
        document.setUsername(dto.getUsername());
        document.setTitle(dto.getTitle());
        document.setContent(dto.getContent());
        document.setDate(dto.getDate());
        return document;
    }
}
