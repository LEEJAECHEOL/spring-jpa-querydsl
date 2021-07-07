package com.example.demo.business.user.application;


import static com.example.demo.business.user.entity.QUser.user;
import com.example.demo.business.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<User> findByName(String name) {
    return jpaQueryFactory.selectFrom(user)
                          .where(user.name.eq(name))
                          .fetch();
  }
}
