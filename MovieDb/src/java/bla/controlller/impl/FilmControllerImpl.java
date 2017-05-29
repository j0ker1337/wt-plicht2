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
import javax.enterprise.context.Dependent;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author joker
 */
@Named(value = "filmController")
@Dependent
@RequestScoped
public class FilmControllerImpl extends ControllerImpl implements FilmController {

    @Override
    public FilmDTO findById(int id) throws filmnotfound, genreNotFound, connectionProblem, usersnotfound, rightsnotfound {
        return super.findFilmByID(id);
    }

    @Override
    public FilmDTO findFilmByName(String name) throws filmnotfound, genreNotFound, connectionProblem, usersnotfound, rightsnotfound {
        return super.findFilmByName(name);
    }

    @Override
    public ArrayList<FilmDTO> findAllFilm() throws filmnotfound, genreNotFound, connectionProblem {
        return super.findAllFilm();
    }

    @Override
    public ArrayList<FilmDTO> findSubFilm(String x) throws filmnotfound, genreNotFound, connectionProblem {
        return super.findSubFilm(x);
    }

    @Override
    public ArrayList<FilmDTO> findFilmsLikedByUser(int id) throws filmnotfound, genreNotFound, connectionProblem {
        return super.findFilmsLikedByUser(id);
    }

    @Override
    public ArrayList<FilmDTO> findFilmLikedByPerson(String name, String vorname) throws connectionProblem, usernotfound, filmnotfound, genreNotFound {
        return super.findFilmLikedByPerson(name, vorname);
    }

    @Override
    public ArrayList<UserDTO> findUsersWhoLikesFilm(int id) throws connectionProblem, usersnotfound, rightsnotfound {
        return super.findUsersWhoLikesFilm(id);
    }

    @Override
    public ArrayList<UserDTO> findpersonsLikesFilm(String name) throws filmnotfound, connectionProblem, usersnotfound, rightsnotfound {
        return super.findpersonsLikesFilm(name);
    }

    @Override
    public GenreDTO findGenreByFilmID(int id) throws genreNotFound, connectionProblem {
        return super.findGenreByFilmID(id);
    }

}
