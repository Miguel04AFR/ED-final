package logica;

import java.util.Iterator;

import cu.edu.cujae.ceis.graph.interfaces.ILinkedWeightedEdgeDirectedGraph;
import cu.edu.cujae.ceis.graph.vertex.Vertex;
import logica.Posicion;

public class Robot {
	private int x;
	private int y;
	private int tamano;
	
	
	
	
	
	public Robot(int x, int y, int tamano) {
		super();
		 setX(x);
		 setY(y);
		 setTamano(tamano);
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
	public int getTamano() {
		return tamano;
	}
	public void setTamano(int tamano) {
		this.tamano = tamano;
	}
	
	public Vertex VertexSituado(ILinkedWeightedEdgeDirectedGraph grafo) {
		Iterator<Vertex> i=grafo.getVerticesList().iterator();
		boolean encontrado=false;
		Vertex v = null;
		while(i.hasNext() && !encontrado) {
			v=i.next();
			if(((Posicion)v.getInfo()).getRobot()){
				encontrado=true;
			}
		}
		return v;
		
	}
	
	public Vertex LLegarMeta(ILinkedWeightedEdgeDirectedGraph grafo, int meta) {
		Vertex posRobot= VertexSituado(grafo);
		Iterator<Vertex> i= posRobot.getAdjacents().iterator();
		while(i.hasNext()) {
			
		}
		
		
		
		
		
		return null;
	}
	

}
