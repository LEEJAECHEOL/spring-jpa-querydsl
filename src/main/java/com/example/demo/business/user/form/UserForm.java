package com.example.demo.business.user.form;

import lombok.Getter;

public class UserForm {

  public static class Response{
    @Getter
    public static class Count{
      private String name;
      private Long num;
    }
  }
}
