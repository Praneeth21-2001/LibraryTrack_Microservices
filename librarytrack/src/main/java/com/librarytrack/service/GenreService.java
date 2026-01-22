package com.librarytrack.service;

import com.librarytrack.Modal.Genre;
import com.librarytrack.exception.GenreException;
import com.librarytrack.payload.dto.GenreDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenreService {

    GenreDTO createGenre(GenreDTO genreDTO) throws GenreException;
    List<GenreDTO> getAllGenres();

    GenreDTO getGenreById(Long genreId) throws GenreException;
    GenreDTO updateGenre(Long genreId, GenreDTO genre) throws GenreException;
    void deleteGenre(Long genreId) throws GenreException;
    void hardDeleteGenre(Long genreId) throws GenreException;

    List<GenreDTO> getAllActiveGenresWithSubgenres();

    List<GenreDTO> getTopLevelGenres();
//    Page<GenreDTO> searchGenres(String searchTerm, Pageable pageable);
    long getTotalActiveGenres();

    long getBookCountByGenres(Long genreId);


}
