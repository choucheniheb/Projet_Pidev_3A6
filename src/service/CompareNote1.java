package service;

import entity.civilisation;

import java.util.Comparator;

public class CompareNote1 implements Comparator<civilisation> {
    public int compare(civilisation c1, civilisation c2) {
        return  (c1.getNom_monument().compareTo(c2.getNom_monument()));
    }
}

