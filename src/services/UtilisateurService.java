/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Permission;
import entities.Role;
import entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import test.TestFX;

/**
 *
 * @author Ihebc
 */
public class UtilisateurService {

    Connection cnx;

    public UtilisateurService() {
        cnx = MyDB.getInstance().getCnx();
    }

    public void ajouter(Utilisateur t) throws SQLException {
        try {
            String req = "INSERT INTO utilisateurs (nom_utilisateur, prenom_utilisateur, mail_utilisateur, numero_telephone, mtp, date_naissance, pseudo, id_role) VALUES ('" + t.getNom_utilisateur() + "','" + t.getPrenom_utilisateur() + "','" + t.getMail_utilisateur() + "','" + t.getNumero_telephone() + "','" + PasswordEncryption.encryptPassword(t.getMtp(), "hidden tunisia") + "','" + t.getDate_naissance() + "','" + t.getPseudo() + "'," + t.getId_role() + ");";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (Exception ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ajouterImage(String image) throws SQLException {
        System.out.println(image);
        String req = "UPDATE utilisateurs SET image = ? where id_utilisateur = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, image);
        ps.setInt(2, TestFX.getId_user());
        ps.executeUpdate();
    }
    

    public void modifierPofile(Utilisateur t) throws SQLException {
        String req = "UPDATE utilisateurs SET nom_utilisateur = ?,prenom_utilisateur = ?,mail_utilisateur = ?,numero_telephone = ?,date_naissance = ?,pseudo = ? where id_utilisateur = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_utilisateur());
        ps.setString(2, t.getPrenom_utilisateur());
        ps.setString(3, t.getMail_utilisateur());
        ps.setString(4, t.getNumero_telephone());
        ps.setString(5, t.getDate_naissance());
        ps.setString(6, t.getPseudo());
        ps.setInt(7, t.getId_utilisateur());
        ps.executeUpdate();
    }

    public void modifierPassword(Utilisateur t) throws SQLException {
        try {
            String req = "UPDATE utilisateurs SET mtp = ? where id_utilisateur = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, PasswordEncryption.encryptPassword(t.getMtp(), "hidden tunisia"));
            ps.setInt(2, t.getId_utilisateur());
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boolean supprimer(Utilisateur t) throws SQLException {
        Boolean ok = false;
        try {
            String req = "DELETE FROM utilisateurs WHERE id_utilisateur = " + t.getId_utilisateur();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            ok = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ok;
    }

    public List<Utilisateur> recuperer() throws SQLException {
        List<Utilisateur> Utilisateurs = new ArrayList<>();
        String s = "select * from Utilisateurs";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
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

    public Utilisateur chercherUtlisateur(int id) throws SQLException {
        String s = "select * from Utilisateurs where id_utilisateur = " + id;
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        Utilisateur u = new Utilisateur();
        if (rs.next()) {
            u.setNom_utilisateur(rs.getString("nom_Utilisateur"));
            u.setPrenom_utilisateur(rs.getString("prenom_Utilisateur"));
            u.setMail_utilisateur(rs.getString("mail_Utilisateur"));
            u.setId_role(rs.getInt("id_role"));
            u.setId_utilisateur(rs.getInt("id_Utilisateur"));
            u.setNumero_telephone(rs.getString("numero_telephone"));
            u.setMtp(rs.getString("mtp"));
            u.setPseudo(rs.getString("pseudo"));
            u.setDate_naissance(rs.getString("date_naissance"));
            u.setImage_user(rs.getString("image"));
        }
        return u;
    }

    public Role chercherRoleUtlisateur(int id) throws SQLException {
        String s = "select * from roles e1 join utilisateurs e2 on e1.id_role= e2.id_role and e2.id_Utilisateur= " + id;
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        Role r = new Role();
        if (rs.next()) {
            r.setNomRole(rs.getString("nom_role"));
            r.setIdRole(rs.getInt("id_role"));
            r.setDiscriptionRole(rs.getString("description_Role"));
            List<Permission> p = new ArrayList<>();
            String s1 = "select * from permissions e1 join role_pemission e2 on (e2.id_permission = e1.id_permission) and e2.id_role = " + r.getIdRole();
            Statement st1 = cnx.createStatement();
            ResultSet rs1 = st1.executeQuery(s1);
            while (rs1.next()) {
                Permission p1 = new Permission();
                p1.setDescriptionPermission(rs1.getString("description_permission"));
                p1.setNomPermission(rs1.getString("nom_permission"));
                p1.setIdModule(rs1.getInt("id_module"));
                p1.setIdPermission(rs1.getInt("id_permission"));
                p.add(p1);
            }
            r.setPermission(p);
        }
        return r;
    }

    public void resetPassword(String userEmail, String newPassword) throws MessagingException {

        try {
            // Adresse e-mail de l'expéditeur
            String from = "chouchene.iheb00@gmail.com";

            // Informations d'authentification pour se connecter au serveur SMTP
            final String username = "chouchene.iheb00@gmail.com";
            final String password = "tooihltbznliuvws";

            // Configuration des propriétés pour la session de messagerie
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            // Création de la session de messagerie
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            // Création du message de réinitialisation de mot de passe
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(userEmail));
            message.setSubject("Réinitialisation de mot de passe");
            message.setText("Bonjour,\n\nVous avez demandé une réinitialisation de votre mot de passe. Votre nouveau mot de passe est : " + PasswordEncryption.decryptPassword(newPassword, "hidden tunisia") + "\n\nCordialement,\nL'équipe de support");

            // Envoi du message de réinitialisation de mot de passe
            Transport.send(message);

            System.out.println("Le message de réinitialisation de mot de passe a été envoyé avec succès.");
        } catch (Exception ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
