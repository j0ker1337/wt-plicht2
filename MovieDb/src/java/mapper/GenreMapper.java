/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import bla.entities.Genre;
import dto.GenreDTO;

/**
 *
 * @author joker
 */
public class GenreMapper {
        public static GenreDTO entityToDTO(Genre genre){
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        return genreDTO;
    }
    
    public static Genre dtoToentityT(GenreDTO genreDTO){
        Genre genre = new Genre();
        genre.setId(genreDTO.getId());
        genre.setName(genreDTO.getName());
        return genre;
    }
}
