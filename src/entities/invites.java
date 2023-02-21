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
public class invites {
    private int id_invite;
    private String nom_invite ; 
    private String prenom_invite ; 
    private String type_invite ; 

    public invites() {
    }

    public invites(int id_invite, String nom_invite, String prenom_invite, String type_invite) {
        this.id_invite = id_invite;
        this.nom_invite = nom_invite;
        this.prenom_invite = prenom_invite;
        this.type_invite = type_invite;
    }

    public invites(String nom_invite, String prenom_invite, String type_invite) {
        this.nom_invite = nom_invite;
        this.prenom_invite = prenom_invite;
        this.type_invite = type_invite;
    }

    public int getId_invite() {
        return id_invite;
    }

    public void setId_invite(int id_invite) {
        this.id_invite = id_invite;
    }

    public String getNom_invite() {
        return nom_invite;
    }

    public void setNom_invite(String nom_invite) {
        this.nom_invite = nom_invite;
    }

    public String getPrenom_invite() {
        return prenom_invite;
    }

    public void setPrenom_invite(String prenom_invite) {
        this.prenom_invite = prenom_invite;
    }

    public String getType_invite() {
        return type_invite;
    }

    public void setType_invite(String type_invite) {
        this.type_invite = type_invite;
    }

    @Override
    public String toString() {
        return "invites{" + "id_invite=" + id_invite + ", nom_invite=" + nom_invite + ", prenom_invite=" + prenom_invite + ", type_invite=" + type_invite + '}';
    }
    
    
    
}
