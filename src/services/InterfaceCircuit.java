/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.circuit;
import java.util.List;

/**
 *
 * @author MSI
 */
public interface InterfaceCircuit {
    
    
    public void ajouter(circuit c) ;
    public void modifier ( String point_depat_circuit , String date_debut_circuit , String date_fin_circuit , int nbr_place_dispo , String description_circuit , int nbr_jour_circuit , String description_evenement , int id_utilisateur  ,String nom_circuit, int id_circuit) ;
    public Boolean supprimer ( int id_circuit) ;
    public List<circuit> afficher() ;
    
    
}
