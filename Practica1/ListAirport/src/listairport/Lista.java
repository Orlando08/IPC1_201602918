/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listairport;

/**
 *
 * @author tonyc
 */
public abstract class Lista {
    
	public Nodo ini, fin;
	int tamano;
	
	public Lista() {
		ini = fin = null;
		tamano = 0;
	}
        
        public abstract void display();
        
        public boolean estaVacia() {
		return null == ini;
	}
	
	public void insertarAlFinal(Object o) {
		if(estaVacia()) {
			ini = new Nodo(o);
			fin =ini;
		}
		else {
			fin = fin.sig = new Nodo(o);
		}
		tamano++;
	}
	
	public Object removerAlInicio() {
		Object aux = ini.getObjeto();
		ini = ini.sig;
		if(ini==null) {
			fin=null;
		}		
		tamano--;
		return aux;
	}
	
	public void listar() {
		Nodo temp = ini;
		while(temp!=null) {
			System.out.println(temp.getObjeto());
			temp = temp.sig;
		}
	}
	
	public int getTamano() {
		return tamano;
        }
    
}
