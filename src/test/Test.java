/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import entities.Permission;
import entities.Role;
import entities.Utilisateur;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import services.PermissionService;
import services.RoleService;
import services.UtilisateurService;


/**
 *
 * @author Skander
 */
public class Test {
    
    
    public static void main(String[] args) {
       
        try {
            //Utilisateur u = new Utilisateur(2, "iheb", "chouchen", "chouchen.iheb@esprit.tn", "26836763", "ihebch", "iheb@1122", "2000-07-07");
            //UtilisateurService ps = new UtilisateurService();
            //ps.ajouter(u);
            //ps.modifier(u);
            //ps.supprimer(u);
            //System.out.println(ps.recuperer(u));
            /*Role r= new Role(15,"mod", "moderator");
            RoleService ps=new RoleService();
            List<Permission> p = new ArrayList<>();
            Permission pe1=new Permission(11, "perm1", "descriptionPermission1", 1);
            Permission pe2=new Permission(12, "perm2", "descriptionPermission2", 1);
            p.add(pe1);
            p.add(pe2);
            r.setPermission(p);
            ps.ajouter(r);
            ps.modifier(r);
            System.out.println(ps.recuperer(r));*/
            Permission u = new Permission(13,"perm_ajouter", "permet de ajouter les utiliateur", 1);
            PermissionService ps = new PermissionService();
            //ps.ajouter(u);
            //ps.modifier(u);
            ps.supprimer(u);
            System.out.println(ps.recuperer(u));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
