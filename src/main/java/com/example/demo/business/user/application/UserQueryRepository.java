package com.example.demo.business.user.application;

import com.example.demo.business.user.entity.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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

  public List<User> orderByName(){
    return jpaQueryFactory.selectFrom(user)
                          .orderBy(user.name.asc())
                          .fetch();
  }

  public List<String> groupByName(){
    return jpaQueryFactory.select(user.name).from(user)
                          .groupBy(user.name)
                          .fetch();
  }

  public User dynamicQuery(String name, int age){
    return jpaQueryFactory
                          .selectFrom(user)
                          .where(
                            eqName(name),
                            gtAge(age)
                            )
                          .fetchFirst();
  }

  public Boolean exist(Long id) {
    Integer fetchOne = jpaQueryFactory
      .selectOne()
      .from(user)
      .where(
        user.id.eq(id)
      )
      .fetchFirst();

    return fetchOne != null;
  }

  private BooleanExpression eqName(String name) {
    if (StringUtils.isEmpty(name)) {
      return null;
    }
    return user.name.eq(name);
  }

  private BooleanExpression gtAge(int age) {
    if (age == 0) {
      return null;
    }
    return user.age.gt(age);
  }
}
