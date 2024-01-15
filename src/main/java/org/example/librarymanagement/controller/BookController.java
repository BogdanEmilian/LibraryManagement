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
    public @ResponseBody ResponseEntity<ResponseMessage> addNewBook(@RequestParam String title, @RequestParam String translator, @RequestParam String collection, @RequestParam String author, @RequestParam Integer pagesNumber, @RequestParam Integer height, @RequestParam Integer width, @RequestParam String editor, @RequestParam Integer total, @RequestParam Integer available, @RequestParam String datePublished, @RequestParam String isbn, @RequestParam String edition, @RequestParam String cover, @RequestParam String category){
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

}
