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

    public int nbTotalPingouins(Partie p) {
        int res = 0;
        for (int i = 0; i < p.nbJoueurs; i++) {
            res = res + p.joueurs[i].nbPingouin;
        }
        return res;
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

    // A appeler avec les coordonnees du pingouin a verifier : Coordonnees c = p.joueurs[num].myPingouins[indicePingu].position;
    public boolean estSeul(Coordonnees c, Partie p) {

        Boolean retour = true;
        Coordonnees a = new Coordonnees(c.x, c.y);

        ArrayList<ArrayList<Coordonnees>> coupPossible = p.b.deplacementPossible(c);

        for (int n = 0; n < 6; n++) {
            for (int m = 0; m < coupPossible.get(n).size(); m++) {
                a = coupPossible.get(n).get(m);
                if ((p.b.getTuile(a).aUnPingouin) && (p.appartientPingouin(a) != notreNumeroDeJoueur(p))) {
                    return false;
                } else {
                    retour = estSeul(a, p);
                }
            }
        }
        return retour;

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
    public CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> CreationAbreMinimax(Coordonnees c, Partie partie, int num, int nbcoupsmax, ArrayList<HashMap<Banquise, ArbreGraphe>> hash, int somme, int first) {

        if (nbcoupsmax <= 0 || isFeuillePingu(partie, partie.joueurs[num])) {
            ArbreGraphe abr = new ArbreGraphe(0);
            abr.estFeuille = true;
            abr.c = c;
            Tuile tuile = partie.b.getTuile(c);
            abr.poids = somme + tuile.nbPoissons;
            return abr;
        } else {
            Partie p = (Partie) partie.clone();

            ArbreGraphe abr = new ArbreGraphe(p.joueurs[num].nbPingouin);

            if (first == 0) {
                abr.c = c;
                Tuile tuile = p.b.getTuile(c);
                abr.poids = tuile.nbPoissons;
            } else {
                abr.poids = 0;
            }
            for (int i = 0; i < p.joueurs[num].nbPingouin; i++) {
                if (p.joueurs[num].myPingouins[i].actif) {
                    c = p.joueurs[num].myPingouins[i].position;
                    Coordonnees a = new Coordonnees(c.x, c.y);

                    for (int n = 0; n < 6; n++) {

                        while ((p.b.getTuile(p.b.getVoisin(n, a)) != null) && (!p.b.getTuile(p.b.getVoisin(n, a)).aUnPingouin) && (p.b.getTuile(p.b.getVoisin(n, a)).nbPoissons != 0)) { // !!!!! mettre le clone dans le while !!!!!

                            Partie p2 = (Partie) p.clone();
                            p2.deplacement(c, p2.b.getVoisin(n, a));
                            p2.verifierPingouinActif();

                            if (hash.get(num).containsKey(p2.b)) {

                                abr.noeudBG[i].add(hash.get(num).get(p2.b));
                            } else {
                                ArbreGraphe abg = CreationAbreMinimax(p2.b.getVoisin(n, a), p2, joueurSuivant(num, p), nbcoupsmax - 1, hash, somme + abr.poids, 0);

                                hash.get(num).put(p2.b, abg);
                                abr.noeudBG[i].add(abg);

                            }
                            a = p.b.getVoisin(n, a);
                        }
                    }

                }
            }
        }
        return abr;
    
    }*/

/**
 * Algorithme Alpha-Beta
 *
 * @param p La partie.
 *
 * @param abr Noeud courant dans l'arbre.
 *
 * @param nbCoupsmax La profondeur de l'arbre = le nombre de coups que l'on veut
 * evalue.
 *
 * @param nbCoups Le nombre de coups qui reste a evaluer.
 *
 * @param coord1stchoice Sauvegarde de la coordonnee du premier deplacement d'un
 * chemin dans l'arbre.
 *
 * @param coordPingu Sauvegarde de la coordonnee du pingouin que l'on deplace en
 * premier dans un chemin dans l'arbre.
 *
 * @param a Approximation de la borne inferieure de la valeur du noeud.
 *
 * @param b Approximation de la borne superieure de la valeur du noeud.
 *
 * @param indicePingu Indice du pingouin courant.
 *
 * @return Renvoie un ArbreGraphe.
 */
public CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> alphaBeta(Partie p, ArbreGraphe abr, int num, int nbCoupsmax, int nbCoups, Coordonnees coord1stchoice, Coordonnees coordPingu, CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> a, CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> b, int indicePingu) { //0 = c'est a nous - On maximise || 1 = autre joueur -on minimise

        if ((nbCoups == nbCoupsmax - 1)) {
            coord1stchoice = abr.c;
            coordPingu = p.joueurs[notreNumeroDeJoueur(p)].myPingouins[indicePingu].position;
        }
        if (nbCoups == 0 || abr.estFeuille) {
            CoupleGenerique<Coordonnees, Coordonnees> c = new CoupleGenerique(coordPingu, coord1stchoice);
            return new CoupleGenerique(c, abr.poids);
        }

        CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> alpha = new CoupleGenerique(a);
        CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> beta = new CoupleGenerique(b);

        CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> tmp = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), coord1stchoice), -1);
        if (num != notreNumeroDeJoueur(p)) {
            tmp = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), coord1stchoice), 1000);
        }

        if (num == notreNumeroDeJoueur(p)) { // c'est a nous - on maximise 

            for (int i = 0; i < p.joueurs[num].nbPingouin; i++) {
                if (p.joueurs[num].myPingouins[i].actif) {
                    if ((abr.noeudBD[i] != null) && (!abr.noeudBD[i].isEmpty())) {
                        for (int j = 0; j < abr.noeudBD[i].size(); j++) {
                            tmp = alphaBeta(p, abr.noeudBD[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
                            if (alpha.e2 <= tmp.e2) { // c'est a nous - on maximise
                                alpha = tmp;
                            }
                            if (alpha.e2 >= beta.e2) {
                                return beta;
                            }
                        }
                    }
                    if ((abr.noeudMD[i] != null) && (!abr.noeudMD[i].isEmpty())) {
                        for (int j = 0; j < abr.noeudMD[i].size(); j++) {
                            tmp = alphaBeta(p, abr.noeudMD[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
                            if (alpha.e2 <= tmp.e2) { // c'est a nous - on maximise
                                alpha = tmp;
                            }
                            if (alpha.e2 >= beta.e2) {
                                return beta;
                            }
                        }
                    }
                    if ((abr.noeudHD[i] != null) && (!abr.noeudHD[i].isEmpty())) {
                        for (int j = 0; j < abr.noeudHD[i].size(); j++) {
                            tmp = alphaBeta(p, abr.noeudHD[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
                            if (alpha.e2 <= tmp.e2) { // c'est a nous - on maximise
                                alpha = tmp;
                            }
                            if (alpha.e2 >= beta.e2) {
                                return beta;
                            }
                        }
                    }
                    if ((abr.noeudHG[i] != null) && (!abr.noeudHG[i].isEmpty())) {
                        for (int j = 0; j < abr.noeudHG[i].size(); j++) {
                            tmp = alphaBeta(p, abr.noeudHG[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
                            if (alpha.e2 <= tmp.e2) { // c'est a nous - on maximise
                                alpha = tmp;
                            }
                            if (alpha.e2 >= beta.e2) {
                                return beta;
                            }
                        }
                    }
                    if ((abr.noeudMG[i] != null) && (!abr.noeudMG[i].isEmpty())) {
                        for (int j = 0; j < abr.noeudMG[i].size(); j++) {
                            tmp = alphaBeta(p, abr.noeudMG[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
                            if (alpha.e2 <= tmp.e2) { // c'est a nous - on maximise
                                alpha = tmp;
                            }
                            if (alpha.e2 >= beta.e2) {
                                return beta;
                            }
                        }
                    }
                    if ((abr.noeudBG[i] != null) && (!abr.noeudBG[i].isEmpty())) {
                        for (int j = 0; j < abr.noeudBG[i].size(); j++) {
                            tmp = alphaBeta(p, abr.noeudBG[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
                            if (alpha.e2 <= tmp.e2) { // c'est a nous - on maximise
                                alpha = tmp;
                            }
                            if (alpha.e2 >= beta.e2) {
                                return beta;
                            }
                        }
                    }
                }

            }
            return alpha;
        } else {
            for (int i = 0; i < p.joueurs[num].nbPingouin; i++) {
                if (p.joueurs[num].myPingouins[i].actif) {
                    if ((abr.noeudBD[i] != null) && (!abr.noeudBD[i].isEmpty())) {
                        for (int j = 0; j < abr.noeudBD[i].size(); j++) {
                            tmp = alphaBeta(p, abr.noeudBD[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
                            if (beta.e2 >= tmp.e2) { // c'est a nous - on maximise
                                beta = tmp;
                            }
                            if (alpha.e2 >= beta.e2) {
                                return alpha;
                            }
                        }
                    }
                    if ((abr.noeudMD[i] != null) && (!abr.noeudMD[i].isEmpty())) {
                        for (int j = 0; j < abr.noeudMD[i].size(); j++) {
                            tmp = alphaBeta(p, abr.noeudMD[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
                            if (beta.e2 >= tmp.e2) { // c'est a nous - on maximise
                                beta = tmp;
                            }
                            if (alpha.e2 >= beta.e2) {
                                return alpha;
                            }
                        }
                    }
                    if ((abr.noeudHD[i] != null) && (!abr.noeudHD[i].isEmpty())) {
                        for (int j = 0; j < abr.noeudHD[i].size(); j++) {
                            tmp = alphaBeta(p, abr.noeudHD[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
                            if (beta.e2 >= tmp.e2) { // c'est a nous - on maximise
                                beta = tmp;
                            }
                            if (alpha.e2 >= beta.e2) {
                                return alpha;
                            }
                        }
                    }
                    if ((abr.noeudHG[i] != null) && (!abr.noeudHG[i].isEmpty())) {
                        for (int j = 0; j < abr.noeudHG[i].size(); j++) {
                            tmp = alphaBeta(p, abr.noeudHG[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
                            if (beta.e2 >= tmp.e2) { // c'est a nous - on maximise
                                beta = tmp;
                            }
                            if (alpha.e2 >= beta.e2) {
                                return alpha;
                            }
                        }
                    }
                    if ((abr.noeudMG[i] != null) && (!abr.noeudMG[i].isEmpty())) {
                        for (int j = 0; j < abr.noeudMG[i].size(); j++) {
                            tmp = alphaBeta(p, abr.noeudMG[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
                            if (beta.e2 >= tmp.e2) { // c'est a nous - on maximise
                                beta = tmp;
                            }
                            if (alpha.e2 >= beta.e2) {
                                return alpha;
                            }
                        }
                    }
                    if ((abr.noeudBG[i] != null) && (!abr.noeudBG[i].isEmpty())) {
                        for (int j = 0; j < abr.noeudBG[i].size(); j++) {
                            tmp = alphaBeta(p, abr.noeudBG[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
                            if (beta.e2 >= tmp.e2) { // c'est a nous - on maximise
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

        if (p.b.nbTuilesLibres() <= 25) {
            System.out.println("mAINTENANT 5");
            nbCoupsmax = 5;
        }

        int num = notreNumeroDeJoueur(p);
        CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> alpha = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), new Coordonnees(0, 0)), -1);
        CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> beta = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), new Coordonnees(0, 0)), 10000);
        ArrayList<HashMap<Banquise, ArbreGraphe>> listHash = new ArrayList<>();
        for (int i = 0; i < p.nbJoueurs; i++) {
            listHash.add(new HashMap<Banquise, ArbreGraphe>());
        }
        /*ArbreGraphe abr = CreationAbreMinimax(new Coordonnees(0, 0), p, num, nbCoupsmax, listHash, 0, 1);
         CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> coucou = alphaBeta(p, abr, num, nbCoupsmax, nbCoupsmax, new Coordonnees(0, 0), new Coordonnees(0, 0), alpha, beta, 0);
         CoupleGenerique<Coordonnees, Coordonnees> retour = new CoupleGenerique(coucou.e1.e1, coucou.e1.e2);
         System.out.println("retour " + retour.e1 + retour.e2);*/
        return null;
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
