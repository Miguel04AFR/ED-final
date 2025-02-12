package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cu.edu.cujae.ceis.graph.edge.Edge;
import cu.edu.cujae.ceis.graph.vertex.Vertex;
import logica.Tramo;

public class EdgeComponente extends JComponent{
	private Edge arista;
	private JLabel pesoArista;
	private VertexComponente partida;
	private VertexComponente fin;

	public EdgeComponente(Edge arista,JLabel pesoArista,VertexComponente partida, VertexComponente fin) {
		this.partida=partida;
		this.pesoArista=pesoArista;
		this.fin=fin;
		this.arista=arista;
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Obtener las posiciones de los vértices
        int x1 = partida.getCoordenadaX(); 
        int y1 = partida.getCoordenadaY(); 
        int x2 = fin.getCoordenadaX(); 
        int y2 = fin.getCoordenadaY(); 

        // Dibujar la línea que conecta los dos vértices
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x1, y1, x2, y2);

        // Dibujar el peso de la arista
        int xLabel= x1 + 5;
        int yLabel = y1 - 10;
    }
	

	public VertexComponente getPartida() {
        return partida;
    }

    public VertexComponente getFin() {
        return fin; 
    }
    
    public Edge getEdge() {
        return arista;
    }
}
