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
public class visites {
    
    private int id_visite;
    private int id_circuit;
    private int id_civilisation;

    public visites() {
    }

    public visites(int id_visite, int id_circuit, int id_civilisation) {
        this.id_visite = id_visite;
        this.id_circuit = id_circuit;
        this.id_civilisation = id_civilisation;
    }

    public visites(int id_circuit, int id_civilisation) {
        this.id_circuit = id_circuit;
        this.id_civilisation = id_civilisation;
    }

    public int getId_visite() {
        return id_visite;
    }

    public void setId_visite(int id_visite) {
        this.id_visite = id_visite;
    }

    public int getId_circuit() {
        return id_circuit;
    }

    public void setId_circuit(int id_circuit) {
        this.id_circuit = id_circuit;
    }

    public int getId_civilisation() {
        return id_civilisation;
    }

    public void setId_civilisation(int id_civilisation) {
        this.id_civilisation = id_civilisation;
    }

    @Override
    public String toString() {
        return "visites{" + "id_visite=" + id_visite + ", id_circuit=" + id_circuit + ", id_civilisation=" + id_civilisation + '}';
    }
    
    
    
    
    
    
    


    

}
