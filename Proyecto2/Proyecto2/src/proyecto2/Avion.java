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
public class Avion {
        private String tipo;
	private int turnosDesabordaje,turnosMantenimiento,numero;
	private int pasajeros;
	
	public Avion(int numero, String tipo, int pasajeros, int turnosDesabordaje, int turnosMantenimiento) {
		this.numero=numero;
		this.tipo=tipo;
		this.pasajeros=pasajeros;
		this.turnosDesabordaje=turnosDesabordaje;
		this.turnosMantenimiento=turnosMantenimiento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero=numero;
	}
	
	public int getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(int pasajeros) {
		this.pasajeros = pasajeros;
	}

	public int getTurnosDesabordaje() {
		return turnosDesabordaje;
	}

	public void setTurnosDesabordaje(int turnosDesabordaje) {
		this.turnosDesabordaje = turnosDesabordaje;
	}

	public int getTurnosMantenimiento() {
		return turnosMantenimiento;
	}

	public void setTurnosMantenimiento(int turnosMantenimiento) {
		this.turnosMantenimiento = turnosMantenimiento;
	}
	
	public String toString() {
		return "\n	AVION: " + numero +"\n	TIPO: " + tipo + "\n	PASAJEROS: " + pasajeros  + "\n	TURNOS DESABORDAJE: " + turnosDesabordaje + "\n	TURNOS MANTENIMIENTO " + turnosMantenimiento;
	}
}
