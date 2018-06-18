/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medievil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author tonyc
 */
public class areaJuego extends javax.swing.JFrame {
    private String  [][] matrizLogica;
    private JButton [][] matrizGrafica;
    private Temporizador temporizador;
    private int tam, valor_dado, turno;
    private Jugador nuevojug;
    private Vector<Resultado> historial;
    private Vector <Vector<JLabel>> listaVidas = new Vector<>();
    private Vector<JLabel> vidasJ1 = new Vector<>(), vidasJ2 = new Vector<>();
    private ImageIcon iconoFondo, guerrero1, princesa1, mago1, jugador1, jugador2, 
            guerrero2, princesa2, mago2;
    private String personaje1, personaje2, imagenGue = "src/Imagenes/guerrero.PNG", 
            imagenPrin = "src/Imagenes/princesa.PNG", imagenMag = "src/Imagenes/mago.PNG",
            imagenGue1 = "src/Imagenes/guerrero1.PNG", imagenPrin1 = "src/Imagenes/princesa1.PNG", 
            imagenMag1 = "src/Imagenes/mago1.PNG";
    private Integer aleatoriox; 
    private Integer aleatorioy, veces=0;
    private boolean iniciar = true, tiraDado=false;
    private Color jugaTurno;

    /**
     * Creates new form areaJuego
     */
    public areaJuego(Jugador nuevojug,Vector<Resultado> historial, int dimensiones) {
        initComponents();
        this.tam = dimensiones;
        this.nuevojug = nuevojug;
        this.historial = historial;
        
        temporizador = new Temporizador(jLabel1, this, historial, nuevojug);
        temporizador.start();
        
        jLabel1=temporizador.getTiempo();      
        
        guerrero1 = new ImageIcon(imagenGue);
        princesa1 = new ImageIcon(imagenPrin);
        mago1 = new ImageIcon(imagenMag);
        
        guerrero2 = new ImageIcon(imagenGue1);
        princesa2 = new ImageIcon(imagenPrin1);
        mago2 = new ImageIcon(imagenMag1);
        
        jLabel6.setText("Jugador 1:"+nuevojug.getNombrejugador1());
        jLabel7.setText("1."+nuevojug.getPersonajes1().get(0).getPersonaje());
        jLabel8.setText("2."+nuevojug.getPersonajes1().get(1).getPersonaje());
        jLabel9.setText("3."+nuevojug.getPersonajes1().get(2).getPersonaje());
        jLabel10.setText("Jugador 2:"+nuevojug.getNombreJugador2());
        jLabel11.setText("1."+nuevojug.getPersonajes2().get(0).getPersonaje());
        jLabel12.setText("2."+nuevojug.getPersonajes2().get(1).getPersonaje());
        jLabel13.setText("3."+nuevojug.getPersonajes2().get(2).getPersonaje());
        
        vidasJ1.add(vida1);vidasJ1.add(vida2);
        vidasJ1.add(vida3);vidasJ1.add(vida4);
        vidasJ1.add(vida5);vidasJ2.add(vida11);
        vidasJ2.add(vida21);vidasJ2.add(vida31);
        vidasJ2.add(vida41);vidasJ2.add(vida51);
        
        listaVidas.add(vidasJ1);
        listaVidas.add(vidasJ2);  
        
        jugaTurno = new Color(64,224,208);
        jLabel6.setForeground(jugaTurno); 
        turno=1;
        
        crearTablero();           
        colocarAumyDism();
        colocarPersonaje();
    }

    public void crearTablero(){
        matrizGrafica = new JButton[tam][tam];
        matrizLogica = new String[tam][tam];

        JPanel buttonPane = new JPanel(new GridLayout(tam, tam));
        buttonPane.setBorder(new LineBorder(Color.BLACK));
        buttonPane.setOpaque(false);
        int columna = 0;
        int fila = 0;

        for (int i = 0; i < (tam*tam); i++) {
            //Logica de arreglo
            JButton b = new JButton();
            b.setToolTipText(""+i);
            b.setPreferredSize(new Dimension(25, 25));
            b.setToolTipText(""+fila+"-"+columna);
            b.setContentAreaFilled(false);
            b.setBorder(new LineBorder(Color.BLACK));
            //Se agrega el boton a ListaBotones
            matrizGrafica[fila][columna] = b;
            //Se agrega el valor del boton a ListaLogicaBotones
            matrizLogica[fila][columna] = "";
            if(columna==(tam-1)){
                columna = 0;
                fila++;
            }else{
                columna++;
            }
        }
        //Agrega ListaBotones al PanelBotones
        for(int ar = 0; ar<tam; ar++){
            for(int cl = 0; cl<tam; cl++){
                buttonPane.add(matrizGrafica[ar][cl]);
            }
        }
        pnlTablero.add(buttonPane);
        pnlTablero.setOpaque(false);
    }
    
    public void dado(){
        Random rm = new Random();
        
        int numero = (int)(rm.nextDouble() * 6 + 1);
        
        jLabel5.setText(""+numero);
        
    }
    public void colocarPersonaje (){
        if(nuevojug.getPersonajes1().get(0).getPersonaje().equals("Guerrero")){
            jugador1 = guerrero1;
            personaje1 = "Guerrero";
        }
        else if(nuevojug.getPersonajes1().get(0).getPersonaje().equals("Princesa")){
            jugador1 = princesa1;
            personaje1 = "Princesa";
        }
        else{ jugador1 = mago1; personaje1 = "Mago"; }
        
        if(nuevojug.getPersonajes2().get(0).getPersonaje().equals("Guerrero")){
            jugador2 = guerrero2;
            personaje2 = "Guerrero";
        }
        else if(nuevojug.getPersonajes2().get(0).getPersonaje().equals("Princesa")){
            jugador2 = princesa2;
            personaje2 = "Princesa";
        }
        else{ jugador2 = mago2; personaje2 = "Mago";}
        
        //Genera posicion aleatoria 
        aleatoriox = ThreadLocalRandom.current().nextInt(0, (tam-2) + 1);
        aleatorioy = ThreadLocalRandom.current().nextInt(0, (tam-2) + 1);
        boolean colocadoJ1 = false, colocadoJ2 = false;
        
        while(!colocadoJ1 || !colocadoJ2){
            if(matrizLogica[aleatoriox][aleatorioy].equals("")){
                if(!colocadoJ1){
                    matrizLogica[aleatoriox][aleatorioy] = "Jugador1";
                    matrizGrafica[aleatoriox][aleatorioy].setIcon(jugador1);
                    colocadoJ1 = true;
                }else{
                    matrizLogica[aleatoriox][aleatorioy] = "Jugador2";
                    matrizGrafica[aleatoriox][aleatorioy].setIcon(jugador2);
                    colocadoJ2 = true;
                }
            }else{
                aleatoriox = ThreadLocalRandom.current().nextInt(0, (tam-2) + 1);
                aleatorioy = ThreadLocalRandom.current().nextInt(0, (tam-2) + 1);
            }
        }
    }
    
    public void colocarAumyDism(){
        ImageIcon bomba= new ImageIcon("src/Imagenes/bomba.PNG");
        ImageIcon vida= new ImageIcon("src/Imagenes/vida.PNG");
        
        //minas 10% del tablero
        int minas = (int) Math.round(((float)tam*tam) *(.10));
        //vidas 5% dek tablero
        int vidas = (int) Math.round(((float)tam*tam) *(.05));
        
        System.out.println("minas"+minas+"- vidas"+vidas);
        
        int minado=0, darVida=0;
        
        while(minado<minas){
             
            aleatoriox = ThreadLocalRandom.current().nextInt(0, (tam-2) + 1);
            aleatorioy = ThreadLocalRandom.current().nextInt(0, (tam-2) + 1);
            
            matrizLogica[aleatoriox][aleatorioy] = "Mina";
            matrizGrafica[aleatoriox][aleatorioy].setIcon(bomba);
            
            minado++;
        }
        
        while(darVida<vidas){
           
            aleatoriox = ThreadLocalRandom.current().nextInt(0, (tam-2) + 1);
            aleatorioy = ThreadLocalRandom.current().nextInt(0, (tam-2) + 1);
            
            matrizLogica[aleatoriox][aleatorioy] = "Vida";
            matrizGrafica[aleatoriox][aleatorioy].setIcon(vida);
            
            darVida++;
        }
    }

    public void moverPersonaje (String direccion){
        //Mueve el que este pintado, porque ese tiene aun el turno
        String jt;
        if(iniciar){
            JOptionPane.showMessageDialog(null, "Debe tirar el dado para moverse", "No permitido", JOptionPane.DEFAULT_OPTION);
        }else{
            if(direccion.equals("Derecha")){
                //Mueve el que este pintado, porque ese tiene aun el turno
                if(turno==1){ jt = "Jugador1"; }else{ jt = "Jugador2"; }
                Movimiento movD = new Movimiento(direccion,Integer.parseInt(jLabel5.getText()), matrizLogica, matrizGrafica, listaVidas, nuevojug, jt, tam);
                movD.hilo1.start();
                this.listaVidas = movD.getListaVidas();
                this.nuevojug = movD.getJugadorActual();
                this.matrizLogica = movD.getMatrizLogica();
                this.matrizGrafica = movD.getMatrizGrafica();
            }
            if(direccion.equals("Izquierda")){
                if(turno==1){ jt = "Jugador1"; }else{ jt = "Jugador2"; }
                Movimiento movI = new Movimiento(direccion,Integer.parseInt(jLabel5.getText()), matrizLogica, matrizGrafica, listaVidas, nuevojug, jt, tam);
                movI.hilo1.start();
                this.listaVidas = movI.getListaVidas();
                this.nuevojug = movI.getJugadorActual();
                this.matrizLogica = movI.getMatrizLogica();
                this.matrizGrafica = movI.getMatrizGrafica();
            }
            if(direccion.equals("Arriba")){
                if(turno==1){ jt = "Jugador1"; }else{ jt = "Jugador2"; }
                Movimiento movArr = new Movimiento("Arriba", Integer.parseInt(jLabel5.getText()), matrizLogica, matrizGrafica, listaVidas, nuevojug, jt, tam);
                movArr.hilo2.start();
                this.listaVidas = movArr.getListaVidas();
                this.nuevojug = movArr.getJugadorActual();
                this.matrizLogica = movArr.getMatrizLogica();
                this.matrizGrafica = movArr.getMatrizGrafica();
            }
            if(direccion.equals("Abajo")){
                if(turno==1){ jt = "Jugador1"; }else{ jt = "Jugador2"; }
                Movimiento movAba = new Movimiento("Abajo", Integer.parseInt(jLabel5.getText()), matrizLogica, matrizGrafica, listaVidas, nuevojug, jt, tam);
                movAba.hilo2.start();
                this.listaVidas = movAba.getListaVidas();
                this.nuevojug = movAba.getJugadorActual();
                this.matrizLogica = movAba.getMatrizLogica();
                this.matrizGrafica = movAba.getMatrizGrafica();
            }
        }
    }
    public void atacar (String trayectoria){
        int horizontalPersonajeActual=0, verticalPersonajeActual=0, dano, alcanceAct, j, vida;
        String jugTurnoActl, perTurnoAct, no_juega;
        boolean dentroRango = false;
        
        if(turno==1){ 
            jugTurnoActl= "Jugador1";
            no_juega = "Jugador2";
            perTurnoAct = personaje1;
        }else{ 
            jugTurnoActl= "Jugador2";
            no_juega = "Jugador1";
            perTurnoAct = personaje2;
        }
        
        for (int y = 0; y < tam; y++) {
            for (int x = 0; x < tam; x++) {
                if (matrizLogica[x][y].equals(jugTurnoActl)) {
                    horizontalPersonajeActual = x;
                    verticalPersonajeActual = y;
                }
            }
        }
        
        if(perTurnoAct.equals("Guerrero")){
            //ataca a 2 de distancia, resta 2 vida
            dano=2;
            alcanceAct=2;
        } else if (perTurnoAct.equals("Princesa")){
            //ataca a 1 de distancia, resta 1 vida
            //quita siguiente turno (cambiar turno)
            dano=1;
            alcanceAct=1;
        }else{
            //ataca a 4 de distancia, resta 1 vida
            dano=1;
            alcanceAct=4;
        }
        
        try{
            if(trayectoria.equals("Derecha")){
                for (int y = 1; y <= alcanceAct; y++) {
                    if (matrizLogica[horizontalPersonajeActual][verticalPersonajeActual+y].equals(no_juega)) {
                        dentroRango = true;
                    }
                }
            }
            if(trayectoria.equals("Abajo")){
                for (int y = 1; y <= alcanceAct; y++) {
                    if (matrizLogica[horizontalPersonajeActual+y][verticalPersonajeActual].equals(no_juega)) {
                        dentroRango = true;
                    }
                }
            }
            if(trayectoria.equals("Izquierda")){
                for (int y = 1; y <= alcanceAct; y++) {
                    if (matrizLogica[horizontalPersonajeActual][verticalPersonajeActual-y].equals(no_juega)) {
                        dentroRango = true;
                    }
                }
            }
            if(trayectoria.equals("Arriba")){
                for (int y = 1; y <= alcanceAct; y++) {
                    if (matrizLogica[horizontalPersonajeActual-y][verticalPersonajeActual].equals(no_juega)) {
                        dentroRango = true;
                    }
                }
            }
        }catch(Exception exception){
            System.out.println("");
        }
        
        if(dentroRango){
            //Si el jugador actual es el J1 se le resta al 2 la vida porque esta en rango de ataque
            if(jugTurnoActl.equals("Jugador2")){
              
                nuevojug.setVida1(nuevojug.getVida1()-dano);
                j=0; vida=nuevojug.getVida1(); 
            }else{
                
                nuevojug.setVida2(nuevojug.getVida2()-dano);
                j=1; vida=nuevojug.getVida2(); 
            }
            
            if(vida==4){
                listaVidas.get(j).get(4).setIcon(new JButton().getIcon());
            }
            if(vida==3){
                listaVidas.get(j).get(4).setIcon(new JButton().getIcon());
                listaVidas.get(j).get(3).setIcon(new JButton().getIcon());
            }
            if(vida==2){
                listaVidas.get(j).get(4).setIcon(new JButton().getIcon());
                listaVidas.get(j).get(3).setIcon(new JButton().getIcon());
                listaVidas.get(j).get(2).setIcon(new JButton().getIcon());
            }
            if(vida==1){
                listaVidas.get(j).get(4).setIcon(new JButton().getIcon());
                listaVidas.get(j).get(3).setIcon(new JButton().getIcon());
                listaVidas.get(j).get(2).setIcon(new JButton().getIcon());
                listaVidas.get(j).get(1).setIcon(new JButton().getIcon());
            }
            if(vida==0 || vida<0){
                if(j==0){
                    nuevojug.setVida1(0);
                }else{
                    nuevojug.setVida2(0);
                }
            }
        }
        
    }
    public void cambiaTurno (){
        boolean cambioPersonaje=false;
        int siguiente;
        
        if(turno==1){
            //Turno lo tiene el J1
            jLabel6.setForeground(null);
            turno=2;
            jLabel10.setForeground(jugaTurno);
            cambioPersonaje=false;
            
            while(!cambioPersonaje){
                for (int r = 0; r < 3; r++) {
                    if (nuevojug.getPersonajes1().get(r).getPersonaje().equals(personaje1) && !cambioPersonaje) {
                        siguiente = (r == 2) ? 0 : r + 1;
                        for (int xx = 0; xx < tam; xx++) {
                            if(!cambioPersonaje){
                                for (int yy = 0; yy < tam; yy++) {
                                    if (matrizLogica[xx][yy].equals("Jugador1")) {
                                        if (nuevojug.getPersonajes1().get(siguiente).getPersonaje().equals("Guerrero")) {
                                            jugador1 = guerrero1;
                                            personaje1 = "Guerrero";
                                        } else if (nuevojug.getPersonajes1().get(siguiente).getPersonaje().equals("Princesa")) {
                                            jugador1 = princesa1;
                                            personaje1 = "Princesa";
                                        } else {
                                            jugador1 = mago1;
                                            personaje1 = "Mago";
                                        }
                                        matrizGrafica[xx][yy].setIcon(jugador1);
                                        cambioPersonaje = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        
        }else{
            jLabel10.setForeground(null);
            jLabel6.setForeground(jugaTurno);
            turno=1;
            cambioPersonaje=false;
            
            while(!cambioPersonaje){
                for (int r = 0; r < 3; r++) {
                    if (nuevojug.getPersonajes2().get(r).getPersonaje().equals(personaje2) && !cambioPersonaje) {
                        siguiente = (r == 2) ? 0 : r + 1;
                        for (int xx = 0; xx < tam; xx++) {
                            if(!cambioPersonaje){
                                for (int yy = 0; yy < tam; yy++) {
                                    if (matrizLogica[xx][yy].equals("Jugador2")) {
                                        if (nuevojug.getPersonajes2().get(siguiente).getPersonaje().equals("Guerrero")) {
                                            jugador2 = guerrero2;
                                            personaje2 = "Guerrero";
                                        } else if (nuevojug.getPersonajes2().get(siguiente).getPersonaje().equals("Princesa")) {
                                            jugador2 = princesa2;
                                            personaje2 = "Princesa";
                                        } else {
                                            jugador2 = mago2;
                                            personaje2 = "Mago";
                                        }
                                        matrizGrafica[xx][yy].setIcon(jugador2);
                                        cambioPersonaje = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //El jugador que ahora tiene el turno no ha tirado el dado
        tiraDado = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTablero = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        vida1 = new javax.swing.JLabel();
        vida2 = new javax.swing.JLabel();
        vida3 = new javax.swing.JLabel();
        vida4 = new javax.swing.JLabel();
        vida5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        vida11 = new javax.swing.JLabel();
        vida21 = new javax.swing.JLabel();
        vida31 = new javax.swing.JLabel();
        vida41 = new javax.swing.JLabel();
        vida51 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pnlTablero.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlTablero.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Tiempo");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Movimiento");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Arriba");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Abajo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Izquierda");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("Derecha");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Ataque");

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("Arriba");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setText("Abajo");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton7.setText("Izquierda");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton8.setText("Derecha");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton9.setText("Tirar Dado");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Jugador1");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Jugador2");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("jLabel7");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("jLabel8");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("jLabel9");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("jLabel11");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("jLabel12");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("jLabel13");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Vidas");

        vida1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icoVida1.png"))); // NOI18N

        vida2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icoVida1.png"))); // NOI18N

        vida3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icoVida1.png"))); // NOI18N

        vida4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icoVida1.png"))); // NOI18N

        vida5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icoVida1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vida3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vida1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(vida2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(vida4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(vida5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vida1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vida2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vida4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vida3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vida5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("Vidas");

        vida11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icoVida2.png"))); // NOI18N

        vida21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icoVida2.png"))); // NOI18N

        vida31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icoVida2.png"))); // NOI18N

        vida41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icoVida2.png"))); // NOI18N

        vida51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icoVida2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vida31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vida11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vida21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vida41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(vida51)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vida11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vida21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vida31)
                    .addComponent(vida41, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vida51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton10.setText("Regresar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(273, 273, 273)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(80, 80, 80)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3))))
                        .addGap(91, 91, 91)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addComponent(jButton6))
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton8)
                                .addGap(165, 165, 165)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addGap(133, 133, 133)
                                .addComponent(jButton9))))
                    .addComponent(pnlTablero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(33, 33, 33))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(70, 70, 70)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton10)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel14))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel20)))
                .addGap(79, 79, 79))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(jButton10)
                .addGap(56, 56, 56))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(pnlTablero, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton3)
                            .addComponent(jButton5)
                            .addComponent(jButton7))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton4)
                            .addComponent(jButton6)
                            .addComponent(jButton8)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        String personajeTurnoActual;
        if(iniciar){
            dado();
            iniciar = false;
        }else{
            if(!tiraDado){
                dado();
                tiraDado=true;
                if(turno==1){ 
                    personajeTurnoActual = personaje1;
                }else{ 
                    personajeTurnoActual = personaje2;
                }

                if (personajeTurnoActual.equals("Princesa") && veces==0){
                    //Deja tirar otra vez, doble turno
                    tiraDado=false;
                    veces++;
                }else{
                    tiraDado = true;
                    veces=0;
                    cambiaTurno();
                }
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        moverPersonaje("Arriba");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        moverPersonaje("Abajo");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        moverPersonaje("Izquierda");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        moverPersonaje("Derecha");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        atacar("Arriba");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        atacar("Abajo");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        atacar("Izquierda");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        atacar("Derecha");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        temporizador.interrupt();
        Configuracion con = new Configuracion(historial);
        con.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(areaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(areaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(areaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(areaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new areaJuego(null,null,0).setVisible(true);
                
            }
        });
       
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel pnlTablero;
    private javax.swing.JLabel vida1;
    private javax.swing.JLabel vida11;
    private javax.swing.JLabel vida2;
    private javax.swing.JLabel vida21;
    private javax.swing.JLabel vida3;
    private javax.swing.JLabel vida31;
    private javax.swing.JLabel vida4;
    private javax.swing.JLabel vida41;
    private javax.swing.JLabel vida5;
    private javax.swing.JLabel vida51;
    // End of variables declaration//GEN-END:variables
}
