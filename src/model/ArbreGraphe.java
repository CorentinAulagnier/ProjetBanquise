package model;

import java.util.ArrayList;

/* Classe qui sert a representer le terrain sous forme d'un graphe
 * Un noeud represente une Tuile qui a 6 noeuds qui sont les tuiles adjacentes sur la banquise
 */
public class ArbreGraphe {
    
    ArrayList<ArbreGraphe> noeudHD; // liste des noeuds en haut a droite de this
    ArrayList<ArbreGraphe> noeudMD; // liste des noeuds a droite de this
    ArrayList<ArbreGraphe> noeudBD; // liste des noeuds en bas a droite de this
    ArrayList<ArbreGraphe> noeudBG; // liste des noeuds en bas a gauche de this
    ArrayList<ArbreGraphe> noeudMG; // liste des noeuds a gauche de this
    ArrayList<ArbreGraphe> noeudHG; // liste des noeuds en haut a gauche de this
    Coordonnees c;  // Coordonnees du noeud sur le terrain
    int poids;      // Nombre de poissons presents sur la tuile
    
    public ArbreGraphe() {
        this.noeudHD = null;
        this.noeudMD = null;
        this.noeudBD = null;
        this.noeudBG = null;
        this.noeudMG = null;
        this.noeudHG = null;
        this.poids = 0;
    }
    
    
}