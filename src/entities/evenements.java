/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author MSI
 */
public class evenements {
    
     private int id_evenement;
    private String titre_evenement ;
    private String type_evenement ; 
    private String date_evenement ;
    private String lieux_evenement ;
    private Double prix_evenement;
    private invites id_invite;
    private String description_evenement ; 
    private Utilisateur id_utilisateur;

    public evenements() {
    }

    public evenements(String titre_evenement, String type_evenement, String lieux_evenement, Double prix_evenement, invites id_invite, String description_evenement, Utilisateur id_utilisateur) {
        this.titre_evenement = titre_evenement;
        this.type_evenement = type_evenement;
        this.lieux_evenement = lieux_evenement;
        this.prix_evenement = prix_evenement;
        this.id_invite = id_invite;
        this.description_evenement = description_evenement;
        this.id_utilisateur = id_utilisateur;
    }

    public evenements(int id_evenement, String titre_evenement, String type_evenement, String date_evenement, String lieux_evenement, Double prix_evenement, invites id_invite, String description_evenement, Utilisateur id_utilisateur) {
        this.id_evenement = id_evenement;
        this.titre_evenement = titre_evenement;
        this.type_evenement = type_evenement;
        this.date_evenement = date_evenement;
        this.lieux_evenement = lieux_evenement;
        this.prix_evenement = prix_evenement;
        this.id_invite = id_invite;
        this.description_evenement = description_evenement;
        this.id_utilisateur = id_utilisateur;
    }

    
    public evenements(String titre_evenement, String type_evenement, String date_evenement, String lieux_evenement, Double prix_evenement, invites id_invite, String description_evenement, Utilisateur id_utilisateur) {
        this.titre_evenement = titre_evenement;
        this.type_evenement = type_evenement;
        this.date_evenement = date_evenement;
        this.lieux_evenement = lieux_evenement;
        this.prix_evenement = prix_evenement;
        this.id_invite = id_invite;
        this.description_evenement = description_evenement;
        this.id_utilisateur = id_utilisateur;
    }
    
    
   
    
    
    
    
    

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public String getTitre_evenement() {
        return titre_evenement;
    }

    public void setTitre_evenement(String titre_evenement) {
        this.titre_evenement = titre_evenement;
    }

    public String getType_evenement() {
        return type_evenement;
    }

    public void setType_evenement(String type_evenement) {
        this.type_evenement = type_evenement;
    }

    public String getDate_evenement() {
        return date_evenement;
    }

    public void setDate_evenement(String date_evenement) {
        this.date_evenement = date_evenement;
    }

    public String getLieux_evenement() {
        return lieux_evenement;
    }

    public void setLieux_evenement(String lieux_evenement) {
        this.lieux_evenement = lieux_evenement;
    }

    public Double getPrix_evenement() {
        return prix_evenement;
    }

    public void setPrix_evenement(Double prix_evenement) {
        this.prix_evenement = prix_evenement;
    }
    
    
    

    public int getId_invite() {
        return id_invite.getId_invite();
    }
    
    public invites getinvites() {
        return id_invite;
    }
    
    public void setId_invite(invites id_invite) {
        this.id_invite = id_invite;
    }

    
    
    
    public String getDescription_evenement() {
        return description_evenement;
    }

    public void setDescription_evenement(String description_evenement) {
        this.description_evenement = description_evenement;
    }

    
    public int getId_utilisateur() {
        return id_utilisateur.getId_utilisateur();
    }
    
    public Utilisateur getUtilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(Utilisateur id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    @Override
    public String toString() {
        return "evenements{" + "id_evenement=" + id_evenement + ", titre_evenement=" + titre_evenement + ", type_evenement=" + type_evenement + ", date_evenement=" + date_evenement + ", lieux_evenement=" + lieux_evenement + ", prix_evenement=" + prix_evenement + ", id_invite=" + id_invite + ", description_evenement=" + description_evenement + ", id_utilisateur=" + id_utilisateur + '}';
    }
    
    
    
    

    
}
