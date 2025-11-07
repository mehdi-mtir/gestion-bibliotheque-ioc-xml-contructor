package com.bibliotheque.service;

import com.bibliotheque.model.Genre;
import com.bibliotheque.model.Livre;

import java.util.List;

public interface LivreService {
    Livre creerLivre(Livre livre);
    Livre obtenirLivre(Long id);
    List<Livre> obtenirTousLesLivres();
    Livre modifierLivre(Livre livre);
    void supprimerLivre(Long id);
    Livre rechercherParIsbn(String isbn);
    List<Livre> rechercherParAuteur(Long auteurId);
    List<Livre> rechercherParGenre(Genre genre);
    List<Livre> obtenirLivresDisponibles();
    void marquerCommeEmprunte(Long livreId);
    void marquerCommeRetourne(Long livreId);
}
