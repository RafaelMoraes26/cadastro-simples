package br.com.cadastro.simples.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import br.com.cadastro.simples.dto.user.post.UserPostResponse;
import br.com.cadastro.simples.dto.user.post.UserPostUpdateRequest;
import br.com.cadastro.simples.service.UserPostService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("userPost")
@Slf4j
public class UserPostController {

    private final UserPostService userPostService;

    public UserPostController(UserPostService userPostService) {
        this.userPostService = userPostService;
    }

    @GetMapping("/")
    public List<UserPostResponse> getAllUserPosts() {
        return userPostService.getAllUserPosts();
    }

    @GetMapping("/findAllPostsByUsername/{username}")
    public List<UserPostResponse> getAllUserPost(@PathVariable String username) {
       return userPostService.getAllUserPostsByUsername(username);
    }

    @GetMapping("/findPostsByPostId/{postId}")
    public UserPostResponse getUserPost(@PathVariable String postId) {
        return userPostService.getUserPostsByPostId(postId);
    }

    @PatchMapping("/updatePost/{postId}")
    public UserPostResponse updateUserPost(@PathVariable String postId, @RequestBody UserPostUpdateRequest userPostUpdateRequest) {
        return userPostService.updateUserPostById(postId, userPostUpdateRequest);
    }

    @DeleteMapping("/deletePost/{postId}")
    public void deleteUserPost(@PathVariable String postId) {
        userPostService.deleteUserPostById(postId);
    }
}
