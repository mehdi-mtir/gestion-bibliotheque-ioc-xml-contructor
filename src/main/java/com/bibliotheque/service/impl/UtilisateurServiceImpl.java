package com.bibliotheque.service.impl;

import com.bibliotheque.model.Utilisateur;
import com.bibliotheque.repository.LivreRepository;
import com.bibliotheque.repository.UtilisateurRepository;
import com.bibliotheque.service.UtilisateurService;

import java.util.List;

public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    public void setUtilisateurRepository(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        validerUtilisateur(utilisateur);

        // Vérifier que l'email n'existe pas déjà
        utilisateurRepository.findByEmail(utilisateur.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("Un utilisateur avec l'email " +
                    utilisateur.getEmail() + " existe déjà");
        });

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur obtenirUtilisateur(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID ne peut pas être null");
        }

        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Aucun utilisateur trouvé avec l'ID: " + id));
    }

    @Override
    public List<Utilisateur> obtenirTousLesUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur modifierUtilisateur(Utilisateur utilisateur) {
        validerUtilisateur(utilisateur);

        if (utilisateur.getId() == null) {
            throw new IllegalArgumentException(
                    "L'ID de l'utilisateur ne peut pas être null pour une modification");
        }

        // Vérifier que l'utilisateur existe
        obtenirUtilisateur(utilisateur.getId());

        return utilisateurRepository.update(utilisateur);
    }

    @Override
    public void supprimerUtilisateur(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID ne peut pas être null");
        }

        // Vérifier que l'utilisateur existe
        obtenirUtilisateur(id);

        utilisateurRepository.delete(id);
    }

    @Override
    public Utilisateur rechercherParEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("L'email ne peut pas être vide");
        }

        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Aucun utilisateur trouvé avec l'email: " + email));
    }

    @Override
    public boolean authentifier(String email, String motDePasse) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("L'email ne peut pas être vide");
        }

        if (motDePasse == null || motDePasse.trim().isEmpty()) {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être vide");
        }

        return utilisateurRepository.findByEmail(email)
                .map(utilisateur -> utilisateur.getMotDePasse().equals(motDePasse) &&
                        utilisateur.estActif())
                .orElse(false);
    }

    private void validerUtilisateur(Utilisateur utilisateur) {
        if (utilisateur == null) {
            throw new IllegalArgumentException("L'utilisateur ne peut pas être null");
        }

        if (utilisateur.getEmail() == null || utilisateur.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("L'email est obligatoire");
        }

        if (!utilisateur.getEmail().contains("@")) {
            throw new IllegalArgumentException("L'email doit être valide");
        }

        if (utilisateur.getMotDePasse() == null || utilisateur.getMotDePasse().trim().isEmpty()) {
            throw new IllegalArgumentException("Le mot de passe est obligatoire");
        }

        if (utilisateur.getMotDePasse().length() < 6) {
            throw new IllegalArgumentException("Le mot de passe doit contenir au moins 6 caractères");
        }

        if (utilisateur.getNom() == null || utilisateur.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }

        if (utilisateur.getPrenom() == null || utilisateur.getPrenom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom est obligatoire");
        }

        if (utilisateur.getRole() == null) {
            throw new IllegalArgumentException("Le rôle est obligatoire");
        }
    }
}

