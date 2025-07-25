package com.example.libraryservice.service;


import com.example.libraryservice.dto.LibrarianDto;
import com.example.libraryservice.entity.Librarian;
import com.example.libraryservice.repository.LibrarianRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianServiceImple implements LibrarianService {

//    private final LibrarianRepository librarianRepository;
//
//    public LibrarianServiceImple(LibrarianRepository repository) {
//
//        this.librarianRepository = repository;
//    }
//
//    @Override
//    public Librarian create(Librarian librarian) {
//
//        return librarianRepository.save(librarian);
//    }
//
//    @Override
//    public List<Librarian> findAll() {
//        return librarianRepository.findAll();
//    }
//
//    @Override
//    public Librarian findById(Long id) {
//        return librarianRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Librarian not found with id: " + id));
//    }
//
//    @Override
//    public List<Librarian> findByLibraryId(Long libraryId) {
//        return librarianRepository.findByLibraryId(libraryId);
//    }
//
//    @Override
//    public Librarian updateById(Long id, Librarian updatedLibrarian) {
//        return librarianRepository.findById(id).map(existing -> {
//            existing.setName(updatedLibrarian.getName());
//            existing.setEmail(updatedLibrarian.getEmail());
//            existing.setLibraryId(updatedLibrarian.getLibraryId());
//            return librarianRepository.save(existing);
//        }).orElseThrow(() -> new RuntimeException("Librarian not found with id: " + id));
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        librarianRepository.deleteById(id);
//    }


    private final LibrarianRepository librarianRepository;

    private final ModelMapper modelMapper;

    public LibrarianServiceImple(LibrarianRepository repository, ModelMapper modelMapper) {
        this.librarianRepository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LibrarianDto create(LibrarianDto dto) {
        Librarian librarian = modelMapper.map(dto, Librarian.class);
        return modelMapper.map(librarianRepository.save(librarian), LibrarianDto.class);
    }

    @Override
    public List<LibrarianDto> findAll() {
        return librarianRepository.findAll().stream()
                .map(lib -> modelMapper.map(lib, LibrarianDto.class))
                .toList();
    }

    @Override
    public LibrarianDto findById(Long id) {
        Librarian librarian = librarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Librarian not found with id: " + id));
        return modelMapper.map(librarian, LibrarianDto.class);
    }

    @Override
    public List<LibrarianDto> findByLibraryId(Long libraryId) {
        return librarianRepository.findByLibraryId(libraryId).stream()
                .map(lib -> modelMapper.map(lib, LibrarianDto.class))
                .toList();
    }

    @Override
    public LibrarianDto updateById(Long id, LibrarianDto dto) {
        Librarian updated = librarianRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setEmail(dto.getEmail());
            existing.setLibraryId(dto.getLibraryId());
            return librarianRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Librarian not found with id: " + id));
        return modelMapper.map(updated, LibrarianDto.class);
    }

    @Override
    public void deleteById(Long id) {
        librarianRepository.deleteById(id);
    }
}

