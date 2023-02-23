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
public class Status {
    private int id_status, nbr_likes;
    private String titre_status;
    private String date_status;
    private String contenu_status;
    private String media_status;
    private int id_utilisateur;

    public Status() {
    }

    public Status(int id_status, int nbr_likes, String titre_status, String date_status, String contenu_status, String media_status, int id_utilisateur) {
        this.id_status = id_status;
        this.nbr_likes = nbr_likes;
        this.titre_status = titre_status;
        this.date_status = date_status;
        this.contenu_status = contenu_status;
        this.media_status = media_status;
        this.id_utilisateur = id_utilisateur;
    }

    public Status(int nbr_likes, String titre_status, String date_status, String contenu_status, String media_status, int id_utilisateur) {
        this.nbr_likes = nbr_likes;
        this.titre_status = titre_status;
        this.date_status = date_status;
        this.contenu_status = contenu_status;
        this.media_status = media_status;
        this.id_utilisateur = id_utilisateur;
    }
    

    public Status(int nbr_likes, String titre_status, String contenu_status, String media_status, int id_utilisateur) {
        this.nbr_likes = nbr_likes;
        this.titre_status = titre_status;
        this.contenu_status = contenu_status;
        this.media_status = media_status;
        this.id_utilisateur = id_utilisateur;
    }

    public Status(int id_status, int nbr_likes, String titre_status, String contenu_status, String media_status, int id_utilisateur) {
        this.id_status = id_status;
        this.nbr_likes = nbr_likes;
        this.titre_status = titre_status;
        this.contenu_status = contenu_status;
        this.media_status = media_status;
        this.id_utilisateur = id_utilisateur;
    }
    
    

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public int getNbr_likes() {
        return nbr_likes;
    }

    public void setNbr_likes(int nbr_likes) {
        this.nbr_likes = nbr_likes;
    }

    public String getTitre_status() {
        return titre_status;
    }

    public void setTitre_status(String titre_status) {
        this.titre_status = titre_status;
    }

    public String getDate_status() {
        return date_status;
    }

    public void setDate_status(String date_status) {
        this.date_status = date_status;
    }

    public String getContenu_status() {
        return contenu_status;
    }

    public void setContenu_status(String contenu_status) {
        this.contenu_status = contenu_status;
    }

    public String getMedia_status() {
        return media_status;
    }

    public void setMedia_status(String media_status) {
        this.media_status = media_status;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    @Override
    public String toString() {
        return "status{" + "id_status=" + id_status + ", nbr_likes=" + nbr_likes + ", titre_status=" + titre_status + ", date_status=" + date_status + ", contenu_status=" + contenu_status + ", media_status=" + media_status + ", id_utilisateur=" + id_utilisateur + '}';
    }
    
}
