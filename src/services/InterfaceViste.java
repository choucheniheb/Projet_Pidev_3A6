/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.visites;
import java.util.List;

/**
 *
 * @author MSI
 */
public interface InterfaceViste {
    
    
    public void ajouter(visites v) ;
    public void modifier (int id_circuit , int id_civilisation ,  int id_visite ) ;
    public void supprimer ( int id_visite) ;
    
    public List<visites> afficher() ;
    
}
