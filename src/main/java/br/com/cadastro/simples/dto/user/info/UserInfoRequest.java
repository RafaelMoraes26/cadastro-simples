package br.com.cadastro.simples.dto.user.info;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserInfoRequest {
    private String fullName;
    private String username;
    @Email
    private String email;
}
