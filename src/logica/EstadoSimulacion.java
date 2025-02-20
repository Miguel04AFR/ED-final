package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class EstadoSimulacion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pasos;
	private ArrayList<Integer> direcciones;
	private boolean llegoMeta;
	private int posicionInicial;
	private int posicionFinal;
	private float distancia;
	
	public EstadoSimulacion() {
		super();
		this.pasos = 0;
		this.direcciones = new ArrayList<>();
		this.llegoMeta = false;
		this.posicionInicial=-1;
		this.posicionFinal=-1;
		this.distancia=0;
	}
	public Integer getPosicionFinal() {
		return posicionFinal;
	}
	public void setPosicionFinal(Integer posicionFinal) {
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
	public Integer getPosicionInicial() {
		return posicionInicial;
	}
	public void setPosicionInicial(Integer posicionInicial) {
		this.posicionInicial = posicionInicial;
	}
	public float getDistancia() {
		return distancia;
	}
	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}
	
	public void reset() {
        this.pasos = 0;
        this.direcciones.clear(); // Limpiar la lista de direcciones
        this.llegoMeta = false;
        this.posicionInicial = -1;
        this.posicionFinal = -1;
        this.distancia = 0;
    }

	

}
