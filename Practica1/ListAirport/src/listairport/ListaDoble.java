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
public class ListaDoble extends Lista{
    
    public ListaDoble() {
	super();
    }
	
    @Override
    public void display() {
		
    }
    
    public void insertarFinal(Object dato) {
	if(ini ==null) {
		ini = new Nodo(dato,null,null);
		fin=ini;
	}
	else {
		Nodo nuevo = new Nodo(dato,fin,null);
		fin.setSig(nuevo);
		fin = nuevo;
	}
	tamano++;
    }
	
    public void insertarInicio(Object dato) {
	if(ini==null) {
		ini = new Nodo(dato,null,null);
		fin = ini;
	}
	else {
		Nodo nuevo = new Nodo(dato,null,ini);
		ini.setAnt(nuevo);
		ini = nuevo;
	}
	tamano++;
    }
	
    public Object obtenerComponente(int id) {
	id = tamano - id-1;
	int cont = 0;
	Nodo temp = ini;
		
	while(cont< id) {
		temp = temp.getSig();
		cont++;
	}
	return temp.getObjeto();
    }	
	
    public void eliminarComponente(int id) {
	id = tamano - id-1;
	if(id==0) {
            ini = ini.getSig();
	}
	else {
            int cont =0;
            Nodo temp = ini;
            while(cont< id-1) {
		temp = temp.getSig();
		cont++;
            }
            temp.setSig(temp.getSig());
	}
	tamano--;
    }	
}
