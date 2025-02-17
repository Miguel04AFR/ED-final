package logica;

import java.io.Serializable;
import java.util.ArrayList;

import cu.edu.cujae.ceis.graph.vertex.Vertex;

public class EstadoSimulacion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int pasos;
	public ArrayList<String> direcciones;
	public boolean llegoMeta;
	public Vertex posicionInicial;
	
	public EstadoSimulacion() {
		super();
		this.pasos = 0;
		this.direcciones = new ArrayList<>();
		this.llegoMeta = false;
		this.posicionInicial = null;
	}
	public int getPasos() {
		return pasos;
	}
	public void setPasos(int pasos) {
		this.pasos = pasos;
	}
	public ArrayList<String> getDirecciones() {
		return direcciones;
	}
	public void setDirecciones(ArrayList<String> direcciones) {
		this.direcciones = direcciones;
	}
	public boolean isLlegoMeta() {
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
