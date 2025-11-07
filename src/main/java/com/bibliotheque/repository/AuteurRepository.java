package com.bibliotheque.repository;

import com.bibliotheque.model.Auteur;

import java.util.List;
import java.util.Optional;

public interface AuteurRepository {
    Auteur save(Auteur auteur);
    List<Auteur> findAll();
    Optional<Auteur> findById(Long id);
    List<Auteur> findByNom(String nom);
    Auteur update(Auteur auteur);
    void delete(Long id);
}
