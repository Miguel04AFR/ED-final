package logica;

import java.util.Iterator;

import cu.edu.cujae.ceis.graph.edge.Edge;
import cu.edu.cujae.ceis.graph.edge.WeightedEdge;
import cu.edu.cujae.ceis.graph.interfaces.ILinkedWeightedEdgeDirectedGraph;
import cu.edu.cujae.ceis.graph.vertex.Vertex;
import logica.Posicion;
import cu.edu.cujae.ceis.graph.edge.*;
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

	public Vertex VertexSituado(ILinkedWeightedEdgeDirectedGraph grafo) {//metodo para situar al robot
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

	public boolean VerificarMeta(Vertex a,ILinkedWeightedEdgeDirectedGraph grafo,int meta) {//metodo para verificar si ese nodo puede llegar a la meta
		if(a.equals(grafo.getVerticesList().get(meta))) {	
			return true;
		}
		Iterator<Edge> i= a.getEdgeList().iterator();
		while(i.hasNext()) {
			Vertex v=i.next().getVertex();

			if(VerificarMeta(v, grafo, meta))
				return true;		
		}

		return false;
	}

	public Vertex LLegarMeta(ILinkedWeightedEdgeDirectedGraph grafo, int meta) {//metodo para que el robot llegue a la meta de la manera mas optima
		Vertex posRobot= VertexSituado(grafo);
		int minimo=2000;
		Iterator<Edge> i= posRobot.getEdgeList().iterator();
		Vertex vp=i.next().getVertex();
		boolean primeroPuede=false;
		if(VerificarMeta(vp,grafo,meta)) {
			primeroPuede=true;
			minimo=tamano(vp,grafo,meta,0);
		}
		else {
			while(i.hasNext() && !primeroPuede) {
				if(VerificarMeta(i.next().getVertex(),grafo,meta)){
					primeroPuede=true;
					minimo=tamano(vp,grafo,meta,0);
				}
			}
		}


		while(i.hasNext() && primeroPuede) {
			Vertex v=i.next().getVertex();
			if(tamano(v,grafo,meta,0)<minimo) {
				minimo=tamano(v,grafo,meta,0);
				vp=v;

			}

			if(!primeroPuede)
				vp=null;

		}

		return vp;
	}

	public int tamano(Vertex a,ILinkedWeightedEdgeDirectedGraph grafo, int meta,int contAc) {
		if(a.equals(grafo.getVerticesList().get(meta))) {	
			return contAc;
		}

		int distanciaMinima = 1000;


		Iterator<Edge> ia=a.getEdgeList().iterator();
		boolean tieneAristas = false;

		while(ia.hasNext()) {
			tieneAristas=true;
			WeightedEdge we=((WeightedEdge)ia.next());
			Vertex v=we.getVertex();
			int cont=((Tramo)we.getWeight()).getKm() + contAc;


			int distancia = tamano(v, grafo, meta, cont);

			if(distancia<distanciaMinima) {
				distanciaMinima = distancia;
			}	
		}

		if(!tieneAristas) {// Si el vértice no tiene aristas salientes y no es la meta
			return -1;
		}

		return distanciaMinima;
	}
}

