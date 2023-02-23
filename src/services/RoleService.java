/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Permission;
import entities.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author Ihebc
 */
public class RoleService implements IService<Role>{
    Connection cnx;

    public RoleService() {
       cnx = MyDB.getInstance().getCnx();
    }
    

    @Override
    public void ajouter(Role t) throws SQLException {
        //remplir le table roles
        String req = "INSERT INTO roles (nom_role,description_role) VALUES ('"+t.getNomRole()+"','"+t.getDiscriptionRole()+"');";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        //recuperer le dernier ligne ajouter
        String req2 = "SELECT id_role FROM roles ORDER BY id_role DESC LIMIT 1;";
        Statement st2 = cnx.createStatement();
        ResultSet rs2 =  st2.executeQuery(req2);
        rs2.next();
        t.setIdRole(rs2.getInt("id_role"));
        System.out.println(t.getIdRole());
        //remplir le table role_permesion
        List<Permission> p=t.getPermission();
        for (Permission p1 : p) {
            String req1 = "INSERT INTO role_pemission (id_role,id_permission) VALUES ("+t.getIdRole()+","+p1.getIdPermission()+");";
            Statement st1 = cnx.createStatement();
            st1.executeUpdate(req1);
        }
    }

    @Override
    public void modifier(Role t) throws SQLException {
        String req = "UPDATE roles SET nom_role = ?,description_role = ? where id_role = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNomRole());
        ps.setString(2, t.getDiscriptionRole());
        ps.setInt(3, t.getIdRole());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(Role t) throws SQLException {
        String req = "DELETE FROM roles WHERE id_role = "+t.getIdRole();
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public List<Role> recuperer() throws SQLException {
        List<Role> roles = new ArrayList<>();
        String s = "select * from Roles";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Role r = new Role();
            r.setNomRole(rs.getString("nom_role"));
            r.setIdRole(rs.getInt("id_role"));
            r.setDiscriptionRole(rs.getString("description_Role"));
            List<Permission> p = new ArrayList<>();
            String s1 = "select * from permissions e1 join role_pemission e2 on (e2.id_permission = e1.id_permission) and e2.id_role = "+r.getIdRole();
            Statement st1 = cnx.createStatement();
            ResultSet rs1 =  st1.executeQuery(s1);
            while(rs1.next()){
                Permission p1 = new Permission();
                p1.setDescriptionPermission(rs1.getString("description_permission"));
                p1.setNomPermission(rs1.getString("nom_permission"));
                p1.setIdModule(rs1.getInt("id_module"));
                p1.setIdPermission(rs1.getInt("id_permission"));
                p.add(p1);
            }
            r.setPermission(p);
            roles.add(r);
        }
        return roles;
    }
    public Role chercherRole(String nom_role) throws SQLException {
        String s = "select * from Roles where nom_role = '"+nom_role+"'";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        Role r = new Role();
        if(rs.next()){
            r.setNomRole(rs.getString("nom_role"));
            r.setIdRole(rs.getInt("id_role"));
            r.setDiscriptionRole(rs.getString("description_Role"));
        }
        return r;
    }
    
}
