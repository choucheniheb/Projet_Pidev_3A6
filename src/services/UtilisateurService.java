/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Utilisateur;
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
 * @author Ihebc
 */
public class UtilisateurService implements IService<Utilisateur>{
    Connection cnx;

    public UtilisateurService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Utilisateur t) throws SQLException {
        String req = "INSERT INTO utilisateurs (nom_utilisateur, prenom_utilisateur, mail_utilisateur, numero_telephone, mtp, date_naissance, pseudo, id_role) VALUES ('"+t.getNom_utilisateur()+"','"+ t.getPrenom_utilisateur()+"','"+t.getMail_utilisateur()+"','"+t.getNumero_telephone()+"','"+ t.getMtp()+"','"+t.getDate_naissance()+"','"+t.getPseudo()+"',"+t.getId_role()+");";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Utilisateur t) throws SQLException {
        String req = "UPDATE utilisateurs SET nom_utilisateur = ?,prenom_utilisateur = ?,mail_utilisateur = ?,numero_telephone = ?,mtp = ?,date_naissance = ?,pseudo = ?,id_role  = ? where id_utilisateur = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_utilisateur());
        ps.setString(2, t.getPrenom_utilisateur());
        ps.setString(3, t.getMail_utilisateur());
        ps.setString(4, t.getNumero_telephone());
        ps.setString(5, t.getMtp());
        ps.setString(6, t.getDate_naissance());
        ps.setString(7, t.getPseudo());
        ps.setInt(8, t.getId_role());
        ps.setInt(9, t.getId_utilisateur());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(Utilisateur t) throws SQLException {
        String req = "DELETE FROM utilisateurs WHERE id_utilisateur = "+t.getId_utilisateur();
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<Utilisateur> recuperer(Utilisateur t) throws SQLException {
        List<Utilisateur> Utilisateurs = new ArrayList<>();
        String s = "select * from Utilisateurs";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Utilisateur u = new Utilisateur();
            u.setNom_utilisateur(rs.getString("nom_Utilisateur"));
            u.setPrenom_utilisateur(rs.getString("prenom_Utilisateur"));
            u.setMail_utilisateur(rs.getString("mail_Utilisateur"));
            u.setId_role(rs.getInt("id_role"));
            u.setId_utilisateur(rs.getInt("id_Utilisateur"));
            u.setNumero_telephone(rs.getString("numero_telephone"));
            u.setMtp(rs.getString("mtp"));
            u.setPseudo(rs.getString("pseudo"));
            u.setDate_naissance(rs.getString("date_naissance"));
            Utilisateurs.add(u);
        }
        return Utilisateurs;
    }
}
