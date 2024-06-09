package br.com.cadastro.simples.controller;

import org.springframework.web.bind.annotation.*;

import br.com.cadastro.simples.dto.user.UserInfoRequest;
import br.com.cadastro.simples.dto.user.UserInfoResponse;

@RestController
@RequestMapping("userInfo")
public class UserInfoController {

    @PostMapping("/create")
    public UserInfoResponse createUserInfos(@RequestBody UserInfoRequest request) {
        return new UserInfoResponse(request.getFullName(), request.getUsername(), request.getEmail());
    }
}
