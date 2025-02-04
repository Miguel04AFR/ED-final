package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import logica.Robot;

public class ComponenteRobot extends JComponent{
	 private Robot robot;
	 
	 public ComponenteRobot() {
		 robot=new Robot(60,60,50);
			setRobot(robot);
		}

	public ComponenteRobot(Robot robot) {
		setRobot(robot);
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}
	

@Override
 protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g; 
 // Coordenadas base 
    int baseX = robot.getX();
    int baseY = robot.getY() + robot.getTamano();
    int torsoTa = robot.getTamano();
    int brazoTa = robot.getTamano() / 5;
    int piernaTa = robot.getTamano() / 4;
    int rostro = robot.getTamano() / 2;
    int ojosTa = robot.getTamano() / 5;
    String nombre = "Precius";
    int nombreTa = g2d.getFontMetrics().stringWidth(nombre);
    
    //Dibuja el nombre (Precius el tanke)
    g2d.setColor(Color.BLACK);
    Font originalFuente = g2d.getFont();
    g2d.setFont(new Font("Arial", Font.BOLD, 16));
    g2d.drawString(nombre, baseX-10 + (robot.getTamano() - nombreTa) / 2, robot.getY() - 10);
    g2d.setFont(originalFuente);
    

    // Dibuja la cabeza del robot
    g2d.setColor(Color.GRAY);
    g2d.fillRect(robot.getX(), robot.getY(), robot.getTamano(), robot.getTamano());

    // Dibuja los ojos del robot
    g2d.setColor(Color.BLACK);
    
    g2d.fillRect(robot.getX() + ojosTa, robot.getY() + ojosTa, ojosTa, ojosTa);
    g2d.fillRect(robot.getX() + 3 * ojosTa, robot.getY() + ojosTa, ojosTa, ojosTa);

    // Dibuja la boca del robot
    g2d.setColor(Color.RED);
    g2d.fillRect(robot.getX() + ojosTa, robot.getY() + 3 * ojosTa, rostro, ojosTa);
    
 

    // Dibuja el torso
    g2d.setColor(Color.DARK_GRAY);
    g2d.fillRect(baseX, baseY, robot.getTamano(), torsoTa);

    // Dibuja los brazos
    g2d.setColor(Color.LIGHT_GRAY);
    g2d.fillRect(baseX - brazoTa, baseY, brazoTa, torsoTa); // Brazo izquierdo
    g2d.fillRect(baseX + robot.getTamano(), baseY, brazoTa, torsoTa); // Brazo derecho

    // Dibuja las piernas
    g2d.setColor(Color.GRAY);
    g2d.fillRect(baseX, baseY + torsoTa, piernaTa, torsoTa); // Pierna izquierda
    g2d.fillRect(baseX + robot.getTamano() - piernaTa, baseY + torsoTa, piernaTa, torsoTa); // Pierna derecha
}

	 
}


