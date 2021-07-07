package com.example.demo.business.user.application;

import com.example.demo.business.user.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
  List<User> findByName(String name);
}
