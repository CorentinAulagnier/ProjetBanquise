package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class AlgoIA {

    IA myIA = null;

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
    /**
     * ****************************************************************************************************
     */
    /* Renvoie un couple de coordonnees :
     * La premiere represente la position du pingouin choisi avant le deplacement
     * La seconde represente la position du pingouin choisi apres le deplacement
     */
    public Coordonnees placementFacile(Banquise b) { // TODO à changer coordonnées en attributs largeur / hauteur (dans classe banquise)
        Random r = new Random();

        int i = 0;
        int j = 0;
        do {
            i = r.nextInt(8);

            if (i % 2 == 0) {
                j = r.nextInt(7);
                j = r.nextInt(8);
            }
        } while (!b.getTuile(new Coordonnees(i, j)).aUnPingouin);

        return new Coordonnees(i, j);
    }

    public CoupleCoordonnees algoFacile(Banquise b) {

        Random r = new Random();
        CoupleCoordonnees cc;
        /* Choix aleatoire du Pingouin */
        int val = r.nextInt(myIA.nbPingouin);

        int i = myIA.myPingouins[val].position.x;
        int j = myIA.myPingouins[val].position.y;
        Coordonnees dep = new Coordonnees(i, j);
        Coordonnees courant;
        int nbDeplPossible = 0;
        int dir = 0;

        do {	// Choix de la direction + nbDeDeplacement possible mis a jour
            nbDeplPossible = 0;
            dir = r.nextInt(6);
            if (dir == 0) { 			// En haut a droite 
                while ((b.getTuile(b.getHD(dep)).nbPoissons != 0) && (!b.getTuile(b.getHD(dep)).aUnPingouin)) {
                    courant = b.getHD(dep);
                    dep = courant;
                    nbDeplPossible = nbDeplPossible + 1;
                }
            } else if (dir == 1) { 	// Au milieu a droite
                while ((b.getTuile(b.getMD(dep)).nbPoissons != 0) && (!b.getTuile(b.getMD(dep)).aUnPingouin)) {
                    courant = b.getMD(dep);
                    dep = courant;
                    nbDeplPossible = nbDeplPossible + 1;
                }
            } else if (dir == 2) { 	// En bas a droite 
                while ((b.getTuile(b.getBD(dep)).nbPoissons != 0) && (!b.getTuile(b.getBD(dep)).aUnPingouin)) {
                    courant = b.getBD(dep);
                    dep = courant;
                    nbDeplPossible = nbDeplPossible + 1;
                }
            } else if (dir == 3) { 	// En bas a gauche 
                while ((b.getTuile(b.getBG(dep)).nbPoissons != 0) && (!b.getTuile(b.getBG(dep)).aUnPingouin)) {
                    courant = b.getBG(dep);
                    dep = courant;
                    nbDeplPossible = nbDeplPossible + 1;
                }
            } else if (dir == 4) { 	// Au milieu a gauche
                while ((b.getTuile(b.getMG(dep)).nbPoissons != 0) && (!b.getTuile(b.getMG(dep)).aUnPingouin)) {
                    courant = b.getMG(dep);
                    dep = courant;
                    nbDeplPossible = nbDeplPossible + 1;
                }
            } else {				// En haut a gauche
                while ((b.getTuile(b.getHG(dep)).nbPoissons != 0) && (!b.getTuile(b.getHG(dep)).aUnPingouin)) {
                    courant = b.getHG(dep);
                    dep = courant;
                    nbDeplPossible = nbDeplPossible + 1;
                }

            }
        } while (nbDeplPossible == 0);

        int nbDepl = r.nextInt(nbDeplPossible) + 1; // Tirage au sort du nb de case a se deplacer
        dep = new Coordonnees(i, j);

        if (dir == 0) { 			// En haut a droite 
            while (nbDepl != 0) {
                dep = b.getHD(dep);
                nbDepl--;
            }
        } else if (dir == 1) { 	// Au milieu a droite
            while (nbDepl != 0) {
                dep = b.getMD(dep);
                nbDepl--;
            }
        } else if (dir == 2) { 	// En bas a droite 
            while (nbDepl != 0) {
                dep = b.getBD(dep);
                nbDepl--;
            }
        } else if (dir == 3) { 	// En bas a gauche 
            while (nbDepl != 0) {
                dep = b.getBG(dep);
                nbDepl--;
            }
        } else if (dir == 4) { 	// Au milieu a gauche
            while (nbDepl != 0) {
                dep = b.getMG(dep);
                nbDepl--;
            }
        } else {				// En haut a gauche
            while (nbDepl != 0) {
                dep = b.getHG(dep);
                nbDepl--;
            }

        }

        cc = new CoupleCoordonnees(new Coordonnees(i, j), dep);

        return cc;
    }

    /**
     * ****************************************************************************************************
     */
    public Coordonnees placementMoyen(Banquise b) { // TODO à changer coordonnées en attributs largeur / hauteur (dans classe banquise)		
        Stack<Coordonnees> pile = new Stack<>();
        Coordonnees c = new Coordonnees(3, 3);
        Tuile tuileDep = b.getTuile(c);
        pile.push(c);
        while ((!pile.empty()) && (tuileDep.nbPoissons != 3) && (tuileDep.aUnPingouin)) {
            c = pile.pop();
            tuileDep = b.getTuile(c);
            if (b.getHD(c) != null) {
                pile.push(b.getHD(c));
            }
            if (b.getMD(c) != null) {
                pile.push(b.getMD(c));
            }
            if (b.getBD(c) != null) {
                pile.push(b.getBD(c));
            }
            if (b.getBG(c) != null) {
                pile.push(b.getBG(c));
            }
            if (b.getMG(c) != null) {
                pile.push(b.getMG(c));
            }
            if (b.getHG(c) != null) {
                pile.push(b.getHG(c));
            }
        }
        return c;
    }

    public ArbreGraphe CreationArbre(Coordonnees c, Banquise b, int dir, int nbcoupsmax) { // crée l'ArbreGraphe à partir de c sur nbcoupsmax coups.

        if (nbcoupsmax == 0) {
            return null;
        }

        ArbreGraphe abr = new ArbreGraphe();
        abr.c = c;
        Tuile tuile = b.getTuile(c);
        abr.poids = tuile.nbPoissons;
        tuile.nbPoissons = 0;
        b.setTuile(c, tuile);
        if ((b.getTuile(b.getBG(c)) != null) && (!b.getTuile(b.getBG(c)).aUnPingouin) && (b.getTuile(b.getBG(c)).nbPoissons != 0)) {
            if (dir == 3) {
                abr.noeudBG = CreationArbre(b.getBG(c), b, 3, nbcoupsmax);
            } else {
                abr.noeudBG = CreationArbre(b.getBG(c), b, 3, nbcoupsmax - 1);
            }
        }
        if (b.getTuile(b.getMG(c)) != null && (!b.getTuile(b.getMG(c)).aUnPingouin) && (b.getTuile(b.getMG(c)).nbPoissons != 0)) {
            if (dir == 4) {
                abr.noeudMG = CreationArbre(b.getMG(c), b, 4, nbcoupsmax);
            } else {
                abr.noeudMG = CreationArbre(b.getMG(c), b, 4, nbcoupsmax - 1);
            }
        }
        if (b.getTuile(b.getHG(c)) != null && (!b.getTuile(b.getHG(c)).aUnPingouin) && (b.getTuile(b.getHG(c)).nbPoissons != 0)) {
            if (dir == 5) {
                abr.noeudHG = CreationArbre(b.getHG(c), b, 5, nbcoupsmax);
            } else {
                abr.noeudHG = CreationArbre(b.getHG(c), b, 5, nbcoupsmax - 1);
            }
        }
        if (b.getTuile(b.getHD(c)) != null && (!b.getTuile(b.getHD(c)).aUnPingouin) && (b.getTuile(b.getHD(c)).nbPoissons != 0)) {
            if (dir == 0) {
                abr.noeudHD = CreationArbre(b.getHD(c), b, 0, nbcoupsmax);
            } else {
                abr.noeudHD = CreationArbre(b.getHD(c), b, 0, nbcoupsmax - 1);
            }
        }
        if (b.getTuile(b.getMD(c)) != null && (!b.getTuile(b.getMD(c)).aUnPingouin) && (b.getTuile(b.getMD(c)).nbPoissons != 0)) {
            if (dir == 1) {
                abr.noeudMD = CreationArbre(b.getMD(c), b, 1, nbcoupsmax);
            } else {
                abr.noeudMD = CreationArbre(b.getMD(c), b, 1, nbcoupsmax - 1);
            }
        }
        if (b.getTuile(b.getBD(c)) != null && (!b.getTuile(b.getBD(c)).aUnPingouin) && (b.getTuile(b.getBD(c)).nbPoissons != 0)) {
            if (dir == 2) {
                abr.noeudBD = CreationArbre(b.getBD(c), b, 2, nbcoupsmax);
            } else {
                abr.noeudBD = CreationArbre(b.getBD(c), b, 2, nbcoupsmax - 1);
            }
        }

        return abr;

    }

    /*
     * dir = 6 a l'appel de la fonction
     */
    public CoupleCoordonneesInt parcoursGraphe(ArbreGraphe abr, int nbCoupsmax, int nbCoups, int dir, int somme, Coordonnees coord1stchoice) {

        if ((nbCoups == 0)) {
            return new CoupleCoordonneesInt(coord1stchoice, somme);
        }
        if ((nbCoups == nbCoupsmax - 1)) {
            coord1stchoice = abr.c;
        }
        CoupleCoordonneesInt val1 = new CoupleCoordonneesInt(new Coordonnees(0, 0), 0);
        CoupleCoordonneesInt val2 = new CoupleCoordonneesInt(new Coordonnees(0, 0), 0);
        CoupleCoordonneesInt val3 = new CoupleCoordonneesInt(new Coordonnees(0, 0), 0);
        CoupleCoordonneesInt val4 = new CoupleCoordonneesInt(new Coordonnees(0, 0), 0);
        CoupleCoordonneesInt val5 = new CoupleCoordonneesInt(new Coordonnees(0, 0), 0);
        CoupleCoordonneesInt val6 = new CoupleCoordonneesInt(new Coordonnees(0, 0), 0);

        if (abr.noeudBD != null) {
            val1 = parcoursGraphe(abr.noeudBD, nbCoupsmax, nbCoups - 1, 2, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
            if (((dir == 2) || (dir == 6)) && abr.noeudBD.noeudBD != null) {
                CoupleCoordonneesInt val12 = parcoursGraphe(abr.noeudBD, nbCoupsmax, nbCoups, 2, somme, coord1stchoice);          // On ne choisi pas le noeud, on continue dans la meme direction
                if (val1.i < val12.i) {
                    val1 = val12;
                }
            }
        }
        if (abr.noeudMD != null) {
            val2 = parcoursGraphe(abr.noeudMD, nbCoupsmax, nbCoups - 1, 1, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
            if (((dir == 1) || (dir == 6)) && abr.noeudMD.noeudMD != null) {
                CoupleCoordonneesInt val22 = parcoursGraphe(abr.noeudMD, nbCoupsmax, nbCoups, 1, somme, coord1stchoice);          // On ne choisi pas le noeud, on continue dans la meme direction
                if (val2.i < val22.i) {
                    val2 = val22;
                }
            }
        }
        if (abr.noeudHD != null) {
            val3 = parcoursGraphe(abr.noeudHD, nbCoupsmax, nbCoups - 1, 0, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
            if (((dir == 0) || (dir == 6)) && abr.noeudHD.noeudHD != null) {
                CoupleCoordonneesInt val32 = parcoursGraphe(abr.noeudHD, nbCoupsmax, nbCoups, 0, somme, coord1stchoice);          // On ne choisi pas le noeud, on continue dans la meme direction
                if (val3.i < val32.i) {
                    val3 = val32;
                }
            }
        }
        if (abr.noeudHG != null) {
            val4 = parcoursGraphe(abr.noeudHG, nbCoupsmax, nbCoups - 1, 5, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
            if (((dir == 5) || (dir == 6)) && abr.noeudHG.noeudHG != null) {
                CoupleCoordonneesInt val42 = parcoursGraphe(abr.noeudHG, nbCoupsmax, nbCoups, 5, somme, coord1stchoice);          // On ne choisi pas le noeud, on continue dans la meme direction
                if (val4.i < val42.i) {
                    val4 = val42;
                }
            }
        }
        if (abr.noeudMG != null) {
            val5 = parcoursGraphe(abr.noeudMG, nbCoupsmax, nbCoups - 1, 4, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
            if (((dir == 4) || (dir == 6)) && abr.noeudMG.noeudMG != null) {
                CoupleCoordonneesInt val52 = parcoursGraphe(abr.noeudMG, nbCoupsmax, nbCoups, 4, somme, coord1stchoice);          // On ne choisi pas le noeud, on continue dans la meme direction
                if (val5.i < val52.i) {
                    val5 = val52;
                }
            }
        }
        if (abr.noeudBG != null) {
            val6 = parcoursGraphe(abr.noeudBG, nbCoupsmax, nbCoups - 1, 3, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
            if (((dir == 3) || (dir == 6)) && abr.noeudBG.noeudBG != null) {
                CoupleCoordonneesInt val62 = parcoursGraphe(abr.noeudBG, nbCoupsmax, nbCoups, 3, somme, coord1stchoice);          // On ne choisi pas le noeud, on continue dans la meme direction
                if (val6.i < val62.i) {
                    val6 = val62;
                }
            }
        }

        int max = Math.max(val1.i, Math.max(val2.i, Math.max(val3.i, Math.max(val4.i, Math.max(val5.i, val6.i)))));
        if (max == val1.i) {
            return val1;
        } else if (max == val2.i) {
            return val2;
        } else if (max == val3.i) {
            return val3;
        } else if (max == val4.i) {
            return val4;
        } else if (max == val5.i) {
            return val5;
        } else {
            return val6;
        }
    }

    public CoupleCoordonnees algoMoyen(Banquise b) {
        int nbcoupsrecherches = 2; // Choix du "2" très réfléchi mais modifiable.
        int max = 0;
        Coordonnees coordmax = null;
        int indx = 0;
        ArrayList<CoupleCoordonneesInt> poidsPingouins = new ArrayList<>();
        for (int i = 0; i < myIA.nbPingouin; i++) {
            ArbreGraphe abr = CreationArbre(myIA.myPingouins[i].position, b, -1, nbcoupsrecherches);
            CoupleCoordonneesInt coordint = parcoursGraphe(abr, nbcoupsrecherches, nbcoupsrecherches, 6, 0, new Coordonnees(0, 0));
            poidsPingouins.add(coordint);
        }
        for (int i = 0; i < poidsPingouins.size(); i++) {
            if (poidsPingouins.get(i).i > max) {
                max = poidsPingouins.get(i).i;
                coordmax = poidsPingouins.get(i).c;
            }
        }
        return new CoupleCoordonnees(myIA.myPingouins[indx].position, coordmax);
    }

    /**
     * ****************************************************************************************************
     */
    //public Coordonnees placementDificile(Banquise b) { // TODO à changer coordonnées en attributs largeur / hauteur (dans classe banquise)		

    }