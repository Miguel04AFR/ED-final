package logica;

import java.io.Serializable;
import java.util.ArrayList;

import cu.edu.cujae.ceis.graph.vertex.Vertex;

public class EstadoSimulacion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pasos;
	private ArrayList<Integer> direcciones;
	private boolean llegoMeta;
	private Vertex posicionInicial;
	private Vertex posicionFinal;
	
	public EstadoSimulacion() {
		super();
		this.pasos = 0;
		this.direcciones = new ArrayList<>();
		this.llegoMeta = false;
		this.posicionInicial = null;
		this.posicionFinal=null;
	}
	public Vertex getPosicionFinal() {
		return posicionFinal;
	}
	public void setPosicionFinal(Vertex posicionFinal) {
		this.posicionFinal = posicionFinal;
	}
	public int getPasos() {
		return pasos;
	}
	public void setPasos(int pasos) {
		this.pasos = pasos;
	}
	public ArrayList<Integer> getDirecciones() {
		return direcciones;
	}
	public void setDirecciones(ArrayList<Integer> direcciones) {
		this.direcciones = direcciones;
	}
	public boolean getLlegoMeta() {
		return llegoMeta;
	}
	public void setLlegoMeta(boolean llegoMeta) {
		this.llegoMeta = llegoMeta;
	}
	public Vertex getPosicionInicial() {
		return posicionInicial;
	}
	public void setPosicionInicial(Vertex posicionInicial) {
		this.posicionInicial = posicionInicial;
	}

	

}
