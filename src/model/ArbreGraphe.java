package model;

/* Classe qui sert a representer le terrain sous forme d'un graphe
 * Un noeud represente une Tuile qui a 6 noeuds qui sont les tuiles adjacentes sur la banquise
 */
public class ArbreGraphe {
    
    ArbreGraphe noeudHD; // noeud en haut a droite de this
    ArbreGraphe noeudMD; // noeud a droite de this
    ArbreGraphe noeudBD; // noeud en bas a droite de this
    ArbreGraphe noeudBG; // noeud en bas a gauche de this
    ArbreGraphe noeudMG; // noeud a gauche de this
    ArbreGraphe noeudHG; // noeud en haut a gauche de this
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