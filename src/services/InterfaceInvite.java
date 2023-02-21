/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.invites;
import java.util.List;

/**
 *
 * @author MSI
 */
public interface InterfaceInvite {
    
    
    public void ajouter(invites i) ;
    public void modifier (String nom_invite , String prenom_invite , String type_invite , int id_invite ) ;
    public void supprimer ( int id_invite) ;
    public List<invites> afficher() ;
    
}
