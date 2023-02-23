/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Permission;
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
public class PermissionService implements IService<Permission>{

    Connection cnx;

    public PermissionService() {
       cnx = MyDB.getInstance().getCnx();
    }
    @Override
    public void ajouter(Permission t) throws SQLException {
        String req = "INSERT INTO permissions (nom_permission, description_permission, id_module) VALUES ('"+t.getNomPermission()+"','"+ t.getDescriptionPermission()+"',"+t.getIdModule()+")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Permission t) throws SQLException {
        String req = "UPDATE permissions SET nom_permission = ?,description_permission = ?,id_module = ? where id_permission = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNomPermission());
        ps.setString(2, t.getDescriptionPermission());
        ps.setInt(3, t.getIdModule());
        ps.setInt(4, t.getIdPermission());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(Permission t) throws SQLException {
        String req = "DELETE FROM permissions WHERE id_permission = "+t.getIdPermission();
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
   }

    @Override
    public List<Permission> recuperer() throws SQLException {
        List<Permission> permission = new ArrayList<>();
        String s = "select * from permissions";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Permission u = new Permission();
            u.setNomPermission(rs.getString("nom_Permission"));
            u.setDescriptionPermission(rs.getString("Description_Permission"));
            u.setIdModule(rs.getInt("id_module"));
            u.setIdPermission(rs.getInt("id_Permission"));
            permission.add(u);
        }
        return permission;
    }
    
}
