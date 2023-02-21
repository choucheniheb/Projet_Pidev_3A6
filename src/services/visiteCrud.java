/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.invites;
import entities.visites;
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
public class visiteCrud implements InterfaceViste {
    
    
    
    
    Connection cnx2 ;
    
    public visiteCrud() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    
    @Override
    public void ajouter(visites v){
        
        try {
            String requete2 = "INSERT INTO visites(id_circuit, id_civilisation ) VALUES ( ?, ? )" ;
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, v.getId_circuit());
            pst.setInt(2, v.getId_civilisation());
            pst.executeUpdate();
            System.out.println("visite ajouter");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    }
    
    
    public void modifier(int id_circuit , int id_civilisation , int id_visite) {

        try {
            String requete2 = " UPDATE visites SET id_circuit = ? , id_civilisation = ?  WHERE id_visite = ? " ;
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1,id_circuit);
            pst.setInt(2,id_civilisation);
            pst.setInt(3,id_visite);
            pst.executeUpdate();
            System.out.println("visite modifie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }

    }

    public void supprimer(int id_visite) {
   try {
            String requete2 = " DELETE FROM visites WHERE id_visite = ? " ;
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1,id_visite);
            pst.executeUpdate();
            System.out.println("visite supprime");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }    
    }

    
    public List<visites> afficher() {
                    List<visites> myList = new  ArrayList<> ();

        try {
            String requete2 = "select * from visites " ;
            Statement st = cnx2.createStatement() ;
            ResultSet rs = st.executeQuery(requete2);
            while(rs.next())
            {
                visites v = new  visites() ;
                v.setId_visite(rs.getInt(1));
                v.setId_circuit(rs.getInt(2));
                v.setId_civilisation(rs.getInt(3));
                

                myList.add(v) ; 
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
        return myList ;
    }

   
    
    
    
    
    
}
