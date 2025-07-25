package com.example.libraryservice.controller;


import com.example.libraryservice.dto.LibrarianDto;
import com.example.libraryservice.entity.Librarian;
import com.example.libraryservice.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/librarians")
public class LibrarianController {

//    @Autowired
//    private LibrarianService service;
//
//    @PostMapping
//    public Librarian create(@RequestBody Librarian librarian) {
//
//        return service.create(librarian);
//    }
//
//    @GetMapping
//    public List<Librarian> getAll() {
//
//        return service.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public Librarian getById(@PathVariable Long id) {
//
//        return service.findById(id);
//    }
//
//    @GetMapping("/library/{libraryId}")
//    public List<Librarian> getByLibraryId(@PathVariable Long libraryId) {
//
//        return service.findByLibraryId(libraryId);
//    }
//
//    @PutMapping("/{id}")
//    public Librarian updateById(@PathVariable Long id, @RequestBody Librarian librarian) {
//
//        return service.updateById(id, librarian);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteById(@PathVariable Long id) {
//
//        service.deleteById(id);
//    }
@Autowired
     private LibrarianService service;

    @PostMapping
    public LibrarianDto create(@RequestBody LibrarianDto librarianDto) {
        return service.create(librarianDto);
    }

    @GetMapping
    public List<LibrarianDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public LibrarianDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/library/{libraryId}")
    public List<LibrarianDto> getByLibraryId(@PathVariable Long libraryId) {
        return service.findByLibraryId(libraryId);
    }

    @PutMapping("/{id}")
    public LibrarianDto updateById(@PathVariable Long id, @RequestBody LibrarianDto librarianDto) {
        return service.updateById(id, librarianDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}

