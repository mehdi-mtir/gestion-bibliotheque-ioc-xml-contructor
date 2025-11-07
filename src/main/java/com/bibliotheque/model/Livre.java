package com.bibliotheque.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livre {
    private Long id;
    private String isbn;
    private String titre;
    private Auteur auteur;
    private LocalDate datePublication;
    private Integer nombrePages;
    private Genre genre;
    private String resume;
    private Boolean disponible;

    // Constructeur sans ID (pour la création)
    public Livre(String isbn, String titre, Auteur auteur, LocalDate datePublication,
                 Integer nombrePages, Genre genre, String resume) {
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.datePublication = datePublication;
        this.nombrePages = nombrePages;
        this.genre = genre;
        this.resume = resume;
        this.disponible = true; // Par défaut, un nouveau livre est disponible
    }

    // equals et hashCode basés sur l'ISBN
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livre livre = (Livre) o;
        return Objects.equals(isbn, livre.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return String.format("'%s' par %s (%s) - %s [%s]",
                titre,
                auteur != null ? auteur.getNomComplet() : "Auteur inconnu",
                genre,
                disponible ? "Disponible" : "Emprunté",
                isbn);
    }

    public boolean estDisponible() {
        return disponible != null && disponible;
    }

    public void emprunter() {
        this.disponible = false;
    }

    public void retourner() {
        this.disponible = true;
    }
}

