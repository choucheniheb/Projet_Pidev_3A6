/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Abonnement;
import entity.Reservation;
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
 * @author wajdi
 */
public class AbonnementService implements IService<Abonnement> {

    Connection cnx;

    public AbonnementService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Abonnement t) throws SQLException {
        String req = "INSERT INTO abonnements(date_ab, date_exp, categorie_ab, id_utilisateur) VALUES ('" + t.getDate_ab() + "','" + t.getDate_exp() + "','" + t.getCategorie() + "'," + t.getId_utilisateur() + ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Abonnement t) throws SQLException {

        String req = "UPDATE abonnements SET date_ab = ?,date_exp = ?,categorie_ab = ?,id_utilisateur = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getDate_ab());
        ps.setString(2, t.getDate_exp());
        ps.setString(3, t.getCategorie());
        ps.setInt(4, t.getId_utilisateur());

        ps.executeUpdate();
    }

    @Override
    public Boolean supprimer(Abonnement t) throws SQLException {
        Boolean ok=false;
        try {
            String req = "DELETE FROM abonnements WHERE id_ab = " + t.getId_ab();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            ok=true;
        } catch (SQLException ex) {
            Logger.getLogger(AbonnementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    @Override
    public List<Abonnement> recuperer() throws SQLException {
        List<Abonnement> Abonnements = new ArrayList<>();
        String s = "select * from Abonnements";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Abonnement u = new Abonnement();
            u.setId_ab(rs.getInt("id_ab"));
            u.setDate_ab(rs.getString("date_ab"));
            u.setDate_exp(rs.getString("date_exp"));
            u.setCategorie(rs.getString("categorie_ab"));
            u.setId_utilisateur(rs.getInt("id_utilisateur"));

            Abonnements.add(u);
        }
        return Abonnements;
    }
    
    public Abonnement chercherAbonnement(Abonnement t) throws SQLException {
        Abonnement u = new Abonnement() ;
        String s = "select * from Abonnements where id_ab = "+t.getId_ab();
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            u.setId_ab(rs.getInt("id_ab"));
            u.setDate_ab(rs.getString("date_ab"));
            u.setDate_exp(rs.getString("date_exp"));
            u.setCategorie(rs.getString("categorie_ab"));
            u.setId_utilisateur(rs.getInt("id_utilisateur"));

            
        }
        return u;
    }

}
