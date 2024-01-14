package org.example.librarymanagement.controller;


import org.example.librarymanagement.entity.User;
import org.example.librarymanagement.repo.UserRepository;
import org.example.librarymanagement.security.ResponseMessage;
import org.example.librarymanagement.security.SHA256Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SignUpController {

    @Autowired
    private UserRepository userRepository;


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/signupadd")
    public @ResponseBody ResponseEntity<ResponseMessage> addNewUser(@RequestParam String username, @RequestParam String password, @RequestParam String phoneNumber, @RequestPart MultipartFile photo){

        String message="";
        User user = new User();

        try{
            user.setUsername(username);
            user.setPasswordHash(SHA256Controller.hash(password));
            user.setPhoneNumber(phoneNumber);
            user.setRole("USER");
            user.setAccountValidated(false);
            user.setIdCard(photo.getBytes());

            userRepository.save(user);
            message = "User has been added with file: " + photo.getName();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e){
            message = "Failed to add user with file: " + photo.getName();
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
