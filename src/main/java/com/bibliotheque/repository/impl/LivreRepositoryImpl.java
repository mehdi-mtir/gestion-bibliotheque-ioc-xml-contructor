package com.bibliotheque.repository.impl;

import com.bibliotheque.model.Genre;
import com.bibliotheque.model.Livre;
import com.bibliotheque.repository.LivreRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class LivreRepositoryImpl implements LivreRepository {

    private final Map<Long, Livre> livres = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Livre save(Livre livre) {
        if (livre == null) {
            throw new IllegalArgumentException("Le livre ne peut pas être null");
        }

        if (livre.getId() == null) {
            livre.setId(idGenerator.getAndIncrement());
        }

        livres.put(livre.getId(), livre);
        return livre;
    }

    @Override
    public Optional<Livre> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(livres.get(id));
    }

    @Override
    public List<Livre> findAll() {
        return new ArrayList<>(livres.values());
    }

    @Override
    public Livre update(Livre livre) {
        if (livre == null || livre.getId() == null) {
            throw new IllegalArgumentException("Le livre et son ID ne peuvent pas être null");
        }

        if (!livres.containsKey(livre.getId())) {
            throw new IllegalArgumentException("Livre non trouvé avec l'ID: " + livre.getId());
        }

        livres.put(livre.getId(), livre);
        return livre;
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID ne peut pas être null");
        }

        if (!livres.containsKey(id)) {
            throw new IllegalArgumentException("Livre non trouvé avec l'ID: " + id);
        }

        livres.remove(id);
    }

    @Override
    public Optional<Livre> findByIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            return Optional.empty();
        }

        return livres.values().stream()
                .filter(livre -> isbn.equals(livre.getIsbn()))
                .findFirst();
    }

    @Override
    public List<Livre> findByAuteur(Long auteurId) {
        if (auteurId == null) {
            return Collections.emptyList();
        }

        return livres.values().stream()
                .filter(livre -> livre.getAuteur() != null &&
                        auteurId.equals(livre.getAuteur().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Livre> findByGenre(Genre genre) {
        if (genre == null) {
            return Collections.emptyList();
        }

        return livres.values().stream()
                .filter(livre -> genre.equals(livre.getGenre()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Livre> findDisponibles() {
        return livres.values().stream()
                .filter(Livre::estDisponible)
                .collect(Collectors.toList());
    }
}

