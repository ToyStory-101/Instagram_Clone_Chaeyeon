package com.ToyStory101.instagram.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer>{
//    extends JpaRepository 때문에 @Repository 안 함. IoC 자동으로 됨.

}
