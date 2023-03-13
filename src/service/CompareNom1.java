package service;



import entity.civilisation;
import java.util.Comparator;

public class CompareNom1 implements Comparator<civilisation> {
    public int compare(civilisation c1, civilisation c2) {
        return  (c1.getNom_civilisation().compareTo(c2.getNom_civilisation()));
    }
}