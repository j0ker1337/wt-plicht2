/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bla.controlller.impl;


import dto.GenreDTO;
import exceptions.connectionProblem;
import exceptions.genreNotFound;

/**
 *
 * @author joker
 */
public interface GenreController {
    public GenreDTO findGenreById(int id) throws genreNotFound, connectionProblem;
    public GenreDTO findGenreByName(String name) throws genreNotFound, connectionProblem;
 
}
