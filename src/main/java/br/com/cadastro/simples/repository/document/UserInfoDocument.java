package br.com.cadastro.simples.repository.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "userInfo")
@Data
@AllArgsConstructor @NoArgsConstructor
public class UserInfoDocument {

    @Id
    private String id;
    private String fullName;
    @Indexed(unique = true)
    private String username;
    private String email;
}
