package model;

public abstract class Visiteur {
	// Par défaut la méthode de visite d'un composant provoque une erreur pour un visiteur. 
	// A redéfinir donc si un composant doit être visité.
    // Les autres méthodes se ramènent à la visite d'un composant
    // Les visiteurs peuvent donc aussi ne spécialiser que le traitement
    // de types d'objets spécifiques en ne redéfinissant que les méthodes
    // associées à ceux qu'ils sont censés visiter
	
	//TODO vérifier s'il faut retourner autre chose que false, puisque tout est overdrive
    public boolean visite(Partie p) {
    	return visite(p);
    }

    public boolean visite(Banquise b) {
		return visite(b);
	}
    
    public boolean visite(Joueur j) {
		return visite(j);
    }
    
    public boolean visite(Pingouin p) {
		return visite(p);
	}
    
    public boolean visite(Tuile t) {
		return visite(t);
    }
}