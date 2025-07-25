package com.example.libraryservice.repository;


import com.example.libraryservice.dto.BookDto;
import com.example.libraryservice.dto.LibraryDto;
import com.example.libraryservice.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Long> {


}
