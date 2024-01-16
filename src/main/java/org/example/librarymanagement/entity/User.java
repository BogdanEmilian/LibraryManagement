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

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUser;

    private String username;
    private String passwordHash;
    private String role;
    private Boolean accountValidated;
    private String phoneNumber;

    @Lob
    @Column(name="id_card", columnDefinition = "MEDIUMBLOB")
    private byte[] idCard;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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
