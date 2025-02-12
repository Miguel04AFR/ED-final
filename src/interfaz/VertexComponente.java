
package interfaz;

import componentesVisuales.BotonAnimacion;
import cu.edu.cujae.ceis.graph.vertex.Vertex;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class VertexComponente extends Vertex{
	private BotonAnimacion boton;
	private int x;
	private int y;

	public VertexComponente(String info,BotonAnimacion boton, int x, int y) {
		super(info);        this.boton=new BotonAnimacion();
        boton.setForeground(Color.CYAN);
		boton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		boton.setText("Vertex");
		boton.setBackground(Color.RED);
		boton.setColorEfecto(Color.YELLOW);
		boton.setBounds(113, 120, 130, 76);
        this.x=x;
		this.y=y;
		
		 boton.setLocation(x, y);
	}
	
	public void setPosicion(int x, int y) {
        this.x = x; // Actualizar coordenada X
        this.y = y; // Actualizar coordenada Y
        boton.setLocation(x, y); // Establecer la nueva posición del botón
    }
	
	 public BotonAnimacion getBoton() {
	        return boton;
	    }
	
	public int getCoordenadaX() {
		return x;
	}
	
	public int getCoordenadaY() {
		return y;
	}
	
	
	
	

}
