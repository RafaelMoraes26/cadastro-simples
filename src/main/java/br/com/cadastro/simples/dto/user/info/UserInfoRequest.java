package br.com.cadastro.simples.dto.user.info;

import lombok.Data;

@Data
public class UserInfoRequest {
    private String fullName;
    private String username;
    private String email;
}
