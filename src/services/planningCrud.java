/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.planning;
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
public class planningCrud implements InterfacePlanning {
    
    
    Connection cnx2 ;
    
    public planningCrud() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    
    @Override
    public void ajouter(planning p){
        
        try {
            String requete2 = "INSERT INTO planning( id_planning , id_circuit, id_evenement ,resto, hotel , emplacement) VALUES ( ? ,?, ?,?,?,?)" ;
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, p.getId_planning());
            pst.setInt(2, p.getId_circuit());
            pst.setInt(3, p.getId_evenement());
            pst.setString(4, p.getResto());
            pst.setString(5, p.getHotel());
            pst.setString(6, p.getEmplacement());


            pst.executeUpdate();
            System.out.println("planning ajouter");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    }
    
    
    @Override
    public void modifier( int id_circuit , int id_evenement , String resto , String hotel , String emplacement ,int id_planning ) {

        try {
            String requete2 = " UPDATE planning SET id_circuit = ? , id_evenement = ? , resto = ? , hotel = ? , emplacement = ? WHERE id_planning = ? " ;
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1,id_circuit);
            pst.setInt(2,id_evenement);
            pst.setString(3,resto);
            pst.setString(4,hotel);
            pst.setString(5,emplacement);
            pst.setInt(6,id_planning);
            
            pst.executeUpdate();
            System.out.println("planning modifie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }

    }

    @Override
    public Boolean supprimer(int id_planning) {
                Boolean ok=false;

   try {
            String requete3 = " DELETE FROM planning WHERE id_planning = ? " ;
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            pst.setInt(1,id_planning);
            pst.executeUpdate();
            System.out.println("planning supprime");
            ok=true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());       
        }  
        return ok;
    }

    
    @Override
    public List<planning> afficher() {
                    List<planning> myList = new  ArrayList<> ();

        try {
            String requete3 = "select * from planning " ;
            Statement st = cnx2.createStatement() ;
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next())
            {
                planning P = new  planning() ;
                P.setId_planning(rs.getInt(1));
                P.setId_circuit(rs.getInt(2));
                P.setId_evenement(rs.getInt(3));
                P.setResto(rs.getString(4));
                P.setHotel(rs.getString(5));
                P.setEmplacement(rs.getString(6));


                

                myList.add(P) ; 
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
        return myList ;
    }

    
}
