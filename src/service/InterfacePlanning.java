/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.planning;
import java.util.List;

/**
 *
 * @author MSI
 */
public interface InterfacePlanning {
    
    
    
    public void ajouter(planning  p) ;
    public void modifier( int id_circuit , int id_evenement , String resto , String hotel , String emplacement ,int id_planning ) ;
    public Boolean supprimer ( int id_planning) ;
    public List<planning> afficher() ;
    
    
}
