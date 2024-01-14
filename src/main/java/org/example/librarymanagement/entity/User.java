package org.example.librarymanagement.entity;

import jakarta.persistence.*;

import java.sql.Blob;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_user;

    private String username;
    private String passwordHash;
    private String role;
    private Boolean accountValidated;
    private String phoneNumber;

    @Lob
    @Column(name="id_card", columnDefinition = "MEDIUMBLOB")
    private byte[] idCard;

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getAccountValidated() {
        return accountValidated;
    }

    public void setAccountValidated(Boolean accountValidated) {
        this.accountValidated = accountValidated;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getIdCard() {
        return idCard;
    }

    public void setIdCard(byte[] idCard) {
        this.idCard = idCard;
    }
}
