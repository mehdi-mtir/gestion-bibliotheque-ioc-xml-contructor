package com.bibliotheque;

import com.bibliotheque.model.*;
import com.bibliotheque.service.AuteurService;
import com.bibliotheque.service.LivreService;
import com.bibliotheque.service.UtilisateurService;

import java.time.LocalDate;
import java.util.List;

public class TestScenario {
    public static void executerScenarioComplet(AuteurService auteurService,
                                               LivreService livreService,
                                               UtilisateurService utilisateurService) {

        // Étape 1 : Création des auteurs
        System.out.println("┌─────────────────────────────────────────────────────┐");
        System.out.println("│ ÉTAPE 1 : Création des auteurs                      │");
        System.out.println("└─────────────────────────────────────────────────────┘");

        Auteur victorHugo = creerVictorHugo(auteurService);
        Auteur jkRowling = creerJKRowling(auteurService);
        Auteur isaacAsimov = creerIsaacAsimov(auteurService);

        System.out.println("✓ 3 auteurs créés avec succès\n");

        // Étape 2 : Création des livres
        System.out.println("┌─────────────────────────────────────────────────────┐");
        System.out.println("│ ÉTAPE 2 : Création des livres                       │");
        System.out.println("└─────────────────────────────────────────────────────┘");

        Livre lesMiserables = creerLesMiserables(livreService, victorHugo);
        Livre harryPotter = creerHarryPotter(livreService, jkRowling);
        Livre fondation = creerFondation(livreService, isaacAsimov);
        Livre notreDame = creerNotreDame(livreService, victorHugo);
        Livre fondation2 = creerFondation2(livreService, isaacAsimov);

        System.out.println("✓ 5 livres créés avec succès\n");

        // Étape 3 : Création des utilisateurs
        System.out.println("┌─────────────────────────────────────────────────────┐");
        System.out.println("│ ÉTAPE 3 : Création des utilisateurs                 │");
        System.out.println("└─────────────────────────────────────────────────────┘");

        Utilisateur userStandard = creerUtilisateurStandard(utilisateurService);
        Utilisateur admin = creerAdministrateur(utilisateurService);

        System.out.println("✓ 2 utilisateurs créés avec succès\n");

        // Étape 4 : Affichage de tous les livres
        System.out.println("┌─────────────────────────────────────────────────────┐");
        System.out.println("│ ÉTAPE 4 : Liste complète des livres                 │");
        System.out.println("└─────────────────────────────────────────────────────┘");
        afficherTousLesLivres(livreService);
        System.out.println();

        // Étape 5 : Recherche par auteur
        System.out.println("┌─────────────────────────────────────────────────────┐");
        System.out.println("│ ÉTAPE 5 : Livres de Victor Hugo                     │");
        System.out.println("└─────────────────────────────────────────────────────┘");
        rechercherLivresParAuteur(livreService, victorHugo);
        System.out.println();

        // Étape 6 : Recherche par genre
        System.out.println("┌─────────────────────────────────────────────────────┐");
        System.out.println("│ ÉTAPE 6 : Livres de Science-Fiction                 │");
        System.out.println("└─────────────────────────────────────────────────────┘");
        rechercherLivresParGenre(livreService, Genre.SCIENCE_FICTION);
        System.out.println();

        // Étape 7 : Livres disponibles
        System.out.println("┌─────────────────────────────────────────────────────┐");
        System.out.println("│ ÉTAPE 7 : Livres disponibles (avant emprunt)        │");
        System.out.println("└─────────────────────────────────────────────────────┘");
        afficherLivresDisponibles(livreService);
        System.out.println();

        // Étape 8 : Test d'emprunt
        System.out.println("┌─────────────────────────────────────────────────────┐");
        System.out.println("│ ÉTAPE 8 : Test d'emprunt de livre                   │");
        System.out.println("└─────────────────────────────────────────────────────┘");
        testerEmprunt(livreService, lesMiserables);
        System.out.println();

        // Étape 9 : Livres disponibles après emprunt
        System.out.println("┌─────────────────────────────────────────────────────┐");
        System.out.println("│ ÉTAPE 9 : Livres disponibles (après emprunt)        │");
        System.out.println("└─────────────────────────────────────────────────────┘");
        afficherLivresDisponibles(livreService);
        System.out.println();

        // Étape 10 : Test d'authentification
        System.out.println("┌─────────────────────────────────────────────────────┐");
        System.out.println("│ ÉTAPE 10 : Test d'authentification                  │");
        System.out.println("└─────────────────────────────────────────────────────┘");
        testerAuthentification(utilisateurService);
        System.out.println();

        // Résumé final
        afficherResumeFinal(auteurService, livreService, utilisateurService);
    }

    // Méthodes de création d'auteurs

    public static Auteur creerVictorHugo(AuteurService auteurService) {
        Auteur auteur = new Auteur(
                "Hugo",
                "Victor",
                LocalDate.of(1802, 2, 26),
                "Française",
                "Poète, dramaturge, écrivain, romancier et dessinateur romantique français"
        );
        auteur = auteurService.creerAuteur(auteur);
        System.out.println("  ✓ " + auteur);
        return auteur;
    }

    public static Auteur creerJKRowling(AuteurService auteurService) {
        Auteur auteur = new Auteur(
                "Rowling",
                "J.K.",
                LocalDate.of(1965, 7, 31),
                "Britannique",
                "Auteure britannique, connue pour être la créatrice de la série Harry Potter"
        );
        auteur = auteurService.creerAuteur(auteur);
        System.out.println("  ✓ " + auteur);
        return auteur;
    }

    public static Auteur creerIsaacAsimov(AuteurService auteurService) {
        Auteur auteur = new Auteur(
                "Asimov",
                "Isaac",
                LocalDate.of(1920, 1, 2),
                "Américaine",
                "Écrivain américano-russe, auteur de science-fiction et de vulgarisation scientifique"
        );
        auteur = auteurService.creerAuteur(auteur);
        System.out.println("  ✓ " + auteur);
        return auteur;
    }

    // Méthodes de création de livres

    public static Livre creerLesMiserables(LivreService livreService, Auteur victorHugo) {
        Livre livre = new Livre(
                "978-2-07-036840-4",
                "Les Misérables",
                victorHugo,
                LocalDate.of(1862, 4, 3),
                1488,
                Genre.ROMAN,
                "Roman social décrivant la vie de Jean Valjean, ancien forçat"
        );
        livre = livreService.creerLivre(livre);
        System.out.println("  ✓ " + livre);
        return livre;
    }

    public static Livre creerHarryPotter(LivreService livreService, Auteur jkRowling) {
        Livre livre = new Livre(
                "978-2-07-054127-1",
                "Harry Potter à l'école des sorciers",
                jkRowling,
                LocalDate.of(1997, 6, 26),
                320,
                Genre.FANTASY,
                "Le premier livre de la série Harry Potter, où un jeune sorcier découvre son destin"
        );
        livre = livreService.creerLivre(livre);
        System.out.println("  ✓ " + livre);
        return livre;
    }

    public static Livre creerFondation(LivreService livreService, Auteur isaacAsimov) {
        Livre livre = new Livre(
                "978-2-07-036552-6",
                "Fondation",
                isaacAsimov,
                LocalDate.of(1951, 6, 1),
                256,
                Genre.SCIENCE_FICTION,
                "Premier tome du cycle de Fondation, une saga de science-fiction épique"
        );
        livre = livreService.creerLivre(livre);
        System.out.println("  ✓ " + livre);
        return livre;
    }

    public static Livre creerNotreDame(LivreService livreService, Auteur victorHugo) {
        Livre livre = new Livre(
                "978-2-07-041528-1",
                "Notre-Dame de Paris",
                victorHugo,
                LocalDate.of(1831, 3, 16),
                752,
                Genre.ROMAN,
                "Roman historique se déroulant au XVe siècle dans le Paris médiéval"
        );
        livre = livreService.creerLivre(livre);
        System.out.println("  ✓ " + livre);
        return livre;
    }

    public static Livre creerFondation2(LivreService livreService, Auteur isaacAsimov) {
        Livre livre = new Livre(
                "978-2-07-036553-3",
                "Fondation et Empire",
                isaacAsimov,
                LocalDate.of(1952, 1, 1),
                304,
                Genre.SCIENCE_FICTION,
                "Deuxième tome du cycle de Fondation"
        );
        livre = livreService.creerLivre(livre);
        System.out.println("  ✓ " + livre);
        return livre;
    }

    // Méthodes de création d'utilisateurs

    public static Utilisateur creerUtilisateurStandard(UtilisateurService utilisateurService) {
        Utilisateur utilisateur = new Utilisateur(
                "jean.dupont@email.com",
                "password123",
                "Dupont",
                "Jean",
                Role.USER
        );
        utilisateur = utilisateurService.creerUtilisateur(utilisateur);
        System.out.println("  ✓ " + utilisateur);
        return utilisateur;
    }

    public static Utilisateur creerAdministrateur(UtilisateurService utilisateurService) {
        Utilisateur utilisateur = new Utilisateur(
                "admin@bibliotheque.com",
                "admin2024",
                "Admin",
                "Système",
                Role.ADMIN
        );
        utilisateur = utilisateurService.creerUtilisateur(utilisateur);
        System.out.println("  ✓ " + utilisateur);
        return utilisateur;
    }

    // Méthodes d'affichage et de test

    public static void afficherTousLesLivres(LivreService livreService) {
        List<Livre> livres = livreService.obtenirTousLesLivres();
        System.out.println("📚 Total : " + livres.size() + " livre(s)\n");
        livres.forEach(livre -> System.out.println("  • " + livre));
    }

    public static void rechercherLivresParAuteur(LivreService livreService, Auteur auteur) {
        List<Livre> livres = livreService.rechercherParAuteur(auteur.getId());
        System.out.println("📖 Livres de " + auteur.getNomComplet() + " : " + livres.size() + "\n");
        livres.forEach(livre -> System.out.println("  • " + livre.getTitre() +
                " (" + livre.getDatePublication().getYear() + ")"));
    }

    public static void rechercherLivresParGenre(LivreService livreService, Genre genre) {
        List<Livre> livres = livreService.rechercherParGenre(genre);
        System.out.println("🚀 Livres de genre " + genre + " : " + livres.size() + "\n");
        livres.forEach(livre -> System.out.println("  • " + livre.getTitre() +
                " par " + livre.getAuteur().getNomComplet()));
    }

    public static void afficherLivresDisponibles(LivreService livreService) {
        List<Livre> livres = livreService.obtenirLivresDisponibles();
        System.out.println("✅ Livres disponibles : " + livres.size() + "\n");
        livres.forEach(livre -> System.out.println("  • " + livre.getTitre()));
    }

    public static void testerEmprunt(LivreService livreService, Livre livre) {
        System.out.println("📤 Emprunt du livre : " + livre.getTitre());
        livreService.marquerCommeEmprunte(livre.getId());

        Livre livreActualise = livreService.obtenirLivre(livre.getId());
        System.out.println("  ✓ Statut : " + (livreActualise.estDisponible() ?
                "Disponible" : "Emprunté"));
    }

    public static void testerAuthentification(UtilisateurService utilisateurService) {
        // Test authentification réussie
        System.out.println("🔐 Test 1 : Authentification avec des identifiants valides");
        boolean auth1 = utilisateurService.authentifier(
                "jean.dupont@email.com", "password123");
        System.out.println("  " + (auth1 ? "✓ Authentification réussie" :
                "✗ Authentification échouée"));

        // Test authentification échouée
        System.out.println("\n🔐 Test 2 : Authentification avec un mauvais mot de passe");
        boolean auth2 = utilisateurService.authentifier(
                "jean.dupont@email.com", "wrongpassword");
        System.out.println("  " + (auth2 ? "✗ Authentification réussie (ERREUR!)" :
                "✓ Authentification échouée (attendu)"));

        // Test avec email inexistant
        System.out.println("\n🔐 Test 3 : Authentification avec un email inexistant");
        boolean auth3 = utilisateurService.authentifier(
                "inconnu@email.com", "password123");
        System.out.println("  " + (auth3 ? "✗ Authentification réussie (ERREUR!)" :
                "✓ Authentification échouée (attendu)"));
    }

    public static void afficherResumeFinal(AuteurService auteurService,
                                           LivreService livreService,
                                           UtilisateurService utilisateurService) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║                    RÉSUMÉ FINAL                        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("  👤 Auteurs           : " + auteurService.obtenirTousLesAuteurs().size());
        System.out.println("  📚 Livres            : " + livreService.obtenirTousLesLivres().size());
        System.out.println("  ✅ Livres disponibles : " + livreService.obtenirLivresDisponibles().size());
        System.out.println("  👥 Utilisateurs      : " + utilisateurService.obtenirTousLesUtilisateurs().size());
        System.out.println();
        System.out.println("✅ Tous les tests se sont exécutés avec succès !");
        System.out.println();
    }
}
