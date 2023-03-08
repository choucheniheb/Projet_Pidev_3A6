/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import services.PasswordEncryption;


/**
 *
 * @author Skander
 */
public class Test {
    
    
    public static void main(String[] args) throws MessagingException {
       
        try {
//            Utilisateur u = new Utilisateur(2, "iheb", "chouchen", "chouchen.iheb@esprit.tn", "26836763", "ihebch", "iheb@1122", "2000-07-07");
//            UtilisateurService ps = new UtilisateurService();
            String password = "iheb@1122";
            System.out.println(PasswordEncryption.encryptPassword(password, "hidden tunisia"));
            System.out.println(PasswordEncryption.decryptPassword("YhUay1FX52TS7dog2KkeU0sJQtX9CBDI27Fs0vy4RNw=", "hidden tunisia"));
//            ps.ajouter(u);
            //ps.modifier(u);
            //ps.supprimer(u);
            //System.out.println(ps.recuperer());
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
            //Permission u = new Permission(13,"perm_ajouter", "permet de ajouter les utiliateur", 1);
            //PermissionService ps = new PermissionService();
            //ps.ajouter(u);
            //ps.modifier(u);
            //ps.supprimer(u);
            //System.out.println(ps.recuperer());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
