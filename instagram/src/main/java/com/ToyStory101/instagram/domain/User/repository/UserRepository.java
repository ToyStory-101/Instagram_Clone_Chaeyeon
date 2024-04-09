package com.ToyStory101.instagram.domain.User.repository;

import com.ToyStory101.instagram.domain.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Integer>{
//    extends JpaRepository 때문에 @Repository 안 함. IoC 자동으로 됨.
    Optional<User> findByUsername(String username);
//    Optional을 해줘야 exception에 걸릴 위험 없어짐.

    boolean existsUserByEmail(String username);

}
