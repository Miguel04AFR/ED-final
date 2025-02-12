package interfaz;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import java.awt.Color;
import logica.*;
import cu.edu.cujae.ceis.graph.vertex.Vertex;

public class Carrera extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */

    /**
     * Create the frame.
     */
    public Carrera(Simulacion simu) {
        {
        	{
                Robot robot = simu.getRobot();
                if (robot == null) {
                    robot = new Robot(100, 100, 200); // Inicializa el robot si es null
                }
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setBounds(100, 100, 938, 649);
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setContentPane(contentPane);
                contentPane.setLayout(null);

                JLayeredPane layeredPane = new JLayeredPane();
                layeredPane.setBounds(0, 0, 922, 610);
                contentPane.add(layeredPane);

                // Agrega la imagen de fondo
                JLabel imageLabel = new JLabel(new ImageIcon(Carrera.class.getResource("/recursos/five.jpg")));
                imageLabel.setBounds(0, 0, 922, 610);
                layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);

                // Agrega los otros componentes encima de la imagen de fondo
                ComponenteRobot componenteRobot = new ComponenteRobot();
                componenteRobot.setForeground(new Color(0, 64, 0));
                componenteRobot.setBounds(64, 157, 433, 377);
                layeredPane.add(componenteRobot, JLayeredPane.PALETTE_LAYER);

                // Fuerza el repintado del componente
                componenteRobot.repaint();
            }
        }
    }
}