package bla.DAO;

import bla.entities.Film;
import exceptions.filmnotfound;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.enterprise.context.Dependent;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named(value = "filmDao")
@Dependent
@RequestScoped
public class filmDao extends Dao {

    public filmDao() {
    }

    public Film findFilmByName(String name) throws filmnotfound {
        String query = ("select * from filme where name like \'" + name + "\'");
        return getFilm(query);
    }

    public Film findFilmByID(int id) throws filmnotfound {
        String query = ("select * from filme where filmID = " + id);
        return getFilm(query);
    }

    public ArrayList<Film> findAllFilm() throws filmnotfound {
        String query = ("select * from filme");
        return getFilms(query);
    }

    public ArrayList<Film> findSubFilm(String x) throws filmnotfound {
        String query = ("select * from filme where name like \'" + x + "%\'");
        return getFilms(query);
    }

    /*  public Film updateFilm(Film fi) throws filmnotfound {
        Savepoint safe = null;
        try {
            safe = getConnection().setSavepoint("safe");
        } catch (SQLException ex) {
            Logger.getLogger(filmDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            String query = ("update filme set name = ?,genre = ?"
                    + " ,jahr = ?,regisseur = ?"
                    + " where filmID = ?");
            PreparedStatement st = getConnection().prepareStatement(query);
            st.setString(1, fi.getName());
            st.setInt(2, fi.getGenre());
            st.setInt(3, fi.getJahr());
            st.setString(4, fi.getRegi());
            st.setInt(5, fi.getFilmID());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("fehler");
            try {
                conn.rollback(safe);
            } catch (SQLException ex) {
                Logger.getLogger(filmDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
        }
        return findFilmByID(fi.getFilmID());
    }
     */
    public Film getFilm(String query) throws filmnotfound {
        Film fi = null;
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            fi = new Film();
            fi.setFilmID(rs.getInt("filmID"));
            fi.setName(rs.getString("name"));
            fi.setGenre(rs.getInt("genre"));
            fi.setJahr(rs.getInt("jahr"));
            fi.setRegi(rs.getString("regisseur"));
        } catch (Exception e) {
            System.out.println("fehler");
            e.printStackTrace();
        }
        if (fi == null) {
            throw new filmnotfound();
        }
        return fi;
    }

    public ArrayList<Film> getFilms( String query) {
        ArrayList<Film> al =null;
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            al=new ArrayList<Film>();
            while (!rs.isLast()) {
                rs.next();
                Film fi = new Film();
                fi.setFilmID(rs.getInt("filmID"));
                fi.setName(rs.getString("name"));
                fi.setGenre(rs.getInt("genre"));
                fi.setJahr(rs.getInt("jahr"));
                fi.setRegi(rs.getString("regisseur"));
                al.add(fi);
            }
        } catch (Exception e) {
            System.out.println("fehler");
            e.printStackTrace();
        }
        return al;
    }

}
