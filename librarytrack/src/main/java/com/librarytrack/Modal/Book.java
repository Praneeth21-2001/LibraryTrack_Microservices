package com.librarytrack.Modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false,unique = true)
    private String isbn;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Genre genre;

    private String publisher;
    private LocalDate publishedDate;
    private String language;
    private Integer pages;
    private String description;
    @Column(nullable = false)
    private Integer totalCopies;
    @Column(nullable = false)
    private Integer availableCopies;

    private BigDecimal price;
    private String imageURL;
    @Column(nullable = false)
    private Boolean active=true;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    @AssertTrue(message = "Available Copies cannot exceed Total copies")
    public boolean isAvailableCopiesValid(){
        if (totalCopies==null || availableCopies==null){
            return true;
        }
        return availableCopies<=totalCopies;
    }

    public Book() {
    }

    public Book(Long id, String isbn, String title, String author, Genre genre, String publisher, LocalDate publishedDate, String language, Integer pages, String description, Integer totalCopies, Integer availableCopies, BigDecimal price, String imageURL, Boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.language = language;
        this.pages = pages;
        this.description = description;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.price = price;
        this.imageURL = imageURL;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
