package br.com.cadastro.simples.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.cadastro.simples.dto.user.info.UserInfoRequest;
import br.com.cadastro.simples.dto.user.info.UserInfoResponse;
import br.com.cadastro.simples.repository.document.UserInfoDocument;

@Component
public class UserInfoMapper {

    public UserInfoResponse fromDocumentToResponse(UserInfoDocument document) {
        UserInfoResponse response = new UserInfoResponse();
        response.setFullName(document.getFullName());
        response.setUsername(document.getUsername());
        response.setEmail(document.getEmail());
        return response;
    }

    public UserInfoDocument fromRequestToDocument(UserInfoRequest request) {
        UserInfoDocument document = new UserInfoDocument();
        document.setFullName(request.getFullName());
        document.setUsername(request.getUsername());
        document.setEmail(request.getEmail());
        document.setCreatedDate(LocalDateTime.now());
        document.setUpdatedDate(LocalDateTime.now());
        return document;
    }
}
