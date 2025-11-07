package com.bibliotheque.repository;

import com.bibliotheque.model.Role;
import com.bibliotheque.model.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository {
    Utilisateur save(Utilisateur utilisateur);
    Optional<Utilisateur> findById(Long id);
    List<Utilisateur> findAll();
    Utilisateur update(Utilisateur utilisateur);
    void delete(Long id);
    Optional<Utilisateur> findByEmail(String email);
    List<Utilisateur> findByRole(Role role);
}
