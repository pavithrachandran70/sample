package com.example.libraryservice.service;


import com.example.libraryservice.dto.LibraryDto;
import com.example.libraryservice.entity.Library;

import java.util.List;
import java.util.Optional;

public interface LibraryService {

    LibraryDto create(LibraryDto libraryDto);

    List<LibraryDto> findAll();

    LibraryDto findById(Long id);

    LibraryDto updateById(Long id, LibraryDto updatedLibraryDto);

    void deleteById(Long id);

    LibraryDto getLibraryWithBooks(Long id);
}
