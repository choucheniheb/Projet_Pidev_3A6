/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author msi
 */
public class StatusService implements IService<Status>{
    Connection cnx ;
   
    public StatusService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Status t) throws SQLException {
        String req = "INSERT INTO status (titre_status, contenu_status, nbr_like, media_status, id_utilisateur) VALUES ('"+t.getTitre_status()+"','"+ t.getContenu_status()+"',"+t.getNbr_likes()+",'"+ t.getMedia_status()+"',"+t.getId_utilisateur()+");";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Status t) throws SQLException {
        String req = "UPDATE status SET titre_status = ?,contenu_status = ?,nbr_like = ?,media_status = ?,id_utilisateur = ? where id_status = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getTitre_status());
        ps.setString(2, t.getContenu_status());
        ps.setInt(3, t.getNbr_likes());
        ps.setString(4, t.getMedia_status());
        ps.setInt(5, t.getId_utilisateur());
        ps.setInt(6, t.getId_status());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(Status t) throws SQLException {
        String req = "DELETE FROM status WHERE id_status = "+t.getId_status();
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<Status> recuperer(Status t) throws SQLException {
        List<Status> myList = new  ArrayList<> ();
        String requete = "select * from status " ;
        Statement st = cnx.createStatement() ;
        ResultSet rs = st.executeQuery(requete);
        while(rs.next())
        {
            Status s = new  Status() ;
            s.setId_status(rs.getInt(1));
            s.setTitre_status(rs.getString(2));
            s.setContenu_status(rs.getString(3));
            s.setDate_status(rs.getString(4));
            s.setNbr_likes(rs.getInt(5));
            s.setMedia_status(rs.getString(6));
            s.setId_utilisateur(rs.getInt(7));
            myList.add(s) ;
        }
        return myList ;
    }
    
}
