package com.example.demo.business.user.application;

import static com.example.demo.business.user.entity.QUser.user;
import com.example.demo.business.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;


// Gradle -> Tasks -> other -> complieJava를 실행하면 Q파일(build/generated)이 생성됨
/**
 * 1번쨰 방법
 *
 * import com.example.demo.business.user.entity.QUser;
 * 와깉 import 되는데 아래처럼 변경하면 QUser.user 를 user로 사용이 가능함
 * import static com.example.demo.business.user.entity.QUser.user;
 *
 */

@Repository
public class UserRepositorySupport extends QuerydslRepositorySupport {
  private final JPAQueryFactory jpaQueryFactory;

  public UserRepositorySupport(JPAQueryFactory jpaQueryFactory) {
    super(User.class);
    this.jpaQueryFactory = jpaQueryFactory;
  }

  public List<User> findByName(String name) {
    return jpaQueryFactory.selectFrom(user)
                          .where(user.name.eq(name))
                          .fetch();
  }

}
