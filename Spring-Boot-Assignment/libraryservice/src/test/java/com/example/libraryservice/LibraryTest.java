package com.example.libraryservice;


import com.example.libraryservice.entity.Library;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

//    @Test
//    void testNoArgsConstructorAndSettersGetters() {
//        Library lib = new Library();
//        lib.setId(1L);
//        lib.setName("Public");
//        lib.setCity("Chennai");
//
//        assertEquals(1L, lib.getId());
//        assertEquals("Public", lib.getName());
//        assertEquals("Chennai", lib.getCity());
//    }
//
//    @Test
//    void testAllArgsConstructor() {
//        Library lib = new Library(2L, "Govt", "Hyderabad");
//        assertEquals(2L, lib.getId());
//        assertEquals("Govt", lib.getName());
//        assertEquals("Hyderabad", lib.getCity());
//    }
@Test
void testNoArgsConstructorAndSettersGetters() {
    Library lib = new Library(); // No-arg constructor

    lib.setId(1L);
    lib.setName("Public");
    lib.setCity("Chennai");

    assertEquals(1L, lib.getId());
    assertEquals("Public", lib.getName());
    assertEquals("Chennai", lib.getCity());
}

    @Test
    void testAllArgsConstructor() {
        Library lib = new Library(2L, "Govt", "Hyderabad");

        assertEquals(2L, lib.getId());
        assertEquals("Govt", lib.getName());
        assertEquals("Hyderabad", lib.getCity());
    }

    @Test
    void testModifyAfterAllArgsConstructor() {
        Library lib = new Library(3L, "Uni Library", "Bangalore");

        lib.setName("Updated Name");
        lib.setCity("Updated City");
        lib.setId(99L);

        assertEquals(99L, lib.getId());
        assertEquals("Updated Name", lib.getName());
        assertEquals("Updated City", lib.getCity());
    }
}
