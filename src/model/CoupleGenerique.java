package model;

public class CoupleGenerique <T1,T2> {
	
	T1 e1;
	T2 e2;
	
	CoupleGenerique (T1 first, T2 second) {
		this.e1 = first;
		this.e2 = second;
	}
	
	/**
	 * Verifie si this est identique au CoupleGenerique en parametre.
	 * 
	 * @param obj
	 *            Le CoupleGenerique a tester.
	 *            
	 * @return Vrai si this est identique au CoupleGenerique en parametre.
	 */
	
    public boolean equals(CoupleGenerique obj) {
        return (this.e1.equals(obj.e1) && this.e2.equals(obj.e2));
    }

}
