// Copyright 2024 Bogdan Emilian https://github.com/BogdanEmilian
//
//    Licensed under the Apache License, Version 2.0 (the "License"); you may
//    not use this file except in compliance with the License. You may obtain
//    a copy of the License at
//
//         http://www.apache.org/licenses/LICENSE-2.0
//
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
//    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
//    License for the specific language governing permissions and limitations
//    under the License.

package org.example.librarymanagement.entity;

import jakarta.persistence.*;
import org.example.librarymanagement.repo.BookRepository;
import org.example.librarymanagement.repo.UserRepository;

import java.util.Date;

@Entity
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idBorrow;

    private Integer userId;
    private Integer bookId;

    private Date dateBorrowed;
    private Boolean returned;


    private static UserRepository userRepository;
    private static BookRepository bookRepository;

    public Integer getIdBorrow() {
        return idBorrow;
    }

    public void setIdBorrow(Integer idBorrow) {
        this.idBorrow = idBorrow;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Date getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(Date dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    @Transient
    public User getUser(Integer userId) {
        // Implement logic to retrieve the User entity based on userId
        return userRepository.findById(userId).orElse(null);
    }

    @Transient
    public Book getBook(Integer bookId) {
        // Implement logic to retrieve the Book entity based on bookId
        return bookRepository.findById(bookId).orElse(null);
    }
}

