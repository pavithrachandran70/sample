package com.example.libraryservice.service;

import com.example.libraryservice.dto.BookDto;
import com.example.libraryservice.dto.LibraryDto;
import com.example.libraryservice.entity.Library;
import com.example.libraryservice.exception.LibraryNotFoundException;
import com.example.libraryservice.repository.LibraryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {


    private LibraryRepository libraryRepository;

    private ModelMapper modelMapper;

    private RestTemplate restTemplate;

    public LibraryServiceImpl(LibraryRepository repo, ModelMapper modelMapper, RestTemplate restTemplate) {
        this.libraryRepository = repo;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
    }


    //Base URL to call the external Book Service.
    //Appends library ID to this URL to fetch books belonging to a specific library.
    private final String BOOK_SERVICE_BASE_URL = "http://localhost:8091/api/books/library/";

   @Override
    public LibraryDto create(LibraryDto libraryDto) {
    Library library = modelMapper.map(libraryDto, Library.class);
    return modelMapper.map(libraryRepository.save(library), LibraryDto.class);
}


    @Override
    public List<LibraryDto> findAll() {
        return libraryRepository.findAll().stream()
                .map(lib -> modelMapper.map(lib, LibraryDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public LibraryDto findById(Long id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException("Library not found with id: " + id));
        return modelMapper.map(library, LibraryDto.class);
    }


@Override
public LibraryDto updateById(Long id, LibraryDto dto) {
    Library updated = libraryRepository.findById(id).map(existing -> {
        existing.setName(dto.getName());
        existing.setCity(dto.getCity());
        return libraryRepository.save(existing);
    }).orElseThrow(() -> new LibraryNotFoundException("Library not found with id: " + id));
    return modelMapper.map(updated, LibraryDto.class);
}


    @Override
    public void deleteById(Long id) {
        libraryRepository.deleteById(id);
    }

    public LibraryDto getLibraryWithBooks(Long id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException("Library not found with id: " + id));
        if (library == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Library not found");

        }

        BookDto[] books = restTemplate.getForObject( BOOK_SERVICE_BASE_URL+ id, BookDto[].class);
        List<BookDto> bookList = Arrays.asList(books);

        return new LibraryDto(library.getId(), library.getName(), library.getCity(), bookList);
    }

}
