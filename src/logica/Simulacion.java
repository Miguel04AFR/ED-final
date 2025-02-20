package logica;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
	private List<EstadoSimulacion> simulaciones1 = new ArrayList<>(); // Simulaciones que llegan a la meta
    private List<EstadoSimulacion> simulaciones2 = new ArrayList<>(); // Simulaciones que no llegan a la meta
	
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
	
	
	//.dat
	public void registrarSimulacion(EstadoSimulacion es) throws CloneNotSupportedException {
	    try (RandomAccessFile raf = new RandomAccessFile("recursos/registro.dat", "rw")) {
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
	        
	        EstadoSimulacion clonedSimulation = (EstadoSimulacion)es.clone();
	        
	        // Convertir el objeto EstadoSimulacion a bytes
	        byte[] estadoBytes = convertEstadoSimulacionToBytes(es);
	        
	        // Guardar el estado de la simulación
	        raf.writeInt(estadoBytes.length); // Escribir longitud del estado
	        raf.write(estadoBytes); // Escribir el estado
	        
	        if (clonedSimulation.getLlegoMeta()) {
	            simulaciones1.add(clonedSimulation); // Agregar la simulación actual
	        } else {
	            simulaciones2.add(clonedSimulation); // Agregar la simulación actual
	        }
	        
	        // Leer el estado de la simulación desde el archivo para verificar
	        long position = raf.getFilePointer() - estadoBytes.length - 4; // Retroceder a la posición donde se escribió la simulación
	        verificarSimulacionRegistrada(position, estadoBytes.length);
	        
	        // Reiniciar el estado de la simulación actual para la próxima ejecución
	        /*es.reset();*/
	    } catch (IOException e) {
	        throw new RuntimeException(e); // Manejo de excepciones
	    }
	}

	private void verificarSimulacionRegistrada(long position, int length) {
	    try (RandomAccessFile raf = new RandomAccessFile("recursos/registro.dat", "r")) {
	        raf.seek(position); // Ir a la posición donde se escribió la simulación
	        int readLength = raf.readInt(); // Leer la longitud del estado
	        if (readLength != length) {
	            throw new RuntimeException("La longitud del estado leído no coincide con la longitud esperada.");
	        }
	        byte[] readBytes = new byte[readLength];
	        raf.readFully(readBytes); // Leer el estado desde el archivo
	        
	        // Imprimir los bytes leídos para verificar
	        System.out.printf("Estado de la simulación registrado correctamente: %s%n", Arrays.toString(readBytes));
	    } catch (IOException e) {
	        throw new RuntimeException("Error al verificar la simulación registrada", e);
	    }
	}
	
	//Reportes
	
	 public void generarCSVAlCerrar() {
		 if(simulaciones1.size()>0) {
	        GenerarCSV("recursos/Registro_1.csv", simulaciones1);
		 }
		 if(simulaciones2.size()>0) {
	        GenerarCSVRegistro2("recursos/Registro_2.csv", simulaciones2);
	    }
	 }
	 
		public void GenerarCSV(String ruta, List<EstadoSimulacion> simulaciones1) {
		    // Lógica para generar el archivo CSV
			// Ordenar las simulaciones por la cantidad de pasos de menor a mayor
	        simulaciones1.sort(Comparator.comparingInt(EstadoSimulacion::getPasos));

	        // Lógica para generar el archivo CSV
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta, true))) { // Modo de adición
	            // Si el archivo es nuevo, escribir el encabezado
	            if (new File(ruta).length() == 0) {
	                writer.write("Posicion Inicial,Posicion Final,Cantidad de Pasos,Fecha\n");
	            }
	            for (EstadoSimulacion es : simulaciones1) {
	                writer.write(es.getPosicionInicial() + "," + es.getPosicionFinal() + "," + es.getPasos() + "," + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");
	                System.out.println("Bien");
	            }
	        } catch (IOException e) {
	            System.err.println("Error al escribir el archivo CSV: " + e.getMessage());
	        }
	    }
		
		
		public void GenerarCSVRegistro2(String ruta, List<EstadoSimulacion> simulaciones2) {
	        // Ordenar las simulaciones por distancia faltante de menor a mayor
	        simulaciones2.sort(Comparator.comparing(EstadoSimulacion::getDistancia));

	        // Lógica para generar el archivo CSV
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta, true))) { // Modo de adición
	            // Si el archivo es nuevo, escribir el encabezado
	            if (new File(ruta).length() == 0) {
	                writer.write("Posicion Inicial,Posicion Final,Distancia Faltante,Fecha\n");
	            }
	            for (EstadoSimulacion es : simulaciones2) {
	                writer.write(es.getPosicionInicial() + "," + es.getPosicionFinal() + "," + es.getDistancia() + "," + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");
	                System.out.println("Bien");
	            }
	        } catch (IOException e) {
	            System.err.println("Error al escribir el archivo CSV: " + e.getMessage());
	        }
		}




		
		
		//Conversiones

	
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
	
	
	/*private EstadoSimulacion convertBytesToEstadoSimulacion(byte[] bytes) {
	    EstadoSimulacion estado = new EstadoSimulacion();
	    try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
	         DataInputStream dis = new DataInputStream(bais)) {
	        
	        // Leer los campos de EstadoSimulacion
	        estado.setPasos(dis.readInt());
	        
	        // Leer la lista de direcciones
	        int size = dis.readInt(); // Tamaño de la lista de direcciones
	        ArrayList<Integer> direcciones = new ArrayList<>();
	        for (int i = 0; i < size; i++) {
	            direcciones.add(dis.readInt());
	        }
	        estado.setDirecciones(direcciones);
	        
	        // Leer el estado de llegoMeta
	        estado.setLlegoMeta(dis.readBoolean());
	        
	        // Leer las posiciones inicial y final
	        estado.setPosicionInicial(dis.readInt());
	        estado.setPosicionFinal(dis.readInt());
	        
	    } catch (IOException e) {
	        System.err.println("Error al convertir bytes a EstadoSimulacion: " + e.getMessage());
	    }
	    return estado;
	}*/
	
	
	
	


}