package com.bibliotheque.repository.impl;

import com.bibliotheque.model.Auteur;
import com.bibliotheque.repository.AuteurRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class AuteurRepositoryImpl implements AuteurRepository {
    private final Map<Long, Auteur> auteurs = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);



    @Override
    public Auteur save(Auteur auteur) {
        if(auteur == null){
            throw new IllegalArgumentException("L'auteur ne peut pas être null");
        }

        if(auteur.getId() == null) {
            auteur.setId(idGenerator.getAndDecrement());
        }

        auteurs.put(auteur.getId(), auteur);
        return auteur;
    }

    @Override
    public List<Auteur> findAll() {
        return new ArrayList<>(auteurs.values());
    }

    @Override
    public Optional<Auteur> findById(Long id) {
        if(id == null){
            return Optional.empty();
        }
        return Optional.ofNullable(auteurs.get(id));
    }

    @Override
    public List<Auteur> findByNom(String nom) {
        if(nom == null || nom.trim().isEmpty()){
            return Collections.emptyList();
        }

        return auteurs.values().stream()
                .filter(auteur -> auteur.getNom() != null && auteur.getNom().toLowerCase().contains(nom.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Auteur update(Auteur auteur) {
        if(auteur == null || auteur.getId() == null){
            throw new IllegalArgumentException("L'auteur et son Id ne doivent pas être null");
        }
        if(!auteurs.containsKey(auteur.getId())){
            throw new IllegalArgumentException("Auteur non trouvé pour l'ID : " + auteur.getId());
        }

        auteurs.put(auteur.getId(), auteur);
        return auteur;
    }

    @Override
    public void delete(Long id) {
        if(id == null){
            throw new IllegalArgumentException("L'ID ne peut pas être null");
        }

        if(!auteurs.containsKey(id)){
            throw new IllegalArgumentException("Auteur non trouvé pour l'ID : " + id);
        }

        auteurs.remove(id);
    }
}
