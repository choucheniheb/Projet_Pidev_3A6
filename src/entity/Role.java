/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ihebc
 */
public class Role {
    private int idRole;
    private String nomRole,discriptionRole;
    private List<Permission> p = new ArrayList<>();

    public Role(String nomRole, String discriptionRole) {
        this.nomRole = nomRole;
        this.discriptionRole = discriptionRole;
    }

    public Role(int idRole, String nomRole, String discriptionRole) {
        this.idRole = idRole;
        this.nomRole = nomRole;
        this.discriptionRole = discriptionRole;
    }

    public Role() {
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
    
    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }

    public String getDiscriptionRole() {
        return discriptionRole;
    }

    public void setDiscriptionRole(String discriptionRole) {
        this.discriptionRole = discriptionRole;
    }

    public List<Permission> getPermission() {
        return p;
    }

    public void setPermission(List<Permission> permission) {
        this.p = permission;
    }

    @Override
    public String toString() {
        return "Role{" + "nomRole=" + nomRole + ", discriptionRole=" + discriptionRole + ", permission=" + p + '}';
    }
}
