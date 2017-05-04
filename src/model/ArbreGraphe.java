package model;

public class ArbreGraphe {
    
    ArbreGraphe noeudHD;
    ArbreGraphe noeudMD;
    ArbreGraphe noeudBD;
    ArbreGraphe noeudBG;
    ArbreGraphe noeudMG;
    ArbreGraphe noeudHG;
    Coordonnees c;
    int poids;
    
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