/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bla.controlller.impl;

import bla.DAO.DaoManager;
import bla.entities.User;
import dto.FilmDTO;
import dto.GenreDTO;
import dto.RightDTO;
import dto.UserDTO;
import exceptions.*;
import java.util.ArrayList;
import mapper.FilmMapper;
import mapper.GenreMapper;
import mapper.RightsMapper;
import mapper.UserMapper;
import util.checker.userCheck;

/**
 *
 * @author joker
 */
class ControllerImpl implements Controller {

    private final DaoManager daoManager;

    public ControllerImpl() {
        daoManager = new DaoManager();
    }

    @Override
    public FilmDTO findFilmByName(String name) throws filmnotfound, genreNotFound, connectionProblem, usersnotfound, rightsnotfound {
        FilmDTO dTO = FilmMapper.entityToDTO(daoManager.getfDao().findFilmByName(name));
        dTO.setUsers(findUsersWhoLikesFilm(dTO.getFilmID()));
        return dTO;
    }

    @Override
    public FilmDTO findFilmByID(int id) throws filmnotfound, genreNotFound, connectionProblem, usersnotfound, rightsnotfound {
        FilmDTO dTO = FilmMapper.entityToDTO(daoManager.getfDao().findFilmByID(id));
        dTO.setUsers(findUsersWhoLikesFilm(dTO.getFilmID()));
        return dTO;
    }

    @Override
    public ArrayList<FilmDTO> findAllFilm() throws filmnotfound, genreNotFound, connectionProblem {
        ArrayList<FilmDTO> films = FilmMapper.entityArrayToDTO(
                daoManager.getfDao().findAllFilm());
        for (FilmDTO x : films) {
            x.setGenre(findGenreByFilmID(x.getFilmID()));
        }
        return films;
    }

    @Override
    public GenreDTO findGenreByFilmID(int id) throws genreNotFound, connectionProblem {
        return GenreMapper.entityToDTO(daoManager.getgDao().findGenreByFilmID(id));
    }

    @Override
    public ArrayList<FilmDTO> findSubFilm(String x) throws filmnotfound, genreNotFound, connectionProblem {
        ArrayList<FilmDTO> films = FilmMapper.entityArrayToDTO(daoManager.getfDao().findSubFilm(x));
        for (FilmDTO y : films) {
            y.setGenre(findGenreByFilmID(y.getFilmID()));
        }
        return films;
    }

    @Override
    public GenreDTO findGenreById(int id) throws genreNotFound, connectionProblem {
        return GenreMapper.entityToDTO(daoManager.getgDao().findById(id));
    }

    @Override
    public GenreDTO findGenreByName(String name) throws genreNotFound, connectionProblem {
        return GenreMapper.entityToDTO(daoManager.getgDao().findByName(name));
    }

    @Override
    public ArrayList<FilmDTO> findFilmsLikedByUser(int id) throws filmnotfound, genreNotFound, connectionProblem {
        ArrayList<FilmDTO> films = FilmMapper.entityArrayToDTO(daoManager.getlDao().findFilmsLikedByUser(id));
        for (FilmDTO x : films) {
            x.setGenre(findGenreByFilmID(x.getFilmID()));
        }
        return films;
    }

    @Override
    public ArrayList<FilmDTO> findFilmLikedByPerson(String name, String vorname) throws connectionProblem, usernotfound, filmnotfound, genreNotFound {
        ArrayList<FilmDTO> films = FilmMapper.entityArrayToDTO(daoManager.getlDao().findFilmsLikedByUser(name, vorname));
        for (FilmDTO x : films) {
            x.setGenre(findGenreByFilmID(x.getFilmID()));
        }
        return films;
    }

    @Override
    public ArrayList<UserDTO> findUsersWhoLikesFilm(int id) throws connectionProblem, usersnotfound, rightsnotfound {
        return UserMapper.entityArrayToDTO(daoManager.getlDao().findUsersWhoLikesFilm(id));
    }

    @Override
    public ArrayList<UserDTO> findpersonsLikesFilm(String name) throws filmnotfound, connectionProblem, usersnotfound, rightsnotfound {
        ArrayList<UserDTO> users = UserMapper.entityArrayToDTO(daoManager.getlDao().findUsersWhoLikesFilm(name));
        for (UserDTO x : users) {
            x.setRight(findRightOfUser(x.getId()));
        }
        return null;
    }

    @Override
    public RightDTO findRightsById(int id) throws rightsnotfound {
        return RightsMapper.entityToDTO(daoManager.getrDao().findById(id));
    }

    @Override
    public UserDTO findUserByName(String name, String vorname) throws connectionProblem, usernotfound, rightsnotfound, filmnotfound, genreNotFound {
        UserDTO udto = UserMapper.entityToDto(daoManager.getUsDao().findUserByName(name, vorname));
        udto.setLikes(findFilmsLikedByUser(udto.getId()));
        return udto;
    }

    @Override
    public UserDTO findUserByID(int id) throws connectionProblem, usernotfound, rightsnotfound, filmnotfound, genreNotFound {
        UserDTO udto = UserMapper.entityToDto(daoManager.getUsDao().findUserByID(id));
        udto.setLikes(findFilmsLikedByUser(udto.getId()));
        return udto;
    }

    @Override
    public UserDTO findUserByUserName(String name) throws connectionProblem, usernotfound, rightsnotfound, filmnotfound, genreNotFound {
        UserDTO udto = UserMapper.entityToDto(daoManager.getUsDao().findUserByUserName(name));
        udto.setLikes(findFilmsLikedByUser(udto.getId()));
        return udto;
    }

    @Override
    public ArrayList<UserDTO> findAllUser() throws connectionProblem, usersnotfound, rightsnotfound, filmnotfound, genreNotFound {
        ArrayList<UserDTO> udto = UserMapper.entityArrayToDTO(daoManager.getUsDao().findAllUser());
        for (UserDTO x : udto) {
            x.setRight(findRightOfUser(x.getId()));
            x.setLikes(findFilmsLikedByUser(x.getId()));
        }
        return udto;
    }

    @Override
    public RightDTO findRightOfUser(int id) throws rightsnotfound {
        return RightsMapper.entityToDTO(daoManager.getrDao().findRightOfUser(id));
    }

    @Override
    public UserDTO update(UserDTO udto) throws UserIdEmpty, UserBNameEmpty, UserFoundException, connectionProblem, UserEmpty, rightsnotfound, usernotfound, filmnotfound, genreNotFound, RightIdEmpty,CombinationNotFound {
        User user = UserMapper.dtoToentity(udto);
        userCheck.checkUser(user, daoManager);
        daoManager.getUsDao().update(user);
        
        UserDTO userdto = likes(udto);
        return userdto;
    }
    
    public UserDTO likes(UserDTO userdto) throws connectionProblem, usernotfound, rightsnotfound, filmnotfound, genreNotFound, CombinationNotFound{
        UserDTO old = findUserByID(userdto.getId());
        if(userdto.getLikes()!=null){
            for(FilmDTO x : old.getLikes()){
                if(old.getLikes().contains(x)){
                    
                }
                else if(daoManager.getlDao().findCombination(userdto.getId(), x.getFilmID())!=null && !old.getLikes().contains(x) ){
                    daoManager.getlDao().delete(userdto.getId(), x.getFilmID());
                }
                else{
                    daoManager.getlDao().insert(userdto.getId(), x.getFilmID());
                }
            }
        }
        userdto.setLikes(findFilmsLikedByUser(userdto.getId()));
        return userdto;
    }
    
    public UserDTO create (UserDTO userDTO) throws UserIdEmpty, UserBNameEmpty, connectionProblem, UserFoundException, UserEmpty, rightsnotfound, rightsnotfound{
        userCheck.checkUser(UserMapper.dtoToentity(userDTO),daoManager);
        return null;
    }
}
