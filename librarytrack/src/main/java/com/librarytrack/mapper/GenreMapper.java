package com.librarytrack.mapper;

import com.librarytrack.Modal.Genre;
import com.librarytrack.payload.dto.GenreDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreMapper {

    public GenreDTO toDTO(Genre genre) {
        if (genre == null) return null;

        GenreDTO dto = GenreDTO.builder()
                .id(genre.getId())
                .code(genre.getCode())
                .name(genre.getName())
                .description(genre.getDescription())
                .displayOrder(genre.getDisplayOrder())
                .active(genre.getActive())
                .createdAt(genre.getCreatedAt())
                .updatedAt(genre.getUpdatedAt())
                .build();

        // Parent genre
        if (genre.getParentGenre() != null) {
            dto.setParentGenreId(genre.getParentGenre().getId());
            dto.setParentGenreName(genre.getParentGenre().getName());
        }

        // Sub-genres
        if (genre.getSubGenres() != null && !genre.getSubGenres().isEmpty()) {
            dto.setSubGenre(
                    genre.getSubGenres().stream()
                            .filter(Genre::getActive) // only active sub-genres
                            .map(this::toDTO)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public Genre toEntity(GenreDTO dto) {
        if (dto == null) return null;

        return Genre.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .description(dto.getDescription())
                .displayOrder(dto.getDisplayOrder())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();
    }

    public void updateEntityFromDTO(GenreDTO genreDTO, Genre existingGenre){
        if (genreDTO == null || existingGenre == null) return;

        if (genreDTO.getCode() != null) existingGenre.setCode(genreDTO.getCode());
        if (genreDTO.getName() != null) existingGenre.setName(genreDTO.getName());
        if (genreDTO.getDescription() != null) existingGenre.setDescription(genreDTO.getDescription());
        if (genreDTO.getDisplayOrder() != null) existingGenre.setDisplayOrder(genreDTO.getDisplayOrder());
        if (genreDTO.getActive() != null) existingGenre.setActive(genreDTO.getActive());
    }

    public List<GenreDTO> toDTOList(List<Genre> genreList){
        return genreList.stream().map(genre -> toDTO(genre)).collect(Collectors.toList());
    }
}
