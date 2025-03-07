package com.example.graphqlserver.controller;

import com.example.graphqlserver.dto.Book;
import com.example.graphqlserver.service.GraphQLCallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    GraphQLCallerService graphQLCallerService;

    @GetMapping
    public ResponseEntity<List<Book>> allBooks() {
        return ResponseEntity.ok(graphQLCallerService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> allBooks(@PathVariable String id) {
        return ResponseEntity.ok(graphQLCallerService.getBookById(id));
    }
}
