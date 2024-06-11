package br.com.cadastro.simples.dto.user.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserInfoResponse {

    private String fullName;
    private String username;
    private String email;

}
