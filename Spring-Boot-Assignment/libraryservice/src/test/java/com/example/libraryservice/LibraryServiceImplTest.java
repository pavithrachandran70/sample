package com.example.libraryservice;


import com.example.libraryservice.dto.BookDto;
import com.example.libraryservice.dto.LibraryDto;

import com.example.libraryservice.entity.Library;
import com.example.libraryservice.exception.LibraryNotFoundException;
import com.example.libraryservice.repository.LibraryRepository;
import com.example.libraryservice.service.LibraryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibraryServiceImplTest {

//    @Mock
//    private LibraryRepository repository;
//
//     @Mock
//    private RestTemplate restTemplate;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @InjectMocks
//    private LibraryServiceImpl service;
//
//    private Library sampleLibrary;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        sampleLibrary = new Library(1L, "Central Library", "Chennai");
//    }
//
//    @Test
//    void testCreateLibrary() {
//        when(repository.save(sampleLibrary)).thenReturn(sampleLibrary);
//
//        Library result = service.create(sampleLibrary);
//
//        assertEquals(sampleLibrary, result);
//        verify(repository, times(1)).save(sampleLibrary);
//    }
//
//    @Test
//    void testGetAllLibraries() {
//        List<Library> list = Arrays.asList(
//                sampleLibrary,
//                new Library(2L, "City Library", "Bangalore")
//        );
//
//        when(repository.findAll()).thenReturn(list);
//
//        List<Library> result = service.findAll();
//
//        assertEquals(2, result.size());
//        verify(repository, times(1)).findAll();
//    }
//
//
//    @Test
//    void testGetLibraryById_Found() {
//        when(repository.findById(1L)).thenReturn(Optional.of(sampleLibrary));
//
//        Library result = service.findById(1L);
//
//        assertNotNull(result);
//        assertEquals("Central Library", result.getName());
//    }
//
//    @Test
//    void testGetLibraryById_NotFound() {
//        when(repository.findById(99L)).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(LibraryNotFoundException.class, () -> {
//            service.findById(99L);
//        });
//
//        assertEquals("Library not found with id: 99", exception.getMessage());
//    }
//
//
//    @Test
//    void testUpdateLibrary_Found() {
//        Library updated = new Library(1L, "Updated Library", "Delhi");
//
//        when(repository.findById(1L)).thenReturn(Optional.of(sampleLibrary));
//        when(repository.save(any(Library.class))).thenReturn(updated);
//
//        Library result = service.update(1L, updated);
//
//        assertNotNull(result);
//        assertEquals("Updated Library", result.getName());
//        verify(repository).save(sampleLibrary);
//    }
//
//    @Test
//    void testUpdateLibrary_NotFound() {
//        when(repository.findById(99L)).thenReturn(Optional.empty());
//
//        Library result = service.update(99L, sampleLibrary);
//
//        assertNull(result);
//    }
//
//    @Test
//    void testDeleteLibrary() {
//        doNothing().when(repository).deleteById(1L);
//
//        service.delete(1L);
//
//        verify(repository, times(1)).deleteById(1L);
//    }
//
//
////    @Test
////    void testGetLibraryWithBooks_LibraryNotFound() {
////        when(repository.findById(10L)).thenReturn(Optional.empty());
////
////        LibraryDto result = service.getLibraryWithBooks(10L);
////
////        assertNull(result);
////    }
//
//    @Test
//    void testGetLibraryWithBooks_Success() {
//        BookDto[] mockBooks = new BookDto[]{
//                new BookDto(101L, "Book A", "Author A", 200.0),
//                new BookDto(102L, "Book B", "Author B", 150.0)
//        };
//
//        when(repository.findById(1L)).thenReturn(Optional.of(sampleLibrary));
//        when(restTemplate.getForObject("http://localhost:8091/api/books/library/1", BookDto[].class))
//                .thenReturn(mockBooks);
//
//        LibraryDto result = service.getLibraryWithBooks(1L);
//
//        assertNotNull(result);
//        assertEquals(2, result.getBooks().size());
//        assertEquals("Central Library", result.getName());
//        verify(repository).findById(1L);
//        verify(restTemplate).getForObject(anyString(), eq(BookDto[].class));
//    }


@Mock
private LibraryRepository repository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private LibraryServiceImpl service;

    private Library sampleLibrary;

    private LibraryDto sampleLibraryDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleLibrary = new Library(1L, "Central Library", "Chennai");
        sampleLibraryDto = new LibraryDto(1L, "Central Library", "Chennai", null);
    }

    @Test
    void testCreateLibrary() {
        when(modelMapper.map(sampleLibraryDto, Library.class)).thenReturn(sampleLibrary);
        when(repository.save(sampleLibrary)).thenReturn(sampleLibrary);
        when(modelMapper.map(sampleLibrary, LibraryDto.class)).thenReturn(sampleLibraryDto);

        LibraryDto result = service.create(sampleLibraryDto);

        assertNotNull(result);
        assertEquals("Central Library", result.getName());
        verify(repository).save(sampleLibrary);
    }

    @Test
    void testGetAllLibraries() {
        Library library2 = new Library(2L, "City Library", "Bangalore");
        LibraryDto dto2 = new LibraryDto(2L, "City Library", "Bangalore", null);

        List<Library> list = Arrays.asList(sampleLibrary, library2);
        when(repository.findAll()).thenReturn(list);
        when(modelMapper.map(sampleLibrary, LibraryDto.class)).thenReturn(sampleLibraryDto);
        when(modelMapper.map(library2, LibraryDto.class)).thenReturn(dto2);

        List<LibraryDto> result = service.findAll();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void testGetLibraryById_Found() {
        when(repository.findById(1L)).thenReturn(Optional.of(sampleLibrary));
        when(modelMapper.map(sampleLibrary, LibraryDto.class)).thenReturn(sampleLibraryDto);

        LibraryDto result = service.findById(1L);

        assertNotNull(result);
        assertEquals("Central Library", result.getName());
    }

    @Test
    void testGetLibraryById_NotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(LibraryNotFoundException.class, () -> {
            service.findById(99L);
        });

        assertEquals("Library not found with id: 99", exception.getMessage());
    }

    @Test
    void testUpdateLibrary_Found() {
        LibraryDto updatedDto = new LibraryDto(1L, "Updated Library", "Delhi", null);
        Library updatedLibrary = new Library(1L, "Updated Library", "Delhi");

        when(repository.findById(1L)).thenReturn(Optional.of(sampleLibrary));
        when(repository.save(any(Library.class))).thenReturn(updatedLibrary);
        when(modelMapper.map(updatedLibrary, LibraryDto.class)).thenReturn(updatedDto);

        LibraryDto result = service.updateById(1L, updatedDto);

        assertNotNull(result);
        assertEquals("Updated Library", result.getName());
        verify(repository).save(sampleLibrary);
    }

    @Test
    void testUpdateLibrary_NotFound() {
        LibraryDto updatedDto = new LibraryDto(99L, "Doesn't Matter", "Nowhere", null);

        when(repository.findById(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(LibraryNotFoundException.class, () -> {
            service.updateById(99L, updatedDto);
        });

        assertEquals("Library not found with id: 99", exception.getMessage());
    }

    @Test
    void testDeleteLibrary() {
        doNothing().when(repository).deleteById(1L);

        service.deleteById(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void testGetLibraryWithBooks_Success() {
        BookDto[] mockBooks = new BookDto[]{
                new BookDto(101L, "Book A", "Author A", 200.0),
                new BookDto(102L, "Book B", "Author B", 150.0)
        };

        when(repository.findById(1L)).thenReturn(Optional.of(sampleLibrary));
        when(restTemplate.getForObject("http://localhost:8091/api/books/library/1", BookDto[].class))
                .thenReturn(mockBooks);

        LibraryDto result = service.getLibraryWithBooks(1L);

        assertNotNull(result);
        assertEquals(2, result.getBooks().size());
        assertEquals("Central Library", result.getName());

        verify(repository).findById(1L);
        verify(restTemplate).getForObject(anyString(), eq(BookDto[].class));
    }

    @Test
    void testGetLibraryWithBooks_LibraryNotFound() {
        when(repository.findById(10L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(LibraryNotFoundException.class, () -> {
            service.getLibraryWithBooks(10L);
        });

        assertEquals("Library not found with id: 10", exception.getMessage());
    }

}

