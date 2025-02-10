package interfaz;

import componentesVisuales.BotonAnimacion;
import cu.edu.cujae.ceis.graph.vertex.Vertex;
import java.awt.Color;

public class VertexComponente extends BotonAnimacion{
	
	private Vertex vertexAsociado;
	private int x;
	private int y;

	public VertexComponente(Vertex vertexAsociado,int x, int y) {
		super();
		setBackground(new Color(153, 153, 204));
		setBounds(50,60,142,93);
		this.vertexAsociado=vertexAsociado;
		this.x=x;
		this.y=y;
	}
	
	public void setPosicion(int x, int y) {
		this.x=x;
		this.y=y;
		setLocation(x,y);
		repaint();
	}
	
	public int getCoordenadaX() {
		return x;
	}
	
	public int getCoordenadaY() {
		return y;
	}
	
	public Vertex getVertexAsociado() {
		return vertexAsociado;
	}
	
	

}
