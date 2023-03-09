/*
 * To change this license header, choose License Headers in Project Properties.


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Nourritures;
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
 * @author Skander
 */
public class nourrituresService implements IService<Nourritures> {

    Connection cnx;

    public nourrituresService() throws SQLException {
        cnx = MyDB.getInstance().getCnx();
    }

    /**
     *
     * @param n
     * @throws SQLException
     */

    @Override
    public void ajouter(Nourritures t) throws SQLException {
         PreparedStatement ps = cnx.prepareStatement ("INSERT INTO Civilisation(nom_nourriture,origine_nourriture,ingrediant,description_nourriture,type_nourriture,prix_nourriture,id_utilisateur,id_civilisation)) VALUES (?,?,?,?,?,?,?,?)");
        ps.setString(1, t.getNom_nourriture());
        ps.setString(2, t.getOrigine_nourriture());
        ps.setString(3, t.getIngrediant());
        ps.setString(4,  t.getDescription_nourriture());
        ps.setString(5, t.getType_nourriture());
         ps.setDouble(6, t.getPrix_nourriture());
         ps.setInt(7, t.getId_utilisateur());
         ps.setInt(8, t.getId_civilisation());
       
        ps.executeUpdate();
   
    } 
   
    @Override
    public void modifier(Nourritures t) throws SQLException {
        PreparedStatement ps = cnx.prepareStatement("UPDATE personne SET nom_nourriture = ?,origine_nourriture = ?,ingrediant = ?,description_nourriture = ?,type_nourriture = ?,prix_nourriture = ? ,id_utilisateur = ? ,id_civilisation = ? where id_nourriture = ?");
        
        ps.setString(1, t.getNom_nourriture());
        ps.setString(2, t.getOrigine_nourriture());
        ps.setString(3, t.getDescription_nourriture());
        ps.setString(4, t.getType_nourriture());
        ps.setString(5, t.getIngrediant());
        ps.setDouble(6,t.getPrix_nourriture());
        ps.setInt(7,t.getId_utilisateur());
         ps.setInt(8,t.getId_civilisation());
       
        ps.executeUpdate();
         System.out.println("succesful ");
        
    }

  
   
    @Override
    public void supprimer(Nourritures t) throws SQLException {

            PreparedStatement s = cnx.prepareStatement("delete from Nourritures where  id_nourritures=?");
            s.setInt(1, t.getId_civilisation());
            s.executeUpdate();
            System.out.println("succesful ");
    }

    
    @Override
    public List<Nourritures> recuperer() throws SQLException {
        List<Nourritures> Nourritures = new ArrayList<>();
        String s = "select * from Nourritures";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Nourritures t = new Nourritures();
            t.setPrix_nourriture(rs.getDouble("prix_nourriture"));
            t.setNom_nourriture(rs.getString("nom_nourriture"));
            t.setOrigine_nourriture(rs.getString("origine_nourriture"));
            t.setIngrediant(rs.getString("ingrediant"));
            t.setDescription_nourriture(rs.getString("description_nourriture"));
            t.setType_nourriture(rs.getString("type_nourriture"));
            t.setId_nourriture(rs.getInt("id_nourriture"));
            t.setId_utilisateur(rs.getInt("id_utilisateur"));
            t.setId_civilisation(rs.getInt("id_civilisation"));
            
            
            
            Nourritures.add(t);
            
        }
        return Nourritures;
    }

}
