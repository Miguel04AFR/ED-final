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
        robot = new Robot(60, 60, 50);
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
        int tamano = robot.getTamano();
        int torsoTa = tamano;
        int brazoTa = tamano / 5;
        int piernaTa = tamano / 4;
        int rostro = tamano / 2;
        int ojosTa = tamano / 5;
        int orejaTa = tamano / 4;
        int dienteAncho = ojosTa / 2;
        int dienteAlto = ojosTa / 2;
        String nombre = "Freddy";
        int nombreTa = g2d.getFontMetrics().stringWidth(nombre);

        // Dibuja el nombre
        g2d.setColor(Color.BLACK);
        Font originalFuente = g2d.getFont();
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString(nombre, baseX - 10 + (tamano - nombreTa) / 2, robot.getY() - 20);
        g2d.setFont(originalFuente);

        // Dibuja la cabeza del robot
        g2d.setColor(new Color(139, 69, 19)); // Color marrón
        g2d.fillRect(baseX, robot.getY(), tamano, tamano);

        // Dibuja los ojos del robot
        g2d.setColor(Color.WHITE);
        g2d.fillOval(baseX + ojosTa, robot.getY() + ojosTa, ojosTa, ojosTa); // Ojo derecho
        g2d.fillOval(baseX + 3 * ojosTa - 5, robot.getY() + ojosTa, ojosTa, ojosTa); // Ojo izquierdo
        g2d.setColor(Color.BLACK);
        g2d.fillOval(baseX + 3 * ojosTa - ojosTa / 4 - 2, robot.getY() + ojosTa + ojosTa / 4, ojosTa / 2, ojosTa / 2); // Pupila ojo izquierdo
        g2d.fillOval(baseX + ojosTa + ojosTa / 4, robot.getY() + ojosTa + ojosTa / 4, ojosTa / 2, ojosTa / 2); // Pupila ojo derecho

        // Dibuja la boca abierta del robot
        g2d.setColor(Color.BLACK);
        g2d.fillRect(baseX + ojosTa, robot.getY() + 3 * ojosTa, rostro, ojosTa + dienteAlto); // Boca más alta para mostrar dientes

        // Dibuja los dientes del robot
        g2d.setColor(Color.WHITE);
        for (int i = 0; i < 4; i++) {
            // Dientes superiores
            int x1 = baseX + ojosTa + i * dienteAncho;
            int y1 = robot.getY() + 3 * ojosTa;
            int x2 = x1 + dienteAncho / 2;
            int y2 = y1 + dienteAlto;
            int x3 = x1 + dienteAncho;
            int[] xPointsSup = {x1, x2, x3};
            int[] yPointsSup = {y1, y2, y1};
            g2d.fillPolygon(xPointsSup, yPointsSup, 3);

            // Dientes inferiores
            y1 = robot.getY() + 4 * ojosTa + dienteAlto;
            y2 = y1 - dienteAlto;
            int[] yPointsInf = {y1, y2, y1};
            g2d.fillPolygon(xPointsSup, yPointsInf, 3);
        }

        // Dibuja las orejas
        g2d.setColor(new Color(139, 69, 19)); // Color marrón
        g2d.fillOval(baseX - orejaTa / 2, robot.getY() + ojosTa / 2, orejaTa, orejaTa);
        g2d.fillOval(baseX + tamano - orejaTa / 2, robot.getY() + ojosTa / 2, orejaTa, orejaTa);

        // Dibuja el sombrero
        g2d.setColor(Color.BLACK);
        g2d.fillRect(baseX + tamano / 4, robot.getY() - tamano / 4, tamano / 2, tamano / 4);
        g2d.fillRect(baseX + tamano / 3, robot.getY() - tamano / 3, tamano / 3, tamano / 6);

        // Dibuja el torso
        g2d.setColor(new Color(160, 82, 45)); // Color marrón más oscuro
        g2d.fillRect(baseX, baseY, tamano, torsoTa);

        // Dibuja los botones en el torso
        g2d.setColor(Color.BLACK);
        g2d.fillOval(baseX + tamano / 2 - ojosTa / 2, baseY + torsoTa / 4, ojosTa, ojosTa);
        g2d.fillOval(baseX + tamano / 2 - ojosTa / 2, baseY + torsoTa / 2, ojosTa, ojosTa);
        g2d.fillOval(baseX + tamano / 2 - ojosTa / 2, baseY + 3 * torsoTa / 4, ojosTa, ojosTa);

        // Dibuja los brazos
        g2d.setColor(new Color(139, 69, 19)); // Color marrón
        g2d.fillRect(baseX - brazoTa, baseY, brazoTa, torsoTa); // Brazo izquierdo
        g2d.fillRect(baseX + tamano, baseY, brazoTa, torsoTa); // Brazo derecho

        // Dibuja las manos
        g2d.setColor(new Color(160, 82, 45)); // Color marrón más oscuro
        g2d.fillOval(baseX - brazoTa, baseY + torsoTa - brazoTa / 2, brazoTa, brazoTa); // Mano izquierda
        g2d.fillOval(baseX + tamano, baseY + torsoTa - brazoTa / 2, brazoTa, brazoTa); // Mano derecha

        // Dibuja las piernas
        g2d.setColor(new Color(139, 69, 19)); // Color marrón
        g2d.fillRect(baseX + piernaTa / 2, baseY + torsoTa, piernaTa, torsoTa); // Pierna izquierda
        g2d.fillRect(baseX + tamano - piernaTa - piernaTa / 2, baseY + torsoTa, piernaTa, torsoTa); // Pierna derecha

        // **Añadir los pies con rectángulos marrones**
        g2d.setColor(new Color(101, 67, 33)); // Color marrón oscuro para los pies
        g2d.fillRect(baseX + piernaTa / 2, baseY + 2 * torsoTa - piernaTa / 2, piernaTa, piernaTa / 2); // Pie izquierdo
        g2d.fillRect(baseX + tamano - piernaTa - piernaTa / 2, baseY + 2 * torsoTa - piernaTa / 2, piernaTa, piernaTa / 2); // Pie derecho

        // Dibuja detalles adicionales en el torso
        g2d.setColor(Color.BLACK);
        g2d.drawLine(baseX + tamano / 4, baseY + torsoTa / 2, baseX + 3 * tamano / 4, baseY + torsoTa / 2); // Línea horizontal

        // Dibuja un micrófono en la mano derecha
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillOval(baseX + tamano + brazoTa / 2 - 5, baseY + torsoTa / 2, brazoTa / 2, brazoTa / 2); // Cabeza del micrófono
        g2d.fillRect(baseX + tamano + brazoTa / 2, baseY + torsoTa / 2 + brazoTa / 2, 5, brazoTa); // Mango del micrófono
    }
}
