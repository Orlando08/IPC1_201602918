/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medievil;

import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author tonyc
 */
public class Temporizador extends Thread{
    private int finJuego, tiempoReal, segundoActual;   
    private Configuracion tempo;
    private Vector<Resultado> historial;
    private JFrame interfaz;
    private boolean gana;
    private Jugador juegoActual;
    private JLabel tiempo;
    
    public Temporizador(JLabel tiempo, JFrame interfaz, Vector<Resultado> historial, Jugador juegoActual) {
        finJuego = 0;
        tiempoReal = Integer.parseInt(tempo.tiempo.getText());
        segundoActual = 0;
        this.tiempo = tiempo;
        this.interfaz = interfaz;
        this.historial = historial;
        this.juegoActual = juegoActual;
        gana=true;
    }
        
    public void run(){
        try {
            while(tiempoReal>finJuego){
                if(juegoActual.getVida1()>0 && juegoActual.getVida2()>0){
                    tiempoReal=tiempoReal-1;

                    tiempo.setText(""+tiempoReal);
                    Thread.sleep(1000);
                }else{
                    //si entra aca un jugador tiene puntaje 0, mataron a sus personajes
                    //No gano por que se acabara el tiempo
                    gana = false;
                    tiempoReal=finJuego;
                }
            }
            System.out.println("INICIA PROCESO DE FINALIZAR TODO");
            JOptionPane.showMessageDialog(null,"Juego terminado");
            interfaz.dispose();
            System.out.println("Hilo "+this.getName()+" TiempoJuego ha finalizado");
            
            Resultado resumen;
            
            int tiempoSeg;
            
            try {
                //String parts = tiempo.getText().toString();
                //tiempoSeg = (Integer.parseInt(parts)*60)+Integer.parseInt(parts);
                
                tiempoSeg = Integer.parseInt(tempo.tiempo.getText());
            } catch (NumberFormatException eee){
                tiempoSeg = 5;
            }

            if(gana){
                resumen = new Resultado(juegoActual, tiempo.getText(), tiempoSeg, true);
            }else{
                resumen = new Resultado(juegoActual, tiempo.getText(), tiempoSeg, false);
            }
            
            if(resumen.getJuegoResult().getVida1()<0){
                resumen.getJuegoResult().setVida1(0);
            }
            if(resumen.getJuegoResult().getVida2()<0){
                resumen.getJuegoResult().setVida2(0);
            }
            
            //Se agrega al vector y ordena, si es >10 el tam se quita el ultimo
            historial.add(resumen);
            historial = metodoBurbuja_menorAmayor(historial);
            if(historial.size()>10){
                historial.remove(10);
            }
            JuegoFinalizado juegofinalizado = new JuegoFinalizado(historial, resumen, juegoActual);
            juegofinalizado.setVisible(true);
            this.interrupt();
        }catch(Exception e){}
       
    }
    public Vector<Resultado> metodoBurbuja_menorAmayor( Vector<Resultado> historial ){
        int i;
        boolean flag = true;
        Resultado temp;
 
        while(flag){
            flag = false;  
            for( i=0;  i < historial.size() -1;  i++ ){
                if(historial.get(i).getTiempoJuego()> historial.get(i+1).getTiempoJuego()){
                    temp = historial.get(i);
                    historial.set(i, historial.get(+1));
                    historial.set(i+1, temp);
                    flag = true; 
                } 
            } 
        } 
        return historial;
    }
    
    public JLabel getTiempo() {
        return tiempo;
    }

    public void setTiempo(JLabel tiempo) {
        this.tiempo = tiempo;
    }

    public Temporizador(Jugador jugadorActual) {
        this.juegoActual = jugadorActual;
    }
    
}
