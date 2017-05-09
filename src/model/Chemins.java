package model;

import java.util.ArrayList;

public class Chemins {

	public ArrayList<Coordonnees> hautDroit;
	public ArrayList<Coordonnees> milieuDroit;
	public ArrayList<Coordonnees> basDroit;
	public ArrayList<Coordonnees> basGauche;
	public ArrayList<Coordonnees> milieuGauche;
	public ArrayList<Coordonnees> hautGauche;
	
	Chemins(Partie p, Coordonnees c) {
		hautDroit = p.b.construireChemin(x -> p.b.getHD(x),c);
		milieuDroit = p.b.construireChemin(x -> p.b.getMD(x),c);
		basDroit = p.b.construireChemin(x -> p.b.getBD(x),c);
		basGauche = p.b.construireChemin(x -> p.b.getBG(x),c);
		milieuGauche = p.b.construireChemin(x -> p.b.getMG(x),c);
		hautGauche = p.b.construireChemin(x -> p.b.getHG(x),c);
	}
	
	public String toString() {
		String s="Coordonnées accessibles:\n";
		s+=stringChemin(hautDroit, "haut droit", 1)+"\n";
		s+=stringChemin(milieuDroit, "milieu droit", 2)+"\n";
		s+=stringChemin(basDroit, "bas droit", 3)+"\n";
		s+=stringChemin(basGauche, "bas gauche", 4)+"\n";
		s+=stringChemin(milieuGauche, "milieu gauche", 5)+"\n";
		s+=stringChemin(hautGauche, "haut gauche", 6)+"\n";
		return s;
	}
	
	public String stringChemin(ArrayList<Coordonnees> array, String nomAxe, int numAxe) {
		String s = String.valueOf(numAxe)+") Axe "+nomAxe;
		for(int i =0; i< array.size(); i++) {
			s+="\t"+String.valueOf(i+1)+": "+array.get(i).toString();
		}
		return s;
	}
	
}