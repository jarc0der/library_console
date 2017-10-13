package com.botscrew.persistance;

import com.botscrew.library.persistance.entities.Author;
import com.botscrew.library.utils.MockDB;
import com.botscrew.library.persistance.entities.Book;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MockBookDBTest {

    private MockDB mockDB;

    @Before
    public void setUp(){
        this.mockDB = new MockDB();
        mockDB.addBook(new Book(new Author(), "name1"));
        mockDB.addBook(new Book(new Author(), "name2"));
    }


    @Test
    public void getBooksTest(){

        assertEquals(mockDB.getAllBooks().size(), 2);
    }

    @Test
    public void editBookNameTest(){

        Book testBook = mockDB.getAllBooks().get(0);

        mockDB.editBook("name3", testBook);

        assertTrue(mockDB.getAllBooks().get(0).getName().equals("name3"));
    }

    @Test
    public void removeBookByNameTest(){
        mockDB.removeBook(mockDB.getAllBooks().get(0));

        assertEquals(mockDB.getAllBooks().size(), 1);
    }

}
