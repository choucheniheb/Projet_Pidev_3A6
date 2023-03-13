package service;

import entity.nourritures;

import java.util.Comparator;

public class CompareNom implements Comparator<nourritures> {
    public int compare(nourritures n1, nourritures n2) {
        return  (n1.getNom_nourriture().compareTo(n2.getNom_nourriture()));
    }
}
