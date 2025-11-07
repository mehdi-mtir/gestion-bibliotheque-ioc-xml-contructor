package com.bibliotheque.model;

public enum Genre {
    ROMAN("Roman"),
    SCIENCE_FICTION("Science-Fiction"),
    FANTASY("Fantasy"),
    HISTOIRE("Histoire"),
    BIOGRAPHIE("Biographie"),
    ESSAI("Essai"),
    POESIE("Poésie"),
    THEATRE("Théâtre"),
    BD("Bande Dessinée"),
    AUTRE("Autre");

    private final String libelle;

    Genre(String libelle) {
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

