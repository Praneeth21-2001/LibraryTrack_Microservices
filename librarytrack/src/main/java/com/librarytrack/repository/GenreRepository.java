package com.librarytrack.repository;

import com.librarytrack.Modal.Genre;
import com.librarytrack.payload.dto.GenreDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre,Long> {

    List<Genre> findByActiveTrueOrderByDisplayOrderAsc();

    List<Genre> findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();

    List<Genre> findByParentGenreIdAndActiveTrueOrderByDisplayOrderAsc(Long parentGenreId);
    boolean existsByParentGenreId(Long parentGenreId);


    long countByActiveTrue();

//    @Query("select count(b) from Book b where b.genre.id = :genreId")
//    long countBooksByGenre(@Param("genreId") Long genreId);

}
