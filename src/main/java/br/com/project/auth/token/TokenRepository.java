package br.com.project.auth.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {

    @Query(value = "select t from Token t inner join User u on t.user.oid = u.oid where u.oid = :oid and (t.expired = false or t.revoked = false)")
    List<Token> findAllValidTokenByUser(String oid);

    Optional<Token> findByToken(String token);
}