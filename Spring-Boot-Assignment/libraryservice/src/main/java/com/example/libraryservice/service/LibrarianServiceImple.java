package com.example.libraryservice.service;


import com.example.libraryservice.entity.Librarian;
import com.example.libraryservice.repository.LibrarianRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianServiceImple implements LibrarianService {

    private final LibrarianRepository repository;

    public LibrarianServiceImple(LibrarianRepository repository) {
        this.repository = repository;
    }

    @Override
    public Librarian create(Librarian librarian) {
        return repository.save(librarian);
    }

    @Override
    public List<Librarian> findAll() {
        return repository.findAll();
    }

    @Override
    public Librarian findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Librarian not found with id: " + id));
    }

    @Override
    public List<Librarian> findByLibraryId(Long libraryId) {
        return repository.findByLibraryId(libraryId);
    }

    @Override
    public Librarian update(Long id, Librarian updatedLibrarian) {
        return repository.findById(id).map(existing -> {
            existing.setName(updatedLibrarian.getName());
            existing.setEmail(updatedLibrarian.getEmail());
            existing.setLibraryId(updatedLibrarian.getLibraryId());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Librarian not found with id: " + id));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

