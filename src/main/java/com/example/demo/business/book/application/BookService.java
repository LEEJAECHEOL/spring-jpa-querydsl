package com.example.demo.business.book.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
  private final BookQueryRepository bookQueryRepository;
  private final BookRepository bookRepository;
}
