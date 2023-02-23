/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author msi
 */
public class Commentaire {
    private int id_commentaire;
    private String contenu_commentaire;
    private String date_commentaire;
    private int id_utilisateur;
    private int id_status;

    public Commentaire() {
    }

    public Commentaire(int id_commentaire, String contenu_commentaire, String date_commentaire, int id_utilisateur, int id_status) {
        this.id_commentaire = id_commentaire;
        this.contenu_commentaire = contenu_commentaire;
        this.date_commentaire = date_commentaire;
        this.id_utilisateur = id_utilisateur;
        this.id_status = id_status;
    }

    public Commentaire(String contenu_commentaire, String date_commentaire, int id_utilisateur, int id_status) {
        this.contenu_commentaire = contenu_commentaire;
        this.date_commentaire = date_commentaire;
        this.id_utilisateur = id_utilisateur;
        this.id_status = id_status;
    }

    public Commentaire(String contenu_commentaire, int id_utilisateur, int id_status) {
        this.contenu_commentaire = contenu_commentaire;
        this.id_utilisateur = id_utilisateur;
        this.id_status = id_status;
    }

    public Commentaire(int id_commentaire, String contenu_commentaire, int id_utilisateur, int id_status) {
        this.id_commentaire = id_commentaire;
        this.contenu_commentaire = contenu_commentaire;
        this.id_utilisateur = id_utilisateur;
        this.id_status = id_status;
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public String getContenu_commentaire() {
        return contenu_commentaire;
    }

    public void setContenu_commentaire(String contenu_commentaire) {
        this.contenu_commentaire = contenu_commentaire;
    }

    public String getDate_commentaire() {
        return date_commentaire;
    }

    public void setDate_commentaire(String date_commentaire) {
        this.date_commentaire = date_commentaire;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    @Override
    public String toString() {
        return "commentaire{" + "id_commentaire=" + id_commentaire + ", contenu_commentaire=" + contenu_commentaire + ", date_commentaire=" + date_commentaire + ", id_utilisateur=" + id_utilisateur + ", id_status=" + id_status + '}';
    }
    
}
