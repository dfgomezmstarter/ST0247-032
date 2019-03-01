/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;


/**
 * Implementacion de un grafo dirigido usando matrices de adyacencia
 *
 * @author Mauricio Toro, Mateo Agudelo, <su nombre>
 */
public class  DigraphAM extends Digraph {
	
        private int [][] Matriz;
	/**
	* Constructor para el grafo dirigido
	* @param size el numero de vertices que tendra el grafo dirigido
	*
	*/
	public DigraphAM(int size) {
		super(size);
                Matriz = new int [size][size];
	
	}

	/**
	* Metodo para añadir un arco nuevo, donde se representa cada nodo con un entero
	* y se le asigna un peso a la longitud entre un nodo fuente y uno destino	
	* @param source desde donde se hara el arco
	* @param destination hacia donde va el arco
	* @param weight el peso de la longitud entre source y destination
	*/
	public void addArc(int source, int destination, int weight) {
		Matriz[source][destination] = weight;
	}

	/**
	* Metodo para obtener una lista de hijos desde un nodo, es decir todos los nodos
	* asociados al nodo pasado como argumento
	* @param vertex nodo al cual se le busca los asociados o hijos
	* @return todos los asociados o hijos del nodo vertex, listados en una ArrayList
	* Para más información de las clases:
 	* @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html"> Ver documentacion ArrayList </a>
	*/
	public ArrayList<Integer> getSuccessors(int vertex) {
            ArrayList<Integer> Nodos = new ArrayList<Integer>();
            for(int j = 0; j < Matriz[vertex].length; j++){
                if(Matriz[vertex][j] != 0){
                    Nodos.add(j);
                }
            }
            return Nodos;
	}

	/**
	* Metodo para obtener el peso o longitud entre dos nodos
	* 
	* @param source desde donde inicia el arco
	* @param destination  donde termina el arco
	* @return un entero con dicho peso
	*/	
	public int getWeight(int source, int destination) {
            return Matriz[source][destination];
	}
        
        
        /**
         * Metodo para determinar si hay camino entro dos vertices de un grafo
         * 
         * @param source Vertice 1
         * @param destination Vertice 2
         * @return un booleano con el valor de dicha consulta
         */
        public boolean Camino(int source, int destination){
            if(Matriz[source][destination] != 0){
                return true;
            }else{
                return false;
            }
        }

}