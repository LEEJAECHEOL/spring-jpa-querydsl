package com.example.demo.business.user.application;

import com.example.demo.business.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.business.user.entity.QUser.user;

/**
 * 3번쨰 방법
 * 상속/구현 없는 Repository
 *
 */

@RequiredArgsConstructor
@Repository
public class UserQueryRepository {
  private final JPAQueryFactory jpaQueryFactory;

  public List<User> findByName(String name) {
    return jpaQueryFactory.selectFrom(user)
      .where(user.name.eq(name))
      .fetch();
  }
}
