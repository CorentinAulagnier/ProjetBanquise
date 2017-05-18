package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
     * Moitie superieure gauche du carre haut gauche
     */
    ArrayList<Coordonnees> platHHG;

    /**
     * Moitie inferieure droite du carre haut gauche
     */
    ArrayList<Coordonnees> platHBG;

    /**
     * Moitie superieure droite du carre haut droit
     */
    ArrayList<Coordonnees> platHHD;

    /**
     * Moitie inferieure gauche du carre haut droit
     */
    ArrayList<Coordonnees> platHBD;

    /**
     * Moitie superieure droite du carre bas gauche
     */
    ArrayList<Coordonnees> platBHG;

    /**
     * Moitie inferieure gauche du carre bas gauche
     */
    ArrayList<Coordonnees> platBBG;

    /**
     * Moitie superieure gauche du carre bas droit
     */
    ArrayList<Coordonnees> platBHD;

    /**
     * Moitie inferieure droit du carre bas droit
     */
    ArrayList<Coordonnees> platBBD;

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
     * Rempli les structures de separation du terrain.
     */
    private void fillStructplat() {
        fillplathhg();
        fillplathbg();
        fillplathhd();
        fillplathbd();
        fillplatbhg();
        fillplatbbg();
        fillplatbhd();
        fillplatbbd();
    }

    /**
     * Rempli la structure Haut Haut Gauche.
     */
    private void fillplathhg() {     /* Zone 1 : moitié superieure gauche du carre haut gauche */

        platHHG.add(new Coordonnees(0, 0));
        platHHG.add(new Coordonnees(0, 1));
        platHHG.add(new Coordonnees(1, 0));
        platHHG.add(new Coordonnees(1, 1));
        platHHG.add(new Coordonnees(2, 0));
    }

    /**
     * Rempli la structure Haut Bas Gauche.
     */
    private void fillplathbg() {    /* Zone 2 : moitié inferieure droite du carre haut gauche */

        platHBG.add(new Coordonnees(0, 2));
        platHBG.add(new Coordonnees(0, 3));
        platHBG.add(new Coordonnees(1, 2));
        platHBG.add(new Coordonnees(1, 3));
        platHBG.add(new Coordonnees(2, 1));
        platHBG.add(new Coordonnees(2, 2));
        platHBG.add(new Coordonnees(3, 0));
        platHBG.add(new Coordonnees(3, 1));
        platHBG.add(new Coordonnees(3, 2));
        platHBG.add(new Coordonnees(3, 3));
    }

    /**
     * Rempli la structure Haut Haut Droite.
     */
    private void fillplathhd() {    /* Zone 3 : moitié superieure droite du carre haut droit */

        platHHD.add(new Coordonnees(0, 5));
        platHHD.add(new Coordonnees(0, 6));
        platHHD.add(new Coordonnees(1, 6));
        platHHD.add(new Coordonnees(1, 7));
        platHHD.add(new Coordonnees(2, 6));
    }

    /**
     * Rempli la structure Haut Bas Droit.
     */
    private void fillplathbd() {    /* Zone 4 : moitié inferieure gauche du carre haut droit */

        platHBD.add(new Coordonnees(0, 4));
        platHHD.add(new Coordonnees(1, 4));
        platHHD.add(new Coordonnees(1, 5));
        platHHD.add(new Coordonnees(2, 3));
        platHHD.add(new Coordonnees(2, 4));
        platHHD.add(new Coordonnees(2, 5));
        platHHD.add(new Coordonnees(3, 4));
        platHHD.add(new Coordonnees(3, 5));
        platHHD.add(new Coordonnees(3, 6));
        platHHD.add(new Coordonnees(3, 7));

    }

    /**
     * Rempli la structure Bas Haut Gauche.
     */
    private void fillplatbhg() {    /* Zone 5 : moitié superieure droite du carre bas gauche */

        platBHG.add(new Coordonnees(4, 0));
        platHBD.add(new Coordonnees(4, 1));
        platHBD.add(new Coordonnees(4, 2));
        platHBD.add(new Coordonnees(5, 1));
        platHBD.add(new Coordonnees(5, 2));
        platHBD.add(new Coordonnees(5, 3));
        platHBD.add(new Coordonnees(6, 1));
        platHBD.add(new Coordonnees(6, 2));
        platHBD.add(new Coordonnees(6, 3));
        platHBD.add(new Coordonnees(7, 2));
        platHBD.add(new Coordonnees(7, 3));

    }

    /**
     * Rempli la structure Bas Bas Gauche.
     */
    private void fillplatbbg() {    /* Zone 6 : moitié inferieure gauche du carre bas gauche */

        platBBG.add(new Coordonnees(5, 0));
        platBBG.add(new Coordonnees(6, 0));
        platBBG.add(new Coordonnees(7, 0));
        platBBG.add(new Coordonnees(7, 1));

    }

    /**
     * Rempli la structure Bas Haut Droite.
     */
    private void fillplatbhd() {    /* Zone 7 : moitié superieure gauche du carre bas droit */

        platBHD.add(new Coordonnees(4, 3));
        platBHD.add(new Coordonnees(4, 4));
        platBHD.add(new Coordonnees(4, 5));
        platBHD.add(new Coordonnees(4, 6));
        platBHD.add(new Coordonnees(5, 4));
        platBHD.add(new Coordonnees(5, 5));
        platBHD.add(new Coordonnees(5, 6));
        platBHD.add(new Coordonnees(6, 4));
        platBHD.add(new Coordonnees(6, 5));
        platBHD.add(new Coordonnees(7, 4));
        platBHD.add(new Coordonnees(7, 5));

    }

    /**
     * Rempli la structure Bas Bas Droite.
     */
    private void fillplatbbd() {     /* Zone 8 : moitié inferieure droit du carre bas droit */

        platBBD.add(new Coordonnees(5, 7));
        platBBD.add(new Coordonnees(6, 6));
        platBBD.add(new Coordonnees(7, 6));
        platBBD.add(new Coordonnees(7, 7));

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
     * ****************************************************************************************************
     */
    /* Renvoie la coordonnees de la case choisie par l'IA Facile pour le placement du pingouin
     * Case choisie aleatoirement 
     */
    /**
     * Algorithme de placement pour l'IA facile
     *
     * @param p La partie.
     *
     * @return renvoie les coordonnees de la case choisie.
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
     * @param c La coordonnee de depart de l'algo.
     *
     * @param poiss Le nombre de poisson que l'on veut sur la case a trouver .
     *
     * @return Les coordonnees de la tuile 3 la plus proche de la coordonnee c .
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

        for (int i = 0; i < p.joueurs[notreNumeroDeJoueur(p)].nbPingouin; i++) {
            if (p.joueurs[notreNumeroDeJoueur(p)].myPingouins[i].actif) {
                CoupleGenerique<Coordonnees, Integer> coordint = parcoursGraphe(p.joueurs[notreNumeroDeJoueur(p)].myPingouins[i].position, p, nbcoupsrecherches, nbcoupsrecherches, 0, new Coordonnees(0, 0));
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

        return new CoupleGenerique<Coordonnees, Coordonnees>(p.joueurs[notreNumeroDeJoueur(p)].myPingouins[indx].position, coordmax);
    }

    /**
     * ****************************************************************************************************
     */
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

    public int nbTotalPingouinsActifsJoueur(Partie p, int numjoueur) {
        int res = 0;

        for (int j = 0; j < p.joueurs[numjoueur].nbPingouin; j++) {
            if (p.joueurs[numjoueur].myPingouins[j].actif) {
                res = res + 1;
            }
        }

        return res;
    }

    public int nbTotalPingouins(Partie p) {
        int res = 0;
        for (int i = 0; i < p.nbJoueurs; i++) {
            res = res + p.joueurs[i].nbPingouin;
        }
        return res;
    }

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

                    if (nbDeTroisAccessibles == 2 && cotoieUnTrois(c, p)) {
                        caroufDe3.add(new CoupleGenerique(c, nbDeTroisAccessibles + 1)); // 3
                    } else if (nbDeTroisAccessibles == 2) {
                        caroufDe3.add(new CoupleGenerique(c, nbDeTroisAccessibles)); // 2
                    } else if (nbDeTroisAccessibles >= 3) {
                        caroufDe3.add(new CoupleGenerique(c, nbDeTroisAccessibles + 1)); // nbTrois + 1
                    }
                }
            }
        }

        return caroufDe3;
    }

    public boolean cotoieUnTrois(Coordonnees c, Partie p) {
        ArrayList<ArrayList<Coordonnees>> coupPossible = p.b.deplacementPossible(c);

        boolean b = false;
        for (int n = 0; n < 6; n++) {
            b = b || (!coupPossible.get(n).isEmpty() && (p.b.getTuile(coupPossible.get(n).get(0)).nbPoissons == 3));
        }
        return b;
    }

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

    public ArrayList<CoupleGenerique<Integer, Integer>> aBloqueUnPingouin(Partie p1, Partie p2) {

        ArrayList<CoupleGenerique<Integer, Integer>> retour = new ArrayList<>();
        int res = 0;
        int pingu1;
        int pingu2;
        for (int i = 0; i < p1.nbJoueurs; i++) {
            for (int j = 0; j < p1.joueurs[i].nbPingouin; j++) {
                if (p1.joueurs[i].myPingouins[j].actif) {
                    pingu1 = estSeul(p1.joueurs[i].myPingouins[j].position, p1);
                    pingu2 = estSeul(p2.joueurs[i].myPingouins[j].position, p2);
                    if (pingu1 == 0 && pingu2 != 0) { // On vient de bloquer un pingouin
                        retour.add(new CoupleGenerique(i, pingu2));
                    }

                }
            }
        }
        return retour;

    }

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

    // A appeler avec les coordonnees du pingouin a verifier : Coordonnees c = p.joueurs[num].myPingouins[indicePingu].position;
    public int estSeul(Coordonnees c, Partie p) {
        if (p.b.nbTuilesLibres() <= 50) {
            int somme = 0;
            LinkedList<Coordonnees> file = new LinkedList<>();
            HashMap<Coordonnees, Integer> hash = new HashMap<>();
            Tuile tuileDep = p.b.getTuile(c);
            file.add(c);
            hash.put(c, 0);

            while (!file.isEmpty()) {
                c = file.pop();
                tuileDep = p.b.getTuile(c);

                if (tuileDep.aUnPingouin && (p.appartientPingouin(c) != notreNumeroDeJoueur(p))) {
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

    /**
     * Creation de l'arbre minimax des coups possibles pour tous les pingouins
     *
     * @param c Les coordonnees c de la racine de l'arbre.
     *
     * @param partie La partie.
     *
     * @param num Le numero du joueur actif.
     *
     * @param nbcoupsmax La profondeur de l'arbre = le nombre de coups restant
     * que l'on veut evalue
     *
     * @param hash Tableau de hachage permettant de reduire le nombre de noeud
     * cree.
     *
     * @param somme Somme servant a la ponderation des feuilles.
     *
     * @param first Vrai si on est dans la premiere execution recursive.
     *
     *
     * @return Renvoie un ArbreGraphe.
     */
    /*
     public CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> alphaBeta(Partie p, Coordonnees c, int num, int nbCoupsmax, int nbCoups, int somme, int first, CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> a, CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> b, int indicePingu, Coordonnees coord1stchoice, Coordonnees coordPingu) {

     if ((nbCoups == nbCoupsmax - 1)) {
     coord1stchoice = c;
     }

     if (nbCoups <= 0 || isFeuillePingu(p, p.joueurs[num])) {
     Tuile tuile = p.b.getTuile(c);
     CoupleGenerique<Coordonnees, Coordonnees> cg = new CoupleGenerique(coordPingu, coord1stchoice);
     return new CoupleGenerique(cg, somme + tuile.nbPoissons);

     }

     CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> alpha = new CoupleGenerique(a);
     CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> beta = new CoupleGenerique(b);
     CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> val = new CoupleGenerique(new CoupleGenerique(coordPingu, coord1stchoice), -100);
     CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> tmp = new CoupleGenerique(new CoupleGenerique(coordPingu, coord1stchoice), -100);

     if (num == notreNumeroDeJoueur(p)) { // c'est a nous - on maximise 
     val = new CoupleGenerique(new CoupleGenerique(coordPingu, coord1stchoice), -100);

     for (int i = 0; i < p.joueurs[num].nbPingouin; i++) {

     if (p.joueurs[num].myPingouins[i].actif) {

     if (((nbCoups == nbCoupsmax) && !estSeul(p.joueurs[num].myPingouins[i].position, p)) || (nbCoups != nbCoupsmax)) {

     if (nbCoups == nbCoupsmax) {
     coordPingu = p.joueurs[notreNumeroDeJoueur(p)].myPingouins[i].position;
     }

     c = p.joueurs[num].myPingouins[i].position;
     ArrayList<ArrayList<Coordonnees>> coupPossible = p.b.deplacementPossible(c);

     for (int n = 0; n < 6; n++) {

     for (int m = 0; m < coupPossible.get(n).size(); m++) {
                       
     Partie p2 = (Partie) p.clone();
     p2.deplacement(c, coupPossible.get(n).get(m));
     p2.verifierPingouinActif();
     tmp = alphaBeta(p2, coupPossible.get(n).get(m), joueurSuivant(num, p2), nbCoupsmax, nbCoups - 1, somme + p.b.getTuile(c).nbPoissons, 0, alpha, beta, i, coord1stchoice, coordPingu);
     if (val.e2 <= tmp.e2) { // c'est a nous - on maximise
     val = tmp;
     }
     if (val.e2 >= beta.e2) {
     return val;
     }
     if(alpha.e2 <= val.e2){
     alpha = val;
     }
     }
     }
     }
     }
     }
     return val;
     } else {
     val = new CoupleGenerique(new CoupleGenerique(coordPingu, coord1stchoice), 1000);

     for (int i = 0; i < p.joueurs[num].nbPingouin; i++) {

     if (p.joueurs[num].myPingouins[i].actif) {

     c = p.joueurs[num].myPingouins[i].position;
     ArrayList<ArrayList<Coordonnees>> coupPossible = p.b.deplacementPossible(c);

     for (int n = 0; n < 6; n++) {

     for (int m = 0; m < coupPossible.get(n).size(); m++) {

     Partie p2 = (Partie) p.clone();
     p2.deplacement(c, coupPossible.get(n).get(m));
     p2.verifierPingouinActif();

     tmp = alphaBeta(p2, coupPossible.get(n).get(m), joueurSuivant(num, p2), nbCoupsmax, nbCoups - 1, somme, 0, alpha, beta, i, coord1stchoice, coordPingu);
     if (val.e2 >= tmp.e2) { // c'est pas a nous - on minimise
     val = tmp;
     }
     if (alpha.e2 >= val.e2) {
     return val;
     }
     if(beta.e2 >= val.e2){
     beta = val;
     }
     }
     }
     }
     }
     return val;
     }
     }
     */
    public CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> alphaBeta(Partie p, Coordonnees c, int num, int nbCoupsmax, int nbCoups, int somme, boolean first, CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> a, CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> b, int indicePingu, Coordonnees coord1stchoice, Coordonnees coordPingu, boolean pingPerdu) {

        if ((nbCoups == nbCoupsmax - 1)) {
            coord1stchoice = c;
        }

        if (nbCoups <= 0 || isFeuillePingu(p, p.joueurs[num])) {
            Tuile tuile = p.b.getTuile(c);
            CoupleGenerique<Coordonnees, Coordonnees> cg = new CoupleGenerique(coordPingu, coord1stchoice);
            if (pingPerdu) {
                return new CoupleGenerique(cg, 0);
            } else {
                // System.out.println("là bas : " + coordPingu + " - " + coord1stchoice + " avec un score : " + (somme + tuile.nbPoissons));
                return new CoupleGenerique(cg, somme + tuile.nbPoissons);
            }

        }

        ArrayList<CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer>> meilleurs_coups = null;
        CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> alpha = new CoupleGenerique(a);
        CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> beta = new CoupleGenerique(b);
        CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> tmp = new CoupleGenerique(new CoupleGenerique(coordPingu, coord1stchoice), -1000);

        if (num == notreNumeroDeJoueur(p)) { // c'est a nous - on maximise 
            tmp = new CoupleGenerique(new CoupleGenerique(coordPingu, coord1stchoice), -1000);

            if (first) {
                meilleurs_coups = new ArrayList<>();
            }

            for (int i = 0; i < p.joueurs[num].nbPingouin; i++) {

                if (p.joueurs[num].myPingouins[i].actif) {

                    int somme_total = somme;

                    if (((first) && (estSeul(p.joueurs[num].myPingouins[i].position, p) == 0)) || (!first)) {

                        if (first) {
                            System.out.println("pingouin num " + i + " position : " + p.joueurs[num].myPingouins[i].position + " poissons sur la case : " + p.b.getTuile(p.joueurs[num].myPingouins[i].position).nbPoissons);
                            coordPingu = p.joueurs[notreNumeroDeJoueur(p)].myPingouins[i].position;
                            somme_total = somme_total - p.b.getTuile(coordPingu).nbPoissons;

                        }

                        c = p.joueurs[num].myPingouins[i].position;
                        ArrayList<ArrayList<Coordonnees>> coupPossible = p.b.deplacementPossible(c);

                        for (int n = 0; n < 6; n++) {

                            for (int m = 0; m < coupPossible.get(n).size(); m++) {

                                Partie p2 = (Partie) p.clone();
                                p2.deplacement(c, coupPossible.get(n).get(m));
                                p2.verifierPingouinActif();

                                ArrayList<CoupleGenerique<Integer, Integer>> pingouinsDevenusInactifs = aBloqueUnPingouin(p, p2);
                                if (pingouinsDevenusInactifs.size() != 0) {
                                    for (int h = 0; h < pingouinsDevenusInactifs.size(); h++) {
                                        if (pingouinsDevenusInactifs.get(h).e1 == num) {
                                            if (pingouinsDevenusInactifs.get(h).e2 > nbTuileRestantes(p)) {
                                                somme_total = somme_total + pingouinsDevenusInactifs.get(h).e2;
                                            } else {
                                                somme_total = somme_total - (pingouinsDevenusInactifs.get(h).e2 * 2); // pif
                                            }
                                        } else {
                                            if (pingouinsDevenusInactifs.get(h).e2 > nbTuileRestantes(p)) {
                                                somme_total = somme_total - pingouinsDevenusInactifs.get(h).e2;
                                            } else {
                                                somme_total = somme_total + (pingouinsDevenusInactifs.get(h).e2 * 2);
                                            }

                                        }
                                    }
                                }

                                tmp = alphaBeta(p2, coupPossible.get(n).get(m), joueurSuivant(num, p2), nbCoupsmax, nbCoups - 1, somme_total + p.b.getTuile(c).nbPoissons, false, alpha, beta, i, coord1stchoice, coordPingu, pingPerdu);
                                if (alpha.e2 <= tmp.e2) { // c'est a nous - on maximise
                                    if (alpha.e2 == tmp.e2) {
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
                System.out.println("siezeze : " + meilleurs_coupsProche.size());

                int choix = r.nextInt(meilleurs_coupsProche.size());
                alpha = meilleurs_coupsProche.get(choix);
            }
            return alpha;
        } else {
            tmp = new CoupleGenerique(new CoupleGenerique(coordPingu, coord1stchoice), 1000);

            for (int i = 0; i < p.joueurs[num].nbPingouin; i++) {

                if (p.joueurs[num].myPingouins[i].actif) {

                    c = p.joueurs[num].myPingouins[i].position;
                    ArrayList<ArrayList<Coordonnees>> coupPossible = p.b.deplacementPossible(c);

                    for (int n = 0; n < 6; n++) {

                        for (int m = 0; m < coupPossible.get(n).size(); m++) {

                            Partie p2 = (Partie) p.clone();

                            p2.deplacement(c, coupPossible.get(n).get(m));
                            p2.verifierPingouinActif();

                            tmp = alphaBeta(p2, coupPossible.get(n).get(m), joueurSuivant(num, p2), nbCoupsmax, nbCoups - 1, somme, false, alpha, beta, i, coord1stchoice, coordPingu, pingPerdu);
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
     * Algorithme Alpha-Beta
     *
     * @param p La partie.
     *
     * @param abr Noeud courant dans l'arbre.
     *
     * @param nbCoupsmax La profondeur de l'arbre = le nombre de coups que l'on
     * veut evalue.
     *
     * @param nbCoups Le nombre de coups qui reste a evaluer.
     *
     * @param coord1stchoice Sauvegarde de la coordonnee du premier deplacement
     * d'un chemin dans l'arbre.
     *
     * @param coordPingu Sauvegarde de la coordonnee du pingouin que l'on
     * deplace en premier dans un chemin dans l'arbre.
     *
     * @param a Approximation de la borne inferieure de la valeur du noeud.
     *
     * @param b Approximation de la borne superieure de la valeur du noeud.
     *
     * @param indicePingu Indice du pingouin courant.
     *
     * @return Renvoie un ArbreGraphe.
     */
    /**
     * Algorithme de choix d'une case a jouer pour l'IA difficile
     *
     * @param p La partie.
     *
     * @return Renvoie un couple de coordonnee : (Position du pingouin choisi
     * avant le deplacement, Position du pingouin choisi apres le deplacement) .
     */
    public CoupleGenerique<Coordonnees, Coordonnees> algoDifficile(Partie p) {
        int nbCoupsmax = 1;
        Coordonnees c0 = new Coordonnees(0, 0);
        int num = notreNumeroDeJoueur(p);
        boolean tousTousSeul = false;
        if (p.b.nbTuilesLibres() <= 50) {
            System.out.println("mAINTENANT 3");
            nbCoupsmax = 3;
        }
        if (p.b.nbTuilesLibres() <= 40) {
            System.out.println("mAINTENANT 5");
            nbCoupsmax = 5;
            tousTousSeul = true;
            for (int i = 0; i < p.joueurs[num].nbPingouin; i++) {
                if (p.joueurs[num].myPingouins[i].actif) {
                    int tmp = estSeul(p.joueurs[num].myPingouins[i].position, p);
                    System.out.println("le pignouin position " + p.joueurs[num].myPingouins[i].position + " est-il tout seul ?? : " + tmp);
                    tousTousSeul = tousTousSeul && (tmp != 0);
                }
            }
        }
        if (p.b.nbTuilesLibres() <= 30) {
            nbCoupsmax = 7;
            System.out.println("mAINTENANT 7");
        }
        if (p.b.nbTuilesLibres() <= 20) {
            nbCoupsmax = 9;
            System.out.println("mAINTENANT 9");
        }

        if (tousTousSeul) {
            System.out.println("av algo moyen");
            return algoMoyen(p);
        } else {
            CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> alpha = new CoupleGenerique(new CoupleGenerique(c0, c0), -1000);
            CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> beta = new CoupleGenerique(new CoupleGenerique(c0, c0), 1000);
            ArrayList<HashMap<Banquise, ArbreGraphe>> listHash = new ArrayList<>();
            for (int i = 0; i < p.nbJoueurs; i++) {
                listHash.add(new HashMap<Banquise, ArbreGraphe>());
            }

            CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> coucou = alphaBeta(p, c0, num, nbCoupsmax, nbCoupsmax, 0, true, alpha, beta, 0, c0, c0, false);
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
