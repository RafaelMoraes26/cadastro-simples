package br.com.cadastro.simples.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import br.com.cadastro.simples.dto.user.info.UserInfoRequest;
import br.com.cadastro.simples.dto.user.info.UserInfoResponse;
import br.com.cadastro.simples.dto.user.info.UserInfoUpdateRequest;
import br.com.cadastro.simples.exception.DuplicateUsernameException;
import br.com.cadastro.simples.exception.UserInfoNotFoundException;
import br.com.cadastro.simples.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("userInfo")
@Slf4j
public class UserInfoController {

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/")
    public List<UserInfoResponse> getAllUserInfos() {
        return userInfoService.findAllUserInfo();
    }

    @GetMapping("/findByUsername/{username}")
    public UserInfoResponse findByUsername(@PathVariable String username) throws UserInfoNotFoundException {
        log.error("findByUsername {}", username);
        return userInfoService.findUserInfoByUsername(username);
    }

    @PostMapping("/create")
    public UserInfoResponse createUserInfos(@RequestBody UserInfoRequest request) throws DuplicateUsernameException {
        return userInfoService.createUserInfo(request);
    }

    @PatchMapping("/updateUserInfo/{username}")
    public UserInfoResponse updateUserInfos(@PathVariable String username, @RequestBody UserInfoUpdateRequest userInfoUpdateRequest) throws UserInfoNotFoundException {
        return userInfoService.updateUserInfosByUsername(username, userInfoUpdateRequest);
    }

}
