package com.example.bookflixspring.book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends CrudRepository<Book, Long>, PagingAndSortingRepository<Book, Long>{
}

