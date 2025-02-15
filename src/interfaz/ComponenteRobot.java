package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import logica.Robot;

public class ComponenteRobot extends JComponent {
    private Robot robot;

    public ComponenteRobot() {
        robot = new Robot(20, 48, 48);
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
        int orejaTa = robot.getTamano() / 4;
        String nombre = "Freddy";
        int nombreTa = g2d.getFontMetrics().stringWidth(nombre);

        // Dibuja el nombre
        g2d.setColor(Color.BLACK);
        Font originalFuente = g2d.getFont();
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString(nombre, baseX - 10 + (robot.getTamano() - nombreTa) / 2, robot.getY() - 35);
        g2d.setFont(originalFuente);

        // Dibuja la cabeza del robot
        g2d.setColor(new Color(139, 69, 19)); // Color marrón
        g2d.fillRect(robot.getX(), robot.getY()-7, robot.getTamano(), robot.getTamano());
        
     // Dibuja el cuello del robot
        g2d.setColor(new Color(139, 69, 19)); // Color marrón
        g2d.fillRect(robot.getX()+15, robot.getY()+40, robot.getTamano()-28, robot.getTamano()-35);

        // Dibuja los ojos del robot
        g2d.setColor(Color.WHITE);
        g2d.fillRect(robot.getX() + ojosTa, robot.getY() + ojosTa, ojosTa, ojosTa); // Ojo derecho
        g2d.fillRect(robot.getX() + 3 * ojosTa - 5, robot.getY() + ojosTa, ojosTa, ojosTa); // Ojo izquierdo
        g2d.setColor(new Color(4,55,69));
        g2d.fillRect(robot.getX() + 3 * ojosTa - ojosTa / 4 +1, robot.getY() + ojosTa + ojosTa / 4, ojosTa / 2, ojosTa / 2); // Pupila ojo izquierdo
        g2d.fillRect(robot.getX() + ojosTa + ojosTa / 4 +2, robot.getY() + ojosTa + ojosTa / 4, ojosTa / 2, ojosTa / 2); // Pupila ojo derecho

       /* // Dibuja la boca del robot
        g2d.setColor(Color.BLACK);
        g2d.fillRect(robot.getX() + ojosTa, robot.getY() + 3 * ojosTa, rostro, ojosTa);
        */
     // Dibuja la nairz del robot
        g2d.setColor(Color.BLACK);
        g2d.fillRect(robot.getX() + ojosTa+10, robot.getY() + 3 * ojosTa-7, rostro-18, ojosTa-4);

        // Dibuja las orejas
        g2d.setColor(new Color(139, 69, 19)); // Color marrón
        g2d.fillRect(robot.getX() - orejaTa / 2, robot.getY() + ojosTa / 2-20, orejaTa, orejaTa);
        g2d.fillRect(robot.getX()-1 + robot.getTamano() - orejaTa / 2, robot.getY() + ojosTa / 2-20, orejaTa+1, orejaTa);

        // Dibuja el sombrero
        g2d.setColor(Color.BLACK);
        g2d.fillRect(robot.getX()-2 + robot.getTamano() / 4, robot.getY() - robot.getTamano() / 4, robot.getTamano() / 2+4, robot.getTamano() / 4);
        g2d.fillRect(robot.getX() + robot.getTamano() / 3, robot.getY() - robot.getTamano() / 3-14, robot.getTamano() / 3, robot.getTamano() / 6+20);

        // Dibuja el torso
        g2d.setColor(new Color(160, 82, 45)); // Color marrón
        g2d.fillRect(baseX, baseY, robot.getTamano(), torsoTa);
        
     // Dibuja los bordes negros del torso
        g2d.setColor(Color.BLACK);
        g2d.drawRect(baseX, baseY, robot.getTamano(),torsoTa);
        

        // Dibuja los botones en el torso
        g2d.setColor(Color.BLACK);
        g2d.fillOval(baseX + robot.getTamano() / 2 - ojosTa / 2, baseY + torsoTa / 4, ojosTa, ojosTa);
        g2d.fillOval(baseX + robot.getTamano() / 2 - ojosTa / 2, baseY + torsoTa / 2, ojosTa, ojosTa);
        g2d.fillOval(baseX + robot.getTamano() / 2 - ojosTa / 2, baseY + 3 * torsoTa / 4, ojosTa, ojosTa);

        // Dibuja los brazos
        g2d.setColor(new Color(139, 69, 19)); // Color marrón
        g2d.fillRect(baseX - brazoTa, baseY+4, brazoTa, torsoTa); // Brazo izquierdo
        g2d.fillRect(baseX + robot.getTamano(), baseY+4, brazoTa, torsoTa); // Brazo derecho

        // Dibuja las piernas
        g2d.setColor(new Color(160, 82, 45)); // Color marrón
        g2d.fillRect(baseX, baseY + torsoTa+1, piernaTa, torsoTa); // Pierna izquierda
        g2d.fillRect(baseX + robot.getTamano() - piernaTa, baseY + torsoTa+1, piernaTa, torsoTa); // Pierna derecha

        // Dibuja detalles adicionales en el torso
        g2d.setColor(Color.BLACK);
        g2d.drawRect(baseX + torsoTa / 4, baseY + torsoTa / 4, torsoTa / 2, torsoTa / 2); // Detalle en el torso
        g2d.drawRect(baseX + torsoTa / 4, baseY + torsoTa / 4 + 13, torsoTa / 2, torsoTa / 2); // Detalle en el torso
        g2d.drawLine(baseX + torsoTa / 4, baseY + torsoTa / 2, baseX + 3 * torsoTa / 4, baseY + torsoTa / 2); // Línea horizontal en el torso
        
        // Dibuja los pies
        g2d.setColor(Color.BLACK); // Color marrón
        g2d.fillRect(baseX, baseY + torsoTa+50, piernaTa+5, torsoTa-40); // Pie iz
        g2d.fillRect(baseX + robot.getTamano() - piernaTa, baseY + torsoTa+50, piernaTa+5, torsoTa-40); // Pie der
    }
    
    
    
    
    
}