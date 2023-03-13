/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Reservation;
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
 * @author wajdi
 */
public class ReservationService implements IService <Reservation> {
    
    Connection cnx;

    public ReservationService() {
       cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Reservation t) throws SQLException {
        String req = "INSERT INTO reservations(date_res, prix_res, id_evenement, id_utilisateur, id_ab) VALUES ('"+t.getDate_res()+"',"+t.getPrix_res()+","+t.getId_evenement()+","+t.getId_utilisateur()+","+t.getId_ab()+")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Reservation t) throws SQLException {
         String req = "UPDATE reservations SET date_res = ?,prix_res = ?,id_evenement = ?,id_utilisateur = ?,id_ab = ? where id_res = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getDate_res());
        ps.setDouble(2, t.getPrix_res());
        ps.setInt(3, t.getId_evenement());
        ps.setInt(4, t.getId_utilisateur());
        ps.setInt(5, t.getId_ab());
        ps.setInt(6, t.getId_res());
        
        ps.executeUpdate();
    }

    @Override
    public Boolean supprimer(Reservation t) throws SQLException {
        Boolean ok=false;
        try{
        String req = "DELETE FROM reservations WHERE id_res = "+t.getId_res();
        Statement st = cnx.createStatement();
        st.executeUpdate(req); 
        ok=true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return ok;
    }

    @Override
    public List<Reservation> recuperer() throws SQLException {
         List<Reservation> Reservations = new ArrayList<>();
        String s = "select * from reservations";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Reservation u = new Reservation();
            u.setId_res(rs.getInt("id_res"));
            u.setDate_res(rs.getString("date_res"));
            u.setPrix_res(rs.getDouble("prix_res"));
            u.setId_evenement(rs.getInt("id_evenement"));
            u.setId_utilisateur(rs.getInt("id_utilisateur"));
            u.setId_ab(rs.getInt("id_ab"));
            
            Reservations.add(u);
        }
        return Reservations;
    }
    
    public List<String> chercherEvenement() {
                    List<String> myList = new  ArrayList<> ();

        try {
            String requete3 = "select titre_evenement from evenements " ;
            Statement st = cnx.createStatement() ;
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next())
            {
                myList.add(rs.getString("titre_evenement")) ; 
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        
        }
    
        return myList ;
    }
    
    
    public Reservation chercherReservation(Reservation t) throws SQLException {
        String s = "select * from reservations where id_res = "+t.getId_res();
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        Reservation u = new Reservation();
        while(rs.next()){
            u.setId_res(rs.getInt("id_res"));
            u.setDate_res(rs.getString("date_res"));
            u.setPrix_res(rs.getDouble("prix_res"));
            u.setId_evenement(rs.getInt("id_evenement"));
            u.setId_utilisateur(rs.getInt("id_utilisateur"));
            u.setId_ab(rs.getInt("id_ab"));
        }
        return u;
    }
    
   

}
