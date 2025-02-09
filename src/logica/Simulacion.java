package logica;

import java.util.LinkedList;

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
	
	
	

}
