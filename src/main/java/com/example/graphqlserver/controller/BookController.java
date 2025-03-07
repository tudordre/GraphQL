package com.example.graphqlserver.controller;

import com.example.graphqlserver.dto.Author;
import com.example.graphqlserver.dto.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/book")
public class BookController {
    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    @QueryMapping
    public List<Book> books() {
        return Book.getAll();
    }

    @MutationMapping
    public Book createBook(@Argument String name, @Argument int pageCount, @Argument String authorId) {
        return Book.addBook(name, pageCount, authorId);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.authorId());
    }
}