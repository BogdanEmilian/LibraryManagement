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

import org.example.librarymanagement.entity.Pdf;
import org.example.librarymanagement.repo.PdfRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class PdfController {

    @Autowired
    private PdfRepository pdfRepository;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getpdfs")
    public ResponseEntity<List<Pdf>> getPdfs(Model model) {
        List<Pdf> pdfs = new ArrayList<>();
        pdfRepository.findAll().forEach(pdfs::add);

        return ResponseEntity.ok(pdfs);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/pdfadd")
    public @ResponseBody ResponseEntity<ResponseMessage> addNewPdf(
            @RequestParam String title,
            @RequestParam String translator,
            @RequestParam String collection,
            @RequestParam String author,
            @RequestParam Integer pagesNumber,
            @RequestParam Integer datePublished,
            @RequestParam String isbn,
            @RequestParam String edition,
            @RequestParam String documentLink) {
        String message = "";
        Pdf pdf = new Pdf();

        try {
            pdf.setTitle(title);
            pdf.setTranslator(translator);
            pdf.setCollection(collection);
            pdf.setAuthor(author);
            pdf.setPagesNumber(pagesNumber);
            pdf.setDatePublished(datePublished);
            pdf.setIsbn(isbn);
            pdf.setEdition(edition);
            pdf.setDocumentLink(documentLink);

            pdfRepository.save(pdf);
            message = "PDF titled " + title + " has been added!";

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/searchpdfs")
    public ResponseEntity<List<Pdf>> searchPdfs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String collection,
            @RequestParam(required = false) Integer datePublished) {

        Iterable<Pdf> allPdfs = pdfRepository.findAll();

        List<Pdf> searchResult = StreamSupport.stream(allPdfs.spliterator(), false)
                .filter(pdf -> isNullOrEmpty(title) || pdf.getTitle().contains(title))
                .filter(pdf -> isNullOrEmpty(author) || pdf.getAuthor().contains(author))
                .filter(pdf -> isNullOrEmpty(isbn) || pdf.getIsbn().equals(isbn))
                .filter(pdf -> isNullOrEmpty(collection) || pdf.getCollection().contains(collection))
                .filter(pdf -> datePublished == null || pdf.getDatePublished().equals(datePublished))
                .collect(Collectors.toList());

        return ResponseEntity.ok(searchResult);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/pdfupdate")
    public ResponseEntity<ResponseMessage> updatePdf(
            @RequestParam Integer id_pdf,
            @RequestParam String title,
            @RequestParam String translator,
            @RequestParam String collection,
            @RequestParam String author,
            @RequestParam Integer pagesNumber,
            @RequestParam Integer datePublished,
            @RequestParam String isbn,
            @RequestParam String edition,
            @RequestParam String documentLink) {
        Pdf existingPdf = pdfRepository.findById(id_pdf).orElse(null);

        if (existingPdf != null) {
            try {
                existingPdf.setTitle(title);
                existingPdf.setTranslator(translator);
                existingPdf.setCollection(collection);
                existingPdf.setAuthor(author);
                existingPdf.setPagesNumber(pagesNumber);
                existingPdf.setDatePublished(datePublished);
                existingPdf.setIsbn(isbn);
                existingPdf.setEdition(edition);
                existingPdf.setDocumentLink(documentLink);

                pdfRepository.save(existingPdf);

                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("PDF updated successfully"));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Failed to update PDF"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("PDF not found"));
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
