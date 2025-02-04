package logica;

import cu.edu.cujae.ceis.graph.interfaces.ILinkedNotDirectedGraph;

public class Simulacion {
	private ILinkedNotDirectedGraph grafo;
	private Robot robot;
	
	public Simulacion(ILinkedNotDirectedGraph grafo, Robot robot) {
		setGrafo( grafo);
		setRobot( robot);
	}

	public ILinkedNotDirectedGraph getGrafo() {
		return grafo;
	}

	public void setGrafo(ILinkedNotDirectedGraph grafo) {
		this.grafo = grafo;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}
	
	
	

}
