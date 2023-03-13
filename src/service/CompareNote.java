package service;

import entity.nourritures;

import java.util.Comparator;

public class CompareNote implements Comparator<nourritures> {
    public int compare(nourritures n1, nourritures n2) {
        return  (n1.getOrigine_nourriture().compareTo(n2.getOrigine_nourriture()));
    }
}
