/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bla.controlller.impl;

import dto.FilmDTO;
import dto.GenreDTO;
import dto.UserDTO;
import exceptions.connectionProblem;
import exceptions.filmnotfound;
import exceptions.genreNotFound;
import exceptions.rightsnotfound;
import exceptions.usernotfound;
import exceptions.usersnotfound;
import java.util.ArrayList;

/**
 *
 * @author joker
 */
public interface FilmController {

    public FilmDTO findById(int id) throws filmnotfound, genreNotFound, connectionProblem, usersnotfound, rightsnotfound;

    public FilmDTO findFilmByName(String name) throws filmnotfound, genreNotFound, connectionProblem, usersnotfound, rightsnotfound;

    public ArrayList<FilmDTO> findAllFilm() throws filmnotfound, genreNotFound, connectionProblem;

    public ArrayList<FilmDTO> findSubFilm(String x) throws filmnotfound, genreNotFound, connectionProblem;

    public ArrayList<FilmDTO> findFilmsLikedByUser(int id) throws filmnotfound, genreNotFound, connectionProblem;

    public ArrayList<FilmDTO> findFilmLikedByPerson(String name, String vorname) throws connectionProblem, usernotfound, filmnotfound, genreNotFound;

    public ArrayList<UserDTO> findUsersWhoLikesFilm(int id) throws connectionProblem, usersnotfound, rightsnotfound;

    public ArrayList<UserDTO> findpersonsLikesFilm(String name) throws filmnotfound, connectionProblem, usersnotfound, rightsnotfound;

    public GenreDTO findGenreByFilmID(int id) throws genreNotFound, connectionProblem;
}
