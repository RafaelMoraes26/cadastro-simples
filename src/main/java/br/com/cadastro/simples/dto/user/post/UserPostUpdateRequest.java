package br.com.cadastro.simples.dto.user.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostUpdateRequest {

    private String title;
    private String content;
}
