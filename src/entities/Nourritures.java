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
public class Nourritures {
  
    private int id_nourriture;
    private String nom_nourriture,origine_nourriture,ingrediant,description_nourriture,type_nourriture; 
    private Double prix_nourriture;
    private int id_utilisateur; 
    private int id_civilisation;
    

 public Nourritures() {
    }

    public Nourritures(String nom_nourriture, String origine_nourriture, String ingrediant, String description_nourriture, String type_nourriture, Double prix_nourriture, int id_utilisateur, int id_civilisation) {
        this.nom_nourriture = nom_nourriture;
        this.origine_nourriture = origine_nourriture;
        this.ingrediant = ingrediant;
        this.description_nourriture = description_nourriture;
        this.type_nourriture = type_nourriture;
        this.prix_nourriture = prix_nourriture;
        this.id_utilisateur = id_utilisateur;
        this.id_civilisation = id_civilisation;
    }

    public Nourritures(int id_nourriture, String nom_nourriture, String origine_nourriture , String ingrediant , String description_nourriture, String type_nourriture,Double prix_nourriture,  int id_utilisateur, int id_civilisation) {
        this.id_nourriture = id_nourriture; 
        this.nom_nourriture = nom_nourriture;
        this.origine_nourriture = origine_nourriture;
        this.ingrediant = ingrediant;
        this.description_nourriture=description_nourriture;
        this.type_nourriture=type_nourriture;
        this.prix_nourriture=prix_nourriture; 
        this.id_utilisateur = id_utilisateur;
        this.id_civilisation = id_civilisation;
    }

    public int getId_nourriture() {
        return id_nourriture;
    }

    public String getNom_nourriture() {
        return nom_nourriture;
    }

    public String getOrigine_nourriture() {
        return origine_nourriture;
    }

    public String getIngrediant() {
        return ingrediant;
    }

    public String getDescription_nourriture() {
        return description_nourriture;
    }

    public String getType_nourriture() {
        return type_nourriture;
    }

    public Double getPrix_nourriture() {
        return prix_nourriture;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public int getId_civilisation() {
        return id_civilisation;
    }

public void setId_nourriture(int id_nourriture) {
        this.id_nourriture = id_nourriture;
    }

    public void setNom_nourriture(String nom_nourriture) {
        this.nom_nourriture = nom_nourriture;
    }

    public void setOrigine_nourriture(String origine_nourriture) {
        this.origine_nourriture = origine_nourriture;
    }

    public void setIngrediant(String ingrediant) {
        this.ingrediant = ingrediant;
    }

    public void setDescription_nourriture(String description_nourriture) {
        this.description_nourriture = description_nourriture;
    }

    public void setType_nourriture(String type_nourriture) {
        this.type_nourriture = type_nourriture;
    }

    public void setPrix_nourriture(Double prix_nourriture) {
        this.prix_nourriture = prix_nourriture;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setId_civilisation(int id_civilisation) {
        this.id_civilisation = id_civilisation;
    }

    @Override
    public String toString() {
        return "Nourritures{" + "id_nourriture=" + id_nourriture + ", nom_nourriture=" + nom_nourriture + ", origine_nourriture=" + origine_nourriture + ", ingrediant=" + ingrediant + ", description_nourriture=" + description_nourriture + ", type_nourriture=" + type_nourriture + ", prix_nourriture=" + prix_nourriture + ", id_utilisateur=" + id_utilisateur + ", id_civilisation=" + id_civilisation + '}';
    }

    public void settype_nourriture(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

 
}
