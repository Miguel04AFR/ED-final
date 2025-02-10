package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import logica.*;
import java.awt.Color;

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
	        
	        ComponenteRobot componenteRobot = new ComponenteRobot();
	        componenteRobot.setForeground(new Color(0, 64, 0));
	        componenteRobot.setBounds(50, 33, 382, 435);
	        contentPane.add(componenteRobot);

	        // Fuerza el repintado del componente
	        componenteRobot.repaint(); 

	}
}
}