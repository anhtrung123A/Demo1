package com.train.aimforthehead.repositories;

import com.train.aimforthehead.domain.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findAllByauthor_id(int id);
    List<BookEntity> findBytitleContainingIgnoreCase(String title);
}
