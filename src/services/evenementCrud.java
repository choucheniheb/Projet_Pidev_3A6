/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.evenements;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class evenementCrud implements InterfaceEvenement {
    
    
    Connection cnx2 ;
    
    public evenementCrud() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    
    public void ajouter(evenements e){
        
        try {
            String requete3 = "INSERT INTO evenements(  titre_evenement, type_evenement ,date_evenement, lieux_evenement , prix_evenement ,id_invite, description_evenement , id_utilisateur  ) VALUES (  ? ,? ,? ,?, ?,?,?,?)" ;
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            pst.setString(1, e.getTitre_evenement());
            pst.setString(2, e.getType_evenement());
            pst.setString(3, e.getDate_evenement());
            pst.setString(4, e.getLieux_evenement());
            pst.setDouble(5, e.getPrix_evenement());
            pst.setInt(6, e.getId_invite());
            pst.setString(7, e.getDescription_evenement());
            pst.setInt(8, e.getId_utilisateur());
            


            pst.executeUpdate();
            System.out.println("evenement ajouter");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    }
    
    
    public void modifier( String titre_evenement , String type_evenement , String date_evenement , String lieux_evenement , double prix_evenement , int id_invite , String description_evenement , int id_utilisateur , int id_evenement) {

        try {
            String requete2 = " UPDATE evenements SET titre_evenement = ? , type_evenement = ? , date_evenement = ? , lieux_evenement = ? , prix_evenement = ? , id_invite = ? , description_evenement = ? , id_utilisateur = ?  WHERE id_evenement = ? " ;
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1,titre_evenement);
            pst.setString(2,type_evenement);
            pst.setString(3,date_evenement);
            pst.setString(4,lieux_evenement);
            pst.setDouble(5,prix_evenement);
            pst.setInt(6,id_invite);
            pst.setString(7,description_evenement);
            pst.setInt(8,id_utilisateur);
            pst.setInt(9,id_evenement);
            
            pst.executeUpdate();
            System.out.println("evenement modifie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }

    }

    public void supprimer(int id_evenement) {
   try {
            String requete3 = " DELETE FROM evenements WHERE id_evenement = ? " ;
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            pst.setInt(1,id_evenement);
            pst.executeUpdate();
            System.out.println("evenement supprime");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }    
    }

    
    public List<evenements> afficher() {
                    List<evenements> myList = new  ArrayList<> ();

        try {
            String requete3 = "select * from evenements " ;
            Statement st = cnx2.createStatement() ;
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next())
            {
                evenements e = new  evenements() ;
                e.setId_evenement(rs.getInt(1));
                e.setTitre_evenement(rs.getString(2));
                e.setType_evenement(rs.getString(3));
                e.setDate_evenement(rs.getString(4));
                e.setLieux_evenement(rs.getString(5));
                e.setPrix_evenement(rs.getDouble(6));
                e.setId_invite(rs.getInt(7));
                e.setDescription_evenement(rs.getString(8));
                e.setId_utilisateur(rs.getInt(9));


                

                myList.add(e) ; 
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
        return myList ;
    }

    
}
