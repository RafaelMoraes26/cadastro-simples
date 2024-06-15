package br.com.cadastro.simples.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.cadastro.simples.repository.document.UserPostDocument;

@Repository
public interface UserPostRepository extends MongoRepository<UserPostDocument, String> {

    List<UserPostDocument> findAllByUsername(String username);

    Optional<UserPostDocument> findByUsername(String username);
}
