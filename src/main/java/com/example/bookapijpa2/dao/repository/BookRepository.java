package com.example.bookapijpa2.dao.repository;

import com.example.bookapijpa2.dao.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
