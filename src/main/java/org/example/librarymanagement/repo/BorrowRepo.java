package org.example.librarymanagement.repo;

import org.example.librarymanagement.entity.Borrow;
import org.springframework.data.repository.CrudRepository;

public interface BorrowRepo extends CrudRepository<Borrow, Integer> {

}
