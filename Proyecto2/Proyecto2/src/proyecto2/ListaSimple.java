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
public class ListaSimple extends Lista{

    public ListaSimple() {
		super();
    }
	
    public void insertarInicio(Object dato) {
	Nodo nodo = new Nodo(dato,ini);		
	ini = nodo;
            if(fin == null) {
		fin = ini;
            }		
            tamano++;
    }	
	
    public Object getComponente(int id) {
	id = tamano - id - 1;
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
		ini = ini.sig;
            }
            else {
                int cont =0;
		Nodo temp = ini;
                while(cont< id-1) {
                    temp = temp.sig;
                    cont++;
		}
		temp.setSig(temp.sig.sig);
            }
	tamano--;
    }
        
    @Override
    public void display() {
        
    }
    
}
