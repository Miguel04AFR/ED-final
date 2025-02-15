package interfaz;

import java.awt.Color;

import javax.swing.JLabel;

import componentesVisuales.BotonAnimacion;
import cu.edu.cujae.ceis.graph.vertex.Vertex;

public class ComponenteVertex extends Vertex{
	private int x;
	private int y;
	private BotonAnimacion boton;
	
	
	public ComponenteVertex(Object info) {
		super(info);
		boton=new BotonAnimacion();
		boton.setBackground(Color.BLUE);
		boton.setBounds(253, 277, 50, 50);
		setX(boton.getX());
		setY(boton.getY());
		
	}
	
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public BotonAnimacion getBoton() {
		return boton;
	}

	
	
}
