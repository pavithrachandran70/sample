package com.example.bookservice;

import com.example.bookservice.dto.BookDTO;
import com.example.bookservice.entity.Book;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.service.BookService;
import org.junit.jupiter.api.BeforeEach;
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
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {


    private BookRepository mockRepo;
    private ModelMapper mockMapper;
    private BookService service;

    @BeforeEach
    void setUp() {
        mockRepo = mock(BookRepository.class);
        mockMapper = mock(ModelMapper.class);
        service = new BookServiceImpl(mockRepo, mockMapper);
    }

    @Test
    void testSaveBook() {
        Book book = new Book(null, "Java", "Author", 300);
        BookDTO dto = new BookDTO();
        dto.setTitle("Java");

        when(mockRepo.save(book)).thenReturn(book);
        when(mockMapper.map(book, BookDTO.class)).thenReturn(dto);

        BookDTO result = service.save(book);
        assertEquals("Java", result.getTitle());
    }

    @Test
    void testFindAllBooks() {
        Book book = new Book(1L, "Java", "Author", 300);
        BookDTO dto = new BookDTO();
        dto.setTitle("Java");

        when(mockRepo.findAll()).thenReturn(List.of(book));
        when(mockMapper.map(book, BookDTO.class)).thenReturn(dto);

        List<BookDTO> result = service.findAll();
        assertEquals(1, result.size());
        assertEquals("Java", result.get(0).getTitle());
    }

    @Test
    void testFindById_Found() {
        Book book = new Book(1L, "Java", "Author", 300);
        BookDTO dto = new BookDTO();
        dto.setTitle("Java");

        when(mockRepo.findById(1L)).thenReturn(Optional.of(book));
        when(mockMapper.map(book, BookDTO.class)).thenReturn(dto);

        BookDTO result = service.findById(1L);
        assertEquals("Java", result.getTitle());
    }

    @Test
    void testFindById_NotFound_ExceptionThrown() {
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
        BookDTO dto = new BookDTO();
        dto.setTitle("New Title");

        when(mockRepo.findById(1L)).thenReturn(Optional.of(oldBook));
        when(mockRepo.save(any(Book.class))).thenReturn(oldBook);
        when(mockMapper.map(oldBook, BookDTO.class)).thenReturn(dto);

        BookDTO result = service.updateById(1L, updatedBook);
        assertEquals("New Title", result.getTitle());
    }

    @Test
    void testUpdate_NotFound_ExceptionThrown() {
        Book updatedBook = new Book(null, "New Title", "New Author", 200);
        when(mockRepo.findById(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            service.updateById(99L, updatedBook);
        });

        assertEquals("Cannot update. Book not found with id: 99", exception.getMessage());
    }

    @Test
    void testDeleteBook() {
        doNothing().when(mockRepo).deleteById(1L);
        service.deleteById(1L);
        verify(mockRepo, times(1)).deleteById(1L);
    }

    @Test
    void testGetBooksByLibraryId() {
        Long libraryId = 10L;
        Book book = new Book(1L, "Java", "Author", 200);
        BookDTO dto = new BookDTO();
        dto.setTitle("Java");

        when(mockRepo.findByLibraryId(libraryId)).thenReturn(List.of(book));
        when(mockMapper.map(book, BookDTO.class)).thenReturn(dto);

        List<BookDTO> result = service.getBooksByLibraryId(libraryId);
        assertEquals(1, result.size());
        assertEquals("Java", result.get(0).getTitle());
    }

}
