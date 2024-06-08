package br.com.cadastro.simples.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoResponse {

    private String fullName;
    private String username;
    private String email;

}
