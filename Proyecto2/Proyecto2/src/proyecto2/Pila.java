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
public class Pila extends Lista{
    private int tam;
	
	public Pila() {
		super();
		tam = 0;
	}
	
	public void display() {
		
	}
	
	public int getTam() {
		return tam;
	}
	
	public void insertar(Object o) {
		Nodo nuevo = new Nodo(o);
		if(estaVacia()) {
			ini = nuevo;			
		}
		else {
			nuevo.setSig(ini);
			ini = nuevo;
		}
		tam++;
	}
	
	public void eliminar() {
		if(!estaVacia()) {
			ini = ini.getSig();
			tam--;
		}
	}
}
