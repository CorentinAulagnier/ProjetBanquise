package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

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
            }else {
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
    public CoupleCoordonnees algoFacile(Partie p) {

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

        if (dir == 0) { 			// En haut a droite 
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
        } else {				// En haut a gauche
            while (nbDepl != 0) {
                dep = p.b.getHG(dep);
                nbDepl--;
            }

        }

        cc = new CoupleCoordonnees(new Coordonnees(i, j), dep);

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

    public ArbreGraphe CreationArbre(Coordonnees c, Partie p, int dir, int nbcoupsmax) { // crée l'ArbreGraphe à partir de c sur nbcoupsmax coups.

        if (nbcoupsmax == 0) {
            return null;
        }

        ArbreGraphe abr = new ArbreGraphe();
        abr.c = c;
        Tuile tuile = p.b.getTuile(c);
        abr.poids = tuile.nbPoissons;
        tuile.nbPoissons = 0;
        p.b.setTuile(c, tuile);

        Coordonnees a = new Coordonnees(c.x, c.y);
        while ((p.b.getTuile(p.b.getBG(a)) != null) && (!p.b.getTuile(p.b.getBG(a)).aUnPingouin) && (p.b.getTuile(p.b.getBG(a)).nbPoissons != 0)) {
            abr.noeudBG.add(CreationArbre(p.b.getBG(a), p, 3, nbcoupsmax - 1));
            a = p.b.getBG(a);
        }
        a = new Coordonnees(c.x, c.y);
        while (p.b.getTuile(p.b.getMG(a)) != null && (!p.b.getTuile(p.b.getMG(a)).aUnPingouin) && (p.b.getTuile(p.b.getMG(a)).nbPoissons != 0)) {
            abr.noeudMG.add(CreationArbre(p.b.getMG(a), p, 4, nbcoupsmax - 1));
            a = p.b.getMG(a);
        }
        a = new Coordonnees(c.x, c.y);
        while (p.b.getTuile(p.b.getHG(a)) != null && (!p.b.getTuile(p.b.getHG(a)).aUnPingouin) && (p.b.getTuile(p.b.getHG(a)).nbPoissons != 0)) {
            abr.noeudHG.add(CreationArbre(p.b.getHG(a), p, 5, nbcoupsmax - 1));
            a = p.b.getHG(a);
        }
        a = new Coordonnees(c.x, c.y);
        while (p.b.getTuile(p.b.getHD(a)) != null && (!p.b.getTuile(p.b.getHD(a)).aUnPingouin) && (p.b.getTuile(p.b.getHD(a)).nbPoissons != 0)) {
            abr.noeudHD.add(CreationArbre(p.b.getHD(a), p, 0, nbcoupsmax - 1));
            a = p.b.getHD(a);
        }
        a = new Coordonnees(c.x, c.y);
        while (p.b.getTuile(p.b.getMD(a)) != null && (!p.b.getTuile(p.b.getMD(a)).aUnPingouin) && (p.b.getTuile(p.b.getMD(a)).nbPoissons != 0)) {
            abr.noeudMD.add(CreationArbre(p.b.getMD(a), p, 1, nbcoupsmax - 1));
            a = p.b.getMG(a);
        }
        a = new Coordonnees(c.x, c.y);
        while (p.b.getTuile(p.b.getBD(a)) != null && (!p.b.getTuile(p.b.getBD(a)).aUnPingouin) && (p.b.getTuile(p.b.getBD(a)).nbPoissons != 0)) {
            abr.noeudBD.add(CreationArbre(p.b.getBD(a), p, 2, nbcoupsmax - 1));
            a = p.b.getBD(a);
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
            for (int i = 0; i < abr.noeudBD.size(); i++) {
                val1 = parcoursGraphe(abr.noeudBD.get(i), nbCoupsmax, nbCoups - 1, 2, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
            }
        }
        if (abr.noeudMD != null) {
            for (int i = 0; i < abr.noeudMD.size(); i++) {
                val2 = parcoursGraphe(abr.noeudMD.get(i), nbCoupsmax, nbCoups - 1, 1, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
            }
        }
        if (abr.noeudHD != null) {
            for (int i = 0; i < abr.noeudHD.size(); i++) {
                val3 = parcoursGraphe(abr.noeudHD.get(i), nbCoupsmax, nbCoups - 1, 0, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
            }
        }
        if (abr.noeudHG != null) {
            for (int i = 0; i < abr.noeudHG.size(); i++) {
                val4 = parcoursGraphe(abr.noeudHG.get(i), nbCoupsmax, nbCoups - 1, 5, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
            }
        }
        if (abr.noeudMG != null) {
            for (int i = 0; i < abr.noeudMG.size(); i++) {
                val5 = parcoursGraphe(abr.noeudMG.get(i), nbCoupsmax, nbCoups - 1, 4, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case
            }
        }
        if (abr.noeudBG != null) {
            for (int i = 0; i < abr.noeudBG.size(); i++) {
                val6 = parcoursGraphe(abr.noeudBG.get(i), nbCoupsmax, nbCoups - 1, 3, somme + abr.poids, coord1stchoice);   // On s'arrete sur la case 
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

    public CoupleCoordonnees algoMoyen(Partie p) {
        int nbcoupsrecherches = 2; // Choix du "2" très réfléchi mais modifiable.
        int max = 0;
        Coordonnees coordmax = null;
        int indx = 0;
        System.out.println(myIA.nbPingouin);
        ArrayList<CoupleCoordonneesInt> poidsPingouins = new ArrayList<>();
        for (int i = 0; i < myIA.nbPingouin; i++) {
            ArbreGraphe abr = CreationArbre(myIA.myPingouins[i].position, p, -1, nbcoupsrecherches);
            CoupleCoordonneesInt coordint = parcoursGraphe(abr, nbcoupsrecherches, nbcoupsrecherches, 6, 0, new Coordonnees(0, 0));
            poidsPingouins.add(coordint);
        }
                System.out.println("poid Pingouin size" + poidsPingouins.size());

        for (int i = 0; i < poidsPingouins.size(); i++) {
            if (poidsPingouins.get(i).i > max) {
                max = poidsPingouins.get(i).i;
                coordmax = poidsPingouins.get(i).c;
            }
        }
        System.out.println("coordmax" + coordmax);
        System.out.println("max "+max);

        
        return new CoupleCoordonnees(myIA.myPingouins[indx].position, coordmax);
    }

    /* Renvoie le numero de Joueur correspondant à l'IA
     */
    public int notreNumeroDeJoueur(Partie p) {
        int num = 0;
        while ((num != p.nbJoueurs) && (p.joueurs[num].nom.equals(myIA.nom))) {   // mettre une methode equals dans la classe abstraite Joueur ????????????????????????????
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

    public int nbPingouinActuel(Joueur j) {
        int nbPing = 0;
        while ((nbPing != j.nbPingouin) && (j.myPingouins[nbPing] != null)) {
            nbPing = nbPing + 1;
        }
        return nbPing;
    }

    /* Placement des pingouins pour l'IA difficile
     * On regarde si la banquise a une configuration speciale, par exemple :
     * 
     * Sinon on utilise le Placement Moyen 
     */
    public Coordonnees PlacementDifficile(Partie p) {

        Coordonnees retour = new Coordonnees();
        int notreNum = notreNumeroDeJoueur(p);
        int notreNbPingouin = nbPingouinActuel(p.joueurs[notreNum]);
        Coordonnees riri = p.joueurs[notreNum].myPingouins[0].position;

        if (p.nbJoueurs == 2) {
            int ennemiNum = numJoueurAdversaire(p, notreNum);
            int ennemiNbPingouin = nbPingouinActuel(p.joueurs[ennemiNum]);

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

}
