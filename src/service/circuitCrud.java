/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Utilisateur;
import entity.circuit;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import test.TestFX;

/**
 *
 * @author MSI
 */
public class circuitCrud implements InterfaceCircuit {
    
    
    Connection cnx2 ;
    
    public circuitCrud() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    
    @Override
    public void ajouter(circuit c){
        
        try {
            String requete3 = "INSERT INTO circuits(  point_depat_circuit, date_debut_circuit ,date_fin_circuit, nbr_place_dispo , description_circuit ,nbr_jour_circuit, id_utilisateur  ,  nom_circuit  ) VALUES (  ? ,?, ?,?,?,?,?,?)" ;
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            pst.setString(1, c.getPoint_depart_circuit());
            pst.setString(2, c.getDate_debut_circuit());
            pst.setString(3, c.getDate_fin_circuit());
            pst.setInt(4, c.getNbr_place_dispo());
            pst.setString(5, c.getDescription_circuit());
            pst.setInt(6, c.getNbr_jour_circuit());
            pst.setInt(7, c.getId_utilisateur());
            pst.setString(8, c.getNom_circuit());
            
            


            pst.executeUpdate();
            System.out.println("circuit ajouter");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    }
    
    
    public void modifier( String point_depat_circuit , String date_debut_circuit , String date_fin_circuit , int nbr_place_dispo , String description_circuit , int nbr_jour_circuit , String description_evenement , int id_utilisateur , String nom_circuit, int id_circuit) {

        try {
            String requete2 = " UPDATE circuits SET point_depat_circuit = ? , date_debut_circuit = ? , date_fin_circuit = ? , nbr_place_dispo = ? , description_circuit = ? , nbr_jour_circuit = ? , id_utilisateur = ? ,  nom_circuit = ?  WHERE id_circuit = ? " ;
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1,point_depat_circuit);
            pst.setString(2,date_debut_circuit);
            pst.setString(3,date_fin_circuit);
            pst.setInt(4,nbr_place_dispo);
            pst.setString(5,description_circuit);
            pst.setInt(6,nbr_jour_circuit);
            pst.setInt(7,id_utilisateur);
            pst.setString(8,nom_circuit);
            pst.setInt(9,id_circuit);
            
            pst.executeUpdate();
            System.out.println("circuit modifie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }

    }

    @Override
    public Boolean supprimer(int id_circuit) {
        Boolean ok=false;
        try {
            String requete3 = " DELETE FROM circuits WHERE id_circuit = ? " ;
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            pst.setInt(1,id_circuit);
            pst.executeUpdate();
            System.out.println("circuit supprime");
            ok=true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        
        }  
        return ok;
    }

    
    @Override
    public List<circuit> afficher() {
                    List<circuit> myList = new  ArrayList<> ();

        try {
            String requete3 = "select * from circuits " ;
            Statement st = cnx2.createStatement() ;
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next())
            {
                circuit c = new  circuit() ;
                c.setId_circuit(rs.getInt(1));
                c.setPoint_depart_circuit(rs.getString(2));
                c.setDate_debut_circuit(rs.getString(3));
                c.setDate_fin_circuit(rs.getString(4));
                c.setNbr_place_dispo(rs.getInt(5));
                c.setDescription_circuit(rs.getString(6));
                c.setNbr_jour_circuit(rs.getInt(7));
              //c.setId_utilisateur(rs.getInt(8));
                Utilisateur user = new Utilisateur();
                user.setId_utilisateur(rs.getInt(8));
                c.setId_utilisateur(TestFX.getId_user());
                
                
                c.setNom_circuit(rs.getString(9));
                


                

                myList.add(c) ; 
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
        return myList ;
    }
    
}

    
