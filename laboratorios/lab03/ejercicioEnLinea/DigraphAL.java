/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import javafx.util.Pair;

/**
 * Implementacion de un grafo dirigido usando listas de adyacencia
 *
 * @author Mauricio Toro, Mateo Agudelo, <su nombre>
 */
public class DigraphAL extends Digraph {
	
        private ArrayList<LinkedList<Pair<Integer, Integer>>> Lista;
	/**
	* Constructor para el grafo dirigido
	* @param @vertices el numero de vertices que tendra el grafo dirigido
	*
	*/
	public DigraphAL(int size) {
            super(size);
            Lista = new ArrayList(size);
            for(int i = 0; i < size; i++){
                Lista.add(new LinkedList<Pair<Integer,Integer>>());
            }
	}

	/**
	* Metodo para añadir un arco nuevo, donde se representa cada nodo con un entero
	* y se le asigna un peso a la longitud entre un nodo fuente y uno destino	
	* @param source desde donde se hara el arco
	* @param destination hacia donde va el arco
	* @param weight el peso de la longitud entre source y destination
	*/
	public void addArc(int source, int destination, int weight) {
            /*for(int i = 0; i < Lista.size(); i++){
                if(i == source){
                    Lista.get(i).add(new Pair(destination, weight));
                }
            }*/
            Lista.get(source).add(new Pair(destination, weight));
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
            LinkedList<Pair<Integer,Integer>> list = Lista.get(vertex);
            for(int i = 0; i < list.size(); i++){
                Nodos.add(list.get(i).getKey());
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
            int valor = 0;
            LinkedList<Pair<Integer,Integer>> Nodos = Lista.get(source);
            for(int i = 0; i < Nodos.size(); i++){
                if(Nodos.get(i).getKey() == destination){
                    valor = Nodos.get(i).getValue();
                }
            }
           return valor;
	}
        
        
        /**
         * Metodo para determinar si hay camino entro dos vertices de un grafo Punto Opcional
         * 
         * @param source Vertice 1
         * @param destination Vertice 2
         * @return un booleano con el valor de dicha consulta
         */
        public boolean Camino(int source, int destination){
            if(!Lista.contains(source)){
                return false;
            }else{
                if(!Lista.get(source).contains(destination)){
                    return false;
                }else{
                    return true;
                }
            }
        }
}