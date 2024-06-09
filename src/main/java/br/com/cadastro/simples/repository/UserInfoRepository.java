package br.com.cadastro.simples.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.cadastro.simples.repository.document.UserInfoDocument;

@Repository
public interface UserInfoRepository extends MongoRepository<UserInfoDocument, String> {

    Optional<UserInfoDocument> findByUsername(String username);
}
