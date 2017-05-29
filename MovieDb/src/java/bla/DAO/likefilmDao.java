package bla.DAO;

import bla.entities.Film;
import bla.entities.User;
import bla.persistence.DBverbindung;
import exceptions.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named(value = "likefilmDao")
@Dependent
public class likefilmDao extends Dao {

    private Connection conn;

    public likefilmDao() {
        this.conn = DBverbindung.getConnection();
    }

    public ArrayList<Film> findFilmsLikedByUser(int id) throws filmnotfound {
        String query = ("select * from filme f join usertofilm x "
                + "on f.filmID = x.film "
                + "where x.user = '" + id + "'");
        filmDao fd = new filmDao();
        return fd.getFilms(query);
    }

    public ArrayList<Film> findFilmsLikedByUser(String name, String vorname) throws connectionProblem, usernotfound, filmnotfound {
        userDao ud = new userDao();
        int userID = ud.findUserByName(name, vorname).getUserID();
        return findFilmsLikedByUser(userID);
    }

    public ArrayList<User> findUsersWhoLikesFilm(int id) throws connectionProblem, usersnotfound {
        ArrayList<User> al = new ArrayList();
        String query = ("select * from person f join usertofilm x "
                + "on f.userID = x.user "
                + "where x.film = \'" + id + "\'");
        userDao ud = new userDao();
        return ud.getUsers(query);
    }

    public ArrayList<User> findUsersWhoLikesFilm(String name) throws filmnotfound, connectionProblem, usersnotfound {
        filmDao fd = new filmDao();
        int filmID = fd.findFilmByName(name).getFilmID();
        return findUsersWhoLikesFilm(filmID);
    }

    public Film findCombination(int userid, int filmid) throws CombinationNotFound {
        Film fi =null;
        filmDao fd = new filmDao();
        String query = ("select * from usertofilm where user=" + userid + "and film=" + filmid);
        try{
           fi= fd.getFilm(query);
        }catch(Exception e){
            throw new CombinationNotFound();
        }
        return fi;
    }

    public Film delete(int userid, int filmid) throws filmnotfound {
        filmDao fd = new filmDao();
        String query = ("delete * from usertofilm where user=? and film=?");
        return fd.getFilm(query);
    }

    public Film insert(int userid, int filmid) throws filmnotfound, CombinationNotFound {
        String query = "insert into usertofilm values(?,?)";
        return updateQuery(query, userid, filmid);
    }

    public Film updateQuery(String query, int userid, int filmid) throws filmnotfound, CombinationNotFound {
        try {
            PreparedStatement st = getConnection().prepareStatement(query);
            st.setInt(1, userid);
            st.setInt(2, filmid);
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("fehler");
            e.printStackTrace();
        }
        return findCombination(userid, userid);
    }
}
