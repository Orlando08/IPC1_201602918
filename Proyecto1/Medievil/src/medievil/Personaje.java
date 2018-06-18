/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medievil;

import java.io.Serializable;

/**
 *
 * @author tonyc
 */
public class Personaje implements Serializable{
    private String personaje;
    private int atacar;
    private int alcanceAtaque;
    protected boolean quitaSiguienteTurno;

    public Personaje(String personaje, int atacar, int alcanceAtaque, boolean quitaSiguienteTurno) {
        this.personaje = personaje;
        this.atacar = atacar;
        this.alcanceAtaque = alcanceAtaque;
        this.quitaSiguienteTurno = quitaSiguienteTurno;
    }

    public boolean isQuitaSiguienteTurno() {
        return quitaSiguienteTurno;
    }

    public void setQuitaSiguienteTurno(boolean quitaSiguienteTurno) {
        this.quitaSiguienteTurno = quitaSiguienteTurno;
    }

    /**
     * @return the personaje
     */
    public String getPersonaje() {
        return personaje;
    }

    /**
     * @param personaje the personaje to set
     */
    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }

    /**
     * @return the atacar
     */
    public int getAtacar() {
        return atacar;
    }

    /**
     * @param atacar the atacar to set
     */
    public void setAtacar(int atacar) {
        this.atacar = atacar;
    }

    /**
     * @return the alcanceAtaque
     */
    public int getAlcanceAtaque() {
        return alcanceAtaque;
    }

    /**
     * @param alcanceAtaque the alcanceAtaque to set
     */
    public void setAlcanceAtaque(int alcanceAtaque) {
        this.alcanceAtaque = alcanceAtaque;
    }


}
