package br.com.cadastro.simples.service;

import static br.com.cadastro.simples.Utils.StringUtils.isAeValidString;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cadastro.simples.dto.user.info.UserInfoRequest;
import br.com.cadastro.simples.dto.user.info.UserInfoResponse;
import br.com.cadastro.simples.dto.user.info.UserInfoUpdateRequest;
import br.com.cadastro.simples.dto.user.post.UserPostResponse;
import br.com.cadastro.simples.dto.user.post.UserPostUpdateRequest;
import br.com.cadastro.simples.exception.*;
import br.com.cadastro.simples.mapper.UserInfoMapper;
import br.com.cadastro.simples.repository.UserInfoRepository;
import br.com.cadastro.simples.repository.document.UserInfoDocument;
import br.com.cadastro.simples.repository.document.UserPostDocument;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final UserInfoMapper userInfoMapper;

    public UserInfoService(UserInfoRepository userInfoRepository, UserInfoMapper userInfoMapper) {
        this.userInfoRepository = userInfoRepository;
        this.userInfoMapper = userInfoMapper;
    }

    public UserInfoResponse findUserInfoByUsername(String username) throws UserInfoNotFoundException{
        log.info("findUserInfoByUsername");
        return userInfoRepository.findByUsername(username)
            .map(userInfoMapper::fromDocumentToResponse)
            .orElseThrow(() -> new UserInfoNotFoundException("User with username " + username + " not found."));
    }

    public UserInfoResponse createUserInfo(UserInfoRequest request) throws DuplicateUsernameException {
        log.info("createUserInfo");
        try {
            UserInfoDocument savedDocument = userInfoRepository.save(userInfoMapper.fromRequestToDocument(request));
            return userInfoMapper.fromDocumentToResponse(savedDocument);
        } catch (DuplicateKeyException ex) {
            throw new DuplicateUsernameException("User with username " + request.getUsername() + " already exists. Please choose another one.");
        }
    }

    public List<UserInfoResponse> findAllUserInfo() {
        log.info("findAllUserInfo");
        return userInfoRepository.findAll().stream()
            .map(userInfoMapper::fromDocumentToResponse)
            .toList();
    }

    @Transactional
    public UserInfoResponse updateUserInfosByUsername(String username, UserInfoUpdateRequest updateRequest) {
        log.info("updateUserInfosByUsername");
        UserInfoDocument userInfoDocumentFromDatabase = userInfoRepository.findByUsername(username)
            .orElseThrow(() -> new UserInfoNotFoundException(String.format("User with username [%s] not found.", username)));

        boolean needsUpdate = false;
        if(isAeValidString(updateRequest.getFullName())) {
            userInfoDocumentFromDatabase.setFullName(updateRequest.getFullName());
            needsUpdate = true;
        }

        if(isAeValidString(updateRequest.getEmail())) {
            if(!updateRequest.getEmail().equals(userInfoDocumentFromDatabase.getEmail())) {
                userInfoRepository.findByEmail(updateRequest.getEmail())
                    .ifPresent(s -> {
                        throw new UserInfoConflictException(String.format("This e-mail address [%s] is already registered, please choose another.", updateRequest.getEmail()));
                    });
            }
            userInfoDocumentFromDatabase.setEmail(updateRequest.getEmail());
            needsUpdate = true;
        }

        if(needsUpdate) {
            userInfoDocumentFromDatabase.setUpdatedDate(LocalDateTime.now());
            userInfoRepository.save(userInfoDocumentFromDatabase);
        }

        return userInfoMapper.fromDocumentToResponse(userInfoDocumentFromDatabase);
    }
}
