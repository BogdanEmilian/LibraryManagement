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
        registry.addViewController("/userValidation").setViewName("userValidation");
        registry.addViewController("/validateUser").setViewName("userValidation");
        registry.addViewController("/borrowings").setViewName("borrowList");
        registry.addViewController("/mainpage").setViewName("mainpage");

    }

}