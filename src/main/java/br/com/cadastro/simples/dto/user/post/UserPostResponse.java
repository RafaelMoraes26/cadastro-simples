package br.com.cadastro.simples.dto.user.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostResponse {

    private String id;
    private String title;
    private String content;
    private String username;
    private String createdDate;
    private String updatedDate;
}
