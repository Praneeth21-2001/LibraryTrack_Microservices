package com.librarytrack.payload.dto;

import com.librarytrack.Modal.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Builder
public class BookDTO {

    private Long id;
    @NotBlank(message = "ISBN is mandatory")
    private String isbn;

    @NotBlank(message = "Title is mandatory")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @NotBlank(message = "Author is mandatory")
    @Size(min = 1, max = 255, message = "Author must be between 1 and 255 characters")
    private String author;

    @NotNull(message = "Genre is mandatory")
    private Long genreId;

    private String genreName;
    private String genreCode;
    @Size(max = 100, message = "Publisher name must not exceed above 100 characters")
    private String publisher;

    private LocalDate publishedDate;
    @Size(max = 20, message = "Language name must not exceed above 20 characters")
    private String language;
    @Min(value = 1, message = "Pages must be atleast 1")
    @Max(value = 50000,message = "Pages must not exceed 50000")
    private Integer pages;
    @Size(max = 2000, message = "Description  must not exceed above 2000 characters")
    private String description;

    @Min(value = 0,message = "Total copies cannot be negative")
    @NotNull(message = "Total copies is mandatory")
    private Integer totalCopies;

    @Min(value = 0,message = "Available copies cannot be negative")
    @NotNull(message = "Available copies is mandatory")
    private Integer availableCopies;
    @DecimalMin(value = "0.0",inclusive = true, message = "Price cannot be negative")
    @Digits(integer = 8,fraction = 2,message = "Price must have atmost 8 integer digits and 2")
    private BigDecimal price;
    @Size(max = 500,message = "Image url must not exceed 500 characters")
    private String coverImageURL;

    private Boolean alreadyHaveLoan;
    private Boolean alreadyHaveReservation;
    private Boolean active=true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
