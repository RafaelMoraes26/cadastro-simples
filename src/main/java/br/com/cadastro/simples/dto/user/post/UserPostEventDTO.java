package br.com.cadastro.simples.dto.user.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPostEventDTO {

    @NotBlank @NotNull @NotEmpty
    private String title;
    @NotBlank @NotNull @NotEmpty
    private String content;
    @NotBlank @NotNull @NotEmpty
    private String username;
    private String date;
}
