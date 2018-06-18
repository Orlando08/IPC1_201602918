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
public class Resultado implements Serializable{
    private Jugador juegoResult;
    private Configuracion tempo;
    private String tiempo;
    private int tiempoJuego;
    private Boolean finTiempo;
    private String resultado;

    public Resultado(Jugador juegoResult, String tiempo, int tiempoJuego, Boolean finTiempo) {
        this.juegoResult = juegoResult;
        this.tiempo = tiempo;
        this.finTiempo = finTiempo;
        
        if(finTiempo){
            if(juegoResult.getVida1()>juegoResult.getVida2()){
                this.resultado = "Ganador: "+juegoResult.getNombrejugador1();
                this.tiempoJuego = juegoResult.getVida1();
            }else if (juegoResult.getVida2()>juegoResult.getVida1()){
                this.resultado = "Ganador: "+juegoResult.getNombreJugador2();
                this.tiempoJuego = juegoResult.getVida2();
            }else{
                this.resultado = "Empate";
                this.tiempoJuego = 300;
            }
            this.tiempo = tempo.tiempo.getText();
        }else{
            //Sino se acabo el tiempo significa que alguien mato todos los personajes de alguien
            if(juegoResult.getVida1()>juegoResult.getVida2()){
                this.resultado = "Ganador: "+juegoResult.getNombrejugador1();
            }else if (juegoResult.getVida2()>juegoResult.getVida1()){
                this.resultado = "Ganador: "+juegoResult.getNombreJugador2();
            }else{
                this.resultado = "Empate";
            }
            this.tiempo = tiempo;
            this.tiempoJuego = tiempoJuego;
        }
    }

    /**
     * @return the juegoResult
     */
    public Jugador getJuegoResult() {
        return juegoResult;
    }

    /**
     * @param juegoResult the juegoResult to set
     */
    public void setJuegoResult(Jugador juegoResult) {
        this.juegoResult = juegoResult;
    }

    /**
     * @return the tempo
     */
    public Configuracion getTempo() {
        return tempo;
    }

    /**
     * @param tempo the tempo to set
     */
    public void setTempo(Configuracion tempo) {
        this.tempo = tempo;
    }

    /**
     * @return the tiempo
     */
    public String getTiempo() {
        return tiempo;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * @return the tiempoJuego
     */
    public int getTiempoJuego() {
        return tiempoJuego;
    }

    /**
     * @param tiempoJuego the tiempoJuego to set
     */
    public void setTiempoJuego(int tiempoJuego) {
        this.tiempoJuego = tiempoJuego;
    }

    /**
     * @return the finTiempo
     */
    public Boolean getFinTiempo() {
        return finTiempo;
    }

    /**
     * @param finTiempo the finTiempo to set
     */
    public void setFinTiempo(Boolean finTiempo) {
        this.finTiempo = finTiempo;
    }

    /**
     * @return the resultado
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
