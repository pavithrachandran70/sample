package com.example.bookservice;

import com.example.bookservice.entity.Book;
import com.example.bookservice.repository.BookRepository;
import org.junit.jupiter.api.Test;


import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.example.bookservice.service.BookServiceImpl;
public class BookServiceTest {

    //Creates a mock (fake) version of BookRepository using Mockito.
    BookRepository mockRepo = mock(BookRepository.class);
    //Creates an instance of BookService and injects the mock repository into it.
    BookServiceImpl service = new BookServiceImpl(mockRepo); // Inject mock repo

    @Test
    void testSaveBook() {

        Book book = new Book(null, "Java", "Author", 300);
        when(mockRepo.save(book)).thenReturn(book);

        Book result = service.save(book);
        assertEquals("Java", result.getTitle());
    }

    @Test
    void testGetAllBooks() {
        when(mockRepo.findAll()).thenReturn(Collections.emptyList());
        assertTrue(service.getAll().isEmpty());
    }
}
