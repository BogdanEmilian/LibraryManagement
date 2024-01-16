package org.example.librarymanagement.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/home").setViewName("login-admin");
        registry.addViewController("/addBook").setViewName("bookAdd");
        registry.addViewController("/addPdf").setViewName("pdfAdd");
        registry.addViewController("/books").setViewName("bookList");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/admin").setViewName("login-admin");
        registry.addViewController("/login").setViewName("login-admin");
        registry.addViewController("/signup").setViewName("signup");
//        registry.addViewController("/signupadd").setViewName("signup");
        registry.addViewController("/adminpanel").setViewName("admin-panel");
        registry.addViewController("/bookEdit").setViewName("bookEdit");
        registry.addViewController("/pdfEdit").setViewName("pdfEdit");
        registry.addViewController("/pdfList").setViewName("pdfList");

    }

}