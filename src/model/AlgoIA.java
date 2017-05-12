package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlgoIA {

    IA myIA = null;
    ArrayList<Coordonnees> platHHG; // Haut Haut Gauche
    ArrayList<Coordonnees> platHBG; // Haut Bas Gauche
    ArrayList<Coordonnees> platHHD; // Haut Haut Droit
    ArrayList<Coordonnees> platHBD; // Haut Bas Droit
    ArrayList<Coordonnees> platBHG; // Bas Haut Gauche
    ArrayList<Coordonnees> platBBG; // Bas Bas Gauche
    ArrayList<Coordonnees> platBHD; // Bas Haut Droit
    ArrayList<Coordonnees> platBBD; // Bas Bas Droit

    public AlgoIA(IA ia) {
        myIA = ia;
    }

    /*  Représentation de la direction selon un int : 
     *    - 0 = Haut droit
     *    - 1 = Milieu droit
     *    - 2 = Bas droit
     *    - 3 = Bas gauche
     *    - 4 = Milieu gauche
     *    - 5 = Haut gauche
     */
    /* 
     * Decoupage du terrain en 8 zones 
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

    private void fillplathhg() {     /* Zone 1 : moitié superieure gauche du carre haut gauche */

        platHHG.add(new Coordonnees(0, 0));
        platHHG.add(new Coordonnees(0, 1));
        platHHG.add(new Coordonnees(1, 0));
        platHHG.add(new Coordonnees(1, 1));
        platHHG.add(new Coordonnees(2, 0));
    }

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

    private void fillplathhd() {    /* Zone 3 : moitié superieure droite du carre haut droit */

        platHHD.add(new Coordonnees(0, 5));
        platHHD.add(new Coordonnees(0, 6));
        platHHD.add(new Coordonnees(1, 6));
        platHHD.add(new Coordonnees(1, 7));
        platHHD.add(new Coordonnees(2, 6));
    }

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

    private void fillplatbbg() {    /* Zone 6 : moitié inferieure gauche du carre bas gauche */

        platBBG.add(new Coordonnees(5, 0));
        platBBG.add(new Coordonnees(6, 0));
        platBBG.add(new Coordonnees(7, 0));
        platBBG.add(new Coordonnees(7, 1));

    }

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

    private void fillplatbbd() {     /* Zone 8 : moitié inferieure droit du carre bas droit */

        platBBD.add(new Coordonnees(5, 7));
        platBBD.add(new Coordonnees(6, 6));
        platBBD.add(new Coordonnees(7, 6));
        platBBD.add(new Coordonnees(7, 7));

    }

    private Coordonnees containsThree(Partie p, ArrayList<Coordonnees> list) { // renvoie une case à 3, ou une valeur spéciale si non trouvée
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
    public Coordonnees placementFacile(Partie p) {
        Random r = new Random();

        int i = 0;
        int j = 0;
        do {
            i = r.nextInt(8);

            if (i % 2 == 0) {
                j = r.nextInt(7);       // Choix aleatoire de la tuile 
            } else {
                j = r.nextInt(8);
            }
        } while (!((p.b.getTuile(new Coordonnees(i, j)) != null) && (!p.b.getTuile(new Coordonnees(i, j)).aUnPingouin)));

        return new Coordonnees(i, j);
    }

    /* Renvoie un couple de coordonnees :
     * La premiere represente la position du pingouin choisi avant le deplacement
     * La seconde represente la position du pingouin choisi apres le deplacement
     * Case choisie aleatoirement par celles accessibles
     */
    public CoupleGenerique<Coordonnees, Coordonnees> algoFacile(Partie p) {

        // !!!!!!!!
        // Ne marche pas quand on choisi un pingouin qui na pas de deplacement possible !!2
        Random r = new Random();
        CoupleGenerique<Coordonnees, Coordonnees> cc;
        /* Choix aleatoire du Pingouin */

        int notreNum = notreNumeroDeJoueur(p);
        int notreNbPingouin = p.joueurs[notreNum].nbPingouin;//p.nbPingouinActif(p.joueurs[notreNum]);

        int val = r.nextInt(notreNbPingouin);  // myIA.nbPingouin pas mis a jour !
        while (!p.joueurs[notreNum].myPingouins[val].actif) {
            val = r.nextInt(notreNbPingouin);
        }

        int i = myIA.myPingouins[val].position.x;
        int j = myIA.myPingouins[val].position.y;
        Coordonnees dep = new Coordonnees(i, j);
        Coordonnees courant;
        int nbDeplPossible = 0;
        int dir = 0;
        do {
            nbDeplPossible = 0;
            dir = r.nextInt(6);     // Choix de la direction aleatoire - specification de l'entier en haut de la classe
            if (dir == 0) {         // En haut a droite 
                while ((p.b.getTuile(p.b.getHD(dep)) != null) && (p.b.getTuile(p.b.getHD(dep)).nbPoissons != 0) && (!p.b.getTuile(p.b.getHD(dep)).aUnPingouin)) {
                    courant = p.b.getHD(dep);
                    dep = courant;
                    nbDeplPossible = nbDeplPossible + 1;    // Calcul du nombre de deplacement possible pour cette direction 
                }
            } else if (dir == 1) { // Au milieu a droite
                while ((p.b.getTuile(p.b.getMD(dep)) != null) && (p.b.getTuile(p.b.getMD(dep)).nbPoissons != 0) && (!p.b.getTuile(p.b.getMD(dep)).aUnPingouin)) {
                    courant = p.b.getMD(dep);
                    dep = courant;
                    nbDeplPossible = nbDeplPossible + 1;    // Calcul du nombre de deplacement possible pour cette direction 
                }
            } else if (dir == 2) { // En bas a droite 
                while ((p.b.getTuile(p.b.getBD(dep)) != null) && (p.b.getTuile(p.b.getBD(dep)).nbPoissons != 0) && (!p.b.getTuile(p.b.getBD(dep)).aUnPingouin)) {
                    courant = p.b.getBD(dep);
                    dep = courant;
                    nbDeplPossible = nbDeplPossible + 1;    // Calcul du nombre de deplacement possible pour cette direction 
                }
            } else if (dir == 3) { // En bas a gauche 
                while ((p.b.getTuile(p.b.getBG(dep)) != null) && (p.b.getTuile(p.b.getBG(dep)).nbPoissons != 0) && (!p.b.getTuile(p.b.getBG(dep)).aUnPingouin)) {
                    courant = p.b.getBG(dep);
                    dep = courant;
                    nbDeplPossible = nbDeplPossible + 1;    // Calcul du nombre de deplacement possible pour cette direction 
                }
            } else if (dir == 4) { // Au milieu a gauche
                while ((p.b.getTuile(p.b.getMG(dep)) != null) && (p.b.getTuile(p.b.getMG(dep)).nbPoissons != 0) && (!p.b.getTuile(p.b.getMG(dep)).aUnPingouin)) {
                    courant = p.b.getMG(dep);
                    dep = courant;
                    nbDeplPossible = nbDeplPossible + 1;    // Calcul du nombre de deplacement possible pour cette direction 
                }
            } else {              // En haut a gauche
                while ((p.b.getTuile(p.b.getHG(dep)) != null) && (p.b.getTuile(p.b.getHG(dep)).nbPoissons != 0) && (!p.b.getTuile(p.b.getHG(dep)).aUnPingouin)) {
                    courant = p.b.getHG(dep);
                    dep = courant;
                    nbDeplPossible = nbDeplPossible + 1;    // Calcul du nombre de deplacement possible pour cette direction 
                }
            }
        } while (nbDeplPossible == 0);

        // Tirage au sort du nb de case a se deplacer 
        int nbDepl = r.nextInt(nbDeplPossible) + 1;
        dep = new Coordonnees(i, j);

        if (dir == 0) {     // En haut a droite 
            while (nbDepl != 0) {
                dep = p.b.getHD(dep);
                nbDepl--;
            }
        } else if (dir == 1) { 	// Au milieu a droite
            while (nbDepl != 0) {
                dep = p.b.getMD(dep);
                nbDepl--;
            }
        } else if (dir == 2) { 	// En bas a droite 
            while (nbDepl != 0) {
                dep = p.b.getBD(dep);
                nbDepl--;
            }
        } else if (dir == 3) { 	// En bas a gauche 
            while (nbDepl != 0) {
                dep = p.b.getBG(dep);
                nbDepl--;
            }
        } else if (dir == 4) { 	// Au milieu a gauche
            while (nbDepl != 0) {
                dep = p.b.getMG(dep);
                nbDepl--;
            }
        } else // En haut a gauche
        {
            while (nbDepl != 0) {
                dep = p.b.getHG(dep);
                nbDepl--;
            }
        }

        cc = new CoupleGenerique<Coordonnees, Coordonnees>(new Coordonnees(i, j), dep);

        return cc;
    }

    /**
     * ****************************************************************************************************
     */
    /* Renvoie les coordonnees de la tuile 3 la plus proche de la coordonnee c 
     */
    public Coordonnees meilleur3(Partie p, Coordonnees c) {
        LinkedList<Coordonnees> file = new LinkedList<>();
        Tuile tuileDep = p.b.getTuile(c);
        file.add(c);
        while ((!file.isEmpty()) && (!((tuileDep.nbPoissons == 3) && (!tuileDep.aUnPingouin)))) { // tant que la case n'est pas valide : valide = (nbPoissons=3 && !aUnPingouin)
            c = file.pop();
            tuileDep = p.b.getTuile(c);

            if (p.b.getHD(c) != null) {
                file.add(p.b.getHD(c));
            }
            if (p.b.getMD(c) != null) {
                file.add(p.b.getMD(c));
            }
            if (p.b.getBD(c) != null) {
                file.add(p.b.getBD(c));
            }
            if (p.b.getBG(c) != null) {
                file.add(p.b.getBG(c));
            }
            if (p.b.getMG(c) != null) {
                file.add(p.b.getMG(c));
            }
            if (p.b.getHG(c) != null) {
                file.add(p.b.getHG(c));
            }
        }
        return c;
    }

    /* Fonction de placement moyen
     * Renvoie la coordonee de la tuile contenant 3 poisssons la plus au centre
     */
    public Coordonnees placementMoyen(Partie p) {
        Coordonnees c = new Coordonnees(3, 3);
        return meilleur3(p, c);
    }

    public boolean feuillePointDinterrogation(Coordonnees c, Partie p) {
        return (((p.b.getTuile(p.b.getBG(c)) == null) || (p.b.getTuile(p.b.getBG(c)).aUnPingouin) || (p.b.getTuile(p.b.getBG(c)).nbPoissons == 0))
                && ((p.b.getTuile(p.b.getMG(c)) == null) || (p.b.getTuile(p.b.getMG(c)).aUnPingouin) || (p.b.getTuile(p.b.getMG(c)).nbPoissons == 0))
                && ((p.b.getTuile(p.b.getHG(c)) == null) || (p.b.getTuile(p.b.getHG(c)).aUnPingouin) || (p.b.getTuile(p.b.getHG(c)).nbPoissons == 0))
                && ((p.b.getTuile(p.b.getBD(c)) == null) || (p.b.getTuile(p.b.getBD(c)).aUnPingouin) || (p.b.getTuile(p.b.getBD(c)).nbPoissons == 0))
                && ((p.b.getTuile(p.b.getMD(c)) == null) || (p.b.getTuile(p.b.getMD(c)).aUnPingouin) || (p.b.getTuile(p.b.getMD(c)).nbPoissons == 0))
                && ((p.b.getTuile(p.b.getHD(c)) == null) || (p.b.getTuile(p.b.getHD(c)).aUnPingouin) || (p.b.getTuile(p.b.getHD(c)).nbPoissons == 0)));
    }

    public boolean feuillePointDinterrogationPingu(Partie p, Joueur j) {
        boolean bool = true;
        for (int i = 0; i < j.nbPingouin; i++) {
            if (j.myPingouins[i].actif) {
                if (!feuillePointDinterrogation(j.myPingouins[i].position, p)) {
                    bool = false;
                }
            }
        }

        return bool;
    }

    public ArbreGraphe CreationArbre(Coordonnees c, Partie partie, int dir, int nbcoupsmax) { // crée l'ArbreGraphe à partir de c sur nbcoupsmax coups.

        if ((nbcoupsmax <= 0) || feuillePointDinterrogation(c, partie)) {
            ArbreGraphe abr = new ArbreGraphe(0);
            abr.estFeuille = true;
            abr.c = c;
            Tuile tuile = partie.b.getTuile(c);
            abr.poids = tuile.nbPoissons;
            return abr;
        }

        Partie p = (Partie) partie.clone();
        ArbreGraphe abr = new ArbreGraphe(1);
        abr.c = c;
        Tuile tuile = p.b.getTuile(c);
        abr.poids = tuile.nbPoissons;
        tuile.nbPoissons = 0;
        p.b.setTuile(c, tuile);

        Coordonnees a = new Coordonnees(c.x, c.y);
        while ((p.b.getTuile(p.b.getBG(a)) != null) && (!p.b.getTuile(p.b.getBG(a)).aUnPingouin) && (p.b.getTuile(p.b.getBG(a)).nbPoissons != 0)) {

            abr.noeudBG[0].add(CreationArbre(p.b.getBG(a), p, 3, nbcoupsmax - 1));

            a = p.b.getBG(a);
        }
        a = new Coordonnees(c.x, c.y);
        while (p.b.getTuile(p.b.getMG(a)) != null && (!p.b.getTuile(p.b.getMG(a)).aUnPingouin) && (p.b.getTuile(p.b.getMG(a)).nbPoissons != 0)) {

            abr.noeudMG[0].add(CreationArbre(p.b.getMG(a), p, 4, nbcoupsmax - 1));

            a = p.b.getMG(a);
        }
        a = new Coordonnees(c.x, c.y);
        while (p.b.getTuile(p.b.getHG(a)) != null && (!p.b.getTuile(p.b.getHG(a)).aUnPingouin) && (p.b.getTuile(p.b.getHG(a)).nbPoissons != 0)) {

            abr.noeudHG[0].add(CreationArbre(p.b.getHG(a), p, 5, nbcoupsmax - 1));

            a = p.b.getHG(a);
        }
        a = new Coordonnees(c.x, c.y);
        while (p.b.getTuile(p.b.getHD(a)) != null && (!p.b.getTuile(p.b.getHD(a)).aUnPingouin) && (p.b.getTuile(p.b.getHD(a)).nbPoissons != 0)) {

            abr.noeudHD[0].add(CreationArbre(p.b.getHD(a), p, 0, nbcoupsmax - 1));

            a = p.b.getHD(a);
        }
        a = new Coordonnees(c.x, c.y);
        while (p.b.getTuile(p.b.getMD(a)) != null && (!p.b.getTuile(p.b.getMD(a)).aUnPingouin) && (p.b.getTuile(p.b.getMD(a)).nbPoissons != 0)) {

            abr.noeudMD[0].add(CreationArbre(p.b.getMD(a), p, 1, nbcoupsmax - 1));

            a = p.b.getMD(a);
        }
        a = new Coordonnees(c.x, c.y);
        while (p.b.getTuile(p.b.getBD(a)) != null && (!p.b.getTuile(p.b.getBD(a)).aUnPingouin) && (p.b.getTuile(p.b.getBD(a)).nbPoissons != 0)) {

            abr.noeudBD[0].add(CreationArbre(p.b.getBD(a), p, 2, nbcoupsmax - 1));

            a = p.b.getBD(a);
        }
        return abr;

    }

    /*
     * dir = 6 a l'appel de la fonction
     */
    public CoupleGenerique<Coordonnees, Integer> parcoursGraphe(ArbreGraphe abr, int nbCoupsmax, int nbCoups, int dir, int somme, Coordonnees coord1stchoice) {

        if ((nbCoups == nbCoupsmax - 1)) {
            coord1stchoice = abr.c;
        }

        if ((nbCoups == 0) || (abr.estFeuille)) {
            return new CoupleGenerique<Coordonnees, Integer>(coord1stchoice, somme);
        }

        CoupleGenerique<Coordonnees, Integer> val1 = new CoupleGenerique<Coordonnees, Integer>(new Coordonnees(0, 0), 0);
        CoupleGenerique<Coordonnees, Integer> val2 = new CoupleGenerique<Coordonnees, Integer>(new Coordonnees(0, 0), 0);
        CoupleGenerique<Coordonnees, Integer> val3 = new CoupleGenerique<Coordonnees, Integer>(new Coordonnees(0, 0), 0);
        CoupleGenerique<Coordonnees, Integer> val4 = new CoupleGenerique<Coordonnees, Integer>(new Coordonnees(0, 0), 0);
        CoupleGenerique<Coordonnees, Integer> val5 = new CoupleGenerique<Coordonnees, Integer>(new Coordonnees(0, 0), 0);
        CoupleGenerique<Coordonnees, Integer> val6 = new CoupleGenerique<Coordonnees, Integer>(new Coordonnees(0, 0), 0);
        CoupleGenerique<Coordonnees, Integer> tmp = new CoupleGenerique<Coordonnees, Integer>(new Coordonnees(0, 0), 0);

        if ((abr.noeudBD[0] != null) && (!abr.noeudBD[0].isEmpty())) {
            val1 = parcoursGraphe(abr.noeudBD[0].get(0), nbCoupsmax, nbCoups - 1, 2, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
            for (int i = 1; i < abr.noeudBD[0].size(); i++) {
                tmp = parcoursGraphe(abr.noeudBD[0].get(i), nbCoupsmax, nbCoups - 1, 2, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
                if (val1.e2 <= tmp.e2) {
                    val1 = tmp;
                }
            }
        }
        if ((abr.noeudMD[0] != null) && (!abr.noeudMD[0].isEmpty())) {
            val2 = parcoursGraphe(abr.noeudMD[0].get(0), nbCoupsmax, nbCoups - 1, 1, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
            for (int i = 1; i < abr.noeudMD[0].size(); i++) {
                tmp = parcoursGraphe(abr.noeudMD[0].get(i), nbCoupsmax, nbCoups - 1, 1, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
                if (val2.e2 <= tmp.e2) {
                    val2 = tmp;
                }
            }
        }
        if ((abr.noeudHD[0] != null) && (!abr.noeudHD[0].isEmpty())) {
            val3 = parcoursGraphe(abr.noeudHD[0].get(0), nbCoupsmax, nbCoups - 1, 0, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
            for (int i = 1; i < abr.noeudHD[0].size(); i++) {
                tmp = parcoursGraphe(abr.noeudHD[0].get(i), nbCoupsmax, nbCoups - 1, 0, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
                if (val3.e2 <= tmp.e2) {
                    val3 = tmp;
                }
            }
        }
        if ((abr.noeudHG[0] != null) && (!abr.noeudHG[0].isEmpty())) {
            val4 = parcoursGraphe(abr.noeudHG[0].get(0), nbCoupsmax, nbCoups - 1, 5, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
            for (int i = 1; i < abr.noeudHG[0].size(); i++) {
                tmp = parcoursGraphe(abr.noeudHG[0].get(i), nbCoupsmax, nbCoups - 1, 5, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
                if (val4.e2 <= tmp.e2) {
                    val4 = tmp;
                }
            }
        }
        if ((abr.noeudMG[0] != null) && (!abr.noeudMG[0].isEmpty())) {
            val5 = parcoursGraphe(abr.noeudMG[0].get(0), nbCoupsmax, nbCoups - 1, 4, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
            for (int i = 1; i < abr.noeudMG[0].size(); i++) {
                tmp = parcoursGraphe(abr.noeudMG[0].get(i), nbCoupsmax, nbCoups - 1, 4, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
                if (val5.e2 <= tmp.e2) {
                    val5 = tmp;
                }
            }
        }
        if ((abr.noeudBG[0] != null) && (!abr.noeudBG[0].isEmpty())) {
            val6 = parcoursGraphe(abr.noeudBG[0].get(0), nbCoupsmax, nbCoups - 1, 3, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case 
            for (int i = 1; i < abr.noeudBG[0].size(); i++) {
                tmp = parcoursGraphe(abr.noeudBG[0].get(i), nbCoupsmax, nbCoups - 1, 3, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case 
                if (val6.e2 <= tmp.e2) {
                    val6 = tmp;
                }
            }
        }

        int max = Math.max(val1.e2, Math.max(val2.e2, Math.max(val3.e2, Math.max(val4.e2, Math.max(val5.e2, val6.e2)))));

        if (max == val1.e2) {
            return val1;
        } else if (max == val2.e2) {
            return val2;
        } else if (max == val3.e2) {
            return val3;
        } else if (max == val4.e2) {
            return val4;
        } else if (max == val5.e2) {
            return val5;
        } else {
            return val6;
        }
    }

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
        System.out.println(myIA.nbPingouin);
        ArrayList<CoupleGenerique<Coordonnees, Integer>> poidsPingouins = new ArrayList<>();
        HashMap<Coordonnees, ArbreGraphe> hash = new HashMap<>();
        for (int i = 0; i < myIA.nbPingouin; i++) {
            ArbreGraphe abr = CreationArbre(myIA.myPingouins[i].position, p, -1, nbcoupsrecherches);
            CoupleGenerique<Coordonnees, Integer> coordint = parcoursGraphe(abr, nbcoupsrecherches, nbcoupsrecherches, 6, 0, new Coordonnees(0, 0));
            poidsPingouins.add(coordint);
        }

        for (int i = 0; i < poidsPingouins.size(); i++) {
            if (poidsPingouins.get(i).e2 > max) {
                max = poidsPingouins.get(i).e2;
                coordmax = poidsPingouins.get(i).e1;
                indx = i;
            }
        }

        return new CoupleGenerique<Coordonnees, Coordonnees>(myIA.myPingouins[indx].position, coordmax);
    }

    /* Renvoie le numero de Joueur correspondant à l'IA
     */
    public int notreNumeroDeJoueur(Partie p) {
        int num = 0;
        while ((num != p.nbJoueurs) && !(p.joueurs[num].nom.equals(myIA.nom))) {   // mettre une methode equals dans la classe abstraite Joueur ????????????????????????????
            num = num + 1;
        }
        return num;
    }

    public int numJoueurAdversaire(Partie p, int notreNum) {
        int num = 0;
        if (notreNum == 0) {
            num = 1;
        }
        return num;
    }


    /* Placement des pingouins pour l'IA difficile
     * On regarde si la banquise a une configuration speciale, par exemple :
     * 
     * Sinon on utilise le Placement Moyen 
     */
    public Coordonnees PlacementDifficile(Partie p) {

        Coordonnees retour = new Coordonnees();
        int notreNum = notreNumeroDeJoueur(p);
        int notreNbPingouin = p.nbPingouinActif(p.joueurs[notreNum]);
        Coordonnees riri = p.joueurs[notreNum].myPingouins[0].position;

        if (p.nbJoueurs == 2) {
            int ennemiNum = numJoueurAdversaire(p, notreNum);
            int ennemiNbPingouin = p.nbPingouinActif(p.joueurs[ennemiNum]);

            if (ennemiNbPingouin <= 3) { // Si l'adversaire a placé 0, 1, 2 ou 3 pingouins
                retour = placementMoyen(p);
            } else {     // l'adversaire n'a plus qu'1 ou 0 pingouin à placer
                Coordonnees c2;

                if (!(c2 = containsThree(p, platHHG)).equals(new Coordonnees(-1, -1))) {
                    Coordonnees p1 = p.joueurs[ennemiNum].myPingouins[0].position;
                    Coordonnees p2 = p.joueurs[ennemiNum].myPingouins[1].position;
                    Coordonnees p3 = p.joueurs[ennemiNum].myPingouins[2].position;
                    if (platHHG.contains(p1) || platHHG.contains(p2) || platHHG.contains(p3)) {
                        Coordonnees p0 = new Coordonnees(0, 0);
                        if ((p1.equals(p0) || p2.equals(p0) || p3.equals(p0)) && (!p.b.getTuile(new Coordonnees(1, 1)).aUnPingouin)) {
                            retour = new Coordonnees(1, 1);
                        } else {
                            if (!(c2 = containsThree(p, platHBG)).equals(new Coordonnees(-1, -1))) {
                                boolean condition = false; // nous n'avons pas de pingu dans la zone HBG
                                for (int i = 0; i < notreNbPingouin; i++) {
                                    if (platHBG.contains(p.joueurs[notreNum].myPingouins[i].position)) {
                                        condition = true;
                                    }
                                }
                                if (!condition) {
                                    retour = c2;
                                } else {
                                    retour = placementMoyen(p);
                                }
                            } else {
                                retour = placementMoyen(p);
                            }
                        }
                    }
                }
            }

        } else if (p.nbJoueurs == 3) {

        } else {

        }
        return retour;
    }

    /* joueurSuivant renvoie le numero du joueur qui suit le joueurActuel.
     * Reprend à 0 si le joueur actuel est le dernier a jouer
     */
    public int joueurSuivant(int joueurActuel, Partie p) {
        if (joueurActuel == p.nbJoueurs - 1) {
            return 0;
        } else {
            return joueurActuel + 1;
        }
    }

    /* public ArbreGraphe CreationAbreMinimax(Coordonnees c, Partie partie, int num, int nbcoupsmax, HashMap<Coordonnees, ArbreGraphe> hash, int somme, int first) {

     if (nbcoupsmax <= 0 || feuillePointDinterrogationPingu(partie, partie.joueurs[num])) {
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
     while ((p.b.getTuile(p.b.getBG(a)) != null) && (!p.b.getTuile(p.b.getBG(a)).aUnPingouin) && (p.b.getTuile(p.b.getBG(a)).nbPoissons != 0)) { // !!!!! mettre le clone dans le while !!!!!
     if (hash.containsKey(p.b.getBG(a))) {
     abr.noeudBG[i].add(hash.get(p.b.getBG(a)));
     } else {
     Partie p2 = (Partie) p.clone();
     p2.deplacement(c, p2.b.getBG(a));
     p2.verifierPingouinActif();

     ArbreGraphe abg = CreationAbreMinimax(p2.b.getBG(a), p2, joueurSuivant(num, p), nbcoupsmax - 1, hash, somme + abr.poids, 0);
     if (abg == null) {
     System.out.println("là bas");
     }

     abr.noeudBG[i].add(abg);

     hash.put(p2.b.getBG(a), abr);
     }
     a = p.b.getBG(a);
     }

     a = new Coordonnees(c.x, c.y);
     while (p.b.getTuile(p.b.getMG(a)) != null && (!p.b.getTuile(p.b.getMG(a)).aUnPingouin) && (p.b.getTuile(p.b.getMG(a)).nbPoissons != 0)) {
     if (hash.containsKey(p.b.getMG(a))) {
     abr.noeudMG[i].add(hash.get(p.b.getMG(a)));
     } else {
     Partie p2 = (Partie) p.clone();
     p2.deplacement(c, p2.b.getMG(a));
     p2.verifierPingouinActif();

     ArbreGraphe abg = CreationAbreMinimax(p2.b.getMG(a), p2, joueurSuivant(num, p), nbcoupsmax - 1, hash, somme + abr.poids, 0);
     if (abg == null) {
     System.out.println("là bas");
     }

     abr.noeudMG[i].add(abg);

     hash.put(p2.b.getMG(a), abr);
     }
     a = p.b.getMG(a);
     }

     a = new Coordonnees(c.x, c.y);
     while (p.b.getTuile(p.b.getHG(a)) != null && (!p.b.getTuile(p.b.getHG(a)).aUnPingouin) && (p.b.getTuile(p.b.getHG(a)).nbPoissons != 0)) {
     if (hash.containsKey(p.b.getHG(a))) {
     abr.noeudHG[i].add(hash.get(p.b.getHG(a)));
     } else {
     Partie p2 = (Partie) p.clone();
     p2.deplacement(c, p2.b.getHG(a));
     p2.verifierPingouinActif();
     ArbreGraphe abg = CreationAbreMinimax(p.b.getHG(a), p2, joueurSuivant(num, p), nbcoupsmax - 1, hash, somme + abr.poids, 0);
     if (abg == null) {
     System.out.println("j'en sais rien moi");
     }
     abr.noeudHG[i].add(abg);

     hash.put(p2.b.getHG(a), abr);
     }
     a = p.b.getHG(a);
     }

     a = new Coordonnees(c.x, c.y);
     while (p.b.getTuile(p.b.getHD(a)) != null && (!p.b.getTuile(p.b.getHD(a)).aUnPingouin) && (p.b.getTuile(p.b.getHD(a)).nbPoissons != 0)) {
     if (hash.containsKey(p.b.getHD(a))) {
     abr.noeudHD[i].add(hash.get(p.b.getHD(a)));
     } else {
     Partie p2 = (Partie) p.clone();
     p2.deplacement(c, p2.b.getHD(a));
     p2.verifierPingouinActif();

     ArbreGraphe abg = CreationAbreMinimax(p2.b.getHD(a), p2, joueurSuivant(num, p), nbcoupsmax - 1, hash, somme + abr.poids, 0);
     if (abg == null) {
     System.out.println("là bas");
     }

     abr.noeudHD[i].add(abg);

     hash.put(p2.b.getHD(a), abr);
     }
     a = p.b.getHD(a);
     }

     a = new Coordonnees(c.x, c.y);
     while (p.b.getTuile(p.b.getMD(a)) != null && (!p.b.getTuile(p.b.getMD(a)).aUnPingouin) && (p.b.getTuile(p.b.getMD(a)).nbPoissons != 0)) {
     if (hash.containsKey(p.b.getMD(a))) {
     abr.noeudMD[i].add(hash.get(p.b.getMD(a)));
     } else {
     Partie p2 = (Partie) p.clone();
     p2.deplacement(c, p2.b.getMD(a));
     p2.verifierPingouinActif();

     ArbreGraphe abg = CreationAbreMinimax(p2.b.getMD(a), p2, joueurSuivant(num, p), nbcoupsmax - 1, hash, somme + abr.poids, 0);
     if (abg == null) {
     System.out.println("là bas");
     }

     abr.noeudMD[i].add(abg);

     hash.put(p2.b.getMD(a), abr);
     }
     a = p.b.getMD(a);
     }

     a = new Coordonnees(c.x, c.y);
     while (p.b.getTuile(p.b.getBD(a)) != null && (!p.b.getTuile(p.b.getBD(a)).aUnPingouin) && (p.b.getTuile(p.b.getBD(a)).nbPoissons != 0)) {
     if (hash.containsKey(p.b.getBD(a))) {
     abr.noeudBD[i].add(hash.get(p.b.getBD(a)));
     } else {
     Partie p2 = (Partie) p.clone();
     p2.deplacement(c, p2.b.getBD(a));
     p2.verifierPingouinActif();

     ArbreGraphe abg = CreationAbreMinimax(p2.b.getBD(a), p2, joueurSuivant(num, p), nbcoupsmax - 1, hash, somme + abr.poids, 0);
     if (abg == null) {
     System.out.println("là bas");
     }

     abr.noeudBD[i].add(abg);

     hash.put(p2.b.getBD(a), abr);
     }
     a = p.b.getBD(a);
     }
     }
     }
     return abr;
     }
     }*/
    /* Pour deux joueurs seulement
     * Creation de l'arbre pour le minimax : 
     * num est le numero du joueur actuel à la profondeur actuelle - 1er appel avec notreNum
     */
    public ArbreGraphe CreationAbreMinimax(Coordonnees c, Partie partie, int num, int nbcoupsmax, ArrayList<HashMap<Banquise, ArbreGraphe>> hash, int somme, int first) {

        if (nbcoupsmax <= 0 || feuillePointDinterrogationPingu(partie, partie.joueurs[num])) {
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
                    while ((p.b.getTuile(p.b.getBG(a)) != null) && (!p.b.getTuile(p.b.getBG(a)).aUnPingouin) && (p.b.getTuile(p.b.getBG(a)).nbPoissons != 0)) { // !!!!! mettre le clone dans le while !!!!!

                        Partie p2 = (Partie) p.clone();
                        p2.deplacement(c, p2.b.getBG(a));
                        p2.verifierPingouinActif();

                        if (hash.get(num).containsKey(p2.b)) {

                            abr.noeudBG[i].add(hash.get(num).get(p2.b));
                        } else {
                            ArbreGraphe abg = CreationAbreMinimax(p2.b.getBG(a), p2, joueurSuivant(num, p), nbcoupsmax - 1, hash, somme + abr.poids, 0);

                            hash.get(num).put(p2.b, abg);
                            abr.noeudBG[i].add(abg);

                        }
                        a = p.b.getBG(a);
                    }

                    a = new Coordonnees(c.x, c.y);
                    while (p.b.getTuile(p.b.getMG(a)) != null && (!p.b.getTuile(p.b.getMG(a)).aUnPingouin) && (p.b.getTuile(p.b.getMG(a)).nbPoissons != 0)) {

                        Partie p2 = (Partie) p.clone();
                        p2.deplacement(c, p2.b.getMG(a));
                        p2.verifierPingouinActif();

                        if (hash.get(num).containsKey(p2.b)) {

                            abr.noeudMG[i].add(hash.get(num).get(p2.b));
                        } else {
                            ArbreGraphe abg = CreationAbreMinimax(p2.b.getMG(a), p2, joueurSuivant(num, p), nbcoupsmax - 1, hash, somme + abr.poids, 0);

                            hash.get(num).put(p2.b, abg);
                            abr.noeudMG[i].add(abg);

                        }
                        a = p.b.getMG(a);
                    }

                    a = new Coordonnees(c.x, c.y);
                    while (p.b.getTuile(p.b.getHG(a)) != null && (!p.b.getTuile(p.b.getHG(a)).aUnPingouin) && (p.b.getTuile(p.b.getHG(a)).nbPoissons != 0)) {

                        Partie p2 = (Partie) p.clone();
                        p2.deplacement(c, p2.b.getHG(a));
                        p2.verifierPingouinActif();
                        if (hash.get(num).containsKey(p2.b)) {

                            abr.noeudHG[i].add(hash.get(num).get(p2.b));
                        } else {
                            ArbreGraphe abg = CreationAbreMinimax(p.b.getHG(a), p2, joueurSuivant(num, p), nbcoupsmax - 1, hash, somme + abr.poids, 0);
                            hash.get(num).put(p2.b, abg);

                            abr.noeudHG[i].add(abg);

                        }
                        a = p.b.getHG(a);
                    }

                    a = new Coordonnees(c.x, c.y);
                    while (p.b.getTuile(p.b.getHD(a)) != null && (!p.b.getTuile(p.b.getHD(a)).aUnPingouin) && (p.b.getTuile(p.b.getHD(a)).nbPoissons != 0)) {

                        Partie p2 = (Partie) p.clone();
                        p2.deplacement(c, p2.b.getHD(a));
                        p2.verifierPingouinActif();
                        if (hash.get(num).containsKey(p2.b)) {

                            abr.noeudHD[i].add(hash.get(num).get(p2.b));
                        } else {
                            ArbreGraphe abg = CreationAbreMinimax(p2.b.getHD(a), p2, joueurSuivant(num, p), nbcoupsmax - 1, hash, somme + abr.poids, 0);
                            hash.get(num).put(p2.b, abg);

                            abr.noeudHD[i].add(abg);

                        }
                        a = p.b.getHD(a);
                    }

                    a = new Coordonnees(c.x, c.y);
                    while (p.b.getTuile(p.b.getMD(a)) != null && (!p.b.getTuile(p.b.getMD(a)).aUnPingouin) && (p.b.getTuile(p.b.getMD(a)).nbPoissons != 0)) {

                        Partie p2 = (Partie) p.clone();
                        p2.deplacement(c, p2.b.getMD(a));
                        p2.verifierPingouinActif();
                        if (hash.get(num).containsKey(p2.b)) {

                            abr.noeudMD[i].add(hash.get(num).get(p2.b));
                        } else {
                            ArbreGraphe abg = CreationAbreMinimax(p2.b.getMD(a), p2, joueurSuivant(num, p), nbcoupsmax - 1, hash, somme + abr.poids, 0);
                            hash.get(num).put(p2.b, abg);

                            abr.noeudMD[i].add(abg);

                        }
                        a = p.b.getMD(a);
                    }

                    a = new Coordonnees(c.x, c.y);
                    while (p.b.getTuile(p.b.getBD(a)) != null && (!p.b.getTuile(p.b.getBD(a)).aUnPingouin) && (p.b.getTuile(p.b.getBD(a)).nbPoissons != 0)) {

                        Partie p2 = (Partie) p.clone();
                        p2.deplacement(c, p2.b.getBD(a));
                        p2.verifierPingouinActif();
                        if (hash.get(num).containsKey(p2.b)) {

                            abr.noeudBD[i].add(hash.get(num).get(p2.b));
                        } else {
                            ArbreGraphe abg = CreationAbreMinimax(p2.b.getBD(a), p2, joueurSuivant(num, p), nbcoupsmax - 1, hash, somme + abr.poids, 0);
                            hash.get(num).put(p2.b, abg);

                            abr.noeudBD[i].add(abg);

                        }
                        a = p.b.getBD(a);

                    }
                }
            }
            return abr;
        }
    }

    /* minimax renvoie un couple CoordonneesInt qui correspond
    
     */
    public CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> minimax(Partie p, ArbreGraphe abr, int num, int nbCoupsmax, int nbCoups, Coordonnees coord1stchoice, Coordonnees coordPingu, CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> a, CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> b, int indicePingu) { //0 = c'est a nous - On maximise || 1 = autre joueur -on minimise

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
                            tmp = minimax(p, abr.noeudBD[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
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
                            tmp = minimax(p, abr.noeudMD[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
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
                            tmp = minimax(p, abr.noeudHD[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
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
                            tmp = minimax(p, abr.noeudHG[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
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
                            tmp = minimax(p, abr.noeudMG[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
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
                            tmp = minimax(p, abr.noeudBG[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
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
                            tmp = minimax(p, abr.noeudBD[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
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
                            tmp = minimax(p, abr.noeudMD[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
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
                            tmp = minimax(p, abr.noeudHD[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
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
                            tmp = minimax(p, abr.noeudHG[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
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
                            tmp = minimax(p, abr.noeudMG[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
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
                            tmp = minimax(p, abr.noeudBG[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice, coordPingu, alpha, beta, i);
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
    /*
     public CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> minimax(Partie p, ArbreGraphe abr, int num, int nbCoupsmax, int nbCoups, Coordonnees coord1stchoice) { //0 = c'est a nous - On maximise || 1 = autre joueur -on minimise

     if ((nbCoups == nbCoupsmax - 1)) {
     coord1stchoice = abr.c;
     }

     if (nbCoups == 0 || abr.estFeuille) {
     if (abr == null) {
     System.out.println("tout est neuf");
     }
     return new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), coord1stchoice), abr.poids);
     }

     CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> val1 = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), coord1stchoice), -1);
     CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> val2 = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), coord1stchoice), -1);
     CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> val3 = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), coord1stchoice), -1);
     CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> val4 = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), coord1stchoice), -1);
     CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> val5 = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), coord1stchoice), -1);
     CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> val6 = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), coord1stchoice), -1);
     CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> tmp = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), coord1stchoice), -1);
     if (num != notreNumeroDeJoueur(p)) {
     val1 = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), coord1stchoice), 1000);
     val2 = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), coord1stchoice), 1000);
     val3 = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), coord1stchoice), 1000);
     val4 = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), coord1stchoice), 1000);
     val5 = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), coord1stchoice), 1000);
     val6 = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), coord1stchoice), 1000);
     tmp = new CoupleGenerique(new CoupleGenerique(new Coordonnees(0, 0), coord1stchoice), 1000);
     }

     ArrayList<CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer>> bestPingu = new ArrayList<>(p.joueurs[num].nbPingouin);
     for (int i = 0; i < p.joueurs[num].nbPingouin; i++) {
     if (p.joueurs[num].myPingouins[i].actif) {
     if ((abr.noeudBD[i] != null) && (!abr.noeudBD[i].isEmpty()) && (abr.noeudBD[i].get(0) != null)) {
     if (abr.noeudBD[i].get(0) == null) {
     System.out.println(" et tout est sauvage ");
     }
     val1 = minimax(p, abr.noeudBD[i].get(0), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice);
     for (int j = 1; j < abr.noeudBD[i].size(); j++) {
     tmp = minimax(p, abr.noeudBD[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice);

     if (num == notreNumeroDeJoueur(p) && (val1.e2 <= tmp.e2)) { // c'est a nous - on maximise 
     val1 = tmp;
     }
     if (num != notreNumeroDeJoueur(p) && (val1.e2 >= tmp.e2)) { // ce n'est pas a nous - on minimise 
     val1 = tmp;
     }
     }
     }

     if ((abr.noeudMD[i] != null) && (!abr.noeudMD[i].isEmpty()) && (abr.noeudMD[i].get(0) != null)) {
     if (abr.noeudMD[i].get(0) == null) {
     System.out.println("libre continent sans grillage");
     }
     val2 = minimax(p, abr.noeudMD[i].get(0), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice);
     for (int j = 1; j < abr.noeudMD[i].size(); j++) {
     tmp = minimax(p, abr.noeudMD[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice);

     if (num == notreNumeroDeJoueur(p) && (val2.e2 <= tmp.e2)) { // c'est a nous - on maximise 
     val2 = tmp;
     }
     if (num != notreNumeroDeJoueur(p) && (val2.e2 >= tmp.e2)) { // ce n'est pas a nous - on minimise 
     val2 = tmp;
     }
     }
     }
     if ((abr.noeudHD[i] != null) && (!abr.noeudHD[i].isEmpty()) && (abr.noeudHD[i].get(0) != null)) {
     if (abr.noeudHD[i].get(0) == null) {
     System.out.println("ou ou ou ou ou ");
     }
     val3 = minimax(p, abr.noeudHD[i].get(0), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice);
     for (int j = 1; j < abr.noeudHD[i].size(); j++) {
     tmp = minimax(p, abr.noeudHD[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice);

     if (num == notreNumeroDeJoueur(p) && (val3.e2 <= tmp.e2)) { // c'est a nous - on maximise 
     val3 = tmp;
     }
     if (num != notreNumeroDeJoueur(p) && (val3.e2 >= tmp.e2)) { // ce n'est pas a nous - on minimise 
     val3 = tmp;
     }
     }
     }
     if ((abr.noeudHG[i] != null) && (!abr.noeudHG[i].isEmpty()) && (abr.noeudHG[i].get(0) != null)) {
     if (abr.noeudHG[i].get(0) == null) {
     System.out.println("c'est pour ça que j'irais");
     }
     val4 = minimax(p, abr.noeudHG[i].get(0), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice);
     for (int j = 1; j < abr.noeudHG[i].size(); j++) {
     tmp = minimax(p, abr.noeudHG[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice);

     if (num == notreNumeroDeJoueur(p) && (val4.e2 <= tmp.e2)) { // c'est a nous - on maximise 
     val4 = tmp;
     }
     if (num != notreNumeroDeJoueur(p) && (val4.e2 >= tmp.e2)) { // ce n'est pas a nous - on minimise 
     val4 = tmp;
     }
     }
     }
     if ((abr.noeudMG[i] != null) && (!abr.noeudMG[i].isEmpty()) && (abr.noeudMG[i].get(0) != null)) {
     if (abr.noeudMG[i].get(0) == null) {
     System.out.println("la bas");
     }
     val5 = minimax(p, abr.noeudMG[i].get(0), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice);
     for (int j = 1; j < abr.noeudMG[i].size(); j++) {
     tmp = minimax(p, abr.noeudMG[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice);

     if (num == notreNumeroDeJoueur(p) && (val5.e2 <= tmp.e2)) { // c'est a nous - on maximise 
     val5 = tmp;
     }
     if (num != notreNumeroDeJoueur(p) && (val5.e2 >= tmp.e2)) { // ce n'est pas a nous - on minimise 
     val5 = tmp;
     }
     }
     }
     if ((abr.noeudBG[i] != null) && (!abr.noeudBG[i].isEmpty()) && (abr.noeudBG[i].get(0) != null)) {
     if (abr.noeudBG[i].get(0) == null) {
     System.out.println("...");
     }
     val6 = minimax(p, abr.noeudBG[i].get(0), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice);
     for (int j = 1; j < abr.noeudBG[i].size(); j++) {
     tmp = minimax(p, abr.noeudBG[i].get(j), joueurSuivant(num, p), nbCoupsmax, nbCoups - 1, coord1stchoice);

     if (num != notreNumeroDeJoueur(p) && (val6.e2 <= tmp.e2)) { // c'est a nous - on maximise 
     val6 = tmp;
     }
     if (num != notreNumeroDeJoueur(p) && (val6.e2 >= tmp.e2)) { // ce n'est pas a nous - on minimise 
     val6 = tmp;
     }
     }
     }

     if (num == notreNumeroDeJoueur(p)) {
     int max = Math.max(val1.e2, Math.max(val2.e2, Math.max(val3.e2, Math.max(val4.e2, Math.max(val5.e2, val6.e2)))));
     if (max == val1.e2) {
     bestPingu.add(i, val1);
     } else if (max == val2.e2) {
     bestPingu.add(i, val2);
     } else if (max == val3.e2) {
     bestPingu.add(i, val3);
     } else if (max == val4.e2) {
     bestPingu.add(i, val4);
     } else if (max == val5.e2) {
     bestPingu.add(i, val5);
     } else {
     bestPingu.add(i, val6);
     }
     } else {
     int min = Math.min(val1.e2, Math.min(val2.e2, Math.min(val3.e2, Math.min(val4.e2, Math.min(val5.e2, val6.e2)))));
     if (min == val1.e2) {
     bestPingu.add(i, val1);
     } else if (min == val2.e2) {
     bestPingu.add(i, val2);
     } else if (min == val3.e2) {
     bestPingu.add(i, val3);
     } else if (min == val4.e2) {
     bestPingu.add(i, val4);
     } else if (min == val5.e2) {
     bestPingu.add(i, val5);
     } else {
     bestPingu.add(i, val6);
     }
     }

     } else {
     if (num == notreNumeroDeJoueur(p)) {
     bestPingu.add(i, new CoupleGenerique(new CoupleGenerique(new Coordonnees(-1, -1), coord1stchoice), -1));
     } else {
     bestPingu.add(i, new CoupleGenerique(new CoupleGenerique(new Coordonnees(-1, -1), coord1stchoice), 1000));
     }
     }
     }
     CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> to_return = bestPingu.get(0);
     if (num == notreNumeroDeJoueur(p)) {
     int max2 = -1;
     for (int i = 0; i < bestPingu.size(); i++) {
     //   System.out.println(bestPingu.get(i).e2);
     if (max2 < bestPingu.get(i).e2) {
     max2 = bestPingu.get(i).e2;
     to_return = bestPingu.get(i);
     to_return.e1.e1 = p.joueurs[num].myPingouins[i].position;
     }
     }
     } else {
     int min2 = +1000;
     for (int i = 0; i < bestPingu.size(); i++) {
     if (min2 > bestPingu.get(i).e2) {
     min2 = bestPingu.get(i).e2;
     to_return = bestPingu.get(i);
     to_return.e1.e1 = p.joueurs[num].myPingouins[i].position;
     }
     }
     }
     //System.out.println("to_return" + to_return.e2 + " size : "+bestPingu.size());
     return to_return;
     }
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
        ArbreGraphe abr = CreationAbreMinimax(new Coordonnees(0, 0), p, num, nbCoupsmax, listHash, 0, 1);
        CoupleGenerique<CoupleGenerique<Coordonnees, Coordonnees>, Integer> coucou = minimax(p, abr, num, nbCoupsmax, nbCoupsmax, new Coordonnees(0, 0), new Coordonnees(0, 0), alpha, beta, 0);
        CoupleGenerique<Coordonnees, Coordonnees> retour = new CoupleGenerique(coucou.e1.e1, coucou.e1.e2);
        System.out.println("retour " + retour.e1 + retour.e2);
        return retour;
    }

    public CoupleGenerique<Coordonnees, Coordonnees> algoDifficileTEST(Partie p) {

        if (p.b.nbTuilesLibres() >= 40) {
            return algoMoyen(p);
        } else {
            return algoDifficile(p);
        }
    }

}
