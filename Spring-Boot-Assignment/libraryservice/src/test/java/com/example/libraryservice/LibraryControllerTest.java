package com.example.libraryservice;


import com.example.libraryservice.controller.LibraryController;
import com.example.libraryservice.dto.LibraryDto;
import com.example.libraryservice.entity.Library;
import com.example.libraryservice.service.LibraryService;
import com.example.libraryservice.service.LibraryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LibraryControllerTest {

    @InjectMocks
    private LibraryController controller;

    @Mock
    private LibraryService service;

    private Library sampleLibrary;
    @MockBean
    private RestTemplate restTemplate;

    //setting the environment
    @BeforeEach
    void setUp() {
        //initializes any @Mock, @InjectMocks, etc., declared in the test class.
        MockitoAnnotations.openMocks(this);
        // test object being created before each test.
        sampleLibrary = new Library(1L, "Central", "Bangalore");
    }

    @Test
    void testCreate() {
        //If service.create() is called with any Library object, then return sampleLibrary.”
        when(service.create(any(Library.class))).thenReturn(sampleLibrary);
        //calling the create method on your controller and passing sampleLibrary
        Library result = controller.create(sampleLibrary);
        assertNotNull(result);
        assertEquals("Central", result.getName());
        //Verifies that the service.create() method was actually called once with sampleLibrary as the argument.
        verify(service).create(sampleLibrary);
    }

    @Test
    void testGetAll() {
        when(service.findAll()).thenReturn(List.of(sampleLibrary));
        List<Library> libraries = controller.getAll();
        assertEquals(1, libraries.size());
        //Verifies that the findAll() method of the service was actually called.
        verify(service).findAll();
    }

    @Test
    void testGetById() {
        when(service.findById(1L)).thenReturn(sampleLibrary);
        Library result = controller.getById(1L);
        assertEquals("Central", result.getName());
        verify(service).findById(1L);
    }

    @Test
    void testGetWithBooks() {
        // create a sample LibraryDto object
        LibraryDto dto = new LibraryDto(1L, "Central", "Bangalore", List.of());
        when(service.getLibraryWithBooks(1L)).thenReturn(dto);
        LibraryDto result = controller.getWithBooks(1L);
        assertEquals("Central", result.getName());
        // verifies that the service method was called exactly once with 1L as the argument.
        verify(service).getLibraryWithBooks(1L);
    }

    @Test
    void testUpdate() {
        Library updated = new Library(1L, "Updated", "Mumbai");
        when(service.update(eq(1L), any(Library.class))).thenReturn(updated);
        Library result = controller.update(1L, updated);
        assertEquals("Updated", result.getName());
        verify(service).update(1L, updated);
    }

    @Test
    void testDelete() {
        doNothing().when(service).delete(1L);
        controller.delete(1L);
        verify(service).delete(1L);
    }
}
