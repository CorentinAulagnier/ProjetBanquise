package model;

import java.util.ArrayList;

/* Classe qui sert a representer le terrain sous forme d'un graphe
 * Un noeud represente une Tuile qui a 6 noeuds qui sont les tuiles adjacentes sur la banquise
 */
public class ArbreGraphe {
    
    ArrayList<ArbreGraphe>[] noeudHD; // liste des noeuds en haut a droite de this
    ArrayList<ArbreGraphe>[] noeudMD; // liste des noeuds a droite de this
    ArrayList<ArbreGraphe>[] noeudBD; // liste des noeuds en bas a droite de this
    ArrayList<ArbreGraphe>[] noeudBG; // liste des noeuds en bas a gauche de this
    ArrayList<ArbreGraphe>[] noeudMG; // liste des noeuds a gauche de this
    ArrayList<ArbreGraphe>[] noeudHG; // liste des noeuds en haut a gauche de this
    Coordonnees c;  // Coordonnees du noeud sur le terrain
    int poids;      // Nombre de poissons presents sur la tuile
    boolean estFeuille;
    
    public ArbreGraphe(int nbtab) {
        
        this.noeudHD = new ArrayList[nbtab];
        this.noeudMD = new ArrayList[nbtab];
        this.noeudBD = new ArrayList[nbtab];
        this.noeudBG = new ArrayList[nbtab];
        this.noeudMG = new ArrayList[nbtab];
        this.noeudHG = new ArrayList[nbtab];
        
        for(int i = 0; i<nbtab;i++){
        this.noeudHD[i] = new ArrayList<>();
        this.noeudMD[i] = new ArrayList<>();
        this.noeudBD[i] = new ArrayList<>();
        this.noeudBG[i] = new ArrayList<>();
        this.noeudMG[i] = new ArrayList<>();
        this.noeudHG[i] = new ArrayList<>();
        }
        this.poids = 0;
        this.estFeuille = false;
    }
    
    
}