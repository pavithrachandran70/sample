package com.example.libraryservice.service;


import com.example.libraryservice.dto.LibraryDto;
import com.example.libraryservice.entity.Library;

import java.util.List;

public interface LibraryService {

    Library create(Library library);

    List<Library> findAll();

    Library findById(Long id);

    Library update(Long id, Library updatedLibrary);

    void delete(Long id);

    LibraryDto getLibraryWithBooks(Long id);
}
