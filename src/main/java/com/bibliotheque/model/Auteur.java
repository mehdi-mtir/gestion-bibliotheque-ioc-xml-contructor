package com.bibliotheque.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auteur {
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String nationalite;
    private String biographie;

    // Constructeur sans ID (pour la création)
    public Auteur(String nom, String prenom, LocalDate dateNaissance,
                  String nationalite, String biographie) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.nationalite = nationalite;
        this.biographie = biographie;
    }

    // equals et hashCode basés sur l'ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auteur auteur = (Auteur) o;
        return Objects.equals(id, auteur.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s) - %s",
                prenom, nom, nationalite,
                dateNaissance != null ? dateNaissance.getYear() : "N/A");
    }

    public String getNomComplet() {
        return prenom + " " + nom;
    }
}

