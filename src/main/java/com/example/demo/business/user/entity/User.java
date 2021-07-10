package com.example.demo.business.user.entity;

import com.example.demo.business.book.entity.Book;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private int age;
  private String email;

  @ManyToOne
  @JoinColumn(name = "bookId")
  private Book book;

  @Builder
  public User(String name, int age, String email, Book book) {
    this.name = name;
    this.age = age;
    this.email = email;
    this.book = book;
  }
}
