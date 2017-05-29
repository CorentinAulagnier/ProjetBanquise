package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class AlgoIA : Implémente les algorithmes utilisés par l'IA
 */
public class AlgoIA {

    /**
     * Reference au joueur IA
     */
    IA myIA = null;

    /**
     * Constructeurs
     */
    /**
     * Constructeurs.
     *
     * @param ia Le joueur de type IA.
     */
    public AlgoIA(IA ia) {
        myIA = ia;
    }

    /**
     * Algorithme de placement pour l'IA facile
     *
     * @param p La partie.
     *
     * @return renvoie les coordonnees de la case choisie (Case choisie
     * aleatoirement )
     */
    public Coordonnees placementFacile(Partie p) {
        Random r = new Random();

        int i = 0;
        int j = 0;
        Coordonnees c = new Coordonnees(i, j);
        do {
            i = r.nextInt(8);

            if (i % 2 == 0) {
                j = r.nextInt(7);       // Choix aleatoire de la tuile 
            } else {
                j = r.nextInt(8);
            }
            c = new Coordonnees(i, j);
        } while ((p.b.getTuile(c) == null) || (p.b.getTuile(c).aUnPingouin) || (p.b.getTuile(c).nbPoissons != 1));

        return c;
    }

    /**
     * Algorithme de choix d'une case a jouer pour l'IA facile
     *
     * @param p La partie.
     *
     * @return Renvoie un couple de coordonnee : (Position du pingouin choisi
     * avant le deplacement, Position du pingouin choisi apres le deplacement) .
     */
    public CoupleGenerique<Coordonnees, Coordonnees> algoFacile(Partie p) {

        Random r = new Random();
        int notreNum = p.joueurActif;
        int notreNbPingouin = p.joueurs[notreNum].nbPingouin;

        /* Choix aleatoire du Pingouin */
        int val = r.nextInt(notreNbPingouin);
        while (!p.joueurs[notreNum].myPingouins[val].actif) {
            val = r.nextInt(notreNbPingouin);
        }
        Coordonnees c1 = p.joueurs[notreNum].myPingouins[val].position;
        ArrayList<ArrayList<Coordonnees>> coupPossible = p.b.deplacementPossible(c1);
        int dir = r.nextInt(coupPossible.size());
        while (coupPossible.get(dir).size() == 0) {
            dir = r.nextInt(coupPossible.size());
        }
        int choix = r.nextInt(coupPossible.get(dir).size());

        Coordonnees c2 = coupPossible.get(dir).get(choix);

        return new CoupleGenerique(c1, c2);

    }

    /**
     * Teste si une case a un pingouin a cote
     *
     * @param p La partie.
     *
     * @param c La coordonnee de la tuile a tester.
     *
     * @return Renvoie vrai si un pingouin se trouve a cote de la tuile, faux
     * sinon.
     */
    public boolean aUnPingouinACote(Partie p, Coordonnees c) {

        boolean trouve = false;
        for (int i = 0; i < 6; i++) {
            if ((p.b.getTuile(p.b.getVoisin(i, c)) != null) && (p.b.getTuile(p.b.getVoisin(i, c)).aUnPingouin && p.appartientPingouin(p.b.getVoisin(i, c)) == p.joueurActif)) {
                trouve = true;
            }
        }
        return trouve;
    }

    /**
     * Teste si une case a un pingouin presque a cote
     *
     * @param p La partie.
     *
     * @param c La coordonnee de la tuile a tester.
     *
     * @return Renvoie vrai si un pingouin se trouve a cote d'une tuile qui est
     * a cote de la tuile de coordonnees c faux sinon.
     */
    public boolean aUnPingouinPresqueACote(Partie p, Coordonnees c) {

        boolean trouve = false;
        for (int i = 0; i < 6; i++) {
            if (p.b.getTuile(p.b.getVoisin(i, c)) != null) {
                trouve = trouve || aUnPingouinACote(p, p.b.getVoisin(i, c));
            }
        }
        return trouve;
    }

    /**
     * Trouve une tuile definie par son nombre de poissons la plus proche d'une
     * certaine coordonnee
     *
     * @param p La partie.
     *
     * @param c La coordonnee de depart de l'algo.
     *
     * @param poiss Le nombre de poisson que l'on veut sur la case a trouver .
     *
     * @return Les coordonnees de la tuile contenant poiss poissons la plus
     * proche de la coordonnee c .
     */
    public Coordonnees meilleur(Partie p, Coordonnees c, int poiss) {
        LinkedList<Coordonnees> file = new LinkedList<>();
        HashMap<Coordonnees, Integer> hash = new HashMap<>();
        Tuile tuileDep = p.b.getTuile(c);
        file.add(c);
        hash.put(c, 0);

        while ((!file.isEmpty()) && (!((tuileDep.nbPoissons == poiss) && (!tuileDep.aUnPingouin) && !aUnPingouinACote(p, c)))) { // tant que la case n'est pas valide : valide = (nbPoissons=3 && !aUnPingouin)
            c = file.pop();
            tuileDep = p.b.getTuile(c);

            for (int i = 0; i < 6; i++) {
                if (p.b.getVoisin(i, c) != null) {
                    if (!hash.containsKey(p.b.getVoisin(i, c))) {
                        file.add(p.b.getVoisin(i, c));
                        hash.put(p.b.getVoisin(i, c), 0);
                    }
                }
            }
        }
        return c;
    }

    /**
     * Fonction de placement moyen
     *
     * @param p La partie.
     *
     * @return Renvoie la coordonee de la tuile libre contenant 3 poisssons la
     * plus au centre .
     */
    public Coordonnees placementMoyen(Partie p) {
        Coordonnees c = new Coordonnees(3, 3);
        Coordonnees retour = meilleur(p, c, 1);
        if ((p.b.getTuile(retour).nbPoissons == 1) && (!p.b.getTuile(retour).aUnPingouin)) {
            return retour;
        } else {
            return placementFacile(p);
        }
    }

    /**
     * Test si une tuile est une feuille
     *
     * @param c Les coordonnees de la tuile a traiter.
     *
     * @param p La partie.
     *
     * @return Renvoie vrai si la tuile de coordonnees c est une destination
     * finale.
     */
    public boolean isFeuille(Coordonnees c, Partie p) {

        boolean trouve = true;

        for (int i = 0; i < 6; i++) {
            trouve = trouve && ((p.b.getTuile(p.b.getVoisin(i, c)) == null) || (p.b.getTuile(p.b.getVoisin(i, c)).aUnPingouin) || (p.b.getTuile(p.b.getVoisin(i, c)).nbPoissons == 0));
        }
        return trouve;
    }

    /**
     * Test si une configuration de partie est une configuration finale
     *
     * @param p La partie.
     *
     * @param j Le joueur.
     *
     * @return Renvoie vrai si dans la partie, le joueur j ne peut plus bouger
     * aucun pingouin.
     */
    public boolean isFeuillePingu(Partie p, Joueur j) {
        boolean bool = true;
        for (int i = 0; i < j.nbPingouin; i++) {
            if (j.myPingouins[i].actif) {
                if (!isFeuille(j.myPingouins[i].position, p)) {
                    bool = false;
                }
            }
        }
        return bool;
    }

    /**
     * Parcours de l'ensemble des coups possibles pour un pingouin
     *
     * @param c Les coordonnees du debut de parcours (du pingouin).
     *
     * @param partie La partie.
     *
     * @param nbCoupsmax Le nombre de coups que l'on veut evaluer.
     *
     * @param nbCoups le nombre de coups qu'il reste a evaluer.
     *
     * @param somme Valeur servant a la ponderation.
     *
     * @param coord1stchoice Sauvegarde de la coordonnee du premier deplacement
     * du pingouin.
     *
     *
     * @return Renvoie un couple (coordonnes, int) = (coordonnees du
     * deplacement,valeur de la ponderation).
     */
    public CoupleGenerique<Coordonnees, Integer> parcoursGraphe(Coordonnees c, Partie partie, int nbCoupsmax, int nbCoups, int somme, Coordonnees coord1stchoice) {

        if ((nbCoups == nbCoupsmax - 1)) {
            coord1stchoice = c;
        }

        if ((nbCoups <= 0) || isFeuille(c, partie)) {
            Tuile tuile = partie.b.getTuile(c);
            return new CoupleGenerique<Coordonnees, Integer>(coord1stchoice, somme);
        }

        Partie p = (Partie) partie.clone();
        Tuile tuile = p.b.getTuile(c);
  
        tuile.nbPoissons = 0;
        p.b.setTuile(c, tuile);

        CoupleGenerique<Coordonnees, Integer> val = new CoupleGenerique<Coordonnees, Integer>(new Coordonnees(0, 0), 0);
        CoupleGenerique<Coordonnees, Integer> tmp = new CoupleGenerique<Coordonnees, Integer>(new Coordonnees(0, 0), 0);

        ArrayList<ArrayList<Coordonnees>> coupPossible = p.b.deplacementPossible(c);
        for (int i = 0; i < 6; i++) { // Boucle des directions

            for (int j = 0; j < coupPossible.get(i).size(); j++) {
                tmp = parcoursGraphe(coupPossible.get(i).get(j), p, nbCoupsmax, nbCoups - 1, somme + p.b.getTuile(coupPossible.get(i).get(j)).nbPoissons, coord1stchoice);   // On s'arrete sur la case
                if (val.e2 <= tmp.e2) {
                    val = tmp;
                }
            }
        }
        return val;
    }

    /**
     * Algorithme de choix d'une case a jouer pour l'IA moyenne
     *
     * @param p La partie.
     *
     * @return Renvoie un couple de coordonnee : (Position du pingouin choisi
     * avant le deplacement, Position du pingouin choisi apres le deplacement) .
     */
    public CoupleGenerique<Coordonnees, Coordonnees> algoMoyen(Partie p) {
        int nbcoupsrecherches = 5; // Choix du "4" très réfléchi mais modifiable.
        if (p.b.nbTuilesLibres() <= 45) {
            nbcoupsrecherches = 6;
        }
        if (p.b.nbTuilesLibres() <= 35) {
            nbcoupsrecherches = 8;
        }
        if (p.b.nbTuilesLibres() <= 25) {
            nbcoupsrecherches = 10;
        }
        if (p.b.nbTuilesLibres() <= 20) {
            nbcoupsrecherches = 15;
        }
        int max = 0;
        Coordonnees coordmax = null;
        int indx = 0;
        ArrayList<CoupleGenerique<Coordonnees, Integer>> poidsPingouins = new ArrayList<>();

        for (int i = 0; i < p.joueurs[p.joueurActif].nbPingouin; i++) {
            if (p.joueurs[p.joueurActif].myPingouins[i].actif) {
                CoupleGenerique<Coordonnees, Integer> coordint = parcoursGraphe(p.joueurs[p.joueurActif].myPingouins[i].position, p, nbcoupsrecherches, nbcoupsrecherches, 0, new Coordonnees(0, 0));
                poidsPingouins.add(i, coordint);
            } else {
                CoupleGenerique<Coordonnees, Integer> cc = new CoupleGenerique(new Coordonnees(0, 0), 0);
                poidsPingouins.add(i, cc);
            }
        }
        ArrayList<CoupleGenerique<Coordonnees, Integer>> meilleursPingouins = new ArrayList<>();
        for (int i = 0; i < poidsPingouins.size(); i++) {
            if (poidsPingouins.get(i).e2 == max) {
                meilleursPingouins.add(new CoupleGenerique(poidsPingouins.get(i).e1,i));
            }
            if (poidsPingouins.get(i).e2 > max) {
                meilleursPingouins.clear();
                meilleursPingouins.add(new CoupleGenerique(poidsPingouins.get(i).e1,i));
                max = poidsPingouins.get(i).e2;

            }
        }
        Random r = new Random();
        int choix = r.nextInt(meilleursPingouins.size());
        indx = meilleursPingouins.get(choix).e2;
        coordmax = meilleursPingouins.get(choix).e1;
        return new CoupleGenerique<Coordonnees, Coordonnees>(p.joueurs[p.joueurActif].myPingouins[indx].position, coordmax);
    }

    /**
     * Donne le nombre de pingouins total present au debut de la partie
     *
     * @param p La partie.
     *
     * @return Renvoie un entier representant la somme des pingouins de la
     * partie ( 8 pour 2 ou 4 joueurs, 9 pour 3 joueurs)
     */
    public int nbTotalPingouins(Partie p) {
        int res = 0;
        for (int i = 0; i < p.nbJoueurs; i++) {
            res = res + p.joueurs[i].nbPingouin;
        }
        return res;
    }

    /**
     * Donne le nombre de tuiles qui restent accessibles
     *
     * @param p La partie.
     *
     * @return Renvoie un entier representant la somme des tuiles accessibles.
     */
    public int nbTuileRestantes(Partie p) {
        int somme = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Coordonnees c = new Coordonnees(i, j);

                if ((p.b.getTuile(c) != null) && !(p.b.getTuile(c).aUnPingouin) && estSeul(c, p) == 0) {
                    somme = somme + p.b.getTuile(c).nbPoissons;
                }

            }
        }
        return somme;
    }

    /**
     * Donne les tuiles n'ayant qu'un poisson qui se trouvent a une intersection
     * de 3
     *
     * @param p La partie.
     *
     * @return Renvoie une liste de (coordonnees, entier) disponibles
     * representant (tuile, nombre de 3 directement accessible) .
     */
    public ArrayList<CoupleGenerique<Coordonnees, Integer>> carrefourDeTroyes(Partie p) {

        ArrayList<CoupleGenerique<Coordonnees, Integer>> caroufDe3 = new ArrayList();

        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {

                Coordonnees c = new Coordonnees(i, j);

                if ((p.b.getTuile(c) != null) && (p.b.getTuile(c).nbPoissons == 1) && (!p.b.getTuile(c).aUnPingouin)) {

                    int k = 0;
                    int nbDeTroisAccessibles = 0;

                    ArrayList<ArrayList<Coordonnees>> coupPossible = p.b.deplacementPossible(c);
                    for (int n = 0; n < 6; n++) { // Boucle des directions

                        if (!coupPossible.get(n).isEmpty()) {
                            int m = 0;
                            while ((m < coupPossible.get(n).size()) && p.b.getTuile(coupPossible.get(n).get(m)).nbPoissons != 3) {
                                m++;
                            }
                            if ((m < coupPossible.get(n).size()) && p.b.getTuile(coupPossible.get(n).get(m)).nbPoissons == 3) {
                                nbDeTroisAccessibles = nbDeTroisAccessibles + 1;
                            }
                            if ((m == coupPossible.get(n).size()) && p.b.getTuile(coupPossible.get(n).get(m - 1)).nbPoissons == 3) {
                                nbDeTroisAccessibles = nbDeTroisAccessibles + 1;
                            }
                        }
                    }

                    if (nbDeTroisAccessibles == 2 && cotoieUnTrois(c, p) && !aUnPingouinPresqueACote(p, c)) {
                        caroufDe3.add(new CoupleGenerique(c, nbDeTroisAccessibles + 1)); // 3
                    } else if ((nbDeTroisAccessibles == 2) && !aUnPingouinPresqueACote(p, c)) {
                        caroufDe3.add(new CoupleGenerique(c, nbDeTroisAccessibles)); // 2
                    } else if ((nbDeTroisAccessibles >= 3) && !aUnPingouinPresqueACote(p, c)) {
                        caroufDe3.add(new CoupleGenerique(c, nbDeTroisAccessibles + 1)); // nbTrois + 1
                    }
                }
            }
        }

        return caroufDe3;
    }

    /**
     * Indique si une tuile de coordonnee c est à côté d'un 3
     *
     * @param c Les coordonnees de la tuile à tester.
     *
     * @param p La partie.
     *
     * @return Renvoie vrai si la tuile est à côté d'un 3
     */
    public boolean cotoieUnTrois(Coordonnees c, Partie p) {
        ArrayList<ArrayList<Coordonnees>> coupPossible = p.b.deplacementPossible(c);

        boolean b = false;
        for (int n = 0; n < 6; n++) {
            b = b || (!coupPossible.get(n).isEmpty() && (p.b.getTuile(coupPossible.get(n).get(0)).nbPoissons == 3));
        }
        return b;
    }

    /**
     * Donne le maximum d'une liste (coordonnee, entier) par rapport à l'entier
     *
     * @param liste la liste à explorer
     *
     * @return Renvoie l'element de la liste qui a l'entier maximum.
     */
    public CoupleGenerique<Coordonnees, Integer> maximum(ArrayList<CoupleGenerique<Coordonnees, Integer>> liste) {
        int i = 0;
        CoupleGenerique<Coordonnees, Integer> max = new CoupleGenerique(new Coordonnees(0, 0), 0);

        while (i != liste.size()) {
            if (max.e2 <= liste.get(i).e2) {
                max = liste.get(i);
            }
            i = i + 1;
        }
        return max;
    }

    /**
     * Fonction de placement difficile
     *
     * @param p La partie.
     *
     * @return Renvoie la coordonnee de placement de pingouin la plus
     * judicieuse.
     */
    public Coordonnees placementDifficile(Partie p) {

        Coordonnees retour = new Coordonnees();
        ArrayList<CoupleGenerique<Coordonnees, Integer>> caroufDe3 = carrefourDeTroyes(p);

        if (!caroufDe3.isEmpty()) {
            CoupleGenerique<Coordonnees, Integer> cg = maximum(caroufDe3);
            retour = cg.e1;
        } else {
            retour = placementMoyen(p);
        }

        return retour;

    }

    /**
     * Fonction donnant le numero de joueur suivant
     *
     * @param joueurActuel numero du joueur actuel.
     *
     * @param p La partie.
     *
     * @return Renvoie la coordonee de placement de pingouin la plus judicieuse.
     */
    public int joueurSuivant(int joueurActuel, Partie p) {
        if (joueurActuel == p.nbJoueurs - 1) {
            return 0;
        } else {
            return joueurActuel + 1;
        }
    }

 

    /**
     * Fonction donnant le nmbre de points accessibles d'un joueur (tous pingouins confondus)
     *
     * @param numjoueur le numero du joueur.
     *
     * @param p La partie.
     *
     * @return Renvoie le nombre de poissons qu'un joueur peut obtenir au maximum.
     */
    public int nbPointsAccessiblesPourTous(Partie p, int numjoueur) {
        HashMap<Coordonnees, Integer> hash = new HashMap<>();
        int somme = 0;

        for (int j = 0; j < p.joueurs[numjoueur].nbPingouin; j++) {
            if (p.joueurs[numjoueur].myPingouins[j].actif) {
                Coordonnees c = p.joueurs[numjoueur].myPingouins[j].position;
                LinkedList<Coordonnees> file = new LinkedList<>();
                Tuile tuileDep = p.b.getTuile(c);
                file.add(c);
                hash.put(c, 0);

                while (!file.isEmpty()) {
                    c = file.pop();
                    tuileDep = p.b.getTuile(c);

                    if (tuileDep.aUnPingouin && tuileDep != p.b.getTuile(p.joueurs[numjoueur].myPingouins[j].position)) {

                    } else {
                        for (int i = 0; i < 6; i++) {
                            if (p.b.getVoisin(i, c) != null && p.b.getTuile(p.b.getVoisin(i, c)).nbPoissons != 0 && !p.b.getTuile(p.b.getVoisin(i, c)).aUnPingouin) {
                                if (!hash.containsKey(p.b.getVoisin(i, c))) {
                                    somme = somme + p.b.getTuile(c).nbPoissons;
                                    file.add(p.b.getVoisin(i, c));
                                    hash.put(p.b.getVoisin(i, c), 0);
                                }
                            }
                        }
                    }
                }
            }
        }
        return somme;
    }

    
    /**
     * Test si le pingouin est seul, et renvoie le nombre de points théorique que ce pingouin peut atteindre idéalement
     *
     * @param pinguBase les coordonnees du pingouin a tester.
     *
     * @param p La partie.
     *
     * @return Renvoie un couple (booleen, entier) representant (vrai si le pingouin est seul, le nombre de points qu'il peut recupérer dans un cas "idéal").
     */
    public CoupleGenerique<Boolean, Integer> nbPointsAccessibles(Coordonnees pinguBase, Partie p) {

        Coordonnees c = pinguBase;
        boolean estSeul = true;
        int somme = 0;
        LinkedList<Coordonnees> file = new LinkedList<>();
        HashMap<Coordonnees, Integer> hash = new HashMap<>();
        Tuile tuileDep = p.b.getTuile(c);
        file.add(c);
        hash.put(c, 0);

        while (!file.isEmpty()) {
            c = file.pop();
            tuileDep = p.b.getTuile(c);

            if (tuileDep.aUnPingouin && tuileDep != p.b.getTuile(pinguBase)) {

                estSeul = false;

            } else {
                for (int i = 0; i < 6; i++) {
                    if (p.b.getVoisin(i, c) != null && p.b.getTuile(p.b.getVoisin(i, c)).nbPoissons != 0 && !p.b.getTuile(p.b.getVoisin(i, c)).aUnPingouin) {
                        if (!hash.containsKey(p.b.getVoisin(i, c))) {
                            somme = somme + p.b.getTuile(c).nbPoissons;
                            file.add(p.b.getVoisin(i, c));
                            hash.put(p.b.getVoisin(i, c), 0);
                        }
                    }
                }
            }
        }
        return new CoupleGenerique(estSeul, somme);
    }

    /**
     * Fonction qui calcule la pondération associée au coup joué entre la partie p1 et la partie p2
     *
     * @param p1 La partie.
     * 
     * @param p2 La partie.
     * 
     * @param numjoueur le numero du joueur jouant le coup a tester.
     *
     * @return Renvoie un entier representant la pondération associée au coup joué entre la partie p1 et la partie p2.
     */
    public int listePingu(Partie p1, Partie p2, int numjoueur) {
        CoupleGenerique<Boolean, Integer> cgAvant = new CoupleGenerique(false, 0);
        CoupleGenerique<Boolean, Integer> cgApres = new CoupleGenerique(false, 0);

        int ponderation = 0;
        int nbTuilesRestantes = nbTuileRestantes(p2);
        for (int j = 0; j < p1.nbJoueurs; j++) {
            for (int p = 0; p < p1.joueurs[j].nbPingouin; p++) {
                if (p1.joueurs[j].myPingouins[p].actif) {

                    cgAvant = p1.joueurs[j].myPingouins[p].peutAtteindre;
                    cgApres = nbPointsAccessibles(p2.joueurs[j].myPingouins[p].position, p2);

                    p2.joueurs[j].myPingouins[p].peutAtteindre = cgApres;

                    if ((j != p1.joueurActif) && (!p2.joueurs[j].myPingouins[p].actif)) { // Si le pingouin de l'autre devient inactif! 
                        ponderation = ponderation + 300;
                    }
                    if ((j == p1.joueurActif) && (!p2.joueurs[j].myPingouins[p].actif)) { // Si un de nos pingouins devient inactif...
                        ponderation = ponderation - 300;
                    }

                    if (p2.joueurs[j].myPingouins[p].actif) {
                        if (!cgAvant.e1 && cgApres.e1) { // Le pingouin devient seul
                            if ((j == p1.joueurActif) && cgApres.e2 > nbTuilesRestantes / 6) { // Si c'est notre pingouin et que c'est interessant pour nous
                                ponderation = ponderation + cgApres.e2;
                            } else if (j == p1.joueurActif) { // Si c'est a nous mais que c'est nul donc on veut pas
                                ponderation = ponderation - nbTuilesRestantes;
                            } else if ((j != p1.joueurActif) && cgApres.e2 > nbTuilesRestantes / 6) { // Si c'est pas notre pingouin mais que c'est bien, on veut pas
                                ponderation = ponderation - cgApres.e2;
                            } else { // Si c'est pas nous, mais c'est pas bien, donc on est content
                                ponderation = ponderation + nbTuilesRestantes;
                            }
                        } else if (p1.appartientPingouin(p1.joueurs[j].myPingouins[p].position) != numjoueur) {
                            if (j == p1.joueurActif) { // Si c'est nous et que c'est pas notre pingouin
                                ponderation = ponderation - (cgAvant.e2 - cgApres.e2);
                            } else if (j != p1.joueurActif) {
                                ponderation = ponderation + ((cgAvant.e2 - cgApres.e2) * 2);
                            }
                        } else if (p1.appartientPingouin(p1.joueurs[j].myPingouins[p].position) == numjoueur) {
                            if (j == p1.joueurActif) { // Si c'est nous et que c'est pas notre pingouin
                                ponderation = ponderation - (cgAvant.e2 - cgApres.e2);
                            } else if (j != p1.joueurActif) {
                                ponderation = ponderation + ((cgAvant.e2 - cgApres.e2) * 2);
                            }
                        }

                    }
                }
            }

            int valAvant = nbPointsAccessiblesPourTous(p1, j);
            int valApres = nbPointsAccessiblesPourTous(p2, j);

            if (j == p1.joueurActif) {
                if (valAvant - valApres > 3) {
                    ponderation = ponderation - (valAvant - valApres);
                }
            } else if (valAvant - valApres > 3) {
                ponderation = ponderation + (valAvant - valApres);
            }
        }
        return ponderation;
    }

    /**
     * Fonction qui met à jour l'attribut "peutAtteindre" de pingouin
     * 
     * @param p La partie.
     */
    public void majValeurZonePing(Partie p) {
        for (int j = 0; j < p.nbJoueurs; j++) {
            for (int i = 0; i < p.joueurs[j].nbPingouin; i++) {
                if (p.joueurs[j].myPingouins[i].actif) {
                    p.joueurs[j].myPingouins[i].peutAtteindre = nbPointsAccessibles(p.joueurs[j].myPingouins[i].position, p);
                }
            }
        }
    }

    /**
     * Regarde si un pingouin bloque un morceau de banquise. - En regardant
     * chacun de ses voisins, si aucun pingouin ne peut plus acceder à cette
     * tuile (estSeul renvoie vrai), on enregistre le gain associé à cette ile
     *
     * @param c1 La coordonnee du pingouin a tester.
     *
     * @param p La partie.
     *
     * @return Renvoie une liste de (Coordonnees, Integer) représentant (un
     * voisin de c1, la somme des poissons que l'on peut obtenir)
     */
    public ArrayList<CoupleGenerique<Coordonnees, Integer>> voisinsSeuls(Coordonnees c1, Partie p) {
        int i = 0;
        ArrayList<CoupleGenerique<Coordonnees, Integer>> arraylist = new ArrayList<>();
        while (i < 6) {
            if (p.b.getVoisin(i, c1) != null && !p.b.getTuile(p.b.getVoisin(i, c1)).aUnPingouin && p.b.getTuile(p.b.getVoisin(i, c1)).nbPoissons != 0) {
                int tmp = estZoneSeule(p.b.getVoisin(i, c1), c1, p);
                if (tmp != 0) {
                    arraylist.add(new CoupleGenerique(p.b.getVoisin(i, c1), tmp));
                }
            }
            i++;
        }
        return arraylist;
    }

    /**
     * Donne la somme des points du morceau de banquise ou se trouve la
     * coordonnee c (a cote d'un pingouin) si aucun pingouin n'est trouve autour
     *
     * @param c La coordonnee à tester.
     *
     * @param pinguBase La coordonnee du pingouin de base.
     *
     * @param p La partie.
     *
     * @return Renvoie 0 si il y a un pingouin sur le meme morceau de banquise
     * que la coordonnee c (qui se trouve à cote du pingouin pinguBase), la
     * somme des points du morceau de banquise sinon
     */
    public int estZoneSeule(Coordonnees c, Coordonnees pinguBase, Partie p) {
        if (p.b.nbTuilesLibres() <= 40) {
            Coordonnees debut = c;
            int somme = 0;
            LinkedList<Coordonnees> file = new LinkedList<>();
            HashMap<Coordonnees, Integer> hash = new HashMap<>();
            Tuile tuileDep = p.b.getTuile(c);
            file.add(c);
            hash.put(c, 0);

            while (!file.isEmpty()) {
                c = file.pop();
                tuileDep = p.b.getTuile(c);

                if (tuileDep.aUnPingouin) {
                    if (tuileDep != p.b.getTuile(pinguBase)) {
                        return 0;
                    }
                } else {
                    for (int i = 0; i < 6; i++) {
                        if (p.b.getVoisin(i, c) != null && p.b.getTuile(p.b.getVoisin(i, c)).nbPoissons != 0) {
                            if (!hash.containsKey(p.b.getVoisin(i, c))) {
                                somme = somme + p.b.getTuile(c).nbPoissons;
                                file.add(p.b.getVoisin(i, c));
                                hash.put(p.b.getVoisin(i, c), 0);
                            }
                        }
                    }
                }
            }
            return somme;
        } else {
            return 0;
        }
    }

    /**
     * Donne la somme des points du morceau de banquise ou se trouve la
     * coordonnee c si aucun pingouin n'est trouve autour
     *
     * @param c La coordonnee du pingouin a tester.
     *
     * @param p La partie.
     *
     * @return Renvoie 0 si il y a un pingouin sur le meme morceau de banquise
     * que la coordonnee c (c non incluse), la somme des points du morceau de
     * banquise sinon
     */
    public int estSeul(Coordonnees c, Partie p) {
        if (p.b.nbTuilesLibres() <= 50) {
            Coordonnees debut = c;
            int somme = 0;
            LinkedList<Coordonnees> file = new LinkedList<>();
            HashMap<Coordonnees, Integer> hash = new HashMap<>();
            Tuile tuileDep = p.b.getTuile(c);
            file.add(c);
            hash.put(c, 0);

            while (!file.isEmpty()) {
                c = file.pop();
                tuileDep = p.b.getTuile(c);

                if (tuileDep.aUnPingouin && (!c.equals(debut))) {
                    if (p.appartientPingouin(c) != p.joueurActif) {
                        return 0;
                    }

                } else {
                    for (int i = 0; i < 6; i++) {
                        if (p.b.getVoisin(i, c) != null && p.b.getTuile(p.b.getVoisin(i, c)).nbPoissons != 0) {
                            if (!hash.containsKey(p.b.getVoisin(i, c))) {
                                somme = somme + p.b.getTuile(c).nbPoissons;
                                file.add(p.b.getVoisin(i, c));
                                hash.put(p.b.getVoisin(i, c), 0);
                            }
                        }
                    }
                }
            }
            return somme;
        } else {
            return 0;
        }
    }

    /**
     * Teste si tous les pingouins doivent rester sur place
     *
     * @param num le numero du joueur actuel.
     *
     * @param p La partie.
     *
     * @return Renvoie vrai si tous les pingouins restent sur place, faux sinon
     */
    public boolean tousLesPingouinsRestentSurPlace(int num, Partie p) {
        for (int i = 0; i < p.joueurs[num].nbPingouin; i++) {
            if (p.joueurs[num].myPingouins[i].actif && !p.joueurs[num].myPingouins[i].resteSurPlace) {
                return false;
            }
        }
        return true;
    }

    /**
     * Regarde quels sont les pingouins que deviennent inactifs d'une partie à
     * l'autre
     *
     * @param num le numero du joueur actuel.
     *
     * @param p La partie.
     *
     * @return Renvoie une liste de (coordonnee, integer) representant
     * (Coordonnee du pingouin devenu inactif, numero du joueur auquel il
     * appartient)
     */
    public ArrayList<CoupleGenerique<Coordonnees, Integer>> pingouinsDevenusInactifs(Partie p1, Partie p2) {
        ArrayList<CoupleGenerique<Coordonnees, Integer>> retour = new ArrayList();
        for (int i = 0; i < p1.nbJoueurs; i++) {
            for (int j = 0; j < p1.joueurs[i].nbPingouin; j++) {
                if ((p1.joueurs[i].myPingouins[j].actif) && (!p2.joueurs[i].myPingouins[j].actif)) {
                    retour.add(new CoupleGenerique(p1.joueurs[i].myPingouins[j].position, i));
                }
            }
        }
        return retour;
    }

    /**
     * Fonction qui calcul la distance d'une coordonnee à un autre pingouin de l'IA
     *
     * @param p La partie.
     * 
     * @param c la coordonnee examinée.
     * 
     * @param depart la premiere coordonee examinée.
     * 
     * @param p La partie.
     *
     * @return Renvoie un entier représentant la distance de la coordonnee depart à la coordonnee du pingouin de l'IA le plus proche 
     */
    public int distanceMinAvecLesAutres(Partie p, Coordonnees c, Coordonnees depart, int num) {
        HashMap<Coordonnees, Integer> hash = new HashMap<>();
        hash.put(c, 0);
        LinkedList<CoupleGenerique<Coordonnees, Integer>> file = new LinkedList<>();
        int distance = 1;
        CoupleGenerique<Coordonnees, Integer> couple = new CoupleGenerique(c, 0);
        file.add(couple);
        while (!file.isEmpty() && !(p.b.getTuile(couple.e1).aUnPingouin && !depart.equals(couple.e1) && p.appartientPingouin(couple.e1) == p.joueurActif)) {

            for (int n = 0; n < 6; n++) {

                if ((p.b.getVoisin(n, couple.e1) != null) && ((p.b.getTuile(p.b.getVoisin(n, couple.e1))).nbPoissons != 0) && (!hash.containsKey(p.b.getVoisin(n, couple.e1)))) {

                    file.add(new CoupleGenerique(p.b.getVoisin(n, couple.e1), couple.e2 + 1));
                    hash.put(p.b.getVoisin(n, couple.e1), 0);
                }
            }

            couple = file.pop();
        }
        if (p.b.getTuile(couple.e1).aUnPingouin && !depart.equals(couple.e1) && p.appartientPingouin(couple.e1) == p.joueurActif) {
            return couple.e2;
        } else {
            return 0;
        }

    }

    /**
     * Algorithme Alpha-Beta
     *
     * @param p La partie.
     *
     * @param c Les coordonnees c de la tuile actuelle
     *
     * @param num Le numero du joueur actif.
     *
     * @param nbCoupsmax La profondeur de l'arbre = le nombre de coups que l'on
     * veut evalue.
     *
     * @param nbCoups Le nombre de coups qui reste a evaluer.
     *
     * @param somme Somme servant a la ponderation des feuilles.
     *
     * @param first Vrai si on est dans la premiere execution recursive.
     *
     * @param a Approximation de la borne inferieure de la valeur du noeud.
     *
     * @param b Approximation de la borne superieure de la valeur du noeud.
     *
     * @param indicePingu Indice du pingouin courant.
     *
     * @param coord1stchoice Sauvegarde de la coordonnee du premier deplacement
     * d'un chemin dans l'arbre.
     *
     * @param coordPingu Sauvegarde de la coordonnee du pingouin que l'on
     * deplace en premier dans un chemin dans l'arbre.
     *
     * @return Renvoie un ((Coordonnees, Coordonnees), Integer ) qui represente
     * ((pingouin au depart, tuile choisie), ponderation associée)
     */
   

    public CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> alphaBeta(Partie p, Coordonnees c, int num, int nbCoupsmax, int nbCoups, int somme, boolean first, CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> a, CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> b, int indicePingu, Coordonnees coord1stchoice, Coordonnees coordPingu) {

        if ((nbCoups == nbCoupsmax - 1)) {
            coord1stchoice = c;
        }

        if (num == p.joueurActif) {
            for (int i = 0; i < p.joueurs[num].nbPingouin; i++) {
                if (p.joueurs[num].myPingouins[i].actif && !p.joueurs[num].myPingouins[i].resteSurPlace) {

                    if (estSeul(p.joueurs[num].myPingouins[i].position, p) != 0) {
                        p.joueurs[num].myPingouins[i].resteSurPlace = true;
                    }
                }
            }
        }

        if (nbCoups <= 0 || isFeuillePingu(p, p.joueurs[num]) || tousLesPingouinsRestentSurPlace(num, p)) {
            CoupleGenerique<Coordonnees, Coordonnees> cg = new CoupleGenerique(coordPingu, coord1stchoice);
            return new CoupleGenerique(cg, somme);
        }
        CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> alpha = new CoupleGenerique(a);
        CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> beta = new CoupleGenerique(b);
        CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> tmp = new CoupleGenerique(new CoupleGenerique(coordPingu, coord1stchoice), -100000);

        if (num == p.joueurActif) { // c'est a nous - on maximise 
            tmp = new CoupleGenerique(new CoupleGenerique(coordPingu, coord1stchoice), -100000);

            for (int i = 0; i < p.joueurs[num].nbPingouin; i++) {

                if (p.joueurs[num].myPingouins[i].actif && !p.joueurs[num].myPingouins[i].resteSurPlace) {

                    int somme_total = somme;

                    if (!p.joueurs[num].myPingouins[i].resteSurPlace) {

                        if (first) {
                            coordPingu = p.joueurs[p.joueurActif].myPingouins[i].position;

                        }

                        c = p.joueurs[num].myPingouins[i].position;
                        ArrayList<ArrayList<Coordonnees>> coupPossible = p.b.deplacementPossible(c);

                        for (int n = 0; n < 6; n++) {

                            for (int m = 0; m < coupPossible.get(n).size(); m++) {

                                int somme_total2 = somme_total;
                                Partie p2 = (Partie) p.clone();
                                p2.deplacement(c, coupPossible.get(n).get(m));
                                p2.verifierPingouinActif();

                                int pond = listePingu(p, p2, num);
                                somme_total2 += pond;

                                /* On regarde si le pingouin se rapproche trop d'un autre que dans notre cas */
                                if (first && num == p.joueurActif) {
                                    int distance = distanceMinAvecLesAutres(p2, coupPossible.get(n).get(m), coupPossible.get(n).get(m), num);
                                    if (distance == 1) {
                                        somme_total2 = somme_total2 - 10;
                                    }
                                    if (distance == 2) {
                                        somme_total2 = somme_total2 - 5;
                                    }
                                }

                                tmp = alphaBeta(p2, coupPossible.get(n).get(m), joueurSuivant(num, p2), nbCoupsmax, nbCoups - 1, somme_total2 + p.b.getTuile(coupPossible.get(n).get(m)).nbPoissons, false, alpha, beta, i, coord1stchoice, coordPingu);

                                if (alpha.e2 <= tmp.e2) { // c'est a nous - on maximise
                                    if (alpha.e2 == tmp.e2) {
                                    } else {
                                        alpha = tmp;
                                    }
                                }
                                if (alpha.e2 >= beta.e2) {
                                    return beta;
                                }
                            }
                        }
                    }
                }
            }
            return alpha;
        } else {
            tmp = new CoupleGenerique(new CoupleGenerique(coordPingu, coord1stchoice), 100000);
            for (int i = 0; i < p.joueurs[num].nbPingouin; i++) {

                if (p.joueurs[num].myPingouins[i].actif) {

                    c = p.joueurs[num].myPingouins[i].position;
                    ArrayList<ArrayList<Coordonnees>> coupPossible = p.b.deplacementPossible(c);

                    for (int n = 0; n < 6; n++) {

                        for (int m = 0; m < coupPossible.get(n).size(); m++) {

                            int somme_total = somme;
                            Partie p2 = (Partie) p.clone();

                            p2.deplacement(c, coupPossible.get(n).get(m));
                            p2.verifierPingouinActif();

                            int pond = listePingu(p, p2, num);

                            somme_total += pond;

                            tmp = alphaBeta(p2, coupPossible.get(n).get(m), joueurSuivant(num, p2), nbCoupsmax, nbCoups - 1, somme_total - p.b.getTuile(coupPossible.get(n).get(m)).nbPoissons, false, alpha, beta, i, coord1stchoice, coordPingu);
                            if (beta.e2 > tmp.e2) { // c'est pas a nous - on minimise
                                beta = tmp;
                            }
                            if (alpha.e2 >= beta.e2) {
                                return alpha;
                            }
                        }
                    }
                }
            }
            return beta;
        }

    }

    
    /**
     * Algorithme de choix d'une case a jouer pour l'IA difficile
     *
     * @param p La partie.
     *
     * @return Renvoie un couple de coordonnee : (Position du pingouin choisi
     * avant le deplacement, Position du pingouin choisi apres le deplacement) .
     */
    public CoupleGenerique<Coordonnees, Coordonnees> algoDifficile(Partie p) {
        majValeurZonePing(p);
        int nbCoupsmax = 3;
        Coordonnees c0 = new Coordonnees(0, 0);
        int num = p.joueurActif;
        int nbTuiles = p.b.nbTuilesLibres();

        boolean tousTousSeul = true;
        for (int i = 0; i < p.joueurs[num].nbPingouin; i++) {
            if ((p.joueurs[num].myPingouins[i].actif) && (!p.joueurs[num].myPingouins[i].resteSurPlace)) {
                int tmp = estSeul(p.joueurs[num].myPingouins[i].position, p);
               
                if ((tmp != 0)) {
                    p.joueurs[num].myPingouins[i].resteSurPlace = true;
                } else {
                    tousTousSeul = false;
                }
            }
        }
        if (nbTuiles <= 40) {
            nbCoupsmax = 5;
        }
        if (nbTuiles <= 31) {
            nbCoupsmax = 7;
        }
        if (nbTuiles <= 25) {
            nbCoupsmax = 9;
        }
        if (nbTuiles <= 17) {
            nbCoupsmax = 13;
        }
        if (nbTuiles <= 10) {
            nbCoupsmax = 17;
        }

        if (tousTousSeul) {
            return algoMoyen(p);
        } else {
            CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> alpha = new CoupleGenerique(new CoupleGenerique(c0, c0), -100000);
            CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> beta = new CoupleGenerique(new CoupleGenerique(c0, c0), 100000);

            CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> coucou = alphaBeta(p, c0, num, nbCoupsmax, nbCoupsmax, 0, true, alpha, beta, 0, c0, c0);

            CoupleGenerique<Coordonnees, Coordonnees> retour = new CoupleGenerique(coucou.e1.e1, coucou.e1.e2);
            return retour;
        }

    }
}
