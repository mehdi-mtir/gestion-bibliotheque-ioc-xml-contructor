package com.bibliotheque.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {
    private Long id;
    private String email;
    private String motDePasse;
    private String nom;
    private String prenom;
    private LocalDate dateInscription;
    private Role role;
    private Boolean actif;

    // Constructeur sans ID (pour la création)
    public Utilisateur(String email, String motDePasse, String nom, String prenom, Role role) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.dateInscription = LocalDate.now();
        this.role = role;
        this.actif = true;
    }

    // equals et hashCode basés sur l'email
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s) - %s [%s]",
                prenom, nom, email, role,
                actif ? "Actif" : "Inactif");
    }

    public String getNomComplet() {
        return prenom + " " + nom;
    }

    public boolean estActif() {
        return actif != null && actif;
    }
}
