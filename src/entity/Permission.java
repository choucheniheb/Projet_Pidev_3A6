/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Ihebc
 */
public class Permission {
    private int idPermission,idModule;
    private String nomPermission,descriptionPermission;

    public Permission(String nomPermission, String descriptionPermission, int idModule) {
        this.nomPermission = nomPermission;
        this.descriptionPermission = descriptionPermission;
        this.idModule = idModule;
    }

    public Permission(int idPermission, String nomPermission, String descriptionPermission, int idModule) {
        this.idPermission = idPermission;
        this.nomPermission = nomPermission;
        this.descriptionPermission = descriptionPermission;
        this.idModule = idModule;
    }

    public Permission() {
    }

    public int getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(int idPermission) {
        this.idPermission = idPermission;
    }

    public String getNomPermission() {
        return nomPermission;
    }

    public void setNomPermission(String nomPermission) {
        this.nomPermission = nomPermission;
    }

    public String getDescriptionPermission() {
        return descriptionPermission;
    }

    public void setDescriptionPermission(String descriptionPermission) {
        this.descriptionPermission = descriptionPermission;
    }

    public int getIdModule() {
        return idModule;
    }

    public void setIdModule(int idModule) {
        this.idModule = idModule;
    }

    @Override
    public String toString() {
        return "Permission{" + "idPermission=" + idPermission + ", nomPermission=" + nomPermission + ", descriptionPermission=" + descriptionPermission + ", idModule=" + idModule + '}';
    }
    
}
