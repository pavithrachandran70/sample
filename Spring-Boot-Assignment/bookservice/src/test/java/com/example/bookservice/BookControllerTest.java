package com.example.bookservice;



import com.example.bookservice.controller.BookController;
import com.example.bookservice.entity.Book;
import com.example.bookservice.service.BookService;
import com.example.bookservice.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    private Book sampleBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleBook = new Book(1L, "Java Basics", "Author A", 500.0);
        sampleBook.setLibraryId(10L);
    }

    @Test
    void testAddBook() {
        when(bookService.save(any(Book.class))).thenReturn(sampleBook);

        Book result = bookController.add(sampleBook);

        assertEquals("Java Basics", result.getTitle());
        verify(bookService).save(sampleBook);
    }

    @Test
    void testListBooks() {
        List<Book> books = Arrays.asList(sampleBook);
        when(bookService.findAll()).thenReturn(books);

        List<Book> result = bookController.list();

        assertEquals(1, result.size());
        assertEquals("Java Basics", result.get(0).getTitle());
    }

    @Test
    void testGetBookById() {
        when(bookService.findById(1L)).thenReturn(sampleBook);

        Book result = bookController.get(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void testUpdateBook() {
        when(bookService.update(eq(1L), any(Book.class))).thenReturn(sampleBook);

        Book result = bookController.update(1L, sampleBook);

        assertNotNull(result);
        assertEquals("Java Basics", result.getTitle());
        verify(bookService).update(1L, sampleBook);
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookService).delete(1L);

        bookController.delete(1L);

        verify(bookService).delete(1L);
    }

    @Test
    void testGetBooksByLibraryId() {
        List<Book> books = List.of(sampleBook);
        when(bookService.getBooksByLibraryId(10L)).thenReturn(books);

        List<Book> result = bookController.getBooksByLibraryId(10L);

        assertEquals(1, result.size());
        assertEquals(10L, result.get(0).getLibraryId());
    }
}
