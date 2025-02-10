package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class EdgeComponente extends JComponent{
	
	private VertexComponente partida;
	private VertexComponente fin;
	private int peso;

	public EdgeComponente(VertexComponente partida, VertexComponente fin, int peso) {
		this.partida=partida;
		this.fin=fin;
		this.peso=peso;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		
		// Obtener las posiciones de los vértices visuales usando los métodos de coordenadas
	    int x1 = partida.getCoordenadaX() + partida.getWidth() / 2;
	    int y1 = partida.getCoordenadaY() + partida.getHeight() / 2;
	    int x2 = fin.getCoordenadaX() + fin.getWidth() / 2;
	    int y2 = fin.getCoordenadaY() + fin.getHeight() / 2;
	    
	    g2d.setColor(Color.BLACK); // Color de la arista
	    g2d.drawLine(x1, y1, x2, y2); // Dibuja la línea entre los vértices
	    
	    
	 // Dibujar el peso de la arista
	    g2d.setColor(Color.RED);
	    g2d.setFont(new Font("Arial", Font.BOLD, 12));
	    String pesoStr = String.valueOf(peso);
	    int pesoX = (x1 + x2) / 2;
	    int pesoY = (y1 + y2) / 2;
	    g2d.drawString(pesoStr, pesoX, pesoY);

	}
	

}
