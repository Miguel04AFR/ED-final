package interfaz;

import java.awt.Color;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import Componente.BotonAnimacionImg;
import cu.edu.cujae.ceis.graph.edge.Edge;
import cu.edu.cujae.ceis.graph.vertex.Vertex;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import logica.*;
import componentesVisuales.BotonAnimacion;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Carrera extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LinkedList<ComponenteVertex> verticesC;
	private LinkedList<EdgeComponente> edgesC;
	private JLabel lblNewLabel;
	private Player exito;
	private Player error;
	private Player ambientacion;
	int  metaOriginal;
	private EstadoSimulacion estadoSimulacion;
	



	public Carrera(Simulacion simu) {
		this.estadoSimulacion = new EstadoSimulacion();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Carrera.class.getResource("/recursos/iconofredd.png")));
		verticesC = new  LinkedList<ComponenteVertex>();
		edgesC = new  LinkedList<EdgeComponente>();

		SonidoAmbientacion();
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
		contentPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);


		ComponenteRobot robot = new ComponenteRobot();
		robot.setForeground(new Color(0, 64, 0));
		robot.setBounds(106, 112, 95, 232);
		// Agrega el componente robot encima de la imagen de fondo
		layeredPane.add(robot, JLayeredPane.PALETTE_LAYER);

		// Fuerza el repintado del componente
		robot.repaint();

		/*   ComponenteVertex a = new ComponenteVertex(new Posicion(false, false));
        a.getBoton().isVisible();
        layeredPane.add(a.getBoton(), JLayeredPane.PALETTE_LAYER);

        ComponenteVertex b = new ComponenteVertex(new Posicion(false, false));
        b.getBoton().isVisible();
        b.getBoton().setBounds(200, 190, 50, 50);
        layeredPane.add(b.getBoton(), JLayeredPane.PALETTE_LAYER);

        EdgeComponente ari = new EdgeComponente(a, b);
        ari.repaint();
        layeredPane.add(ari, JLayeredPane.PALETTE_LAYER);
		 */
		BotonAnimacion btnmcnPasoAPaso = new BotonAnimacion();
		btnmcnPasoAPaso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(simu.getRobot().VertexSituado(simu.getGrafo()).getEdgeList().size()==0)) {//verificar que el robot no este en una isla
					int meta = simu.encontrarMeta();
					if(!(simu.getRobot().VertexSituado(simu.getGrafo()).
							equals(simu.getGrafo().getVerticesList().get(meta)))) {
						Vertex v= simu.getRobot().LLegarMeta(simu.getGrafo(),meta);
						if(!(v==null)) {
							moverRobot(v,simu,verticesC,robot);
							estadoSimulacion.setPasos(estadoSimulacion.getPasos()+1); // Incrementar el contador de pasos
							estadoSimulacion.getDirecciones().add(simu.getGrafo().getVerticesList().indexOf(v)); // Agregar dirección
		                    
							if((simu.getRobot().VertexSituado(simu.getGrafo()).
									equals(simu.getGrafo().getVerticesList().get(meta)) && (meta==metaOriginal))) {//esto es que llego a la meta
								detenerAmbientacion();
								SonidoExito();
								estadoSimulacion.setLlegoMeta(true);
								simu.registrarSimulacion(estadoSimulacion);
								
							}
						
						}
						else {
							if((!(simu.getRobot().VertexSituado(simu.getGrafo()).
									equals(simu.getGrafo().getVerticesList().get(meta))))) {
								lblNewLabel.setText("No hay camino posible para la meta");
								cartelDirecion();
								
							}
							// Buscamos el vértice más cercano accesible
							Vertex camino = encontrarVerticeAccesibleMasCercanoAMeta(simu, verticesC);

							if (camino != null) {
								// La meta se actualiza dentro de encontrarVerticeMasCercano
								// Verifica si hay camino a la nueva meta(el vertex mas cercano)
								if (simu.getRobot().VerificarMeta(simu.getRobot().VertexSituado(simu.getGrafo()), simu.getGrafo(), simu.encontrarMeta())) {
									// Intentamos mover el robot
									Vertex nuevoV = simu.getRobot().LLegarMeta(simu.getGrafo(), simu.encontrarMeta());
									if (nuevoV != null) {
										moverRobot(nuevoV, simu, verticesC, robot);
										estadoSimulacion.setPasos(estadoSimulacion.getPasos()+1); // Incrementar el contador de pasos
										estadoSimulacion.getDirecciones().add(simu.getGrafo().getVerticesList().indexOf(nuevoV));
										
									} else {
										lblNewLabel.setText("No hay camino para la meta,ni al vertice mas cercano");
										cartelDirecion();
										simu.registrarSimulacion(estadoSimulacion);
									}
								} else {
									lblNewLabel.setText("No hay camino para la meta,ni al vertice mas cercano");
									cartelDirecion();
									simu.registrarSimulacion(estadoSimulacion);
								}
							} else {
								lblNewLabel.setText("No hay camino para la meta,ni al vertice mas cercano");
								cartelDirecion();
								simu.registrarSimulacion(estadoSimulacion);
							}
						}

					}//esto es para redirigir la posicion del robot
					

				}
				else {
					lblNewLabel.setText("El vertice de inicio no tiene camino");
					cartelDirecion();
					detenerAmbientacion();
					 SonidoError();
				}
				

			}
			
			
		});
		btnmcnPasoAPaso.setForeground(new Color(0, 0, 205));
		btnmcnPasoAPaso.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
		btnmcnPasoAPaso.setText("Paso a paso");
		btnmcnPasoAPaso.setBorder(new LineBorder(new Color(0, 0, 128), 3));
		btnmcnPasoAPaso.setIcon(new ImageIcon(Carrera.class.getResource("/recursos/five2 (2).jpg")));
		btnmcnPasoAPaso.setBounds(42, 519, 209, 80);

		// Asegurar que el texto se muestre encima del ícono
		btnmcnPasoAPaso.setHorizontalTextPosition(BotonAnimacion.CENTER); // Centra el texto horizontalmente
		btnmcnPasoAPaso.setVerticalTextPosition(BotonAnimacion.CENTER); // Centra el texto verticalmente

		layeredPane.add(btnmcnPasoAPaso, JLayeredPane.PALETTE_LAYER);

		BotonAnimacion btnmcnGrafoAleatorio = new BotonAnimacion();
		btnmcnGrafoAleatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SonidoAmbientacion();
				verticesC.clear();
				edgesC.clear();
				simu.getGrafo().getVerticesList().clear();


				// Limpiar solo los componentes de ComponenteVertex(ojo esto se enseña con un boton) y EdgeComponente
				for (int j = 0; j < layeredPane.getComponentCount(); j++) {
					if ((layeredPane.getComponent(j) instanceof BotonAnimacion && layeredPane.getComponent(j).getWidth()==50 && layeredPane.getComponent(j).getHeight()==50) || 
							layeredPane.getComponent(j) instanceof EdgeComponente) {
						layeredPane.remove(j);
						j--; // Ajustar índice después de eliminar componente


					}
				}
				layeredPane.repaint();
				grafoRandomC(simu, verticesC, layeredPane, edgesC,robot);
			}
		});

		BotonAnimacion btnmcnHastaElFinal = new BotonAnimacion();
		btnmcnHastaElFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(simu.getRobot().VertexSituado(simu.getGrafo()).getEdgeList().size()==0)) {//verificar que el robot no este en una isla
					int meta = simu.encontrarMeta();
					if(!(simu.getRobot().VertexSituado(simu.getGrafo()).
							equals(simu.getGrafo().getVerticesList().get(meta)))) {
						moverRobot(simu.getGrafo().getVerticesList().get(meta), simu, verticesC, robot);
						detenerAmbientacion();
						SonidoExito();
					}
					else {
						if((!(simu.getRobot().VertexSituado(simu.getGrafo()).
								equals(simu.getGrafo().getVerticesList().get(meta))))) {
							lblNewLabel.setText("No hay camino posible para la meta");
							cartelDirecion();

						}
						else {
							Vertex camino = encontrarVerticeAccesibleMasCercanoAMeta(simu, verticesC);
							if (camino != null) 
							moverRobot(camino,simu,verticesC,robot);
							else {
								lblNewLabel.setText("No hay camino para la meta,ni al vertice mas cercano");
								cartelDirecion();
								
							}
						}

					}

				}
			else {

				lblNewLabel.setText("El vertice de inicio no tiene camino");
				cartelDirecion();
				detenerAmbientacion();
				SonidoError();
			}	

		}

		});
		btnmcnHastaElFinal.setVerticalTextPosition(SwingConstants.CENTER);
		btnmcnHastaElFinal.setText("Hasta el final");
		btnmcnHastaElFinal.setIcon(new ImageIcon(Carrera.class.getResource("/recursos/dfdf (1).jpg")));
		btnmcnHastaElFinal.setHorizontalTextPosition(SwingConstants.CENTER);
		btnmcnHastaElFinal.setForeground(Color.YELLOW);
		btnmcnHastaElFinal.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
		btnmcnHastaElFinal.setBorder(new LineBorder(new Color(0, 0, 128), 3));
		btnmcnHastaElFinal.setBounds(306, 519, 232, 80);
		layeredPane.add(btnmcnHastaElFinal);
		btnmcnGrafoAleatorio.setVerticalTextPosition(SwingConstants.CENTER);
		btnmcnGrafoAleatorio.setText("Grafo aleatorio");
		btnmcnGrafoAleatorio.setIcon(new ImageIcon(Carrera.class.getResource("/recursos/five5.jpg")));
		btnmcnGrafoAleatorio.setHorizontalTextPosition(SwingConstants.CENTER);
		btnmcnGrafoAleatorio.setForeground(new Color(255, 20, 147));
		btnmcnGrafoAleatorio.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
		btnmcnGrafoAleatorio.setBorder(new LineBorder(new Color(0, 0, 128), 3));
		btnmcnGrafoAleatorio.setBounds(605, 519, 232, 80);
		layeredPane.add(btnmcnGrafoAleatorio);




		// Inicialización del grafo aleatorio
		grafoRandomC(simu, verticesC, layeredPane, edgesC,robot);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 23));
		lblNewLabel.setBounds(127, 11, 594, 40);
		layeredPane.add(lblNewLabel);
		lblNewLabel.setVisible(false);

	}

	public void grafoRandomC(Simulacion simu,LinkedList<ComponenteVertex> verticesC, JLayeredPane layeredPane, LinkedList<EdgeComponente> edgesC,ComponenteRobot robot) {
		simu.grafoRandom(verticesC,layeredPane);
		simu.asignarAristasAleatorias(verticesC,layeredPane,edgesC);
		simu.asignarMeta(0,simu.getGrafo().getVerticesList().size()-1);
		simu.posRobotIni(verticesC,robot);
		verticesC.get(simu.encontrarMeta()).getBoton().setBackground(Color.PINK);
		metaOriginal=simu.getMeta();
		estadoSimulacion.setPosicionInicial(simu.getRobot().VertexSituado(simu.getGrafo())); // Guardar posición inicial
		estadoSimulacion.setPosicionFinal(simu.getGrafo().getVerticesList().get(metaOriginal));


	}

	public Vertex encontrarVerticeAccesibleMasCercanoAMeta(Simulacion simu, LinkedList<ComponenteVertex> verticesC) {
		Vertex posRobot = simu.getRobot().VertexSituado(simu.getGrafo());
		//Vertex meta = simu.getGrafo().getVerticesList().get(simu.encontrarMeta());

		// Obtener los vértices accesibles desde la posición actual del robot
		LinkedList<Vertex> verticesAccesibles = simu.getRobot().obtenerVerticesAccesibles(posRobot, simu.getGrafo());

		if (verticesAccesibles.isEmpty()) {
			// No hay vértices accesibles
			return null;
		}

		// Encontrar el vértice accesible más cercano a la meta 
		Vertex verticeMasCercano = null;
		double menorDistancia = Double.MAX_VALUE;

		Iterator<Vertex> iterator = verticesAccesibles.iterator();
		while (iterator.hasNext()) {
			Vertex v = iterator.next();
			int indiceV = simu.getGrafo().getVerticesList().indexOf(v);
			ComponenteVertex compV = verticesC.get(indiceV);

			// Calcular la distancia desde el vértice actual a la meta
			double distancia = calcularDistancia(compV, verticesC.get(simu.encontrarMeta()));

			if (distancia < menorDistancia) {
				menorDistancia = distancia;
				verticeMasCercano = v;
			}
		}

		if (verticeMasCercano != null) {
			// Actualizar la meta en simu
			int indiceNuevaMeta = simu.getGrafo().getVerticesList().indexOf(verticeMasCercano);
			simu.setMeta(indiceNuevaMeta);
		}

		return verticeMasCercano;
	}

	private double calcularDistancia(ComponenteVertex v1, ComponenteVertex v2) {
		int x1 = v1.getBoton().getX();
		int y1 = v1.getBoton().getY();
		int x2 = v2.getBoton().getX();
		int y2 = v2.getBoton().getY();

		return Math.hypot(x2 - x1, y2 - y1); //esto lo di en calculo || para los vectores jamas pense que me serviria
	}

	private void moverRobot(Vertex v,Simulacion simu,LinkedList<ComponenteVertex> verticesC,ComponenteRobot robot) {
		int indiceV = simu.getGrafo().getVerticesList().indexOf(v);
		robot.setBounds(
				verticesC.get(indiceV).getBoton().getX() - 14,
				verticesC.get(indiceV).getBoton().getY() - 160,
				robot.getWidth(),
				robot.getHeight());
	}
	public void cartelDirecion() {

		Timer timer = new Timer(1000, new ActionListener() {
			int i=0;
			@Override

			public void actionPerformed(ActionEvent e) {

				if(i<4) {
					if(i%2==0) {
						lblNewLabel.setVisible(true);
					}
					else
						lblNewLabel.setVisible(false);
				}
				else {
					lblNewLabel.setVisible(false);
					((Timer)e.getSource()).stop();
				}
				i++;

			}

		});

		timer.setRepeats(true);
		timer.start();


	}
	
	public  void SonidoError(){
		  Thread hiloContinua = new Thread(new Runnable() {//esto se llama hilo y sirve para que el programa no se congele mientras suena el sonido
		        @Override
		        public void run() {
		try{
		String sonidoError="audios/FNaF_1_-_Risa_de_niña,_normal_(Golden_Freddy).mp3"; //el sonido de error
		 FileInputStream fis = new FileInputStream(sonidoError);
		 error = new Player(fis);
		 error.play();
		}
		catch (FileNotFoundException e){
			System.out.println("archivo no encontrado");
		}
		catch (JavaLayerException e){
			System.out.println("Error al reproducir");
		}

	}
		
	});
		  hiloContinua.start();//esto hace que el programa no se congele
	}
	
	public  void SonidoExito(){
		  Thread hiloContinua = new Thread(new Runnable() {//esto se llama hilo y sirve para que el programa no se congele mientras suena el sonido
		        @Override
		        public void run() {
		try{
		String sonidoExito = "audios/FNaF_-_Grito_de_niños.mp3"; //el sonido de  exito
		 FileInputStream fis = new FileInputStream(sonidoExito);
		 exito = new Player(fis);
		 exito.play();
		}
		catch (FileNotFoundException e){
			System.out.println("archivo no encontrado");
		}
		catch (JavaLayerException e){
			System.out.println("Error al reproducir");
		}

	}
		
	});
		  hiloContinua.start();//esto hace que el programa no se congele
	}
	
	public  void SonidoAmbientacion(){
		  Thread hiloContinua = new Thread(new Runnable() {//esto se llama hilo y sirve para que el programa no se congele mientras suena el sonido
		        @Override
		        public void run() {
		try{
		String sonidoAmbientacion = "audios/MenúFNAF.mp3"; //el sonido de  ambientacion
		 FileInputStream fis = new FileInputStream(sonidoAmbientacion);
		 ambientacion = new Player(fis);
		 ambientacion.play();
		}
		catch (FileNotFoundException e){
			System.out.println("archivo no encontrado");
		}
		catch (JavaLayerException e){
			System.out.println("Error al reproducir");
		}

	}
		
	});
		  hiloContinua.start();//esto hace que el programa no se congele
	}
	
	public void detenerAmbientacion() {
	    if (ambientacion != null) {
	        ambientacion.close();
	    }
	}

}
