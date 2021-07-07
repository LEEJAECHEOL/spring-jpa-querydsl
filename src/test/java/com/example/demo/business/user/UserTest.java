package com.example.demo.business.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.business.user.application.UserQueryRepository;
import com.example.demo.business.user.application.UserRepository;
import com.example.demo.business.user.application.UserRepositorySupport;
import com.example.demo.business.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class UserTest {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserRepositorySupport userRepositorySupport;

  @Autowired
  private UserQueryRepository userQueryRepository;

  @Test
  public void test() {
    User user = new User("cheol", 24, "cheol@naver.com");

    userRepository.save(user);
//    List<User> result = userRepositorySupport.findByName("cheol");
//    List<User> result = userRepository.findByName("cheol");
    List<User> result = userQueryRepository.findByName("cheol");

    assertThat(result.get(0));

  }

}
