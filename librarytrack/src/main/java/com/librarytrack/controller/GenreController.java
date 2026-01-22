package com.librarytrack.controller;

import com.librarytrack.Modal.Genre;
import com.librarytrack.exception.GenreException;
import com.librarytrack.payload.dto.GenreDTO;
import com.librarytrack.payload.response.ApiResponse;
import com.librarytrack.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    private final GenreService genreService;


    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/create")
    public ResponseEntity<GenreDTO> createGenre(@Valid @RequestBody GenreDTO genre) throws GenreException {

        GenreDTO createGenre = genreService.createGenre(genre);
        return ResponseEntity.ok(createGenre);
    }

    @GetMapping()
    public ResponseEntity<List<GenreDTO>> getAllGenres(){

        List<GenreDTO> genres = genreService.getAllGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{genreId}")
    public ResponseEntity<?> getGenreById(@PathVariable Long genreId) throws GenreException {
        GenreDTO genre = genreService.getGenreById(genreId);
        return ResponseEntity.ok(genre);
    }

    @PutMapping("/{genreId}")
    public ResponseEntity<?> updateGenre(@PathVariable Long genreId,@RequestBody GenreDTO genreDTO) throws GenreException {
        GenreDTO genre = genreService.updateGenre(genreId,genreDTO);
        return ResponseEntity.ok(genre);
    }

    @DeleteMapping("/{genreId}")
    public ResponseEntity<?> softDelete(@PathVariable Long genreId) throws GenreException {
        genreService.deleteGenre(genreId);
        ApiResponse response = new ApiResponse("Soft Delted",true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/hardDelete/{genreId}")
    public ResponseEntity<?> hardDelete(@PathVariable Long genreId) throws GenreException {
        genreService.hardDeleteGenre(genreId);
        ApiResponse response = new ApiResponse("Hard Deleted",true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/top-level")
    public ResponseEntity<?> topLevelGenre() throws GenreException {
        List<GenreDTO> genres = genreService.getTopLevelGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/count")
    public ResponseEntity<?> getTotalActiveGenres() throws GenreException {
        Long genres = genreService.getTotalActiveGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/book-count/{genreId}")
    public ResponseEntity<?> getBookCountByGenres(@PathVariable Long genreId) throws GenreException {
        Long count = genreService.getBookCountByGenres(genreId);
        return ResponseEntity.ok(count);
    }


}
