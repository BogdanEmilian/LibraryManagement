package org.example.librarymanagement.repo;

import org.example.librarymanagement.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
