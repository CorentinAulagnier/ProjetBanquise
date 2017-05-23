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
     * Observe une liste representant une zone de la banquise
     *
     * @param p La partie.
     *
     * @param list La liste des cases de la partie à observer.
     *
     * @return renvoie les coordonnees d'une case a 3 presente dans la liste, ou
     * (-1, -1) si non trouvée.
     */
    private Coordonnees containsThree(Partie p, ArrayList<Coordonnees> list) { // 
        for (int i = 0; i < list.size(); i++) {
            if ((p.b.getTuile(list.get(i)).nbPoissons == 3) && !(p.b.getTuile(list.get(i)).aUnPingouin)) {
                return list.get(i);
            }
        }
        return new Coordonnees(-1, -1); // valeur spéciale
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
        int notreNum = notreNumeroDeJoueur(p);
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
     * Algorithme de choix d'une case a jouer pour l'IA facile
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
            if ((p.b.getTuile(p.b.getVoisin(i, c)) != null) && (p.b.getTuile(p.b.getVoisin(i, c)).aUnPingouin)) {
                trouve = true;
            }
        }
        return trouve;
    }

    /**
     * Algorithme de choix d'une case a jouer pour l'IA facile
     *
     * @param p La partie.
     *
     * @param c La coordonnee de la tuile a tester.
     *
     * @return Renvoie vrai si un pingouin se trouve a cote de la tuile, faux
     * sinon.
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
     * Algorithme de choix d'une case a jouer pour l'IA moyenne
     *
     * @param p La partie.
     *
     * @param c La coordonnee de depart de l'algo.
     *
     * @param poiss Le nombre de poisson que l'on veut sur la case a trouver .
     *
     * @return Les coordonnees de la tuile poiss la plus proche de la coordonnee
     * c .
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
     * Test si une case est une feuille
     *
     * @param c Les coordonnees de la case a traiter.
     *
     * @param p La partie.
     *
     * @return Renvoie vrai si la case de coordonnees c est une destination
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
            return new CoupleGenerique<Coordonnees, Integer>(coord1stchoice, somme + tuile.nbPoissons);
        }

        Partie p = (Partie) partie.clone();
        Tuile tuile = p.b.getTuile(c);
        int poids = tuile.nbPoissons;
        tuile.nbPoissons = 0;
        p.b.setTuile(c, tuile);

        CoupleGenerique<Coordonnees, Integer> val = new CoupleGenerique<Coordonnees, Integer>(new Coordonnees(0, 0), 0);
        CoupleGenerique<Coordonnees, Integer> tmp = new CoupleGenerique<Coordonnees, Integer>(new Coordonnees(0, 0), 0);

        ArrayList<ArrayList<Coordonnees>> coupPossible = p.b.deplacementPossible(c);
        for (int i = 0; i < 6; i++) { // Boucle des directions

            for (int j = 0; j < coupPossible.get(i).size(); j++) {
                tmp = parcoursGraphe(coupPossible.get(i).get(j), p, nbCoupsmax, nbCoups - 1, somme + poids, coord1stchoice);   // On s'arrete sur la case
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

        for (int i = 0; i < poidsPingouins.size(); i++) {
            if (poidsPingouins.get(i).e2 > max) {
                max = poidsPingouins.get(i).e2;
                coordmax = poidsPingouins.get(i).e1;
                indx = i;
            }
        }

        return new CoupleGenerique<Coordonnees, Coordonnees>(p.joueurs[p.joueurActif].myPingouins[indx].position, coordmax);
    }

    /**
     * DOnne le numero de l'objet dans le tableau de joueur de la partie
     *
     * @param p La partie.
     *
     * @return Renvoie le numero de joueur de l'IA
     */
    public int notreNumeroDeJoueur(Partie p) {
        int num = 0;
        while ((num != p.nbJoueurs) && !(p.joueurs[num].nom.equals(myIA.nom))) {   // mettre une methode equals dans la classe abstraite Joueur ????????????????????????????
            num = num + 1;
        }
        return num;
    }

    /**
     * Donne le nombre de pingouins actifs encore en jeu
     *
     * @param p La partie.
     *
     * @return Renvoie un entier representant la somme des pingouins actifs
     */
    public int nbTotalPingouinsActifs(Partie p) {
        int res = 0;
        for (int i = 0; i < p.nbJoueurs; i++) {
            for (int j = 0; j < p.joueurs[i].nbPingouin; j++) {
                if (p.joueurs[i].myPingouins[j].actif) {
                    res = res + 1;
                }
            }
        }
        return res;
    }

    /**
     * Donne le nombre de pingouins actifs encore en jeu pour un joueur
     * specifique
     *
     * @param p La partie.
     *
     * @param numjoueur Le numero du joueur.
     *
     * @return Renvoie un entier representant la somme des pingouins actifs du
     * joueur
     */
    public int nbTotalPingouinsActifsJoueur(Partie p, int numjoueur) {
        int res = 0;

        for (int j = 0; j < p.joueurs[numjoueur].nbPingouin; j++) {
            if (p.joueurs[numjoueur].myPingouins[j].actif) {
                res = res + 1;
            }
        }

        return res;
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

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

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
            System.out.println("BIEN");
        } else {
            System.out.println("bof...");
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

    public int getNbVoisinsDirectLibre(Coordonnees c, Partie p) {
        int somme = 0;
        for (int i = 0; i < 6; i++) {
            if (p.b.getVoisin(i, c) != null && p.b.getTuile(p.b.getVoisin(i, c)).nbPoissons != 0 && !p.b.getTuile(p.b.getVoisin(i, c)).aUnPingouin) {
                somme++;
            }
        }
        return somme;
    }

    /**
     * Donne le nombre de pingouins seuls sur leur ilot dans la partie
     *
     * @param p La partie.
     *
     * @return Renvoie un entier représentant la somme des pingouins seuls sur
     * un ilot dans la partie
     */
    public int nbTotalPingouinsSeuls(Partie p) {
        int res = 0;
        for (int i = 0; i < p.nbJoueurs; i++) {
            for (int j = 0; j < p.joueurs[i].nbPingouin; j++) {
                if (p.joueurs[i].myPingouins[j].actif) {
                    if (estSeul(p.joueurs[i].myPingouins[j].position, p) != 0) {
                        res = res + 1;
                    }
                }
            }
        }
        return res;
    }

    public int nbTotalPingouinsSeulsJoueur(Partie p, int numjoueur) {
        int res = 0;

        for (int j = 0; j < p.joueurs[numjoueur].nbPingouin; j++) {
            if (p.joueurs[numjoueur].myPingouins[j].actif) {
                if (estSeul(p.joueurs[numjoueur].myPingouins[j].position, p) != 0) {
                    res = res + 1;
                } else {
                    for (int u = 0; u < 6; u++) {
                        if (p.b.getTuile(p.b.getVoisin(u, p.joueurs[numjoueur].myPingouins[j].position)) != null && !p.b.getTuile(p.b.getVoisin(u, p.joueurs[numjoueur].myPingouins[j].position)).aUnPingouin && p.b.getTuile(p.b.getVoisin(u, p.joueurs[numjoueur].myPingouins[j].position)).nbPoissons != 0) {
                            if (estZoneSeule(p.b.getVoisin(u, p.joueurs[numjoueur].myPingouins[j].position), p.joueurs[numjoueur].myPingouins[j].position, p) > (nbTuileRestantes(p) / 8)) {
                                res = res + 1;
                                break;
                            }
                        }
                    }
                }
            }
        }

        return res;
    }

    public boolean peutAccederA(Coordonnees c1, Coordonnees c2, Partie p) {
        int somme = 0;
        LinkedList<Coordonnees> file = new LinkedList<>();
        HashMap<Coordonnees, Integer> hash = new HashMap<>();
        Tuile tuileDep = p.b.getTuile(c1);
        file.add(c1);
        hash.put(c1, 0);

        while (!file.isEmpty() && !c1.equals(c2)) {
            for (int i = 0; i < 6; i++) {
                if (p.b.getVoisin(i, c1) != null && p.b.getTuile(p.b.getVoisin(i, c1)).nbPoissons != 0) {
                    if (!hash.containsKey(p.b.getVoisin(i, c1))) {
                        file.add(p.b.getVoisin(i, c1));
                        hash.put(p.b.getVoisin(i, c1), 0);
                    }
                }
            }
            c1 = file.pop();

        }
        return (c1.equals(c2));
    }

    /**
     * Donne la liste des pingouins qui vont pouvoir acceder à une zone seule
     * d'une partie à l'autre
     *
     * @param p1 La partie avant le deplacement à tester.
     *
     * @param p2 La partie après le deplacement à tester.
     *
     * @return Renvoie une liste (entier, entier) représentant (numero du
     * joueur, somme des tuiles qu'il sera le seul à pouvoir atteindre)
     */
    public ArrayList<CoupleGenerique<Integer, Integer>> vaAccederAUneZoneSeule(Partie p1, Partie p2) {
        ArrayList<CoupleGenerique<Integer, Integer>> retour = new ArrayList<>();
        for (int i = 0; i < p1.nbJoueurs; i++) {
            for (int j = 0; j < p1.joueurs[i].nbPingouin; j++) {
                if (p1.joueurs[i].myPingouins[j].actif) {
                    if (p2.joueurs[i].myPingouins[j].actif) {

                        ArrayList<CoupleGenerique<Coordonnees, Integer>> temp1 = voisinsSeuls(p1.joueurs[i].myPingouins[j].position, p1);
                        ArrayList<CoupleGenerique<Coordonnees, Integer>> temp2 = voisinsSeuls(p2.joueurs[i].myPingouins[j].position, p2);

                        if (!temp1.equals(temp2) && (temp2.size() != 0)) {
                            retour.add(new CoupleGenerique(i, maximum(temp2).e2));
                        }
                    }
                }

            }
        }
        return retour;
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

    public int estZoneSeule(Coordonnees c, Coordonnees pinguBase, Partie p) {
        if (p.b.nbTuilesLibres() <= 55) {
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
                    if (!c.equals(pinguBase)) {
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
     * Donne la somme des points que le pingouin est le seul à pouvoir
     * atteindre.
     *
     * @param c La coordonnee du pingouin a tester.
     *
     * @param p La partie.
     *
     * @return Renvoie 0 si le pingouin n'est pas seul, la somme des points
     * qu'il est le seul à pouvoir atteindre sinon
     */
// A appeler avec les coordonnees du pingouin a verifier : Coordonnees c = p.joueurs[num].myPingouins[indicePingu].position;
    public int estSeul(Coordonnees c, Partie p) {
        if (p.b.nbTuilesLibres() <= 55) {
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

                    return 0;

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

    public int calculDistance(Coordonnees c1, Coordonnees c2) {
        int dist = 0;
        if (c1.x % 2 == 0) {

        } else {

        }

        return dist;
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

        if (nbCoups <= 0 || isFeuillePingu(p, p.joueurs[num]) || nbTotalPingouinsSeulsJoueur(p, notreNumeroDeJoueur(p)) == nbTotalPingouinsActifsJoueur(p, notreNumeroDeJoueur(p))) {
            CoupleGenerique<Coordonnees, Coordonnees> cg = new CoupleGenerique(coordPingu, coord1stchoice);
            //System.out.println("là bas : " + coordPingu + " - " + coord1stchoice + " avec un score : " + somme);
            return new CoupleGenerique(cg, somme);
        }

        ArrayList<CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer>> meilleurs_coups = null;
        CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> alpha = new CoupleGenerique(a);
        CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> beta = new CoupleGenerique(b);
        CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> tmp = new CoupleGenerique(new CoupleGenerique(coordPingu, coord1stchoice), -100000);

        if (num == notreNumeroDeJoueur(p)) { // c'est a nous - on maximise 
            tmp = new CoupleGenerique(new CoupleGenerique(coordPingu, coord1stchoice), -100000);

            if (first) {
                meilleurs_coups = new ArrayList<>();
            }

            for (int i = 0; i < p.joueurs[num].nbPingouin; i++) {

                if (p.joueurs[num].myPingouins[i].actif && !p.joueurs[num].myPingouins[i].resteSurPlace) {

                    int somme_total = somme;

                    /* Si une zone accessible par le pingouin ne contient aucun autre pingouin et que le nombre de tuile a gagner est suffisant : le pingouin reste danse cette zone */
                    for (int u = 0; u < 6; u++) { // Pour chaque direction 
                        if (p.b.getTuile(p.b.getVoisin(u, p.joueurs[num].myPingouins[i].position)) != null && !p.b.getTuile(p.b.getVoisin(u, p.joueurs[num].myPingouins[i].position)).aUnPingouin && p.b.getTuile(p.b.getVoisin(u, p.joueurs[num].myPingouins[i].position)).nbPoissons != 0) {
                            if (estZoneSeule(p.b.getVoisin(u, p.joueurs[num].myPingouins[i].position), p.joueurs[num].myPingouins[i].position, p) > (nbTuileRestantes(p) / 8)) {
                                p.joueurs[num].myPingouins[i].resteSurPlace = true;
                            }
                        }
                    }
                    
                    if (estSeul(p.joueurs[num].myPingouins[i].position, p) != 0) {
                        p.joueurs[num].myPingouins[i].resteSurPlace = true;
                    }
                    
                    if (!p.joueurs[num].myPingouins[i].resteSurPlace) {
                        
                        if (first) {
                            System.out.println("pingouin num " + i + " position : " + p.joueurs[num].myPingouins[i].position + " poissons sur la case : " + p.b.getTuile(p.joueurs[num].myPingouins[i].position).nbPoissons);
                            coordPingu = p.joueurs[notreNumeroDeJoueur(p)].myPingouins[i].position;

                        }

                        c = p.joueurs[num].myPingouins[i].position;
                        ArrayList<ArrayList<Coordonnees>> coupPossible = p.b.deplacementPossible(c);

                        for (int n = 0; n < 6; n++) {

                            for (int m = 0; m < coupPossible.get(n).size(); m++) {

                                int somme_total2 = somme_total;
                                Partie p2 = (Partie) p.clone();
                                p2.deplacement(c, coupPossible.get(n).get(m));
                                p2.verifierPingouinActif();

                                int nbPinguActif = nbTotalPingouinsActifs(p);
                                int nbJoueurPinguActif = nbTotalPingouinsActifsJoueur(p, num);

                                ArrayList<CoupleGenerique<Integer, Integer>> pingouinsAccedantAUneZoneSeule = vaAccederAUneZoneSeule(p, p2);
                                if (pingouinsAccedantAUneZoneSeule.size() != 0) {
                                    for (int h = 0; h < pingouinsAccedantAUneZoneSeule.size(); h++) {
                                        if (pingouinsAccedantAUneZoneSeule.get(h).e1 == num) {
                                            // System.out.println("ah : " + pingouinsDevenusInactifs.get(h).e2);
                                            if (pingouinsAccedantAUneZoneSeule.get(h).e2 > (nbTuileRestantes(p2) / 6)) {
                                                somme_total2 = somme_total2 + pingouinsAccedantAUneZoneSeule.get(h).e2;
                                            } else {
                                                somme_total2 = somme_total2 - 50; //- pingouinsDevenusInactifs.get(h).e2; // pif
                                            }
                                        } else if (pingouinsAccedantAUneZoneSeule.get(h).e2 > (nbTuileRestantes(p2) / 6)) {
                                            somme_total2 = somme_total2 - pingouinsAccedantAUneZoneSeule.get(h).e2;
                                        } else {
                                            somme_total2 = somme_total2 + 50;
                                        }
                                    }
                                }
                                /* Regarde si un pignouin est passé inactifs. */
                                ArrayList<CoupleGenerique<Coordonnees, Integer>> pinguDevenusInactifs = pingouinsDevenusInactifs(p, p2);
                                if (!pinguDevenusInactifs.isEmpty()) {
                                    for (int w = 0; w < pinguDevenusInactifs.size(); w++) {
                                        if (pinguDevenusInactifs.get(w).e2 == num) {
                                            somme_total2 = somme_total2 - 100;
                                        } else {
                                            somme_total2 = somme_total2 + 100;
                                        }
                                    }
                                }

                                /* Pondere le poids en fonction de la liberte (sauf le pingouin de l'adversaire) de la tuile */
                                /*          for (int o = 0; o < 6; o++) {
                                 if (p2.b.getVoisin(o, coupPossible.get(n).get(m)) == null || (p2.b.getTuile(p2.b.getVoisin(o, coupPossible.get(n).get(m))).aUnPingouin && p2.appartientPingouin(p2.b.getVoisin(o, coupPossible.get(n).get(m))) == num) || p2.b.getTuile(p2.b.getVoisin(o, coupPossible.get(n).get(m))).nbPoissons == 0) {
                                 somme_total2 = somme_total2 - 0.1;
                                 }
                                 }
                                 */
                                tmp = alphaBeta(p2, coupPossible.get(n).get(m), joueurSuivant(num, p2), nbCoupsmax, nbCoups - 1, (somme_total2 + p2.b.getTuile(coupPossible.get(n).get(m)).nbPoissons) * nbCoups, false, alpha, beta, i, coord1stchoice, coordPingu);

                                if (alpha.e2 <= tmp.e2) { // c'est a nous - on maximise
                                    if (Objects.equals(alpha.e2, tmp.e2)) {
                                        if (first) {
                                          meilleurs_coups.add(new CoupleGenerique(tmp));
                                        }
                                    } else {
                                        if (first) {
                                           meilleurs_coups.clear();
                                           meilleurs_coups.add(new CoupleGenerique(tmp));
                                        }
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

           if (first) {
                ArrayList<CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer>> meilleurs_coupsProche = new ArrayList<>();
                int distanceMin = 1111;
                for (int l = 0; l < meilleurs_coups.size(); l++) {
                    CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> temp = meilleurs_coups.get(l);

                    int distance = Math.abs(temp.e1.e1.x - temp.e1.e2.x) + Math.abs(temp.e1.e1.y - temp.e1.e2.y); // Distance de Manhattan
                    System.out.println("coord : " + temp.e1.e1 + " : " + temp.e1.e2 + " Distance : " + distance);
                    if (distanceMin == distance) {
                        meilleurs_coupsProche.add(new CoupleGenerique(temp));
                    } else if (distanceMin > distance) {
                        distanceMin = distance;
                        meilleurs_coupsProche.clear();
                        meilleurs_coupsProche.add(new CoupleGenerique(temp));
                    }
                }
                Random r = new Random();

                int choix = r.nextInt(meilleurs_coupsProche.size());
                alpha = meilleurs_coupsProche.get(choix);
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

                            int nbPinguActif = nbTotalPingouinsActifs(p);
                            int nbJoueurPinguActif = nbTotalPingouinsActifsJoueur(p, num);

                            p2.deplacement(c, coupPossible.get(n).get(m));
                            p2.verifierPingouinActif();

                            ArrayList<CoupleGenerique<Integer, Integer>> pingouinsAccedantAUneZoneSeule = vaAccederAUneZoneSeule(p, p2);
                            if (pingouinsAccedantAUneZoneSeule.size() != 0) {
                                for (int h = 0; h < pingouinsAccedantAUneZoneSeule.size(); h++) {
                                    if (pingouinsAccedantAUneZoneSeule.get(h).e1 == num) {
                                        if (pingouinsAccedantAUneZoneSeule.get(h).e2 > (nbTuileRestantes(p2) / 6)) {
                                            somme_total = somme_total - 50;
                                        } else {
                                            somme_total = somme_total + pingouinsAccedantAUneZoneSeule.get(h).e2; // pif
                                        }
                                    } else if (pingouinsAccedantAUneZoneSeule.get(h).e2 > (nbTuileRestantes(p2) / 6)) {
                                        somme_total = somme_total + 50;
                                    } else {
                                        somme_total = somme_total - pingouinsAccedantAUneZoneSeule.get(h).e2;
                                    }
                                }
                            }

                            /* Regarde si un pignouin est passé inactifs. */
                            ArrayList<CoupleGenerique<Coordonnees, Integer>> pinguDevenusInactifs = pingouinsDevenusInactifs(p, p2);
                            if (!pinguDevenusInactifs.isEmpty()) {
                                for (int w = 0; w < pinguDevenusInactifs.size(); w++) {
                                    if (pinguDevenusInactifs.get(w).e2 == num) {
                                        // somme_total = somme_total + 100;
                                    } else {
                                        somme_total = somme_total - 100;
                                    }
                                }
                            }

                            tmp = alphaBeta(p2, coupPossible.get(n).get(m), joueurSuivant(num, p2), nbCoupsmax, nbCoups - 1, somme_total - p2.b.getTuile(coupPossible.get(n).get(m)).nbPoissons, false, alpha, beta, i, coord1stchoice, coordPingu);
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
        int nbCoupsmax = 3;
        Coordonnees c0 = new Coordonnees(0, 0);
        int num = notreNumeroDeJoueur(p);

        boolean tousTousSeul = true;
        for (int i = 0; i < p.joueurs[num].nbPingouin; i++) {
            if ((p.joueurs[num].myPingouins[i].actif)&& (!p.joueurs[num].myPingouins[i].resteSurPlace)){
                int tmp = estSeul(p.joueurs[num].myPingouins[i].position, p);
                boolean reste = false;
                for (int u = 0; u < 6; u++) {
                    if (p.b.getTuile(p.b.getVoisin(u, p.joueurs[num].myPingouins[i].position)) != null && !p.b.getTuile(p.b.getVoisin(u, p.joueurs[num].myPingouins[i].position)).aUnPingouin && p.b.getTuile(p.b.getVoisin(u, p.joueurs[num].myPingouins[i].position)).nbPoissons != 0) {
                        if (estZoneSeule(p.b.getVoisin(u, p.joueurs[num].myPingouins[i].position), p.joueurs[num].myPingouins[i].position, p) > (nbTuileRestantes(p) / 8)) {
                            reste = true;
                        }
                    }
                }
                if ((tmp !=0) || reste ){
                    p.joueurs[num].myPingouins[i].resteSurPlace = true;
                } else {
                    tousTousSeul = false;
                }
            }
        }

        if (p.b.nbTuilesLibres() <= 55) {
            System.out.println("mAINTENANT 3");
            nbCoupsmax = 3;
        }
        if (p.b.nbTuilesLibres() <= 50) {
            System.out.println("mAINTENANT 5");
            nbCoupsmax = 5;
        }
        if (p.b.nbTuilesLibres() <= 40) {
            System.out.println("mAINTENANT 7");
            nbCoupsmax = 5;

        }
        if (p.b.nbTuilesLibres() <= 30) {
            nbCoupsmax = 7;
            System.out.println("mAINTENANT 7");
        }
        if (p.b.nbTuilesLibres() <= 20) {
            nbCoupsmax = 11;
            System.out.println("mAINTENANT 9");
        }

        if (tousTousSeul) {
            System.out.println("av algo moyen");
            return algoMoyen(p);
        } else {
            CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> alpha = new CoupleGenerique(new CoupleGenerique(c0, c0), -100000);
            CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> beta = new CoupleGenerique(new CoupleGenerique(c0, c0), 100000);
            ArrayList<HashMap<Banquise, ArbreGraphe>> listHash = new ArrayList<>();
            for (int i = 0; i < p.nbJoueurs; i++) {
                listHash.add(new HashMap<Banquise, ArbreGraphe>());
            }

            CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> coucou = alphaBeta(p, c0, num, nbCoupsmax, nbCoupsmax, 0, true, alpha, beta, 0, c0, c0);
            System.out.println("coco " + coucou.e2);

            CoupleGenerique<Coordonnees, Coordonnees> retour = new CoupleGenerique(coucou.e1.e1, coucou.e1.e2);
            System.out.println("retour " + retour.e1 + retour.e2);
            return retour;
        }

    }

    /**
     * Algorithme de TEST de choix d'une case a jouer pour l'IA difficile
     *
     * @param p La partie.
     *
     * @return Renvoie un couple de coordonnee : (Position du pingouin choisi
     * avant le deplacement, Position du pingouin choisi apres le deplacement) .
     */
    public CoupleGenerique<Coordonnees, Coordonnees> algoDifficileTEST(Partie p) {

        if (p.b.nbTuilesLibres() >= 40) {
            return algoMoyen(p);
        } else {
            return algoDifficile(p);
        }
    }

}
