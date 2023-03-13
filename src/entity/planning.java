/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author MSI
 */
public class planning {
    private int id_planning ;
    private int id_circuit ;
    private int id_evenement ;
    private String resto ;
    private String hotel ;
    private String emplacement ;

    public planning() {
    }

    public planning(int id_planning, int id_circuit, int id_evenement, String resto, String hotel, String emplacement) {
        this.id_planning = id_planning;
        this.id_circuit = id_circuit;
        this.id_evenement = id_evenement;
        this.resto = resto;
        this.hotel = hotel;
        this.emplacement = emplacement;
    }

    public planning(int id_circuit, int id_evenement, String resto, String hotel, String emplacement) {
        this.id_circuit = id_circuit;
        this.id_evenement = id_evenement;
        this.resto = resto;
        this.hotel = hotel;
        this.emplacement = emplacement;
    }

    public int getId_planning() {
        return id_planning;
    }

    public void setId_planning(int id_planning) {
        this.id_planning = id_planning;
    }

    public int getId_circuit() {
        return id_circuit;
    }

    public void setId_circuit(int id_circuit) {
        this.id_circuit = id_circuit;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public String getResto() {
        return resto;
    }

    public void setResto(String resto) {
        this.resto = resto;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    @Override
    public String toString() {
        return "planning{" + "id_planning=" + id_planning + ", id_circuit=" + id_circuit + ", id_evenement=" + id_evenement + ", resto=" + resto + ", hotel=" + hotel + ", emplacement=" + emplacement + '}';
    }

    
    
    
    
    
    
    
}
