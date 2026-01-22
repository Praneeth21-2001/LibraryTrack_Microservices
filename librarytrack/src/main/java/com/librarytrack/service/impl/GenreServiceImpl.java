package com.librarytrack.service.impl;

import com.librarytrack.Modal.Genre;
import com.librarytrack.exception.GenreException;
import com.librarytrack.mapper.GenreMapper;
import com.librarytrack.payload.dto.GenreDTO;
import com.librarytrack.repository.GenreRepository;
import com.librarytrack.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    public GenreDTO createGenre(GenreDTO dto) throws GenreException {

        // Convert DTO to entity
        Genre genre = genreMapper.toEntity(dto);

        // Set parent genre if present
        if (dto.getParentGenreId() != null) {
            Genre parent = genreRepository.findById(dto.getParentGenreId())
                    .orElseThrow(() -> new GenreException("Parent genre not found"));
            genre.setParentGenre(parent);
        }

        // Save
        Genre saved = genreRepository.save(genre);

        // Convert back to DTO
        return genreMapper.toDTO(saved);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(genreMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GenreDTO getGenreById(Long genreId) throws GenreException {

        Genre genre = genreRepository.findById(genreId).orElseThrow(()->new GenreException("genre not found"));

        return genreMapper.toDTO(genre);
    }

    @Override
    public GenreDTO updateGenre(Long genreId, GenreDTO genre) throws GenreException {
        Genre existingGenre = genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreException("Genre not found"));

        // update basic fields
        genreMapper.updateEntityFromDTO(genre, existingGenre);

        // handle parent genre (SERVICE responsibility)
        if (genre.getParentGenreId() != null) {
            Genre parent = genreRepository.findById(genre.getParentGenreId())
                    .orElseThrow(() -> new GenreException("Parent genre not found"));
            existingGenre.setParentGenre(parent);
        }

        Genre updatedGenre = genreRepository.save(existingGenre);

        return genreMapper.toDTO(updatedGenre);
    }

    @Override
    public void deleteGenre(Long genreId) throws GenreException {
        Genre existingGenre = genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreException("Genre not found"));
        existingGenre.setActive(false);
        genreRepository.save(existingGenre);

    }

    @Override
    public void hardDeleteGenre(Long genreId) throws GenreException {
        if (genreRepository.existsByParentGenreId(genreId)) {
            throw new GenreException(
                    "Cannot delete genre. Sub-genres exist. Delete them first."
            );
        }

        Genre existingGenre = genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreException("Genre not found"));

        genreRepository.delete(existingGenre);

    }

    @Override
    public List<GenreDTO> getAllActiveGenresWithSubgenres() {
        List<Genre> topLevelGenres = genreRepository.findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();

        return genreMapper.toDTOList(topLevelGenres);
    }

    @Override
    public List<GenreDTO> getTopLevelGenres() {
        List<Genre> topLevelGenres = genreRepository.findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();

        return genreMapper.toDTOList(topLevelGenres);
    }

    @Override
    public long getTotalActiveGenres() {
        return genreRepository.countByActiveTrue();
    }

    @Override
    public long getBookCountByGenres(Long genreId) {
        return 0;
    }
}
