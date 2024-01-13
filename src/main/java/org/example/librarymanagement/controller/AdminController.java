package org.example.librarymanagement.controller;

import org.example.librarymanagement.entity.Admin;
import org.example.librarymanagement.repo.AdminRepository;
import org.example.librarymanagement.security.SHA256Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(@RequestParam String username, @RequestParam String password){
        Admin admin = new Admin();

        admin.setUsername(username);
        admin.setPasswordHash(SHA256Controller.hash(password));
        admin.setRole("ADMINISTRATOR");

        adminRepository.save(admin);

        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }
}
