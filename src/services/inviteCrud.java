/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.invites;
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
public class inviteCrud implements InterfaceInvite {
    
    
    Connection cnx2 ;
    
    public inviteCrud() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    
    @Override
    public void ajouter(invites i){
        
        try {
            String requete1 = "INSERT INTO invites( nom_invite, prenom_invite, type_invite) VALUES ( ?, ?, ?)" ;
            PreparedStatement pst = cnx2.prepareStatement(requete1);
            pst.setString(1, i.getNom_invite());
            pst.setString(2, i.getPrenom_invite());             
            pst.setString(3, i.getType_invite());
            pst.executeUpdate();
            System.out.println("invite ajouter");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    }
    
    
    public void modifier(String nom_invite , String prenom_invite ,  String type_invite , int id_invite) {

        try {
            String requete1 = " UPDATE invites SET nom_invite = ? , prenom_invite = ? , type_invite = ? WHERE id_invite = ? " ;
            PreparedStatement pst = cnx2.prepareStatement(requete1);
            pst.setString(1,nom_invite);
            pst.setString(2,prenom_invite);
            pst.setString(3,type_invite);
            pst.setInt(4,id_invite);
            pst.executeUpdate();
            System.out.println("invite modifie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }

    }

    @Override
    public void supprimer(int id_invite) {
   try {
            String requete2 = " DELETE FROM invites WHERE id_invite = ? " ;
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1,id_invite);
            pst.executeUpdate();
            System.out.println("invite supprime");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }    
    }

    
    @Override
    public List<invites> afficher() {
                    List<invites> myList = new  ArrayList<> ();

        try {
            String requete1 = "select * from invites " ;
            Statement st = cnx2.createStatement() ;
            ResultSet rs = st.executeQuery(requete1);
            while(rs.next())
            {
                invites i = new  invites() ;
                i.setId_invite(rs.getInt(1));
                i.setNom_invite(rs.getString(2));
                i.setPrenom_invite(rs.getString(3));
                i.setType_invite(rs.getString(4));

                myList.add(i) ; 
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
        return myList ;
    }

   

 
    
    
    
    
}
