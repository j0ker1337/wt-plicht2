/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bla.controlller.impl;


import dto.UserDTO;
import exceptions.*;
import java.util.ArrayList;
import javax.enterprise.context.Dependent;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
/**
 *
 * @author joker
 */
@Named(value = "userController")
@Dependent
@RequestScoped
public class UserControllerImpl extends ControllerImpl implements UserController { 

    @Override
    public ArrayList<UserDTO> findAllUsers() throws connectionProblem, usersnotfound, usersnotfound, rightsnotfound, filmnotfound, genreNotFound {
        return super.findAllUser();
    }

    @Override
    public UserDTO findUserById(int id) throws connectionProblem, rightsnotfound, filmnotfound, usernotfound, genreNotFound {
        return super.findUserByID(id);
    }

    @Override
    public UserDTO findUserByName(String name, String vorname) throws connectionProblem, usernotfound, rightsnotfound, filmnotfound, genreNotFound {
        return super.findUserByName(name, vorname);
    }

    /**
     *
     * @param udto
     * @return
     * @throws UserIdEmpty
     * @throws UserBNameEmpty
     * @throws UserFoundException
     * @throws connectionProblem
     * @throws UserEmpty
     * @throws rightsnotfound
     * @throws usernotfound
     * @throws filmnotfound
     * @throws genreNotFound
     * @throws RightIdEmpty
     */
    @Override
    public UserDTO update(UserDTO udto) throws UserIdEmpty, UserBNameEmpty, UserFoundException, connectionProblem, UserEmpty, rightsnotfound, usernotfound, filmnotfound, genreNotFound, RightIdEmpty,CombinationNotFound {
        return super.update(udto);
    } 
}
