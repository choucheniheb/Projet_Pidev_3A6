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
package services;

import entities.civilisation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
         String req = "INSERT INTO civilisation(nom_civilisation, nom_monument, description_civilisation, date_debut_civilisation,date_fin_civilisation,id_utilisateur) VALUES (?,?,?,?,?,?)";

              
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_civilisation());
        ps.setString(2, t.getNom_monument());
        ps.setString(3, t.getDescription_civilisation());
        ps.setString(4,  t.getDate_debut_civilisation());
        ps.setString(5, t.getDate_fin_civilisation());
        ps.setInt(6, t.getId_utilisateur());
       
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
    public Boolean supprimer(civilisation t) throws SQLException {
        Boolean ok=false;
        try {
            String req = "DELETE FROM civilisation WHERE id_civilisation  = "+t.getId_civilisation();
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
         List<civilisation> civilisations = new ArrayList<>();
        String s = "select * from civilisation";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            civilisation u = new civilisation();
            u.setId_civilisation(rs.getInt("id_civilisation"));
            u.setNom_civilisation(rs.getString("Nom_civilisation"));
            u.setNom_monument(rs.getString("Nom_monument"));
            u.setDescription_civilisation(rs.getString("Description_civilisation"));
            u.setDate_debut_civilisation(rs.getString("Date_debut_civilisation"));
            u.setDate_fin_civilisation(rs.getString("Date_fin_civilisation"));
            u.setId_civilisation(rs.getInt("id_utilisateur"));
           
            civilisations.add(u);
        }
        return civilisations;
    }

}
