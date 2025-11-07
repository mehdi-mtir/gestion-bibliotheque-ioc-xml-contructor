package com.bibliotheque.service;

import com.bibliotheque.model.Auteur;

import java.util.List;

public interface AuteurService {
    Auteur creerAuteur(Auteur auteur);
    Auteur obtenirAuteur(Long id);
    List<Auteur> obtenirTousLesAuteurs();
    Auteur modifierAuteur(Auteur auteur);
    void supprimerAuteur(Long id);
    List<Auteur> rechercherParNom(String nom);
}
