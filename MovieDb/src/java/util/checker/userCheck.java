/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.checker;

import bla.DAO.DaoManager;
import bla.entities.User;
import exceptions.RightIdEmpty;
import exceptions.UserBNameEmpty;
import exceptions.UserEmpty;
import exceptions.UserFoundException;
import exceptions.UserIdEmpty;
import exceptions.connectionProblem;
import exceptions.rightsnotfound;
import exceptions.usernotfound;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joker
 */
public class userCheck {

    public static void checkUser(User user, DaoManager daoManger) throws UserIdEmpty, UserBNameEmpty, connectionProblem, UserFoundException, UserEmpty, rightsnotfound {
        if (user == null) {
            throw new UserEmpty();
        }
        if (user.getUserID() == 0) {
            throw new UserIdEmpty();
        }
        if (user.getRightsID() == 0) {
            try {
                throw new RightIdEmpty();
            } catch (RightIdEmpty ex) {
                Logger.getLogger(userCheck.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (user.getBname() == null) {
            throw new UserBNameEmpty();
        }
        try {
            if (daoManger.getUsDao().findUserByUserName(user.getBname()) != null) {
                throw new UserFoundException();
            }
        } catch (usernotfound e) {

        }
        daoManger.getrDao().findById(user.getRightsID());
    }
}
