package com.example.demo.business.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.business.book.application.BookQueryRepository;
import com.example.demo.business.book.application.BookRepository;
import com.example.demo.business.book.entity.Book;
import com.example.demo.business.user.application.UserQueryRepository;
import com.example.demo.business.user.application.UserRepository;
import com.example.demo.business.user.application.UserRepositorySupport;
import com.example.demo.business.user.entity.User;
import com.example.demo.business.user.form.UserForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
public class UserTest {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private BookRepository bookRepository;
  @Autowired
  private UserRepositorySupport userRepositorySupport;
  @Autowired
  private BookQueryRepository bookQueryRepository;
  @Autowired
  private UserQueryRepository userQueryRepository;

  @Test
  public void test() {
//    User user = new User("cheol", 24, "cheol@naver.com");
//
//    userRepository.save(user);
////    List<User> result = userRepositorySupport.findByName("cheol");
////    List<User> result = userRepository.findByName("cheol");
//    List<User> result = userQueryRepository.findByName("cheol");
//
//    assertThat(result.get(0));

  }

  @Test
  public void join_test(){
    Book book = new Book("흥부와 놀부", "흥부", 30);
    bookRepository.save(book);
    User user = new User("cheol", 24, "cheol@naver.com", book);
    userRepository.save(user);

    User result = bookQueryRepository.joinBook();

    assertThat(result);
  }
  @Test
  public void ordeyBy_test(){
    List<User> users = Arrays.asList(
      new User("다", 12, "다@naver.com", null),
      new User("나", 12, "나@naver.com", null),
      new User("가", 12, "가@naver.com", null)
    );
    userRepository.saveAll(users);

    List<User> result = userQueryRepository.orderByName();

    assertThat(result);
  }

  @Test
  public void groupBy_test(){
    List<User> users = Arrays.asList(
      new User("다", 12, "다@naver.com", null),
      new User("나", 12, "나@naver.com", null),
      new User("다", 12, "다@naver.com", null),
      new User("나", 12, "나@naver.com", null),
      new User("가", 12, "가@naver.com", null)
    );
    userRepository.saveAll(users);

    List<String> result = userQueryRepository.groupByName();

    assertThat(result);
  }

  @Test
  public void select_subquery_test(){
    List<User> users = Arrays.asList(
      new User("가", 12, "가@naver.com", null),
      new User("나", 12, "나@naver.com", null)
    );
    List<Book> books = Arrays.asList(
      new Book("흥부와 놀부1", "가", 10),
      new Book("흥부와 놀부2", "가", 20),
      new Book("흥부와 놀부3", "나", 30),
      new Book("흥부와 놀부4", "나", 40),
      new Book("흥부와 놀부5", "나", 50)
    );
    userRepository.saveAll(users);
    bookRepository.saveAll(books);

    List<UserForm.Response.Count> result = bookQueryRepository.selectSubQuery();

    assertThat(result);
  }

  @Test
  public void where_subquery_test(){
    List<User> users = Arrays.asList(
      new User("가", 12, "가@naver.com", null),
      new User("나", 12, "나@naver.com", null)
    );
    List<Book> books = Arrays.asList(
      new Book("흥부와 놀부1", "가", 10),
      new Book("흥부와 놀부2", "가", 20),
      new Book("흥부와 놀부3", "나", 30),
      new Book("흥부와 놀부4", "나", 40),
      new Book("흥부와 놀부5", "나", 50)
    );
    userRepository.saveAll(users);
    bookRepository.saveAll(books);

    List<Book> result = bookQueryRepository.whereSubQuery("가");

    assertThat(result);
  }

  @Test
  public void dynamicQuery_test(){
    List<User> users = Arrays.asList(
      new User("다", 12, "다@naver.com", null),
      new User("나", 22, "나@naver.com", null),
      new User("다", 32, "다@naver.com", null),
      new User("나", 42, "나@naver.com", null),
      new User("가", 52, "가@naver.com", null)
    );
    userRepository.saveAll(users);

    User result = userQueryRepository.dynamicQuery("", 30);

    assertThat(result);
  }
  @Test
  public void exist_test(){
    List<User> users = Arrays.asList(
      new User("다", 12, "다@naver.com", null),
      new User("나", 22, "나@naver.com", null),
      new User("다", 32, "다@naver.com", null),
      new User("나", 42, "나@naver.com", null),
      new User("가", 52, "가@naver.com", null)
    );
    userRepository.saveAll(users);

    Boolean result = userQueryRepository.exist( 2L);

    assertThat(result);
  }
}
