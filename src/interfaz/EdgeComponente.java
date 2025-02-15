package interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cu.edu.cujae.ceis.graph.edge.Edge;
import cu.edu.cujae.ceis.graph.edge.WeightedEdge;
import cu.edu.cujae.ceis.graph.vertex.Vertex;
import logica.Tramo;

public class EdgeComponente extends JComponent{
	private Edge arista;
	private ComponenteVertex partida;
	private ComponenteVertex fin;
	private JLabel peso;

	public EdgeComponente(ComponenteVertex partida, ComponenteVertex fin) {
		this.partida=partida;
		this.fin=fin;
		setBounds(0, 0, 938, 649); // Establecer el tamaño del componente
		
	}
	
	public EdgeComponente(Edge arista,ComponenteVertex partida, ComponenteVertex fin) {
		this.partida=partida;
		this.fin=fin;
		this.arista=arista;
		setBounds(0, 0, 938, 649); // Establecer el tamaño del componente
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

            // Obtener las posiciones de los vértices
            int x1 = partida.getBoton().getX() + partida.getBoton().getWidth() / 2; 
            int y1 = partida.getBoton().getY() + partida.getBoton().getHeight() / 2; 
            int x2 = fin.getBoton().getX() + fin.getBoton().getWidth() / 2; 
            int y2 = fin.getBoton().getY() + fin.getBoton().getHeight() / 2; 

            // Calcular el ángulo de la línea
            double angle = Math.atan2(y2 - y1, x2 - x1);

            int arrowHeadLength = 15; // Longitud de la flecha
            int vertexRadius = fin.getBoton().getWidth() / 2; // Radio del vértice

            // Calcular el punto donde termina la línea
            int xEnd = x2 - (int)((vertexRadius + arrowHeadLength) * Math.cos(angle));
            int yEnd = y2 - (int)((vertexRadius + arrowHeadLength) * Math.sin(angle));

            // Dibujar la línea
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2)); // Grosor de la línea
            g2d.drawLine(x1, y1, xEnd, yEnd);//este basicStroke es imporante para futuros proyectos(no olvidar)

            // Calcular la posición de la punta de la flecha
            int xArrowTip = x2 - (int)(vertexRadius * Math.cos(angle));
            int yArrowTip = y2 - (int)(vertexRadius * Math.sin(angle));

            // Calcular los puntos de la cabeza de la flecha
            int xArrow1 = xArrowTip - (int) (arrowHeadLength * Math.cos(angle - Math.PI / 6));
            int yArrow1 = yArrowTip - (int) (arrowHeadLength * Math.sin(angle - Math.PI / 6));

            int xArrow2 = xArrowTip - (int) (arrowHeadLength * Math.cos(angle + Math.PI / 6));
            int yArrow2 = yArrowTip - (int) (arrowHeadLength * Math.sin(angle + Math.PI / 6));

            // Dibujar la cabeza de la flecha
            int[] xPoints = { xArrowTip, xArrow1, xArrow2 };
            int[] yPoints = { yArrowTip, yArrow1, yArrow2 };
            g2d.fillPolygon(xPoints, yPoints, 3);//primera vez que el sen y cos me sirven para algo XD

            // Dibujar el peso en el centro de la línea
            if (arista != null && ((WeightedEdge) arista).getWeight() != null) {
                String pesoStr = String.valueOf(((Tramo)((WeightedEdge) arista).getWeight()).getKm());
                int xLabel = (x1 + x2) / 2;
                int yLabel = (y1 + y2) / 2;
                g2d.setColor(Color.BLUE);
                g2d.setFont(new Font("Arial", Font.BOLD, 14));
                g2d.drawString(pesoStr, xLabel, yLabel);
            }
        
        }
	

	public ComponenteVertex getPartida() {
        return partida;
    }

    public ComponenteVertex getFin() {
        return fin; 
    }
    
    public Edge getEdge() {
        return arista;
    }
    
    public JLabel getPeso() {
    	return peso;
    }
    
    public void setLabel(Tramo t) {
    	peso.setText(String.valueOf(t.getKm()));
    }
}
