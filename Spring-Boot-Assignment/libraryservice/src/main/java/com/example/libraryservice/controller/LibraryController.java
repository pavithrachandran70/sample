//package com.example.libraryservice.controller;
//
//
//import com.example.libraryservice.dto.LibraryDto;
//import com.example.libraryservice.entity.Library;
//import com.example.libraryservice.service.LibraryService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/libraries")
//public class LibraryController {
//
////    @Autowired
////    private LibraryService service;
////
////    @PostMapping
////    public Library create(@RequestBody Library library) {
////        return service.create(library);
////    }
////
////
////    @GetMapping
////    public List<Library> findAll() {
////        return service.findAll();
////    }
////
////    @GetMapping("/{id}")
////    public Library getAll(@PathVariable Long id) {
////        return service.findById(id);
////    }
////
//////    @GetMapping("/{id}/with-books", produces = MediaType.APPLICATION_JSON_VALUE)
////     @GetMapping(value = "/{id}/with-books", produces = MediaType.APPLICATION_JSON_VALUE)
////    public LibraryDto getWithBooks(@PathVariable Long id) {
////        return service.getLibraryWithBooks(id);
////    }
////
////
////
////    @PutMapping("/{id}")
////    public Library update(@PathVariable Long id, @RequestBody Library updatedLibrary) {
////        return service.update(id, updatedLibrary);
////    }
////
////    @DeleteMapping("/{id}")
////    public void delete(@PathVariable Long id) {
////        service.delete(id);
////    }
//@Autowired
//private LibraryService service;
//
//    @PostMapping
//    public Library add(@RequestBody Library library) {
//        return service.create(library);
//    }
//
//    @GetMapping
//    public List<Library> list() {
//        return service.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public Library get(@PathVariable Long id) {
//        return service.findById(id);
//    }
//
//    @PutMapping("/{id}")
//    public Library update(@PathVariable Long id, @RequestBody Library updatedLibrary) {
//        return service.update(id, updatedLibrary);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        service.delete(id);
//    }
//
//    @GetMapping("/{id}/with-books")
//    public LibraryDto getWithBooks(@PathVariable Long id) {
//        return service.getLibraryWithBooks(id);
//    }
//}

package com.example.libraryservice.controller;


import com.example.libraryservice.dto.LibraryDto;
import com.example.libraryservice.entity.Library;
import com.example.libraryservice.service.LibraryService;
import com.example.libraryservice.service.LibraryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {

    @Autowired
    private LibraryService service;

    @PostMapping
    public Library create(@RequestBody Library library) {
        return service.create(library);
    }


    @GetMapping
    public List<Library> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Library getById(@PathVariable Long id) {
        return service.findById(id);
    }

    //    @GetMapping("/{id}/with-books", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/{id}/with-books", produces = MediaType.APPLICATION_JSON_VALUE)
    public LibraryDto getWithBooks(@PathVariable Long id) {
        return service.getLibraryWithBooks(id);
    }



    @PutMapping("/{id}")
    public Library update(@PathVariable Long id, @RequestBody Library updatedLibrary) {
        return service.update(id, updatedLibrary);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}