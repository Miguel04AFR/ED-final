package logica;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cu.edu.cujae.ceis.graph.edge.Edge;
import cu.edu.cujae.ceis.graph.edge.WeightedEdge;
import cu.edu.cujae.ceis.graph.interfaces.ILinkedWeightedEdgeDirectedGraph;
import cu.edu.cujae.ceis.graph.vertex.Vertex;
import logica.Posicion;
import cu.edu.cujae.ceis.graph.edge.*;
import logica.Simulacion;



public class Robot {
	private int x;
	private int y;
	private int tamano;





	public Robot(int x, int y, int tamano) {
		super();
		setX(x);
		setY(y);
		setTamano(tamano);
	}


	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getTamano() {
		return tamano;
	}
	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

	public Vertex VertexSituado(ILinkedWeightedEdgeDirectedGraph grafo) {//metodo para situar al robot
		Iterator<Vertex> i=grafo.getVerticesList().iterator();
		boolean encontrado=false;
		Vertex v = null;
		while(i.hasNext() && !encontrado) {
			v=i.next();
			if(((Posicion)v.getInfo()).getRobot()){
				encontrado=true;
			}
		}
		return v;

	}

	public boolean VerificarMeta(Vertex a, ILinkedWeightedEdgeDirectedGraph grafo, int meta) {
	    LinkedList<Vertex> visitados = new LinkedList<>();//declaro este metodo para guardar en esta lista los vertex visitados
	    return VerificarMetaRecursivo(a, grafo, meta, visitados);
	}

	private boolean VerificarMetaRecursivo(Vertex a, ILinkedWeightedEdgeDirectedGraph grafo, int meta, LinkedList<Vertex> visitados) {
	    if (a.equals(grafo.getVerticesList().get(meta))) {
	        return true;
	    }

	    // Añadir el vertex actual a los visitados
	    if (visitados.contains(a)) {
	        // Si el nodo ya estaba en la lista, significa que ya fue visitado, así que regresamos false
	        return false;
	    }
	    visitados.add(a);

	    // Copiar la lista de aristas antes de iterar,da error sino hago la copia
	    LinkedList<Edge> copia = new LinkedList<>(a.getEdgeList());
	    Iterator<Edge> i = copia.iterator();

	    while (i.hasNext()) {
	        Vertex v = i.next().getVertex();
	        if (VerificarMetaRecursivo(v, grafo, meta, visitados)) {
	            return true;
	        }
	    }

	    return false;
	}

	public Vertex LLegarMeta(ILinkedWeightedEdgeDirectedGraph grafo, int meta) {//metodo para que el robot llegue a la meta de la manera mas optima
		Vertex posRobot= VertexSituado(grafo);
		int minimo=2000;
		Vertex vp=null;
		
		Iterator<Edge> i= posRobot.getEdgeList().iterator();
		if (i.hasNext()) {
		 vp=i.next().getVertex();
		boolean primeroPuede=false;
		if(VerificarMeta(vp,grafo,meta)) {
			primeroPuede=true;
			minimo=tamano(vp,grafo,meta,0);
		}
		else {
			while(i.hasNext() && !primeroPuede) {
				if(VerificarMeta(i.next().getVertex(),grafo,meta)){
					primeroPuede=true;
					minimo=tamano(vp,grafo,meta,0);
				}
			}
		}

		while(i.hasNext() && primeroPuede) {
			Vertex v=i.next().getVertex();
			if(VerificarMeta(v,grafo,meta)) {
			if(tamano(v,grafo,meta,0)<minimo) {
				minimo=tamano(v,grafo,meta,0);
				vp=v;

			}
			}
		}
			if(!primeroPuede)
				vp=null;
			else {
				((Posicion)posRobot.getInfo()).setRobot(false);
				((Posicion)vp.getInfo()).setRobot(true);

			}
		}
		return vp;
	}

	public void registrarSimulacion(int pasos, List<String> direcciones, boolean llegoMeta, Vertex posicionInicial, Vertex meta) {
        String nombreArchivo = "recursos/registro_simulacion.dat"; // Nombre del archivo donde se guardarán los datos
        File archivo = new File(nombreArchivo);

        try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw")) {
        	// Mover el puntero al final del archivo para agregar nuevos registros
            raf.seek(raf.length());

            // Convertir los datos a un arreglo de bytes
            byte[] salidaBytes = ("Salida: " + posicionInicial.getInfo() + "\n").getBytes();
            byte[] metaBytes = ("Meta: " + meta.getInfo() + "\n").getBytes();
            byte[] pasosBytes = ("Pasos: " + pasos + "\n").getBytes();
            byte[] direccionesBytes = ("Direcciones: " + String.join(", ", direcciones) + "\n").getBytes();
            byte[] llegoMetaBytes = ("Llegó a la meta: " + (llegoMeta ? "Sí" : "No") + "\n").getBytes();
            byte[] separadorBytes = "--------------------------------------------------\n".getBytes(); // Separador para registros

            // Escribir la longitud de cada registro y luego el registro en el archivo
            raf.writeLong(salidaBytes.length);
            raf.write(salidaBytes);
            raf.writeLong(metaBytes.length);
            raf.write(metaBytes);
            raf.writeLong(pasosBytes.length);
            raf.write(pasosBytes);
            raf.writeLong(direccionesBytes.length);
            raf.write(direccionesBytes);
            raf.writeLong(llegoMetaBytes.length);
            raf.write(llegoMetaBytes);
            raf.writeLong(separadorBytes.length);
            raf.write(separadorBytes);

            System.out.println("Registrando simulación...");
        } catch (IOException e) {
        	System.err.println("Error al escribir en el archivo: " + e.getMessage());
            e.printStackTrace(); // Manejo de excepciones
        }
    }


	public int tamano(Vertex a, ILinkedWeightedEdgeDirectedGraph grafo, int meta, int contAc) {//distancia mas cortga
	    LinkedList<Vertex> visitados = new LinkedList<>();//declaro este metodo para guardar en esta lista los vertex visitados
	    return tamanoRecursivo(a, grafo, meta, contAc, visitados);
	}

	private int tamanoRecursivo(Vertex a, ILinkedWeightedEdgeDirectedGraph grafo, int meta, int contAc, LinkedList<Vertex> visitados) {
	    if (a.equals(grafo.getVerticesList().get(meta))) {
	        return contAc;
	    }

	    if (visitados.contains(a)) {
	        // Si el vértice ya estaba en la lista, significa que ya fue visitado, así que regresamos -1
	        return -1;
	    }
	    visitados.add(a);

	    int distanciaMinima = 1000;
	    Iterator<Edge> ia = a.getEdgeList().iterator();
	    boolean tieneAristas = false;

	    while (ia.hasNext()) {
	        tieneAristas = true;
	        WeightedEdge we = (WeightedEdge) ia.next();
	        Vertex v = we.getVertex();
	        int cont = ((Tramo) we.getWeight()).getKm() + contAc;

	        int distancia = tamanoRecursivo(v, grafo, meta, cont, visitados);

	        if (distancia != -1 && distancia < distanciaMinima) {
	            distanciaMinima = distancia;
	        }
	    }

	    if (!tieneAristas) {
	        // Si el vértice no tiene aristas salientes y no es la meta
	        return -1;
	    }

	    return distanciaMinima;
	}
	
	public LinkedList<Vertex> obtenerVerticesAccesibles(Vertex origen,ILinkedWeightedEdgeDirectedGraph grafo) {
		LinkedList<Vertex> visitados = new LinkedList<>();
	    Deque<Vertex> cola = new LinkedList<>();
	    cola.add(origen);
	    visitados.add(origen);

	    while (!cola.isEmpty()) {
	        Vertex actual = cola.poll();
	        Iterator<Edge> iv=actual.getEdgeList().iterator();
	       while(iv.hasNext()) {
	            Vertex vecino = iv.next().getVertex();
	            if (!visitados.contains(vecino)) {
	                visitados.add(vecino);
	                cola.add(vecino);
	            }
	        }
	    }

	    return visitados;
	}
}

