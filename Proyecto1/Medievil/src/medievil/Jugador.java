/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medievil;

import java.util.Vector;

/**
 *
 * @author tonyc
 */
public class Jugador {
    private String nombrejugador1;
    private String nombrejugador2;
    private int vida1;
    private int vida2;
    private int duracion;
    private Vector<Personaje> personajes1;
    private Vector<Personaje> personajes2;
    
    public Jugador(){
        
    }
    public Jugador(String nombrejugador1, String nombrejugador2){
        this.nombrejugador1 = nombrejugador1;
        this.nombrejugador2 = nombrejugador2;
        this.vida1 = 5;
        this.vida2 = 5;
        
    }

    /**
     * @return the nombrejugador1
     */
    public String getNombrejugador1() {
        return nombrejugador1;
    }

    /**
     * @param nombrejugador1 the nombrejugador1 to set
     */
    public void setNombrejugador1(String nombrejugador1) {
        this.nombrejugador1 = nombrejugador1;
    }

    /**
     * @return the nombreJugador2
     */
    public String getNombreJugador2() {
        return nombrejugador2;
    }

    /**
     * @param nombreJugador2 the nombreJugador2 to set
     */
    public void setNombrejugador2(String nombreJugador2) {
        this.nombrejugador2 = nombreJugador2;
    }

    /**
     * @return the vida1
     */
    public int getVida1() {
        return vida1;
    }

    /**
     * @param vida1 the vida1 to set
     */
    public void setVida1(int vida1) {
        this.vida1 = vida1;
    }

    /**
     * @return the vida2
     */
    public int getVida2() {
        return vida2;
    }

    /**
     * @param vida2 the vida2 to set
     */
    public void setVida2(int vida2) {
        this.vida2 = vida2;
    }

    /**
     * @return the duracion
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the personajes1
     */
    public Vector<Personaje> getPersonajes1() {
        return personajes1;
    }

    /**
     * @param personajes1 the personajes1 to set
     */
    public void setPersonajes1(Vector<Personaje> personajes1) {
        this.personajes1 = personajes1;
    }

    /**
     * @return the personajes2
     */
    public Vector<Personaje> getPersonajes2() {
        return personajes2;
    }

    /**
     * @param personajes2 the personajes2 to set
     */
    public void setPersonajes2(Vector<Personaje> personajes2) {
        this.personajes2 = personajes2;
    }

    
}
