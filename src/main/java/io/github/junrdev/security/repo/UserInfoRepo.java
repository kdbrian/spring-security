package io.github.junrdev.security.repo;

import io.github.junrdev.security.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByName(String name);
}
