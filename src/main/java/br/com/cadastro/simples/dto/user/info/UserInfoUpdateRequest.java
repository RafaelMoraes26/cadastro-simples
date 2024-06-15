package br.com.cadastro.simples.dto.user.info;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserInfoUpdateRequest {
    private String fullName;
    @Email
    private String email;
}
