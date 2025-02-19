package logica;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JLayeredPane;

import cu.edu.cujae.ceis.graph.edge.WeightedEdge;
import cu.edu.cujae.ceis.graph.interfaces.ILinkedWeightedEdgeDirectedGraph;
import cu.edu.cujae.ceis.graph.vertex.Vertex;
import interfaz.ComponenteRobot;
import interfaz.ComponenteVertex;
import interfaz.EdgeComponente;

public class Simulacion {
	private ILinkedWeightedEdgeDirectedGraph grafo;
	private Robot robot;
	private int meta;
	
	public Simulacion(ILinkedWeightedEdgeDirectedGraph grafo, Robot robot) {
		setGrafo( grafo);
		setRobot( robot);
		
	}

	public ILinkedWeightedEdgeDirectedGraph getGrafo() {
		return grafo;
	}

	public void setGrafo(ILinkedWeightedEdgeDirectedGraph grafo) {
		this.grafo = grafo;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}
	
	public void asignarMeta(int min,int max) {//normalmente va a ser 0 y size-1 
	        Random random = new Random();
	         int meta=random.nextInt((max - min) + 1) + min;
	         
	         ((Posicion)grafo.getVerticesList().get(meta).getInfo()).setMeta(true);
	         this.meta = meta;//por si acaso
	}
	
	public void grafoRandom(LinkedList<ComponenteVertex> verticesC, JLayeredPane layeredPane) {
	    Random randomVertex = new Random();
	    int cantVert = randomVertex.nextInt((20 - 5) + 1) + 5;
	    int panelX = 68;  // Coordenada x inicial del panel
	    int panelY = 112; // Coordenada y inicial del panel
	    int panelWidth = 769; // Ancho del panel
	    int panelHeight = 385; // Alto del panel
	    int minDistancia = 100; // Distancia mínima entre los vértices

	    for (int i = 0; i < cantVert; i++) {
	        boolean valDistancia = false;
	        int x = 0;
	        int y = 0;

	        while (!valDistancia) {
	            x = panelX + randomVertex.nextInt(panelWidth - 50);
	            y = panelY + randomVertex.nextInt(panelHeight - 50);

	            valDistancia = true;
	            Iterator<ComponenteVertex> iterator = verticesC.iterator();
	            while (iterator.hasNext() && valDistancia) {
	                ComponenteVertex cv = iterator.next();
	                int otraX = cv.getBoton().getX();
	                int otraY = cv.getBoton().getY();

	                // Calcular la distancia entre el nuevo vértice y los otros uno a uno
	                double distancia = Math.sqrt(Math.pow(otraX - x, 2) + Math.pow(otraY - y, 2));
	                if (distancia < minDistancia) {
	                    valDistancia = false;
	                }
	            }
	        }

	        // Crear el vértice y el componente después de encontrar una posición válida
	        CrearVertex();
	        ComponenteVertex c = new ComponenteVertex(getGrafo().getVerticesList().get(i));
	        c.getBoton().setBounds(x, y, 50, 50); // Establecer la posición y el tamaño del botón
	        verticesC.add(c);
	        layeredPane.add(c.getBoton(), JLayeredPane.PALETTE_LAYER);
	    }
	}
	
	public void CrearVertex () {
		grafo.insertVertex(new Posicion(false,false));
	}
	
	public void asignarAristasAleatorias(LinkedList<ComponenteVertex> verticesC,JLayeredPane layeredPane, LinkedList<EdgeComponente> edgesC) {//poner aristas en vertexs 
	    Random random = new Random();
	    int numVertices = grafo.getVerticesList().size();
	    int numAristas = random.nextInt((25 - 10) + 1) + 10; // Entre 10 y 25 aristas

	    for (int i = 0; i < numAristas; i++) {
	        int vertice1 = random.nextInt(numVertices);//vertex del que sale la arista
	        int vertice2 = random.nextInt(numVertices);//vertex al que apunta la arista
	        // Evitar bucles (aristas de un vértice a sí mismo)
	        while (vertice1 == vertice2) {
	            vertice2 = random.nextInt(numVertices);
	        }

	        // Asignar un peso aleatorio entre 1 y 12
	        Tramo peso =new Tramo(random.nextInt((12 - 1) + 1) + 1);
	        WeightedEdge e = new WeightedEdge(grafo.getVerticesList().get(vertice2), peso);
	        grafo.getVerticesList().get(vertice1).getEdgeList().add(e);     
	        ComponenteVertex partida = verticesC.get(vertice1);
	        ComponenteVertex fin = verticesC.get(vertice2);
	        EdgeComponente edgeComponente = new EdgeComponente(e,partida, fin);
	        edgesC.add(edgeComponente);
	        layeredPane.add(edgeComponente, JLayeredPane.PALETTE_LAYER);
	        edgeComponente.repaint();
	        
	        
	    }
	    
	    
	 
	}

	public void posRobotIni(LinkedList<ComponenteVertex> verticesC, ComponenteRobot robot) {//poner al robot en un vertice inicial 
	    Random random = new Random();
	    int posIni = random.nextInt(grafo.getVerticesList().size());
	    while(posIni==encontrarMeta()) {
	    	posIni=random.nextInt(grafo.getVerticesList().size());
	    }
	    robot.setBounds( verticesC.get(posIni).getBoton().getX()-14,verticesC.get(posIni).getBoton().getY()-160,robot.getWidth(),robot.getHeight());
	    ((Posicion)grafo.getVerticesList().get(posIni).getInfo()).setRobot(true);

	}
	
	public int encontrarMeta() {//encontrar la posicion de la meta
		return meta;
	}

	public int getMeta() {
		return meta;
	}

	public void setMeta(int nuevoIndiceMeta) {
	    // Desmarcar la meta anterior
	    ((Posicion)grafo.getVerticesList().get(this.meta).getInfo()).setMeta(false);
	    // Marcar la nueva meta
	    ((Posicion)grafo.getVerticesList().get(nuevoIndiceMeta).getInfo()).setMeta(true);
	    // Actualizar el índice de la meta
	    this.meta = nuevoIndiceMeta;
	}
	
	public void registrarSimulacion(EstadoSimulacion es) {
	    try {
	        RandomAccessFile raf = new RandomAccessFile("recursos/registro.dat", "rw");
	        // Crear encabezado si el archivo está vacío
	        if (raf.length() == 0) {
	            raf.seek(0);
	            String header = "Registro de Simulaciones";
	            byte[] bytes = header.getBytes(StandardCharsets.UTF_8); // Convertir a bytes
	            raf.writeInt(bytes.length);
	            raf.write(bytes);
	            raf.writeInt(0); // Contador de simulaciones
	        }
	        // Lógica para guardar la simulación
	        raf.seek(0);
	        int skip = raf.readInt(); // Leer longitud del encabezado
	        raf.skipBytes(skip); // Saltar el encabezado
	        int count = raf.readInt() + 1; // Incrementar contador de simulaciones
	        raf.seek(raf.getFilePointer() - 4); // Volver al contador
	        raf.writeInt(count); // Escribir nuevo contador
	        
	        // Convertir el objeto EstadoSimulacion a bytes
	        byte[] estadoBytes = convertEstadoSimulacionToBytes(es);
	        
	        // Guardar el estado de la simulación
	        raf.writeInt(estadoBytes.length); // Escribir longitud del estado
	        raf.write(estadoBytes); // Escribir el estado
	        raf.close(); // Cerrar el archivo
	    } catch (IOException e) {
	        throw new RuntimeException(e); // Manejo de excepciones
	    }
	}
	
	
	private byte[] convertEstadoSimulacionToBytes(EstadoSimulacion es) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            DataOutputStream dos = new DataOutputStream(baos);
            
            // Escribir los campos de EstadoSimulacion manualmente
            dos.writeInt(es.getPasos());
            dos.writeInt(es.getDirecciones().size()); // Tamaño de la lista de direcciones
            for (Integer direccion : es.getDirecciones()) {
                dos.writeInt(direccion); // Escribir cada dirección
            }
            dos.writeBoolean(es.getLlegoMeta());
            dos.writeInt(es.getPosicionInicial());
            dos.writeInt(es.getPosicionFinal());
            
            dos.flush(); // Asegurarse de que todos los datos se escriban
            return baos.toByteArray(); // Obtener el arreglo de bytes
        } catch (IOException e) {
            throw new RuntimeException("Error al convertir el objeto EstadoSimulacion a bytes", e);
        }
    }


}