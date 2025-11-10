package com.bibliotheque.service.impl;

import com.bibliotheque.model.Auteur;
import com.bibliotheque.repository.AuteurRepository;
import com.bibliotheque.service.AuteurService;

import java.util.List;

public class AuteurServiceImpl implements AuteurService {
    private AuteurRepository auteurRepository;

    public AuteurServiceImpl(){}

    public void setAuteurRepository(AuteurRepository auteurRepository) {
        this.auteurRepository = auteurRepository;
    }

    @Override
    public Auteur creerAuteur(Auteur auteur) {
        validerAuteur(auteur);
        return auteurRepository.save(auteur);
    }

    @Override
    public Auteur obtenirAuteur(Long id) {
        if(id == null){
            throw new IllegalArgumentException("l'ID ne peut pas être null");
        }
        return auteurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aucun auteur trouvé pour l'ID : " + id));
    }

    @Override
    public List<Auteur> obtenirTousLesAuteurs() {
        return auteurRepository.findAll();
    }

    @Override
    public Auteur modifierAuteur(Auteur auteur) {
        validerAuteur(auteur);

        if(auteur.getId() == null){
            throw new IllegalArgumentException("l'ID de l'auteur ne paut pas être null");
        }

        //Vérifier que l'auteur existe
        obtenirAuteur(auteur.getId());

        return auteurRepository.update(auteur);
    }

    @Override
    public void supprimerAuteur(Long id) {
        if(id == null){
            throw new IllegalArgumentException("l'ID de l'auteur ne paut pas être null");
        }
        //Vérifier que l'auteur existe
        obtenirAuteur(id);

        auteurRepository.delete(id);

    }

    @Override
    public List<Auteur> rechercherParNom(String nom) {
        if(nom == null || nom.trim().isEmpty()){
            throw new IllegalArgumentException("Le nom de l'auteur ne peut pas être null");
        }

        return auteurRepository.findByNom(nom);
    }

    private void validerAuteur(Auteur auteur){
        if(auteur == null){
            throw new IllegalArgumentException("L'auteur ne peut pas être null");
        }
        if(auteur.getNom() == null || auteur.getNom().trim().isEmpty()){
            throw new IllegalArgumentException("Le nom de l'auteur est obligatoire");
        }
        if(auteur.getPrenom() == null || auteur.getPrenom().trim().isEmpty()){
            throw new IllegalArgumentException("Le prénom de l'auteur est obligatoire");
        }
    }
}
