package bla.DAO;

import bla.entities.User;
import exceptions.connectionProblem;
import exceptions.usernotfound;
import exceptions.usersnotfound;
import java.sql.*;
import java.util.ArrayList;
import javax.enterprise.context.Dependent;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named(value = "userDao")
@Dependent
@RequestScoped
public class userDao extends Dao {

    public userDao() {
    }

    public User findUserByName(String name, String vorname) throws connectionProblem, usernotfound {
        String query = ("select * from person where name like \'" + name + "\' and vorname like \'" + vorname + "\'");
        return getUser(query);
    }

    public User findUserByID(int id) throws connectionProblem, usernotfound {
        String query = ("select * from person where userID = " + id);
        return getUser(query);
    }

    public User findUserByUserName(String name) throws connectionProblem, usernotfound {
        String query = ("select * from person where benutzerName Like " + name);
        return getUser(query);
    }

    public User update(User user) throws connectionProblem{
        String query = "update persons set name=?,vorname=?,geburtsdatum=?,benutzerName=?,password=?,rightsID=? where filmID=?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(query);
            pre.setString(1, user.getName());
            pre.setString(2, user.getVorname());
            pre.setDate(3, user.getGeburt());
            pre.setString(4,user.getBname());
            pre.setString(5, user.getPass());
            pre.setInt(6,user.getRightsID());
            pre.setInt(7, user.getUserID());
            pre.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.print("sadasd");
        }
        return user;
    }

    public ArrayList<User> findAllUser() throws connectionProblem, usersnotfound {
        String query = ("select * from person");
        return getUsers(query);
    }

    private User getUser(String query) throws connectionProblem, usernotfound {
        User us = null;
        try {

            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            us = new User();
            rs.next();
            us.setUserID(rs.getInt("userID"));
            us.setVorname(rs.getString("vorname"));
            us.setName(rs.getString("name"));
            us.setGeburt(rs.getDate("geburtsdatum"));
            us.setBname(rs.getString("benutzerName"));
            us.setPass(rs.getString("password"));
            us.setRightsID(rs.getInt("rightsID"));
        } catch (Exception e) {
            System.out.println("fehler");
            e.printStackTrace();
        }
        if (us == null) {
            throw new usernotfound();
        }
        return us;
    }

    public ArrayList<User> getUsers(String query) throws connectionProblem, usersnotfound {
        ArrayList<User> al = null;
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            al = new ArrayList<User>();
            if (!rs.wasNull()) {
                while (!rs.isLast()) {
                    rs.next();
                    User us = new User();
                    us.setUserID(rs.getInt("userID"));
                    us.setVorname(rs.getString("vorname"));
                    us.setName(rs.getString("name"));
                    us.setGeburt(rs.getDate("geburtsdatum"));
                    us.setBname(rs.getString("benutzerName"));
                    us.setPass(rs.getString("password"));
                    us.setRightsID(rs.getInt("rightsID"));
                    al.add(us);
                }
            }
        } catch (Exception e) {
            System.out.println("fehler");
            e.printStackTrace();
        }
        if (al == null) {
            throw new usersnotfound();
        }
        return al;
    }

}
