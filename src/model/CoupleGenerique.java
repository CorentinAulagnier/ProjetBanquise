package model;

import java.io.Serializable;

public class CoupleGenerique<T1, T2> implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public T1 e1;
    public T2 e2;

    public CoupleGenerique(T1 first, T2 second) {
        this.e1 = first;
        this.e2 = second;
    }

    public CoupleGenerique(CoupleGenerique<T1, T2> c) {

        this.e1 = (T1) c.e1;
        this.e2 = (T2) c.e2;
    }

    /**
     * Verifie si this est identique au CoupleGenerique en parametre.
     *
     * @param obj Le CoupleGenerique a tester.
     *
     * @return Vrai si this est identique au CoupleGenerique en parametre.
     */
    public boolean equals(CoupleGenerique<T1, T2> obj) {
        return (this.e1.equals(obj.e1) && this.e2.equals(obj.e2));
    }

}
