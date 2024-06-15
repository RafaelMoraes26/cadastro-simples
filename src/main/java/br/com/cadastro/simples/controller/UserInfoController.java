package br.com.cadastro.simples.controller;

import org.springframework.web.bind.annotation.*;

import br.com.cadastro.simples.dto.user.info.UserInfoRequest;
import br.com.cadastro.simples.dto.user.info.UserInfoResponse;
import br.com.cadastro.simples.exception.DuplicateUsernameException;
import br.com.cadastro.simples.exception.UserInfoNotFoundException;
import br.com.cadastro.simples.service.UserInfoService;

@RestController
@RequestMapping("userInfo")
public class UserInfoController {

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping("/create")
    public UserInfoResponse createUserInfos(@RequestBody UserInfoRequest request) throws DuplicateUsernameException {
        return userInfoService.createUserInfo(request);
    }

    @GetMapping("/findByUsername/{username}")
    public UserInfoResponse findByUsername(@PathVariable String username) throws UserInfoNotFoundException {
        return userInfoService.findUserInfoByUsername(username);
    }
}
