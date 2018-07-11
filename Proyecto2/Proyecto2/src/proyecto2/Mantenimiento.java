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
public class Mantenimiento {
    private boolean estatus;
    private Avion avion;
    private int numero;
	
	public Mantenimiento(int numero, boolean estado, Avion avion) {
		this.estatus=estado;
		this.avion=avion;
                this.numero = numero;
	}

	public boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
        public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero=numero;
	}

	public Avion getAvion() {
		return avion;
	}
	
	
	public void setAvion(Avion avion) {
		this.avion = avion;
	}
	
	public String getDisponible() {
		if(getEstatus()) {
			return "Disponible";
		}
		else {
			return "Ocupado";
		}
	}
}
