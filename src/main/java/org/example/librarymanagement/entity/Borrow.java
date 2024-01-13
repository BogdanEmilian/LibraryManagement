package org.example.librarymanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_borrow;

    private Integer user_id;
    private Integer book_id;
    private String dateBorrowed;
    private Boolean returned;

    public Integer getId_borrow() {
        return id_borrow;
    }

    public void setId_borrow(Integer id_borrow) {
        this.id_borrow = id_borrow;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }
}
