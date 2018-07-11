/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author tonyc
 */
public class InterfazGrafica extends JFrame{
    private ImageIcon nuevo;	
	
	
	public InterfazGrafica(ImageIcon nuevo, String titulo) {
		super("Grafica");
		this.nuevo=nuevo;		
		add(getTitulo(titulo));
		JScrollPane scroll = new JScrollPane(new JLabel(nuevo));
		scroll.setBounds(20, 100, 700, 450);
		add(scroll);
		init();
	}
        
        public JLabel getTitulo(String title) {
		JLabel titulo = new JLabel(title);
		titulo.setBounds(220, 10, 200, 70);
		titulo.setForeground(Color.blue);
		titulo.setOpaque(true);
		titulo.setFont(new Font("Arial",1,16));
		titulo.setVerticalAlignment(JLabel.CENTER);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		titulo.setVisible(true);
		return titulo;
	}
	
	public void setIcon(ImageIcon nuevo) {
		this.nuevo = nuevo;
	}

	public void init() {
		setLayout(null);
		setResizable(false);
		setSize(750,600);
		setLocationRelativeTo(null);		
		setVisible(true);
	}
	
        public ImageIcon getIcon() {
            return nuevo;
        }		
		

}
