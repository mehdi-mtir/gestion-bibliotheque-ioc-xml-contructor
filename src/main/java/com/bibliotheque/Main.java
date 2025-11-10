package com.bibliotheque;

import com.bibliotheque.repository.AuteurRepository;
import com.bibliotheque.repository.LivreRepository;
import com.bibliotheque.repository.UtilisateurRepository;
import com.bibliotheque.repository.impl.AuteurRepositoryImpl;
import com.bibliotheque.repository.impl.LivreRepositoryImpl;
import com.bibliotheque.repository.impl.UtilisateurRepositoryImpl;
import com.bibliotheque.service.AuteurService;
import com.bibliotheque.service.LivreService;
import com.bibliotheque.service.UtilisateurService;
import com.bibliotheque.service.impl.AuteurServiceImpl;
import com.bibliotheque.service.impl.LivreServiceImpl;
import com.bibliotheque.service.impl.UtilisateurServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try{
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

            //Initialisation des services
            AuteurService auteurService = context.getBean(AuteurServiceImpl.class); //IoC et DI
            LivreService livreService = context.getBean(LivreServiceImpl.class);
            UtilisateurService utilisateurService = context.getBean(UtilisateurServiceImpl.class);

            TestScenario.executerScenarioComplet(auteurService, livreService, utilisateurService);

        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution : " + e.getMessage());
            e.printStackTrace();
        }
    }
}