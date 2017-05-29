/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bla.DAO;

import bla.entities.Rights;
import exceptions.rightsnotfound;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author joker
 */
public class rightsDao extends Dao {

    public Rights findById(int id) throws rightsnotfound {
        String query = "select * from rights where id='" + id + "'";
        return getRights(query);
    }
    
    public Rights findRightOfUser(int id) throws rightsnotfound{
        String query = "select r.id,r.`name` from person p join rights r on r.id=p.rightsID where p.userID="+id;
        return getRights(query);
    }

    public Rights getRights(String query) throws rightsnotfound {
        Rights rights = null;
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                rights = new Rights();
                rights.setId(rs.getInt("id"));
                rights.setName(rs.getString("name"));
            }
        } catch (Exception ex) {
            System.err.println("fehler");
        }
        if (rights == null) {
            throw new rightsnotfound();
        }
        return rights;
    }

}
