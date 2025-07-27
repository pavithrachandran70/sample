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

    private LibraryDto sampleLibraryDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleLibraryDto = new LibraryDto(1L, "Central", "Bangalore", List.of());
    }

    @Test
    void testCreate() {
        when(service.create(any(LibraryDto.class))).thenReturn(sampleLibraryDto);

        LibraryDto result = controller.create(sampleLibraryDto);

        assertNotNull(result);
        assertEquals("Central", result.getName());
        verify(service).create(sampleLibraryDto);
    }

    @Test
    void testGetAll() {
        when(service.findAll()).thenReturn(List.of(sampleLibraryDto));

        List<LibraryDto> result = controller.getAll();

        assertEquals(1, result.size());
        assertEquals("Central", result.get(0).getName());
        verify(service).findAll();
    }

    @Test
    void testGetById() {
        when(service.findById(1L)).thenReturn(sampleLibraryDto);

        LibraryDto result = controller.getById(1L);

        assertEquals("Central", result.getName());
        verify(service).findById(1L);
    }

    @Test
    void testUpdate() {
        LibraryDto updatedDto = new LibraryDto(1L, "Updated", "Mumbai", List.of());
        when(service.updateById(eq(1L), any(LibraryDto.class))).thenReturn(updatedDto);

        LibraryDto result = controller.updateById(1L, updatedDto);

        assertEquals("Updated", result.getName());
        verify(service).updateById(1L, updatedDto);
    }

    @Test
    void testDelete() {
        doNothing().when(service).deleteById(1L);

        controller.deleteById(1L);

        verify(service).deleteById(1L);
    }

    @Test
    void testGetWithBooks() {
        when(service.getLibraryWithBooks(1L)).thenReturn(sampleLibraryDto);

        LibraryDto result = controller.getWithBooks(1L);

        assertEquals("Central", result.getName());
        verify(service).getLibraryWithBooks(1L);
    }
}