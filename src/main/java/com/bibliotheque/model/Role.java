package com.bibliotheque.model;

public enum Role {
    USER("Utilisateur"),
    LIBRARIAN("Bibliothécaire"),
    ADMIN("Administrateur");

    private final String libelle;

    Role(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
