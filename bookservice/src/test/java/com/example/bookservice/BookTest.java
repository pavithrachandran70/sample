package com.example.bookservice;


import com.example.bookservice.entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookTest {

    @Test
    void testAllFieldsAndConstructor() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        book.setPrice(599.0);
        book.setLibraryId(2L);

        assertEquals(1L, book.getId());
        assertEquals("Effective Java", book.getTitle());
        assertEquals("Joshua Bloch", book.getAuthor());
        assertEquals(599.0, book.getPrice());
        assertEquals(2L, book.getLibraryId());
    }

    @Test
    void testParameterizedConstructor() {
        Book book = new Book(1L, "Clean Code", "Robert C. Martin", 750.0);
        book.setLibraryId(5L);

        assertEquals(1L, book.getId());
        assertEquals("Clean Code", book.getTitle());
        assertEquals("Robert C. Martin", book.getAuthor());
        assertEquals(750.0, book.getPrice());
        assertEquals(5L, book.getLibraryId());
    }
}
