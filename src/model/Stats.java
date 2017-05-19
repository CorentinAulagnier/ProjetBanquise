package model;

public class Stats {
	
	int nbPoissons;
	int nbTuiles;
	int nbVictoires;
	
	public Stats() {
		this.nbPoissons = 0;
		this.nbTuiles = 0;
		this.nbVictoires = 0;
	}
	
	public String toString() {
		return "\tpoissons : "+this.nbPoissons+"\ttuiles : "+this.nbTuiles+"\tvictoires : "+this.nbVictoires;
	}
}
