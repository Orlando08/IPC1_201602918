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
public class Cola extends Lista{
    int tam=tamano;
	
    public Cola() {
        super();		
	tam=tamano;
    }
		
    public void display() {
		
    }
        
    public Object inicioCola() {
	if(ini==null) {
	
        return null;
        }		
	return ini.getObjeto();
			
    }
			
    public void insertar(Object dato) {
		
	if(estaVacia()) {
            ini = new Nodo(dato);
            fin=ini;
	}
	else {
            fin = fin.sig = new Nodo(dato);
	}		
	tam++;
    }
	
    public Object quitar() {
	if(estaVacia()) {
        
            return null;
	}		
	
        Object aux = ini.getObjeto();
	ini = ini.sig;
	tam--; 
	return aux;
    }
	
    public Object obtenerComponente(int id) {
	id = tam-id-1;
	int cont =0;
	Nodo temp = ini;
		
        while(cont<id) {
            temp = temp.getSig();
            cont++;
	}
	return temp.getObjeto();
    }
	
    public int tamanoCola() {
	return tam;
    }
}
