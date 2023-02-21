/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MSI
 */
public class circuit {
    private int id_circuit;
    private String point_depart_circuit ; 
    private String date_debut_circuit ;
    private String date_fin_circuit ;
    private int nbr_place_dispo ;
    private String description_circuit ; 
    private int nbr_jour_circuit ;
    private int id_utilisateur;
    private String nom_circuit ;

    public circuit() {
    }

    public circuit(int id_circuit, String point_depart_circuit, String date_debut_circuit, String date_fin_circuit, int nbr_place_dispo, String description_circuit, int nbr_jour_circuit, int id_utilisateur, String nom_circuit) {
        this.id_circuit = id_circuit;
        this.point_depart_circuit = point_depart_circuit;
        this.date_debut_circuit = date_debut_circuit;
        this.date_fin_circuit = date_fin_circuit;
        this.nbr_place_dispo = nbr_place_dispo;
        this.description_circuit = description_circuit;
        this.nbr_jour_circuit = nbr_jour_circuit;
        this.id_utilisateur = id_utilisateur;
        this.nom_circuit = nom_circuit;
    }

    public circuit(String point_depart_circuit, String date_debut_circuit, String date_fin_circuit, int nbr_place_dispo, String description_circuit, int nbr_jour_circuit, int id_utilisateur,  String nom_circuit) {
        this.point_depart_circuit = point_depart_circuit;
        this.date_debut_circuit = date_debut_circuit;
        this.date_fin_circuit = date_fin_circuit;
        this.nbr_place_dispo = nbr_place_dispo;
        this.description_circuit = description_circuit;
        this.nbr_jour_circuit = nbr_jour_circuit;
        this.id_utilisateur = id_utilisateur;

        this.nom_circuit = nom_circuit;
    }

    public int getId_circuit() {
        return id_circuit;
    }

    public void setId_circuit(int id_circuit) {
        this.id_circuit = id_circuit;
    }

    public String getPoint_depart_circuit() {
        return point_depart_circuit;
    }

    public void setPoint_depart_circuit(String point_depart_circuit) {
        this.point_depart_circuit = point_depart_circuit;
    }

    public String getDate_debut_circuit() {
        return date_debut_circuit;
    }

    public void setDate_debut_circuit(String date_debut_circuit) {
        this.date_debut_circuit = date_debut_circuit;
    }

    public String getDate_fin_circuit() {
        return date_fin_circuit;
    }

    public void setDate_fin_circuit(String date_fin_circuit) {
        this.date_fin_circuit = date_fin_circuit;
    }

    public int getNbr_place_dispo() {
        return nbr_place_dispo;
    }

    public void setNbr_place_dispo(int nbr_place_dispo) {
        this.nbr_place_dispo = nbr_place_dispo;
    }

    public String getDescription_circuit() {
        return description_circuit;
    }

    public void setDescription_circuit(String description_circuit) {
        this.description_circuit = description_circuit;
    }

    public int getNbr_jour_circuit() {
        return nbr_jour_circuit;
    }

    public void setNbr_jour_circuit(int nbr_jour_circuit) {
        this.nbr_jour_circuit = nbr_jour_circuit;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }


    public String getNom_circuit() {
        return nom_circuit;
    }

    public void setNom_circuit(String nom_circuit) {
        this.nom_circuit = nom_circuit;
    }

    @Override
    public String toString() {
        return "circuit{" + "id_circuit=" + id_circuit + ", point_depart_circuit=" + point_depart_circuit + ", date_debut_circuit=" + date_debut_circuit + ", date_fin_circuit=" + date_fin_circuit + ", nbr_place_dispo=" + nbr_place_dispo + ", description_circuit=" + description_circuit + ", nbr_jour_circuit=" + nbr_jour_circuit + ", id_utilisateur=" + id_utilisateur + ", nom_circuit=" + nom_circuit + '}';
    }
    
    
    
    
    
    
    

    
}
    

    