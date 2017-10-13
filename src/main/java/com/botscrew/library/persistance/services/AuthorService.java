package com.botscrew.library.persistance.services;

import com.botscrew.library.persistance.entities.Author;

import java.util.List;

public interface AuthorService {

    void addAuthor(Author author);

     Author getLastAddedAuthor();

    List<Author> getAllAuthors();
}
