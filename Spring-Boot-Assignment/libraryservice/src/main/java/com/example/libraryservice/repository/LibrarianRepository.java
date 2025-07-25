package com.example.libraryservice.repository;


import com.example.libraryservice.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibrarianRepository extends JpaRepository<Librarian, Long> {

    //fin
    List<Librarian> findByLibraryId(Long libraryId);
}
