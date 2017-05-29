/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author joker
 */
@Named("Film")
@RequestScoped
public class FilmDTO extends DTO{
 private int filmID;
    private String name;
    private int jahr;
    private String regi;
    private GenreDTO genre;
    ArrayList<UserDTO> users;

    
    
    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }

    

    public int getJahr() {
        return jahr;
    }

    public void setJahr(int jahr) {
        this.jahr = jahr;
    }

    public String getRegi() {
        return regi;
    }

    public void setRegi(String regi) {
        this.regi = regi;
    }

    public ArrayList<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserDTO> users) {
        this.users = users;
    }

    
    

    
}
