/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.civilisation;
import entity.nourritures;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author nourh
 */
public class civilisationService implements IService<civilisation> {

    Connection cnx;

    public civilisationService() {
        cnx = MyDB.getInstance().getCnx();
    }

    
    @Override
    public void ajouter(civilisation t) throws SQLException {
         String req = "INSERT INTO civilisation(nom_civilisation, nom_monument, description_civilisation, date_debut_civilisation,date_fin_civilisation,id_utilisateur,image) VALUES (?,?,?,?,?,?,?)";

              
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_civilisation());
        ps.setString(2, t.getNom_monument());
        ps.setString(3, t.getDescription_civilisation());
        ps.setString(4,  t.getDate_debut_civilisation());
        ps.setString(5, t.getDate_fin_civilisation());
        ps.setInt(6, t.getId_utilisateur());
         ps.setString(7,t.getImage());
       
       
        ps.executeUpdate();
         
         
         
   
    }
   

    @Override
    public void modifier(civilisation t) throws SQLException {
       
        String req = "UPDATE civilisation SET nom_civilisation = ?,nom_monument= ?,description_civilisation = ?,date_debut_civilisation = ?,date_fin_civilisation = ?,id_utilisateur = ? where id_civilisation = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_civilisation());
        ps.setString(2, t.getNom_monument());
        ps.setString(3, t.getDescription_civilisation());
        ps.setString(4, t.getDate_debut_civilisation());
        ps.setString(5, t.getDate_fin_civilisation());
        ps.setInt(6, t.getId_utilisateur());
        ps.setInt(7, t.getId_civilisation());
       
        ps.executeUpdate();
    }

    @Override
    public Boolean supprimer(civilisation c) throws SQLException {
        Boolean ok=false;
        try {
            String req = "DELETE FROM civilisation WHERE id_civilisation  = "+c.getId_civilisation();
            Statement st = cnx.createStatement();  
            st.executeUpdate(req);
            ok=true;
        } catch (SQLException ex) {
            Logger.getLogger(civilisationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    @Override
    public List<civilisation> recuperer() throws SQLException {
        ObservableList<civilisation> civilisations = FXCollections.observableArrayList();
        String s = "select * from civilisation";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            civilisation u = new civilisation();
            u.setId_civilisation(rs.getInt(1));
            u.setNom_civilisation(rs.getString(2));
            u.setNom_monument(rs.getString(3));
            u.setDescription_civilisation(rs.getString(6));
            u.setDate_debut_civilisation(rs.getString(4));
            u.setDate_fin_civilisation(rs.getString(5));
            u.setId_civilisation(rs.getInt(7));
             u.setImage(rs.getString(8));
           
            civilisations.add(u);
        }  System.out.println("test222");
        
        return civilisations;
    }

}
