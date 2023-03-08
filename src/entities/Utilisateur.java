/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Ihebc
 */
public class Utilisateur {
    private int id_utilisateur,id_role;
    private String nom_utilisateur,prenom_utilisateur,mail_utilisateur,numero_telephone,pseudo,mtp;
    private String date_naissance,nom_Role,image_user;

    public Utilisateur(int id_utilisateur, int id_role, String nom_utilisateur, String prenom_utilisateur, String mail_utilisateur, String numero_telephone, String pseudo, String mtp, String date_naissance) {
        this.id_utilisateur = id_utilisateur;
        this.id_role = id_role;
        this.nom_utilisateur = nom_utilisateur;
        this.prenom_utilisateur = prenom_utilisateur;
        this.mail_utilisateur = mail_utilisateur;
        this.numero_telephone = numero_telephone;
        this.pseudo = pseudo;
        this.mtp = mtp;
        this.date_naissance = date_naissance;
    }

    public Utilisateur(int id_role, String nom_utilisateur, String prenom_utilisateur, String mail_utilisateur, String numero_telephone, String pseudo, String mtp, String date_naissance) {
        this.id_role = id_role;
        this.nom_utilisateur = nom_utilisateur;
        this.prenom_utilisateur = prenom_utilisateur;
        this.mail_utilisateur = mail_utilisateur;
        this.numero_telephone = numero_telephone;
        this.pseudo = pseudo;
        this.mtp = mtp;
        this.date_naissance = date_naissance;
    }

    public Utilisateur(int id_utilisateur, String nom_utilisateur, String prenom_utilisateur, String mail_utilisateur, String numero_telephone, String pseudo, String date_naissance) {
        this.id_utilisateur = id_utilisateur;
        this.nom_utilisateur = nom_utilisateur;
        this.prenom_utilisateur = prenom_utilisateur;
        this.mail_utilisateur = mail_utilisateur;
        this.numero_telephone = numero_telephone;
        this.pseudo = pseudo;
        this.date_naissance = date_naissance;
    }

    public Utilisateur(int id_utilisateur, String mtp) {
        this.id_utilisateur = id_utilisateur;
        this.mtp = mtp;
    }

    
    
    public Utilisateur() {
    }

    public String getImage_user() {
        return image_user;
    }

    public void setImage_user(String image_user) {
        this.image_user = image_user;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public String getPrenom_utilisateur() {
        return prenom_utilisateur;
    }

    public void setPrenom_utilisateur(String prenom_utilisateur) {
        this.prenom_utilisateur = prenom_utilisateur;
    }

    public String getMail_utilisateur() {
        return mail_utilisateur;
    }

    public void setMail_utilisateur(String mail_utilisateur) {
        this.mail_utilisateur = mail_utilisateur;
    }

    public String getNumero_telephone() {
        return numero_telephone;
    }

    public void setNumero_telephone(String numero_telephone) {
        this.numero_telephone = numero_telephone;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMtp() {
        return mtp;
    }

    public void setMtp(String mtp) {
        this.mtp = mtp;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getNom_Role() {
        return nom_Role;
    }

    public void setNom_Role(String nom_Role) {
        this.nom_Role = nom_Role;
    }
   

    @Override
    public String toString() {
        return "Utilisateur{" + "id_utilisateur=" + id_utilisateur + ", id_role=" + id_role + ", nom_utilisateur=" + nom_utilisateur + ", prenom_utilisateur=" + prenom_utilisateur + ", mail_utilisateur=" + mail_utilisateur + ", numero_telephone=" + numero_telephone + ", pseudo=" + pseudo + ", mtp=" + mtp + ", date_naissance=" + date_naissance + '}';
    }

    
    
    
}
