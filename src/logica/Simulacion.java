package logica;

import java.util.LinkedList;
import java.util.Random;

import cu.edu.cujae.ceis.graph.interfaces.ILinkedDirectedGraph;
import cu.edu.cujae.ceis.graph.interfaces.ILinkedNotDirectedGraph;
import cu.edu.cujae.ceis.graph.interfaces.ILinkedWeightedEdgeDirectedGraph;
import cu.edu.cujae.ceis.graph.vertex.Vertex;

public class Simulacion {
	private ILinkedWeightedEdgeDirectedGraph grafo;
	private Robot robot;
	
	public Simulacion(ILinkedWeightedEdgeDirectedGraph grafo, Robot robot) {
		setGrafo( grafo);
		setRobot( robot);
		
	}

	public ILinkedWeightedEdgeDirectedGraph getGrafo() {
		return grafo;
	}

	public void setGrafo(ILinkedWeightedEdgeDirectedGraph grafo) {
		this.grafo = grafo;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}
	
	public void AsignarMeta(int min,int max) {//normalmente va a ser 0 y size-1
	        Random random = new Random();
	         int meta=random.nextInt((max - min) + 1) + min;
	         
	         ((Posicion)grafo.getVerticesList().get(meta).getInfo()).setMeta(true);
	}
	
	
	

}