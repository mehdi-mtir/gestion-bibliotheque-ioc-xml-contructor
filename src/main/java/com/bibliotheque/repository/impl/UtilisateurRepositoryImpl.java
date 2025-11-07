package com.bibliotheque.repository.impl;

import com.bibliotheque.model.Role;
import com.bibliotheque.model.Utilisateur;
import com.bibliotheque.repository.UtilisateurRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class UtilisateurRepositoryImpl implements UtilisateurRepository {

    private final Map<Long, Utilisateur> utilisateurs = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        if (utilisateur == null) {
            throw new IllegalArgumentException("L'utilisateur ne peut pas être null");
        }

        if (utilisateur.getId() == null) {
            utilisateur.setId(idGenerator.getAndIncrement());
        }

        utilisateurs.put(utilisateur.getId(), utilisateur);
        return utilisateur;
    }

    @Override
    public Optional<Utilisateur> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(utilisateurs.get(id));
    }

    @Override
    public List<Utilisateur> findAll() {
        return new ArrayList<>(utilisateurs.values());
    }

    @Override
    public Utilisateur update(Utilisateur utilisateur) {
        if (utilisateur == null || utilisateur.getId() == null) {
            throw new IllegalArgumentException("L'utilisateur et son ID ne peuvent pas être null");
        }

        if (!utilisateurs.containsKey(utilisateur.getId())) {
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID: " + utilisateur.getId());
        }

        utilisateurs.put(utilisateur.getId(), utilisateur);
        return utilisateur;
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID ne peut pas être null");
        }

        if (!utilisateurs.containsKey(id)) {
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID: " + id);
        }

        utilisateurs.remove(id);
    }

    @Override
    public Optional<Utilisateur> findByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }

        return utilisateurs.values().stream()
                .filter(utilisateur -> email.equalsIgnoreCase(utilisateur.getEmail()))
                .findFirst();
    }

    @Override
    public List<Utilisateur> findByRole(Role role) {
        if (role == null) {
            return Collections.emptyList();
        }

        return utilisateurs.values().stream()
                .filter(utilisateur -> role.equals(utilisateur.getRole()))
                .collect(Collectors.toList());
    }
}

