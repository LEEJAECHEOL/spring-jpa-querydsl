package com.example.demo.business.book.application;

import com.example.demo.business.book.entity.Book;
import com.example.demo.business.user.entity.User;
import com.example.demo.business.user.form.UserForm;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.business.user.entity.QUser.user;
import static com.example.demo.business.book.entity.QBook.book;

@RequiredArgsConstructor
@Repository
public class BookQueryRepository {
  private final JPAQueryFactory jpaQueryFactory;

  // left, right, inner 조인 동일함   .on()도 사용이 가능
  public User joinBook() {
    return jpaQueryFactory.selectFrom(user)
      .join(user.book, book)
      .fetchFirst();
  }

  public User joinBook2() {
    return jpaQueryFactory.selectFrom(user)
      .join(user.book, book)
      .on(user.age.gt(30))
      .fetchFirst();
  }

  public List<UserForm.Response.Count> selectSubQuery(){
    return jpaQueryFactory
      .select(
        Projections.fields(
          UserForm.Response.Count.class,
          user.name,
          ExpressionUtils.as(
            JPAExpressions.select(book.count())
              .from(book)
              .where(book.author.eq(user.name)),
            "num")
        )
      )
      .from(user)
      .fetch();
  }

  public List<Book> whereSubQuery(String name){
    return jpaQueryFactory
      .selectFrom(book)
      .where(book.author.eq(
        JPAExpressions
          .select(user.name)
          .from(user)
          .where(user.name.eq(name))
        )
      )
      .fetch();
  }
}
