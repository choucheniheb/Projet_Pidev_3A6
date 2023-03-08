/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author wajdi
 */
public class Abonnement {
    private int id_ab;
    private String date_ab;
    private String date_exp;
    private String categorie;
    private int id_utilisateur;

    public Abonnement() {
    }

    public Abonnement(int id_ab, String date_ab, String date_exp, String categorie, int id_utilisateur) {
        this.id_ab = id_ab;
        this.date_ab = date_ab;
        this.date_exp = date_exp;
        this.categorie = categorie;
        this.id_utilisateur = id_utilisateur;
    }

    public Abonnement(String date_ab, String date_exp, String categorie, int id_utilisateur) {
        this.date_ab = date_ab;
        this.date_exp = date_exp;
        this.categorie = categorie;
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_ab() {
        return id_ab;
    }

    public void setId_ab(int id_ab) {
        this.id_ab = id_ab;
    }

    public String getDate_ab() {
        return date_ab;
    }

    public void setDate_ab(String date_ab) {
        this.date_ab = date_ab;
    }

    public String getDate_exp() {
        return date_exp;
    }

    public void setDate_exp(String date_exp) {
        this.date_exp = date_exp;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "id_ab=" + id_ab + ", date_ab=" + date_ab + ", date_exp=" + date_exp + ", categorie=" + categorie + ", id_utilisateur=" + id_utilisateur + '}';
    }
    
    
    
}
