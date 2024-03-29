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
import org.example.librarymanagement.repo.BookRepository;
import org.example.librarymanagement.security.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

//    @CrossOrigin(origins = "http://localhost:8080")
//    @GetMapping("/getbooks")
//    public List<Book> getBooks(){
//        List<Book> bookList = new ArrayList<>();
//
//        bookRepository.findAll().forEach(book -> bookList.add(book));
//
//        return bookList;
//    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getbooks")
    public ResponseEntity<List<Book>> getBooks(Model model) {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);

        // Return the Thymeleaf template name
        return ResponseEntity.ok(books);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/bookadd")
    public @ResponseBody ResponseEntity<ResponseMessage> addNewBook(    @RequestParam String title,
                                                                        @RequestParam String translator,
                                                                        @RequestParam String collection,
                                                                        @RequestParam String author,
                                                                        @RequestParam Integer pagesNumber,
                                                                        @RequestParam Integer height,
                                                                        @RequestParam Integer width,
                                                                        @RequestParam String editor,
                                                                        @RequestParam Integer total,
                                                                        @RequestParam Integer available,
                                                                        @RequestParam String datePublished,
                                                                        @RequestParam String isbn,
                                                                        @RequestParam String edition,
                                                                        @RequestParam String cover,
                                                                        @RequestParam String category){
        String message = "";
        Book book = new Book();

        try {
            book.setTitle(title);
            book.setTranslator(translator);
            book.setCollection(collection);
            book.setAuthor(author);
            book.setPagesNumber(pagesNumber);
            book.setHeight(height);
            book.setWidth(width);
            book.setEditor(editor);
            book.setTotal(total);
            book.setAvailable(available);
            book.setDatePublished(datePublished);
            book.setIsbn(isbn);
            book.setEdition(edition);
            book.setCover(cover);
            book.setCategory(category);

            bookRepository.save(book);
            message = "Book titled " + title + " has been added!";
            System.out.println(book.getId_book());

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/searchbooks")
    public ResponseEntity<List<Book>> searchBooks(  @RequestParam(required = false) String title,
                                                    @RequestParam(required = false) String author,
                                                    @RequestParam(required = false) String isbn,
                                                    @RequestParam(required = false) String category,
                                                    @RequestParam(required = false) String editor,
                                                    @RequestParam(required = false) String collection,
                                                    @RequestParam(required = false) String datePublished) {

        Iterable<Book> allBooks = bookRepository.findAll();

        List<Book> searchResult = StreamSupport.stream(allBooks.spliterator(), false)
                .filter(book -> isNullOrEmpty(title) || book.getTitle().contains(title))
                .filter(book -> isNullOrEmpty(author) || book.getAuthor().contains(author))
                .filter(book -> isNullOrEmpty(isbn) || book.getIsbn().equals(isbn))
                .filter(book -> isNullOrEmpty(category) || book.getCategory().contains(category))
                .filter(book -> isNullOrEmpty(editor) || book.getEditor().contains(editor))
                .filter(book -> isNullOrEmpty(collection) || book.getCollection().contains(collection))
                .filter(book -> isNullOrEmpty(datePublished) || book.getDatePublished().contains(datePublished))
                .collect(Collectors.toList());

        return ResponseEntity.ok(searchResult);
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }




    // Add endpoint for updating a book
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/bookupdate")
    public ResponseEntity<ResponseMessage> updateBook(  @RequestParam Integer id_book,
                                                          @RequestParam String title,
                                                          @RequestParam String translator,
                                                          @RequestParam String collection,
                                                          @RequestParam String author,
                                                          @RequestParam Integer pagesNumber,
                                                          @RequestParam Integer height,
                                                          @RequestParam Integer width,
                                                          @RequestParam String editor,
                                                          @RequestParam Integer total,
                                                          @RequestParam Integer available,
                                                          @RequestParam String datePublished,
                                                          @RequestParam String isbn,
                                                          @RequestParam String edition,
                                                          @RequestParam String cover,
                                                          @RequestParam String category) {
        // Fetch the existing book from the repository
        Book existingBook = bookRepository.findById(id_book).orElse(null);

        if (existingBook != null) {
            try {
                // Update the book fields
                existingBook.setTitle(title);
                existingBook.setTranslator(translator);
                existingBook.setCollection(collection);
                existingBook.setAuthor(author);
                existingBook.setPagesNumber(pagesNumber);
                existingBook.setHeight(height);
                existingBook.setWidth(width);
                existingBook.setEditor(editor);
                existingBook.setTotal(total);
                existingBook.setAvailable(available);
                existingBook.setDatePublished(datePublished);
                existingBook.setIsbn(isbn);
                existingBook.setEdition(edition);
                existingBook.setCover(cover);
                existingBook.setCategory(category);

                // Save the updated book
                bookRepository.save(existingBook);

                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Book updated successfully"));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Failed to update book"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Book not found"));
        }
    }

}

