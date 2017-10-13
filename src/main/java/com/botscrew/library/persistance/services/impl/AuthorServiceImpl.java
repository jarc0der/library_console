package com.botscrew.library.persistance.services.impl;

import com.botscrew.library.persistance.entities.Author;
import com.botscrew.library.persistance.repository.AuthorRepository;
import com.botscrew.library.persistance.services.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository = new AuthorRepository();

    @Override
    public void addAuthor(Author author) {
        authorRepository.add(author);
    }

    @Override
    public Author getLastAddedAuthor() {
       return authorRepository.getLast();
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.getAll();
    }
}
