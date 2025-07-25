package com.example.libraryservice.service;


import com.example.libraryservice.entity.Librarian;

import java.util.List;

public interface LibrarianService {
    Librarian create(Librarian librarian);
    List<Librarian> findAll();
    Librarian findById(Long id);
    List<Librarian> findByLibraryId(Long libraryId);
    Librarian update(Long id, Librarian updatedLibrarian);
    void delete(Long id);
}
