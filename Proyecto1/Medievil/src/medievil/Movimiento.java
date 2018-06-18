/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medievil;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author tonyc
 */
public class Movimiento extends Thread{
    
    private Jugador jugadorActual;
    
    private Vector <Vector<JLabel>> listaVidas;
    private String  [][] matrizLogica;
    private JButton [][] matrizGrafica;
    private String trayecto, turno, no_juega;
    private int nMovimientos, tam, horizontal, vertical, j, v, move=0;
    private Integer rndX, rndY, rnd;
    private int numero=1, centro;
    private ImageIcon iconoVida;
    private boolean movido = false, encontrado =false;  

    public Movimiento(String trayecto, int nMovimientos, String  [][] matrizLogica, JButton [][] matrizGrafica, 
            Vector <Vector<JLabel>> listaVidas, Jugador jugadorActual, String turno, int tam) {
        this.nMovimientos = nMovimientos;
        this.matrizLogica = matrizLogica;
        this.matrizGrafica = matrizGrafica;
        this.jugadorActual = jugadorActual;
        this.listaVidas = listaVidas;
        this.trayecto = trayecto;
        this.tam = tam;
        centro = (int) Math.round(((float)tam) /2);
        iconoVida = new ImageIcon("src/imagen/icoVida1.PNG");
        this.turno = turno;
        
        if(turno.equals("Jugador1")){
            this.turno = turno;
            this.no_juega = "Jugador2";
        }else{
            this.turno = "Jugador2";
            this.no_juega = "Jugador1";
        }
        for (int yy = 0; yy < tam; yy++) {
            for (int xx = 0; xx < tam; xx++) {
                if (matrizLogica[xx][yy].equals(turno)) {
                    horizontal = xx;
                    vertical = yy;
                }
            }
        }
    }
        public void moverDer_Izq(){

        try {
            System.out.println("____(Inicia)Hilo Mover ID");
            while(move<nMovimientos){
                if(trayecto.equals("Derecha")){
                    if(!matrizLogica[horizontal][vertical+1].equals(no_juega)){
                    //mover derecha  
                        if(matrizLogica[horizontal][vertical+1].equals("Mina")){ piezaDano(1); }
                        if(matrizLogica[horizontal][vertical+1].equals("Vida") && (jugadorActual.getVida1()<5 &&
                                jugadorActual.getVida1()>0)){ piezaVida(); }
                        
                        matrizLogica[horizontal][vertical+1] = turno;
                        matrizLogica[horizontal][vertical] = "";
                        
                        matrizGrafica[horizontal][vertical+1].setIcon(matrizGrafica[horizontal][vertical].getIcon());
                        matrizGrafica[horizontal][vertical].setIcon(new JButton().getIcon());
                        Thread.sleep(500);
                        vertical++;
                        move++;

                    }else{
                        rnd = ThreadLocalRandom.current().nextInt(0, 0 + 1);
                        if(rnd == 0 ){ 
                            System.out.println("***Inicia subHilo moviendo AR_AB con mov faltantes: "+(nMovimientos-move));
                            Movimiento movArr = new Movimiento("Arriba", (nMovimientos-move), matrizLogica, matrizGrafica, listaVidas, jugadorActual, turno, tam);
                            movArr.hilo2.start();
                            this.listaVidas = movArr.getListaVidas();
                            this.jugadorActual = movArr.getJugadorActual();
                            this.matrizLogica = movArr.getMatrizLogica();
                            this.matrizGrafica = movArr.getMatrizGrafica();
                            move=nMovimientos;
                        } else { 
                            System.out.println("***Inicia subHilo moviendo AR_AB con mov faltantes: "+(nMovimientos-move));
                            Movimiento movArr = new Movimiento("Abajo", (nMovimientos-move), matrizLogica, matrizGrafica, listaVidas, jugadorActual, turno, tam);
                            movArr.hilo2.start();
                            this.listaVidas = movArr.getListaVidas();
                            this.jugadorActual = movArr.getJugadorActual();
                            this.matrizLogica = movArr.getMatrizLogica();
                            this.matrizGrafica = movArr.getMatrizGrafica();
                            move=nMovimientos;
                        }
                    }
                }
                if(trayecto.equals("Izquierda")){
                    if(!matrizLogica[horizontal][vertical-1].equals(no_juega)){
                       
                        if(matrizLogica[horizontal][vertical-1].equals("Mina")){ piezaDano(1); }
                        if(matrizLogica[horizontal][vertical-1].equals("Vida") && (jugadorActual.getVida1()<5 &&
                                jugadorActual.getVida1()>0)){ piezaVida(); }
                      
                        matrizLogica[horizontal][vertical-1] = turno;
                        matrizLogica[horizontal][vertical] = "";
                  
                        matrizGrafica[horizontal][vertical-1].setIcon(matrizGrafica[horizontal][vertical].getIcon());
                        matrizGrafica[horizontal][vertical].setIcon(new JButton().getIcon());
                        Thread.sleep(500);
                        vertical--;
                        move++;

                    }else{
                        rnd = ThreadLocalRandom.current().nextInt(0, 0 + 1);
                        if(rnd == 0 ){ 
                            System.out.println("***Inicia subHilo moviendo AR_AB con mov faltantes: "+(nMovimientos-move));
                            Movimiento movArr = new Movimiento("Arriba", (nMovimientos-move), matrizLogica, matrizGrafica, listaVidas, jugadorActual, turno, tam);
                            movArr.hilo2.start();
                            this.listaVidas = movArr.getListaVidas();
                            this.jugadorActual = movArr.getJugadorActual();
                            this.matrizLogica = movArr.getMatrizLogica();
                            this.matrizGrafica = movArr.getMatrizGrafica();
                            move=nMovimientos;
                        } else { 
                            System.out.println("***Inicia subHilo moviendo AR_AB con mov faltantes: "+(nMovimientos-move));
                            Movimiento movAba = new Movimiento("Abajo", (nMovimientos-move), matrizLogica, matrizGrafica, listaVidas, jugadorActual, turno, tam);
                            movAba.hilo2.start();
                            this.listaVidas = movAba.getListaVidas();
                            this.jugadorActual = movAba.getJugadorActual();
                            this.matrizLogica = movAba.getMatrizLogica();
                            this.matrizGrafica = movAba.getMatrizGrafica();
                            move=nMovimientos;
                        }
                    }
                }
            }
            System.out.println("____(Termina)Hilo Mover ID");
            this.interrupt();
        }catch(Exception e){
            salioTablero(matrizGrafica[horizontal][vertical].getIcon(), horizontal, vertical);
        }
    }
       
        public void moverArr_Aba(){
            System.out.println("____(Inicia)Hilo Mover AA");
        try {
            while(move<nMovimientos){
                if(trayecto.equals("Abajo")){
                    if(!matrizLogica[horizontal+1][vertical].equals(no_juega)){
                    //mover Abajo    
                        if(matrizLogica[horizontal+1][vertical].equals("Mina")){ piezaDano(1); }
                        if(matrizLogica[horizontal+1][vertical].equals("Vida") && (jugadorActual.getVida1()<5 &&
                                jugadorActual.getVida1()>0)){ piezaVida(); }
             
                        matrizLogica[horizontal+1][vertical] = turno;
                        matrizLogica[horizontal][vertical] = "";

                        matrizGrafica[horizontal+1][vertical].setIcon(matrizGrafica[horizontal][vertical].getIcon());
                        matrizGrafica[horizontal][vertical].setIcon(new JButton().getIcon());
                        Thread.sleep(500);
                        horizontal++;
                        move++;

                    }else{
                        rnd = ThreadLocalRandom.current().nextInt(0, 0 + 1);
                        if(rnd == 0 ){ 
                            System.out.println("***Inicia subHilo moviendo IZ_DE (izquierda)con mov faltantes: "+(nMovimientos-move));
                            Movimiento movIz = new Movimiento("Izquierda", (nMovimientos-move), matrizLogica, matrizGrafica, listaVidas, jugadorActual, turno, tam);
                            movIz.hilo1.start();
                            this.listaVidas = movIz.getListaVidas();
                            this.jugadorActual = movIz.getJugadorActual();
                            this.matrizLogica = movIz.getMatrizLogica();
                            this.matrizGrafica = movIz.getMatrizGrafica();
                            move=nMovimientos;
                        } else { 
                            System.out.println("***Inicia subHilo moviendo IZ_DE (derecha) con mov faltantes: "+(nMovimientos-move));
                            Movimiento movDer = new Movimiento("Derecha", (nMovimientos-move), matrizLogica, matrizGrafica, listaVidas, jugadorActual, turno, tam);
                            movDer.hilo1.start();
                            this.listaVidas = movDer.getListaVidas();
                            this.jugadorActual = movDer.getJugadorActual();
                            this.matrizLogica = movDer.getMatrizLogica();
                            this.matrizGrafica = movDer.getMatrizGrafica();
                            move=nMovimientos;
                        }
                    }
                }
                if(trayecto.equals("Arriba")){
                    if(!matrizLogica[horizontal-1][vertical].equals(no_juega)){
                     //Mover Arriba   
                        if(matrizLogica[horizontal-1][vertical].equals("Mina")){ piezaDano(1); }
                        if(matrizLogica[horizontal-1][vertical].equals("Vida") && (jugadorActual.getVida1()<5 &&
                                jugadorActual.getVida1()>0)){ piezaVida(); }
                   
                        matrizLogica[horizontal-1][vertical] = turno;
                        matrizLogica[horizontal][vertical] = "";
             
                        matrizGrafica[horizontal-1][vertical].setIcon(matrizGrafica[horizontal][vertical].getIcon());
                        matrizGrafica[horizontal][vertical].setIcon(new JButton().getIcon());
                        Thread.sleep(500);
                        horizontal--;
                        move++;

                    }else{
                        rnd = ThreadLocalRandom.current().nextInt(0, 0 + 1);
                        if(rnd == 0 ){ 
                            System.out.println("***Inicia subHilo moviendo IZ_DE (izquierda)con mov faltantes: "+(nMovimientos-move));
                            Movimiento movIzq = new Movimiento("Izquierda", (nMovimientos-move), matrizLogica, matrizGrafica, listaVidas, jugadorActual, turno, tam);
                            movIzq.hilo1.start();
                            this.listaVidas = movIzq.getListaVidas();
                            this.jugadorActual = movIzq.getJugadorActual();
                            this.matrizLogica = movIzq.getMatrizLogica();
                            this.matrizGrafica = movIzq.getMatrizGrafica();
                            move=nMovimientos;
                        } else { 
                            System.out.println("***Inicia subHilo moviendo IZ_DE (derecha) con mov faltantes: "+(nMovimientos-move));
                            Movimiento movDer = new Movimiento("Derecha", (nMovimientos-move), matrizLogica, matrizGrafica, listaVidas, jugadorActual, turno, tam);
                            movDer.hilo1.start();
                            this.listaVidas = movDer.getListaVidas();
                            this.jugadorActual = movDer.getJugadorActual();
                            this.matrizLogica = movDer.getMatrizLogica();
                            this.matrizGrafica = movDer.getMatrizGrafica();
                            move=nMovimientos;
                        }
                    }
                }
            }
            System.out.println("____(Termina)Hilo Mover AA");
            this.interrupt();
        }catch(Exception e){
            salioTablero(matrizGrafica[horizontal][vertical].getIcon(), horizontal, vertical);
        }
        }
    Thread hilo1 = new Thread(){    
    public void run(){
        moverDer_Izq();
    }
    };
    Thread hilo2 = new Thread(){
        public void run(){
            moverArr_Aba();
        }
        
    };

    /**
     * @return the jugadorActual
     */
    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    /**
     * @return the listaVidas
     */
    public Vector <Vector<JLabel>> getListaVidas() {
        return listaVidas;
    }

    /**
     * @return the matrizLogica
     */
    public String[][] getMatrizLogica() {
        return matrizLogica;
    }

    /**
     * @return the matrizGrafica
     */
    public JButton[][] getMatrizGrafica() {
        return matrizGrafica;
    }
    public void salioTablero (Icon icono, int fi, int co){
        //Genera posicion centro
        int reux = centro, reuy =centro, cPro=0;
        
        boolean ponerJuga1 = true, ponerJuga2 = false;
        
        if(turno.equals("Jugador1")){
            ponerJuga1 = false;
            ponerJuga2 = true;
        }
        
        while(!ponerJuga1 || !ponerJuga2){
            if(matrizLogica[reux][reuy].equals("")){
                if(!ponerJuga1){
                    matrizLogica[fi][co] = "";
                    matrizGrafica[fi][co].setIcon(new JButton().getIcon());
                    matrizLogica[reux][reuy] = "Jugador1";
                    matrizGrafica[reux][reuy].setIcon(icono);
                    ponerJuga1 = true;
                }else if(!ponerJuga2){
                    matrizLogica[fi][co] = "";
                    matrizGrafica[fi][co].setIcon(new JButton().getIcon());
                    matrizLogica[reux][reuy] = "Jugador2";
                    matrizGrafica[reux][reuy].setIcon(icono);
                    ponerJuga2 = true;
                }
            }else{
                if(!encontrado){
                    for(int probFila = (centro-numero); probFila <= (centro+numero); probFila++){
                        if(!encontrado){
                            for(int probCol = (centro-numero); probCol <= (centro+numero); probCol++){
                                if(matrizLogica[probFila][probCol].equals("")){
                                    reux = probFila;
                                    reuy = probCol;
                                    cPro++;
                                    encontrado = true;
                                    break;
                                }else{
                                    cPro++;
                                }
                            }
                        }
                    }
                    if(numero==1 && cPro==8){ numero++; cPro=0; }
                    if(numero==2 && cPro==16){ numero++; cPro=0; }
                    if(numero==3 && cPro==33){ numero++; cPro=0; }
                }
            }
        }
        piezaDano(1);
    }
 public void piezaDano (int tipoDano){
        if(turno.equals("Jugador1")){
            //Resta vida - LOGICO
            jugadorActual.setVida1(jugadorActual.getVida1()-tipoDano);
            j=0; v=jugadorActual.getVida1(); 
        }else{
            //Resta vida - LOGICO
            jugadorActual.setVida2(jugadorActual.getVida2()-tipoDano);
            j=1; v=jugadorActual.getVida2(); 
        }
        //Resta vida - GRAFICO
        if(v==4){
            listaVidas.get(j).get(4).setIcon(new JButton().getIcon());
        }
        if(v==3){
            listaVidas.get(j).get(4).setIcon(new JButton().getIcon());
            listaVidas.get(j).get(3).setIcon(new JButton().getIcon());
        }
        if(v==2){
            listaVidas.get(j).get(4).setIcon(new JButton().getIcon());
            listaVidas.get(j).get(3).setIcon(new JButton().getIcon());
            listaVidas.get(j).get(2).setIcon(new JButton().getIcon());
        }
        if(v==1){
            listaVidas.get(j).get(4).setIcon(new JButton().getIcon());
            listaVidas.get(j).get(3).setIcon(new JButton().getIcon());
            listaVidas.get(j).get(2).setIcon(new JButton().getIcon());
            listaVidas.get(j).get(1).setIcon(new JButton().getIcon());
        }
        if(v==0 || v<0){
            if(j==0){
                jugadorActual.setVida1(0);
            }else{
                jugadorActual.setVida2(0);
            }
        }
    }
    
    public void piezaVida (){
        if(turno.equals("Jugador1")){
            //Suma vida - LOGICO
            jugadorActual.setVida1(jugadorActual.getVida1()+1);
            j=0; v=jugadorActual.getVida1(); 
        } else {
            //Suma vida - LOGICO
            jugadorActual.setVida2(jugadorActual.getVida2()+1);
            j=1; v=jugadorActual.getVida2(); 
        }
        //Suma vida - GRAFICO
        if(v==1){
            listaVidas.get(j).get(0).setIcon(iconoVida);
        }
        if(v==2){
            listaVidas.get(j).get(1).setIcon(iconoVida);
            listaVidas.get(j).get(0).setIcon(iconoVida);
        }
        if(v==3){
            listaVidas.get(j).get(2).setIcon(iconoVida);
            listaVidas.get(j).get(1).setIcon(iconoVida);
            listaVidas.get(j).get(0).setIcon(iconoVida);
        }
        if(v==4){
            listaVidas.get(j).get(3).setIcon(iconoVida);
            listaVidas.get(j).get(2).setIcon(iconoVida);
            listaVidas.get(j).get(1).setIcon(iconoVida);
            listaVidas.get(j).get(0).setIcon(iconoVida);
        }
    }    
    
}