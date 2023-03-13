/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;



/**
 *
 * @author wajdi
 */
public class Reservation {

    public static Object getItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int id_res;
    private String date_res;
    private double prix_res;
    
    private int id_evenement;
    private int id_utilisateur;
    private int id_ab;

    public Reservation(int id_res, String date_res, double prix_res, int id_evenement, int id_utilisateur, int id_ab) {
        this.id_res = id_res;
        this.date_res = date_res;
        this.prix_res = prix_res;
        this.id_evenement = id_evenement;
        this.id_utilisateur = id_utilisateur;
        this.id_ab = id_ab;
    }

    public Reservation(String date_res, double prix_res, int id_evenement, int id_utilisateur, int id_ab) {
        this.date_res = date_res;
        this.prix_res = prix_res;
        this.id_evenement = id_evenement;
        this.id_utilisateur = id_utilisateur;
        this.id_ab = id_ab;
    }

    

    public Reservation() {
    }

    public int getId_ab() {
        return id_ab;
    }

    public void setId_ab(int id_ab) {
        this.id_ab = id_ab;
    }

    
    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public String getDate_res() {
        return date_res;
    }

    public void setDate_res(String date_res) {
        this.date_res = date_res;
    }

    public double getPrix_res() {
        return prix_res;
    }

    public void setPrix_res(double prix_res) {
        this.prix_res = prix_res;
    }

   

   

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_res=" + id_res + ", date_res=" + date_res + ", prix_res=" + prix_res + ", id_evenement=" + id_evenement + ", id_utilisateur=" + id_utilisateur + ", id_ab=" + id_ab + '}';
    }

    
    
    
    
}
