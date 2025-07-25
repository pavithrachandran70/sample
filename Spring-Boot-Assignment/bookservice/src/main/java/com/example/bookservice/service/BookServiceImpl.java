package com.example.bookservice.service;


import com.example.bookservice.dto.BookDTO;
import com.example.bookservice.entity.Book;
import com.example.bookservice.exception.BookNotFoundException;
import com.example.bookservice.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

//    //bookrepository
//    private final BookRepository repo;
////    @Autowired
////    private BookRepository repo;
//
//    public BookServiceImpl(BookRepository repo) {
//        this.repo = repo;
//    }
//
//    //create
//    public Book save(Book book) {
//        return repo.save(book);
//    }
//
//    public List<Book> findAll() {
//        return repo.findAll();
//    }
//
//    public Book findById(Long id) {
//        return repo.findById(id)
//                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
//
//    }
//
//    public Book updateById(Long id, Book updated) {
//        Book book = repo.findById(id)
//                .orElseThrow(() -> new BookNotFoundException("Cannot update. Book not found with id: " + id));
//        if (book != null) {
//            book.setTitle(updated.getTitle());
//            book.setAuthor(updated.getAuthor());
//            book.setPrice(updated.getPrice());
//            return repo.save(book);
//        }
//        return null;
//    }
//
//    public void deleteById(Long id) {
//        repo.deleteById(id);
//    }
//    public List<Book> getBooksByLibraryId(Long libraryId) {
//        return repo.findByLibraryId(libraryId);
//    }
//
//    @Override
//    public List<Book> findByTitleAndPriceRange(String title, double minPrice, double maxPrice) {
//        return repo.findByTitleAndPriceBetween(title, minPrice, maxPrice);
//    }
//
//    @Override
//    public Book findByTitleAndAuthor(String title, String author) {
//        return repo.findByTitleAndAuthor(title, author);
//    }

    private final BookRepository bookRepository;

    //maps fields between Book and BookDTO objects.
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository repo, ModelMapper mapper) {
        this.bookRepository = repo;
        this.modelMapper = mapper;
    }

    // Entity to DTO
    private BookDTO convertToDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }


    //Saves the Book entity to the database.
    public BookDTO save(Book book) {
        //Returns the saved book as a BookDTO.
        return convertToDTO(bookRepository.save(book));
    }

    public List<BookDTO> findAll() {
        //findAll() method from Spring Data JPA's JpaRepository.
        return bookRepository.findAll()
                .stream()
                .map(this::convertToDTO).toList();
    }

    public BookDTO findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        return convertToDTO(book);
    }

    public BookDTO updateById(Long id, Book updated) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Cannot update. Book not found with id: " + id));
        book.setTitle(updated.getTitle());
        book.setAuthor(updated.getAuthor());
        book.setPrice(updated.getPrice());
        return convertToDTO(bookRepository.save(book));
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<BookDTO> getBooksByLibraryId(Long libraryId) {
        return bookRepository.findByLibraryId(libraryId)
                .stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<BookDTO> findByTitleAndPriceRange(String title, double minPrice, double maxPrice) {
        return bookRepository.findByTitleAndPriceBetween(title, minPrice, maxPrice)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public BookDTO findByTitleAndAuthor(String title, String author) {
        Book book = bookRepository.findByTitleAndAuthor(title, author).orElseThrow(() -> new BookNotFoundException("Book not found with title and author"));
        return convertToDTO(book);
    }

}

