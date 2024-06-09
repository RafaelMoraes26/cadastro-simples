package br.com.cadastro.simples.repository.document;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "userInfo")
@Data
@AllArgsConstructor @NoArgsConstructor
public class UserInfoDocument {

    private String id;
    private String fullName;
    private String username;
    private String email;
}
