package br.com.cadastro.simples.repository.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "userPost")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostDocument {

    @Id
    private String id;
    private String title;
    private String content;
    private String username;
    private String date;
}
