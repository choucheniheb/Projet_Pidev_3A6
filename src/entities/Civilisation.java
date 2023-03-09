/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author nourh
 */
public class Civilisation {
    
    private int id_civilisation;
    private int id_utilisateur;
    private String nom_civilisation , nom_monument,description_civilisation; 
    private String date_debut_civilisation , date_fin_civilisation;
   

   

    public Civilisation() {
    }

    public Civilisation(int id_civilisation, String nom_civilisation, String nom_monument, String description_civilisation, String date_debut_civilisation, String date_fin_civilisation,int id_utilisateur) {
        this.id_civilisation = id_civilisation;
        this.nom_civilisation = nom_civilisation;
        this.nom_monument = nom_monument;
        this.description_civilisation = description_civilisation;
        this.date_debut_civilisation = date_debut_civilisation;
        this.date_fin_civilisation = date_fin_civilisation;
         this.id_utilisateur = id_utilisateur;
    }

    public Civilisation(String nom_civilisation, String nom_monument, String description_civilisation, String date_debut_civilisation, String date_fin_civilisation, int id_utilisateur) {
        this.nom_civilisation = nom_civilisation;
        this.nom_monument = nom_monument;
        this.description_civilisation = description_civilisation;
        this.date_debut_civilisation = date_debut_civilisation;
        this.date_fin_civilisation = date_fin_civilisation;
        this.id_utilisateur=id_utilisateur;
    }

    public int getId_civilisation() {
        return id_civilisation;
    }

    public void setId_civilisation(int id_civilisation) {
        this.id_civilisation = id_civilisation;
    }

    public String getNom_civilisation() {
        return nom_civilisation;
    }

    public void setNom_civilisation(String nom_civilisation) {
        this.nom_civilisation = nom_civilisation;
    }

    public String getNom_monument() {
        return nom_monument;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }
    

    public void setNom_monument(String nom_monument) {
        this.nom_monument = nom_monument;
    }

    public String getDescription_civilisation() {
        return description_civilisation;
    }

    public void setDescription_civilisation(String description_civilisation) {
        this.description_civilisation = description_civilisation;
    }

    public String getDate_debut_civilisation() {
        return date_debut_civilisation;
    }

    public void setDate_debut_civilisation(String date_debut_civilisation) {
        this.date_debut_civilisation = date_debut_civilisation;
    }

    public String getDate_fin_civilisation() {
        return date_fin_civilisation;
    }

    public void setDate_fin_civilisation(String date_fin_civilisation) {
        this.date_fin_civilisation = date_fin_civilisation;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    @Override
    public String toString() {
        return "Civilisation{" + "id_civilisation=" + id_civilisation + ", id_utilisateur=" + id_utilisateur + ", nom_civilisation=" + nom_civilisation + ", nom_monument=" + nom_monument + ", description_civilisation=" + description_civilisation + ", date_debut_civilisation=" + date_debut_civilisation + ", date_fin_civilisation=" + date_fin_civilisation + '}';
    }

    
  
    
}

