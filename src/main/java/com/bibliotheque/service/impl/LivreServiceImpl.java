package com.bibliotheque.service.impl;

import com.bibliotheque.model.Genre;
import com.bibliotheque.model.Livre;
import com.bibliotheque.repository.LivreRepository;
import com.bibliotheque.service.LivreService;

import java.util.List;

public class LivreServiceImpl implements LivreService {

    private LivreRepository livreRepository;

    public void setLivreRepository(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    @Override
    public Livre creerLivre(Livre livre) {
        validerLivre(livre);

        // Vérifier que l'ISBN n'existe pas déjà
        livreRepository.findByIsbn(livre.getIsbn()).ifPresent(l -> {
            throw new IllegalArgumentException("Un livre avec l'ISBN " + livre.getIsbn() + " existe déjà");
        });

        return livreRepository.save(livre);
    }

    @Override
    public Livre obtenirLivre(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID ne peut pas être null");
        }

        return livreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Aucun livre trouvé avec l'ID: " + id));
    }

    @Override
    public List<Livre> obtenirTousLesLivres() {
        return livreRepository.findAll();
    }

    @Override
    public Livre modifierLivre(Livre livre) {
        validerLivre(livre);

        if (livre.getId() == null) {
            throw new IllegalArgumentException("L'ID du livre ne peut pas être null pour une modification");
        }

        // Vérifier que le livre existe
        obtenirLivre(livre.getId());

        return livreRepository.update(livre);
    }

    @Override
    public void supprimerLivre(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID ne peut pas être null");
        }

        // Vérifier que le livre existe
        obtenirLivre(id);

        livreRepository.delete(id);
    }

    @Override
    public Livre rechercherParIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("L'ISBN ne peut pas être vide");
        }

        return livreRepository.findByIsbn(isbn)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Aucun livre trouvé avec l'ISBN: " + isbn));
    }

    @Override
    public List<Livre> rechercherParAuteur(Long auteurId) {
        if (auteurId == null) {
            throw new IllegalArgumentException("L'ID de l'auteur ne peut pas être null");
        }

        return livreRepository.findByAuteur(auteurId);
    }

    @Override
    public List<Livre> rechercherParGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Le genre ne peut pas être null");
        }

        return livreRepository.findByGenre(genre);
    }

    @Override
    public List<Livre> obtenirLivresDisponibles() {
        return livreRepository.findDisponibles();
    }

    @Override
    public void marquerCommeEmprunte(Long livreId) {
        if (livreId == null) {
            throw new IllegalArgumentException("L'ID du livre ne peut pas être null");
        }

        Livre livre = obtenirLivre(livreId);

        if (!livre.estDisponible()) {
            throw new IllegalStateException("Le livre '" + livre.getTitre() + "' est déjà emprunté");
        }

        livre.emprunter();
        livreRepository.update(livre);
    }

    @Override
    public void marquerCommeRetourne(Long livreId) {
        if (livreId == null) {
            throw new IllegalArgumentException("L'ID du livre ne peut pas être null");
        }

        Livre livre = obtenirLivre(livreId);

        if (livre.estDisponible()) {
            throw new IllegalStateException("Le livre '" + livre.getTitre() + "' n'est pas emprunté");
        }

        livre.retourner();
        livreRepository.update(livre);
    }

    private void validerLivre(Livre livre) {
        if (livre == null) {
            throw new IllegalArgumentException("Le livre ne peut pas être null");
        }

        if (livre.getIsbn() == null || livre.getIsbn().trim().isEmpty()) {
            throw new IllegalArgumentException("L'ISBN est obligatoire");
        }

        if (livre.getTitre() == null || livre.getTitre().trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre est obligatoire");
        }

        if (livre.getAuteur() == null) {
            throw new IllegalArgumentException("L'auteur est obligatoire");
        }

        if (livre.getGenre() == null) {
            throw new IllegalArgumentException("Le genre est obligatoire");
        }
    }
}
