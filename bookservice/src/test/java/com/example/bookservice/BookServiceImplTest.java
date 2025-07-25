package com.example.bookservice;

import com.example.bookservice.entity.Book;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.service.BookService;
import org.junit.jupiter.api.Test;
import com.example.bookservice.exception.BookNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.example.bookservice.service.BookServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {
//
//    //Creates a mock (fake) version of BookRepository using Mockito.
//    BookRepository mockRepo = mock(BookRepository.class);
//    //Creates an instance of BookService and injects the mock repository into it.
//    BookService service = new BookServiceImpl(mockRepo); // Inject mock repo
//
//    @Test
//    void testSaveBook() {
//
//        Book book = new Book(null, "Java", "Author", 300);
//        when(mockRepo.save(book)).thenReturn(book);
//
//        Book result = service.save(book);
//        assertEquals("Java", result.getTitle());
//    }
//
//    @Test
//    void testfindAllBooks() {
//
//        //If mockRepo.findAll() is called, return an empty list."
//        when(mockRepo.findAll()).thenReturn(Collections.emptyList());
//        // actually returns an empty list.
//        assertTrue(service.findAll().isEmpty());
//    }
//
//    @Test
//    void testfindById_Found() {
//
//        Book book = new Book(1L, "Java", "Author", 300);
//        when(mockRepo.findById(1L)).thenReturn(Optional.of(book));
//
//        Book result = service.findById(1L);
//        //Checks that the result is not null — meaning a book was found.
//        assertNotNull(result);
//        //Checks that the title of the result is "Java" — confirming that the correct book was returned.
//        assertEquals("Java", result.getTitle());
//    }
//
//    @Test
//    void testGetById_NotFound() {
//        when(mockRepo.findById(2L)).thenReturn(Optional.empty());
//
//        Book result = service.findById(2L);
//        assertNull(result);
//    }
//
//    @Test
//    void testUpdate_Found() {
//        Book oldBook = new Book(1L, "Old Title", "Old Author", 100);
//        Book updatedBook = new Book(null, "New Title", "New Author", 200);
//
//        when(mockRepo.findById(1L)).thenReturn(Optional.of(oldBook));
//        // returns the saved book
//        //Whenever save() is called with any book, just return that book back
//        //i.getArgument(0) fetches the first argument passed to save() (i.e., the updated book).
//        when(mockRepo.save(any(Book.class))).thenAnswer(i -> i.getArgument(0));
//
//        //This calls your BookServiceImpl.update() method with ID 1 and the new book details.
//        Book result = service.update(1L, updatedBook);
//        assertNotNull(result);
//        // // New title set correctly
//        assertEquals("New Title", result.getTitle());
//        //// New author set correctly
//        assertEquals("New Author", result.getAuthor());
//        //// New price set correctly
//        assertEquals(200, result.getPrice());
//    }
//
//    @Test
//    void testUpdate_NotFound() {
//        Book updatedBook = new Book(null, "New Title", "New Author", 200);
//        when(mockRepo.findById(99L)).thenReturn(Optional.empty());
//
//        Book result = service.update(99L, updatedBook);
//        assertNull(result);
//    }
//
//    @Test
//    void testDeleteBook() {
//        Long bookId = 1L;
//        //does not return anything.
//        doNothing().when(mockRepo).deleteById(bookId);
//
//        service.delete(bookId);
//        verify(mockRepo, times(1)).deleteById(bookId);
//    }
//
//    @Test
//    void testGetBooksByLibraryId() {
//        Long libraryId = 10L;
//        List<Book> books = List.of(new Book(1L, "Java", "Author", 200));
//        when(mockRepo.findByLibraryId(libraryId)).thenReturn(books);
//
//        List<Book> result = service.getBooksByLibraryId(libraryId);
//        assertEquals(1, result.size());
//        assertEquals("Java", result.get(0).getTitle());
//    }
BookRepository mockRepo = mock(BookRepository.class);
    BookService service = new BookServiceImpl(mockRepo);

    @Test
    void testSaveBook() {
        Book book = new Book(null, "Java", "Author", 300);
        when(mockRepo.save(book)).thenReturn(book);

        Book result = service.save(book);
        assertEquals("Java", result.getTitle());
    }

    @Test
    void testfindAllBooks() {
        when(mockRepo.findAll()).thenReturn(Collections.emptyList());
        assertTrue(service.findAll().isEmpty());
    }

    @Test
    void testfindById_Found() {
        Book book = new Book(1L, "Java", "Author", 300);
        when(mockRepo.findById(1L)).thenReturn(Optional.of(book));

        Book result = service.findById(1L);
        assertNotNull(result);
        assertEquals("Java", result.getTitle());
    }

    @Test
    void testfindById_NotFound_ExceptionThrown() {
        when(mockRepo.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            service.findById(2L);
        });
        assertEquals("Book not found with id: 2", exception.getMessage());
    }

    @Test
    void testUpdate_Found() {
        Book oldBook = new Book(1L, "Old Title", "Old Author", 100);
        Book updatedBook = new Book(null, "New Title", "New Author", 200);

        when(mockRepo.findById(1L)).thenReturn(Optional.of(oldBook));
        when(mockRepo.save(any(Book.class))).thenAnswer(i -> i.getArgument(0));

        Book result = service.update(1L, updatedBook);
        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
        assertEquals("New Author", result.getAuthor());
        assertEquals(200, result.getPrice());
    }

    @Test
    void testUpdate_NotFound_ExceptionThrown() {
        Book updatedBook = new Book(null, "New Title", "New Author", 200);
        when(mockRepo.findById(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            service.update(99L, updatedBook);
        });
        assertEquals("Cannot update. Book not found with id: 99", exception.getMessage());
    }

    @Test
    void testDeleteBook() {
        Long bookId = 1L;
        doNothing().when(mockRepo).deleteById(bookId);

        service.delete(bookId);
        verify(mockRepo, times(1)).deleteById(bookId);
    }

    @Test
    void testGetBooksByLibraryId() {
        Long libraryId = 10L;
        List<Book> books = List.of(new Book(1L, "Java", "Author", 200));
        when(mockRepo.findByLibraryId(libraryId)).thenReturn(books);

        List<Book> result = service.getBooksByLibraryId(libraryId);
        assertEquals(1, result.size());
        assertEquals("Java", result.get(0).getTitle());
    }

}
