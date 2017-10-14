package com.botscrew.library.persistance.entities;

import com.botscrew.library.persistance.entities.domain.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "name")
    private String name;

    public Book(String name) {
        this.name = name;
    }

    public Book() {

    }

    public Book(Author author, String name2) {
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
