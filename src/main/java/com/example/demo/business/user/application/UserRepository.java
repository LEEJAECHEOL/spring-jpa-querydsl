package com.example.demo.business.user.application;

import com.example.demo.business.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 2번째 방법
 * UserRepositoryCustom, UserRepositoryImpl 를 만들어서
 * 상속을 받으면 UserRepository 하나로 사용이 가능함.
 */

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

}
