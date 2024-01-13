package org.example.librarymanagement.repo;

import org.example.librarymanagement.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {

}
