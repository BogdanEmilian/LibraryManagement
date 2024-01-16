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
