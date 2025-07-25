package com.example.libraryservice.entity;


import jakarta.persistence.*;

@Entity
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private Long libraryId; // No JPA relation, just store ID

    public Librarian() {}

    public Librarian(Long id, String name, String email, Long libraryId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.libraryId = libraryId;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Long getLibraryId() { return libraryId; }
    public void setLibraryId(Long libraryId) { this.libraryId = libraryId; }
}
