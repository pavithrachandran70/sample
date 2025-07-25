package com.example.bookservice;



import com.example.bookservice.controller.BookController;
import com.example.bookservice.dto.BookDTO;
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

//    @InjectMocks
//    private BookController bookController;
//
//    @Mock
//    private BookService bookService;
//
//    private Book sampleBook;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        sampleBook = new Book(1L, "Java Basics", "Author A", 500.0);
//        sampleBook.setLibraryId(10L);
//    }
//
//    @Test
//    void testAddBook() {
//        when(bookService.save(any(Book.class))).thenReturn(sampleBook);
//
//        Book result = bookController.add(sampleBook);
//
//        assertEquals("Java Basics", result.getTitle());
//        verify(bookService).save(sampleBook);
//    }
//
//    @Test
//    void testListBooks() {
//        List<Book> books = Arrays.asList(sampleBook);
//        when(bookService.findAll()).thenReturn(books);
//
//        List<Book> result = bookController.list();
//
//        assertEquals(1, result.size());
//        assertEquals("Java Basics", result.get(0).getTitle());
//    }
//
//    @Test
//    void testGetBookById() {
//        when(bookService.findById(1L)).thenReturn(sampleBook);
//
//        Book result = bookController.get(1L);
//
//        assertEquals(1L, result.getId());
//    }
//
//    @Test
//    void testUpdateBook() {
//        when(bookService.updateById(eq(1L), any(Book.class))).thenReturn(sampleBook);
//
//        Book result = bookController.update(1L, sampleBook);
//
//        assertNotNull(result);
//        assertEquals("Java Basics", result.getTitle());
//        verify(bookService).updateById(1L, sampleBook);
//    }
//
//    @Test
//    void testDeleteBook() {
//        doNothing().when(bookService).deleteById(1L);
//
//        bookController.deleteById(1L);
//
//        verify(bookService).deleteById(1L);
//    }
//
//    @Test
//    void testGetBooksByLibraryId() {
//        List<Book> books = List.of(sampleBook);
//        when(bookService.getBooksByLibraryId(10L)).thenReturn(books);
//
//        List<Book> result = bookController.getBooksByLibraryId(10L);
//
//        assertEquals(1, result.size());
//        assertEquals(10L, result.get(0).getLibraryId());
//    }

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    private BookDTO sampleBookDTO;

    @BeforeEach
    void setUp() {
        sampleBookDTO = new BookDTO();
        sampleBookDTO.setId(1L);
        sampleBookDTO.setTitle("Java Basics");
        sampleBookDTO.setAuthor("Author A");
        sampleBookDTO.setPrice(500.0);
        sampleBookDTO.setLibraryId(10L);
    }

    @Test
    void testCreateBook() {
        when(bookService.save(any())).thenReturn(sampleBookDTO);

        BookDTO result = bookController.create(new com.example.bookservice.entity.Book());

        assertEquals("Java Basics", result.getTitle());
        verify(bookService).save(any());
    }

    @Test
    void testGetAllBooks() {
        List<BookDTO> books = Arrays.asList(sampleBookDTO);
        when(bookService.findAll()).thenReturn(books);

        List<BookDTO> result = bookController.getAllBooks();

        assertEquals(1, result.size());
        assertEquals("Java Basics", result.get(0).getTitle());
    }

    @Test
    void testGetBookById() {
        when(bookService.findById(1L)).thenReturn(sampleBookDTO);

        BookDTO result = bookController.getById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void testUpdateBookById() {
        when(bookService.updateById(eq(1L), any())).thenReturn(sampleBookDTO);

        BookDTO result = bookController.updateById(1L, new com.example.bookservice.entity.Book());

        assertNotNull(result);
        assertEquals("Java Basics", result.getTitle());
        verify(bookService).updateById(eq(1L), any());
    }

    @Test
    void testDeleteBookById() {
        doNothing().when(bookService).deleteById(1L);

        bookController.deleteById(1L);

        verify(bookService).deleteById(1L);
    }

    @Test
    void testGetBooksByLibraryId() {
        List<BookDTO> books = List.of(sampleBookDTO);
        when(bookService.getBooksByLibraryId(10L)).thenReturn(books);

        List<BookDTO> result = bookController.getBooksByLibraryId(10L);

        assertEquals(1, result.size());
        assertEquals(10L, result.get(0).getLibraryId());
    }

    @Test
    void testSearchBooksByTitleAndPrice() {
        List<BookDTO> books = List.of(sampleBookDTO);
        when(bookService.findByTitleAndPriceRange("Java", 100.0, 600.0)).thenReturn(books);

        List<BookDTO> result = bookController.searchBooksByTitleAndPrice("Java", 100.0, 600.0);

        assertEquals(1, result.size());
        assertEquals("Java Basics", result.get(0).getTitle());
    }

    @Test
    void testSearchBookByTitleAndAuthor() {
        when(bookService.findByTitleAndAuthor("Java Basics", "Author A")).thenReturn(sampleBookDTO);

        BookDTO result = bookController.searchBookByTitleAndAuthor("Java Basics", "Author A");

        assertEquals("Author A", result.getAuthor());
        assertEquals("Java Basics", result.getTitle());
    }
}
