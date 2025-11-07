package com.bibliotheque.service;

import com.bibliotheque.model.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    Utilisateur creerUtilisateur(Utilisateur utilisateur);
    Utilisateur obtenirUtilisateur(Long id);
    List<Utilisateur> obtenirTousLesUtilisateurs();
    Utilisateur modifierUtilisateur(Utilisateur utilisateur);
    void supprimerUtilisateur(Long id);
    Utilisateur rechercherParEmail(String email);
    boolean authentifier(String email, String motDePasse);
}
