/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

/**
 *
 * @author tonyc
 */
public class Nodo {
        public Nodo sig;
	public Nodo ant;
	public Object objeto;
	
	public Nodo(Object objeto, Nodo ant, Nodo sig) {
		this.sig=sig;
		this.objeto=objeto;
		this.ant=ant;
	}
        
        public Nodo(Object objeto, Nodo sig) { 
		this.sig=sig;
		this.objeto=objeto;
	
        }
	
	public Nodo(Object objeto) {
		this.objeto=objeto;
		setSig(null);
	}


    /**
     * @return the sig
     */
    public Nodo getSig() {
        return sig;
    }

    /**
     * @param sig the sig to set
     */
    public void setSig(Nodo sig) {
        this.sig = sig;
    }

    /**
     * @return the ant
     */
    public Nodo getAnt() {
        return ant;
    }

    /**
     * @param ant the ant to set
     */
    public void setAnt(Nodo ant) {
        this.ant = ant;
    }

    /**
     * @return the objeto
     */
    public Object getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

}
