package com.botscrew.library.persistance.services.impl;

import com.botscrew.library.persistance.entities.Author;
import com.botscrew.library.persistance.repository.AuthorRepository;
import com.botscrew.library.persistance.services.AuthorService;

import java.util.List;
import java.util.Optional;

public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository = new AuthorRepository();

    @Override
    public void addAuthor(Author author) {
        authorRepository.add(author);
    }

    @Override
    public Author getLastAddedAuthor() {
        List<Author> authorList = getAllAuthors();

        Optional<Author> author = authorList.stream().skip(authorList.size() - 1).findAny();

        return author.orElse(new Author("Unknown"));
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.getAll();
    }
}
