package com.bibliotheque.repository;

import com.bibliotheque.model.Genre;
import com.bibliotheque.model.Livre;

import java.util.List;
import java.util.Optional;

public interface LivreRepository {
    Livre save(Livre livre);
    Optional<Livre> findById(Long id);
    List<Livre> findAll();
    Livre update(Livre livre);
    void delete(Long id);
    Optional<Livre> findByIsbn(String isbn);
    List<Livre> findByAuteur(Long auteurId);
    List<Livre> findByGenre(Genre genre);
    List<Livre> findDisponibles();
}