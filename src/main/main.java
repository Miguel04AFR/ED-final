package main;

import java.awt.EventQueue;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;

import cu.edu.cujae.ceis.graph.LinkedGraph;
import cu.edu.cujae.ceis.graph.interfaces.ILinkedNotDirectedGraph;
import interfaz.Carrera;
import logica.*;
import cu.edu.cujae.ceis.graph.interfaces.*;

public class main {

	public static void main(String[] args) {
		ILinkedWeightedEdgeDirectedGraph grafo = new LinkedGraph();
		Robot robot = new Robot(100,100,150);
		Simulacion simu=new Simulacion(grafo,robot);
		FlatDarkPurpleIJTheme.setup();
		
		
		
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
