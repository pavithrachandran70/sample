package com.example.libraryservice.dto;


import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private double price;
    public BookDto() {}

    public BookDto(Long id, String title, String author, Double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public Double getPrice() { return price; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPrice(Double price) { this.price = price; }
}

