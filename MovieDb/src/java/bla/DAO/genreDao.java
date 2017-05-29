/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bla.DAO;

import bla.entities.Genre;
import exceptions.connectionProblem;
import exceptions.genreNotFound;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author joker
 */
public class genreDao extends Dao{


    public genreDao() {
    }
    public Genre findById(int id) throws genreNotFound, connectionProblem {
        String query = "select * from genre where genreID='" + id + "'";
        return getGenres(query);
    }
    
    public Genre findByName(String name) throws genreNotFound, connectionProblem{
          String query = "select * from genre where name like '" + name + "'";
        return getGenres(query);
    }
    
      public Genre findGenreByFilmID(int id) throws genreNotFound, connectionProblem{
          String query = "select g.name,g.`genreID` from filme f join genre g on f.genre=g.genreID where f.filmID="+id;
        return getGenres(query);
    }
    
    public Genre getGenres(String query) throws genreNotFound, connectionProblem {
        Genre genre=null;
        try {
            genre =new Genre();
            Statement st = getConnection().createStatement();
             ResultSet rs = st.executeQuery(query);
            rs.next();
            genre.setId(rs.getInt("genreID"));
            genre.setName(rs.getString("name"));
        } catch (Exception e) {
            System.out.println("fehler");
            e.printStackTrace();
        }
        if(genre==null){
            throw new genreNotFound();
        }
        return genre;
    }


}
