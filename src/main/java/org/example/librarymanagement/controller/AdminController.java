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

import org.example.librarymanagement.entity.User;
import org.example.librarymanagement.repo.UserRepository;
import org.example.librarymanagement.security.SHA256Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping(path = "/addUser")
    public @ResponseBody String addNewUser(@RequestParam String username,
                                           @RequestParam String password,
                                           @RequestParam String phoneNumber,
                                           @RequestPart MultipartFile photo){
        User user = new User();

        try{
            user.setUsername(username);
            user.setPasswordHash(SHA256Controller.hash(password));
            user.setPhoneNumber(phoneNumber);
            user.setRole("USER");
            user.setAccountValidated(false);

            user.setIdCard(photo.getBytes());

            userRepository.save(user);

        } catch (Exception e){
            e.printStackTrace();
        }

        return "Saved";
    }
}
