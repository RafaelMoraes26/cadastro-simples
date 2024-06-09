package br.com.cadastro.simples.mapper;

import org.springframework.stereotype.Component;

import br.com.cadastro.simples.dto.user.UserInfoRequest;
import br.com.cadastro.simples.dto.user.UserInfoResponse;
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
        return document;
    }
}
