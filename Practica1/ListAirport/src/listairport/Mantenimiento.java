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
public class Mantenimiento {
    private boolean estatus;
    private Avion avion;
	
	public Mantenimiento(boolean estado, Avion avion) {
		this.estatus=estado;
		this.avion=avion;
	}

	public boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
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
