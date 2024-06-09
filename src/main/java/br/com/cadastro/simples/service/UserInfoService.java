package br.com.cadastro.simples.service;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import br.com.cadastro.simples.dto.user.UserInfoRequest;
import br.com.cadastro.simples.dto.user.UserInfoResponse;
import br.com.cadastro.simples.exception.DuplicateUsernameException;
import br.com.cadastro.simples.exception.UserInfoNotFoundException;
import br.com.cadastro.simples.mapper.UserInfoMapper;
import br.com.cadastro.simples.repository.UserInfoRepository;
import br.com.cadastro.simples.repository.document.UserInfoDocument;

@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final UserInfoMapper userInfoMapper;

    public UserInfoService(UserInfoRepository userInfoRepository, UserInfoMapper userInfoMapper) {
        this.userInfoRepository = userInfoRepository;
        this.userInfoMapper = userInfoMapper;
    }

    public UserInfoResponse findUserInfoByUsername(String username) throws UserInfoNotFoundException{
        return userInfoRepository.findByUsername(username)
            .map(userInfoMapper::fromDocumentToResponse)
            .orElseThrow(() -> new UserInfoNotFoundException("User with username " + username + " not found."));
    }

    public UserInfoResponse createUserInfo(UserInfoRequest request) throws DuplicateUsernameException {
        try {
            UserInfoDocument savedDocument = userInfoRepository.save(userInfoMapper.fromRequestToDocument(request));
            return userInfoMapper.fromDocumentToResponse(savedDocument);
        } catch (DuplicateKeyException ex) {
            throw new DuplicateUsernameException("User with username " + request.getUsername() + " already exists. Please choose another one.");
        }
    }
}
