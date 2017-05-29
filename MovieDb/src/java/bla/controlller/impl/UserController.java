/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bla.controlller.impl;

import dto.RightDTO;
import dto.UserDTO;
import exceptions.CombinationNotFound;
import exceptions.RightIdEmpty;
import exceptions.UserBNameEmpty;
import exceptions.UserEmpty;
import exceptions.UserFoundException;
import exceptions.UserIdEmpty;
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

public interface UserController{
    ArrayList<UserDTO> findAllUsers() throws connectionProblem, usersnotfound, usersnotfound, rightsnotfound, filmnotfound, genreNotFound;
    UserDTO findUserById(int id) throws connectionProblem, rightsnotfound, filmnotfound, usernotfound, genreNotFound;
    UserDTO findUserByUserName(String name) throws connectionProblem, usernotfound, rightsnotfound, filmnotfound, genreNotFound;
    RightDTO findRightOfUser(int id) throws rightsnotfound;
    public UserDTO update(UserDTO udto) throws UserIdEmpty, UserBNameEmpty, UserFoundException, connectionProblem, UserEmpty, rightsnotfound, usernotfound, filmnotfound, genreNotFound, RightIdEmpty,CombinationNotFound;
}