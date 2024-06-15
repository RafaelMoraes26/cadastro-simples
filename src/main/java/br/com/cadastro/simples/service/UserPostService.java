package br.com.cadastro.simples.service;

import static br.com.cadastro.simples.Utils.StringUtils.isAeValidString;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cadastro.simples.dto.user.post.UserPostResponse;
import br.com.cadastro.simples.dto.user.post.UserPostUpdateRequest;
import br.com.cadastro.simples.exception.UserInfoNotFoundException;
import br.com.cadastro.simples.exception.UserPostNotFoundException;
import br.com.cadastro.simples.mapper.UserPostMapper;
import br.com.cadastro.simples.repository.UserPostRepository;
import br.com.cadastro.simples.repository.document.UserPostDocument;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserPostService {

    private final UserPostRepository repository;
    private final UserPostMapper mapper;

    public UserPostService(UserPostRepository repository, UserPostMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<UserPostResponse> getAllUserPostsByUsername(String username) {
        log.info("getAllUserPostsByUsername");
        List<UserPostDocument> documents = repository.findAllByUsername(username);
        return documents.stream().map(mapper::fromDocumentToResponse).toList();
    }

    public UserPostResponse getUserPostsByPostId(String postId) {
        log.info("getUserPostsByUsername");
        return repository.findById(postId)
            .map(mapper::fromDocumentToResponse)
            .orElseThrow(() -> new UserInfoNotFoundException(String.format("User doesn't have any post with Id [%s]", postId)));
    }

    @Transactional
    public UserPostResponse updateUserPostById(String postId, UserPostUpdateRequest updateRequest) {
        log.info("updateUserPostById");
        UserPostDocument userPostDocumentFromDatabase = repository.findById(postId)
            .orElseThrow(() -> new UserPostNotFoundException(String.format("User post not found with Id [%s]", postId)));

        boolean needsUpdate = false;
        if (isAeValidString(updateRequest.getContent())) {
            userPostDocumentFromDatabase.setContent(updateRequest.getContent());
            needsUpdate = true;
        }

        if (isAeValidString(updateRequest.getTitle())) {
            userPostDocumentFromDatabase.setTitle(updateRequest.getTitle());
            needsUpdate = true;
        }

        if (needsUpdate) {
            userPostDocumentFromDatabase.setUpdatedDate(LocalDateTime.now());
            repository.save(userPostDocumentFromDatabase);
        }

        return mapper.fromDocumentToResponse(userPostDocumentFromDatabase);
    }

    public List<UserPostResponse> getAllUserPosts() {
        log.info("getAllUserPosts");
        List<UserPostDocument> documents = repository.findAll();

        log.info(documents.toString());

        return documents
            .stream().map(mapper::fromDocumentToResponse).toList();
    }

    public void deleteUserPostById(String postId) {
        log.info("deleteUserPostById");
        repository.deleteById(postId);
    }
}
