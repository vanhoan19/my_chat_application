package com.hoanvan.messenger_v1.repository;

import com.hoanvan.messenger_v1.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
    Optional<AccountEntity> findByUsername(String username);
    Boolean existsByUsername(String username);

    @Query("SELECT a FROM AccountEntity a WHERE a.username = :username AND a.password = :password")
    Optional<AccountEntity> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    Boolean existsByUsernameAndPassword(String username, String password);

    @Query(value = "SELECT * FROM account WHERE nickname LIKE %:nickname%", nativeQuery = true)
    List<AccountEntity> findAccountsByNickname(@Param("nickname") String nickname);
}
