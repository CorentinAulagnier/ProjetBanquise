package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;

public class Historique implements Serializable {
	
	public Stack<Partie> undo;
	public Stack<Partie> redo;
	
	/**
	 * Constructeur
	 */
	
	public Historique() {
		undo = new Stack<Partie>();
		redo = new Stack<Partie>();
	}

/*******************************************************************************************************/
	
	/**
	 * Annuler le dernier coup joue.
	 * 
	 * @param old
	 *            partie ou il faut annuler le coup.
	 *           
	 * @return la Partie au sommet de la pile undo i.e. dernier coup joue
	 */
	
	public Partie annuler(Partie old) {
		if (!undo.empty()){
			Partie p = undo.pop().clone();
			redo.push(old);
			return p;
		}
		return null;
	}
	
	/**
	 * Refaire le dernier coup annule.
	 * 
	 * @param old
	 *            partie ou il faut annuler le coup.
	 *           
	 * @return la Partie au sommet de la pile redo i.e. dernier coup annule
	 */
	
	public Partie retablir(Partie old) {
		if (!redo.empty()){
			Partie p = redo.pop().clone();
			undo.push(old);
			return p;
		}
		return null;
	}

/*******************************************************************************************************/
	
	/**
	 * Sauvegarde la partie en cours.
	 * 
	 * @param name
	 *            Nom du fichier de sauvegarde
	 */
	
	public void sauvegarder(String name) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name+".historique"));
			oos.writeObject(this);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Charge la partie en parametre.
	 * 
	 * @param name
	 *            Nom du fichier a recuperer
	 */
	
	public void charger(String name) {
		try {
			System.out.println("name :" + name);
			ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(name)) ;		
			Historique h = (Historique)ois.readObject() ;
			this.undo = h.undo;
			this.redo = h.redo;
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/*******************************************************************************************************/
	
	/**
	 * Verifie le joueur actif peut effectuer l'action "annuler"
	 * 
	 * @return vrai si le joueur actif peut annuler
	 */
	
	public boolean peutAnnuler() {
		return (!undo.empty());
	}
	
	/**
	 * Verifie le joueur actif peut effectuer l'action "retablir"
	 * 
	 * @return vrai si le joueur actif peut retablir la dernière action annulee
	 */
	
	public boolean peutRefaire() {
		return (!redo.empty());
	}

/*******************************************************************************************************/
	
	/**
	 * Affichage.
	 *      
	 * @return Un affichage de l'historique de cette partie.
	 */
	
	public String toString() {
		String s = "Undo :\n";
		for(Partie p : undo) {
			s+=p;
		}
		s = "Redo :\n";
		for(Partie p : redo) {
			s+=p;
		}
		return s;
	}
}
