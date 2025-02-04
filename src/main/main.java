package main;

import java.awt.EventQueue;

import cu.edu.cujae.ceis.graph.LinkedGraph;
import cu.edu.cujae.ceis.graph.interfaces.ILinkedNotDirectedGraph;
import interfaz.Carrera;
import logica.*;

public class main {

	public static void main(String[] args) {
		ILinkedNotDirectedGraph grafo = new LinkedGraph();
		Robot robot = new Robot(100,100,150);
		Simulacion simu=new Simulacion(grafo,robot);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Carrera frame = new Carrera(simu);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
