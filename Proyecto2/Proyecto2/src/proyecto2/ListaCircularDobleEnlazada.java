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
public class ListaCircularDobleEnlazada extends Lista{
   
    public ListaCircularDobleEnlazada() {
	super();		
    }
	
    public void ingresar(Object objeto) {
	Nodo nuevo = new Nodo(objeto);
		
        if(ini==null) {			
            ini = nuevo;
            ini.sig=ini;
            nuevo.ant=fin;
            fin = nuevo;			
	}
	else {
            fin.sig=nuevo;
            nuevo.sig=ini;
            nuevo.ant=fin;	
            fin = nuevo;
            ini.ant=fin;
	}
    }
			
    public void eliminar(int a) {
	if(ini!=null) {
            if(a>0) {
                int cont = 1;
                Nodo aux =ini;				
		while((aux.getSig()!=ini)&& (cont<a)){
                    cont ++;
                    aux = aux.getSig();
		}
		if(cont==1) {
                    if(aux.getSig()==ini) {
			ini=null;
                    }
                    else {
			Nodo ant = aux.getAnt();
			ant.setSig(aux.getSig());
			aux=aux.getSig();
			aux.setAnt(ant);
			ini=aux;
                    }
		}
		else {
                    Nodo ant = aux.getAnt();
                    aux.setAnt(null);
                    ant.setSig(aux.getSig());
                    aux=aux.getSig();
                    aux.setAnt(ant);
		}
            }
	}
    }
	
    public void listar() {
	Nodo temp = ini;		
	do {
            System.out.println(temp.getObjeto());
            temp = temp.sig;
	}
	while(temp!=ini);
    }
    public void display() {
		
    }
}
