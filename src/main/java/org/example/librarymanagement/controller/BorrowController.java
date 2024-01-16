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

package org.example.librarymanagement.controller;

import org.example.librarymanagement.entity.Book;
import org.example.librarymanagement.entity.Borrow;
import org.example.librarymanagement.repo.BookRepository;
import org.example.librarymanagement.repo.BorrowRepository;
import org.example.librarymanagement.security.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BorrowController {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    // Endpoint for borrowing a book
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/borrowbook")
    public @ResponseBody ResponseEntity<ResponseMessage> borrowBook(@RequestParam Integer userId,
                                                                    @RequestParam Integer bookId) {

        String message = "";

        try {
            // Check if the book is available for borrowing
            Book book = bookRepository.findById(bookId).orElse(null);
            System.out.println("BOOOK ID IS: "+bookId);
            if (book == null || book.getAvailable() <= 0) {
                message = "Book not available for borrowing.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
            }

            // Create a new Borrow instance
            Borrow borrow = new Borrow();
            borrow.setUserId(userId);
            borrow.setBookId(bookId);
            borrow.setDateBorrowed(new Date());
            borrow.setReturned(false);

            // Update book availability
            book.setAvailable(book.getAvailable() - 1);
            bookRepository.save(book);

            // Save the borrow record
            borrowRepository.save(borrow);

            message = "Book borrowed successfully!";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Failed to borrow book"));
        }
    }

    // Endpoint for retrieving all borrows
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getborrows")
    public ResponseEntity<List<Borrow>> getBorrows(Model model) {
        List<Borrow> borrows = new ArrayList<>();
        borrowRepository.findAll().forEach(borrows::add);

        return ResponseEntity.ok(borrows);
    }

    // Add endpoint for returning a borrowed book
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/returnbook")
    public @ResponseBody ResponseEntity<ResponseMessage> returnBook(@RequestParam Integer borrowId) {
        String message = "";

        try {
            // Fetch the existing borrow record
            Borrow borrow = borrowRepository.findById(borrowId).orElse(null);

            if (borrow != null && !borrow.getReturned()) {
                // Update the borrow record to mark it as returned
                borrow.setReturned(true);
                borrowRepository.save(borrow);

                // Update the book availability
                Book book = bookRepository.findById(borrow.getBookId()).orElse(null);
                if (book != null) {
                    book.setAvailable(book.getAvailable() + 1);
                    bookRepository.save(book);
                }

                message = "Book returned successfully!";
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } else {
                message = "Borrow record not found or book already returned.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Failed to return book"));
        }
    }

}
