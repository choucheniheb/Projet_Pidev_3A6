/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.evenements;
import java.util.List;

/**
 *
 * @author MSI
 */
public interface InterfaceEvenement {
    
    
    public void ajouter(evenements e) ;
    public void modifier( String titre_evenement , String type_evenement , String date_evenement , String lieux_evenement , double prix_evenement , int id_invite , String description_evenement , int id_utilisateur , int id_evenement);
    public Boolean supprimer ( int id_evenement) ;
    public List<evenements> afficher() ;
    
    
}
