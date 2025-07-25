package com.example.libraryservice.service;


import com.example.libraryservice.dto.LibrarianDto;
import com.example.libraryservice.entity.Librarian;

import java.util.List;

public interface LibrarianService {
    LibrarianDto create(LibrarianDto librarianDto);
    List<LibrarianDto> findAll();
    LibrarianDto findById(Long id);
    List<LibrarianDto> findByLibraryId(Long libraryId);
    LibrarianDto updateById(Long id, LibrarianDto updatedLibrarianDto);
    void deleteById(Long id);
}
