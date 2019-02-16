/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller4;

import java.util.ArrayList;

/**
 * Clase en la cual se implementan los metodos del Taller 4
 *
 * @author Mauricio Toro, Andres Paez
 */
public class Taller4 {

    /**
     * Metodo auxiliar para llamar el metodo hayCaminoDFS posterior
     *
     * @param g grafo dado
     * @param v vertices
     * @param w vertice
     * @return true si hay camino, false de lo contrario
     */
    public static boolean hayCaminoDFS(DigraphAL g, int v, int w) {
        boolean[] visitados = new boolean[g.size()];
        return hayCaminoDFS(g, v, w, visitados);
    }

    /**
     * Metodo que recorre el grafo por medio de dfs
     *
     * @param g grafo dado
     * @param v vertices
     * @param w vertice
     * @param visitados ayuda a tener un conteo acerca de que nodos han sido o
     * no visitados
     * @return true si hay camino, false de lo contrario
     */
    private static boolean hayCaminoDFS(Digraph g, int nodoA, int nodoB, boolean[] visitados) {
        visitados[nodoA] = true;
        if (nodoA == nodoB) {
            return true;
            
        } else {
            ArrayList<Integer> Hijos = g.getSuccessors(nodoA);
            if (Hijos != null) 
                
                for (Integer hijo : Hijos) 
                    if (!visitados[hijo] && hayCaminoDFS(g, hijo, nodoB, visitados)) 
                        return true;
                return false;
        }
    }

    /**
     * Metodo que recorre el grafo por medio de dfs teniendo en cuenta que se
     * quiere encontrar el de menor costo
     *
     * @param g grafo dado
     * @param inicio nodo desde el cual empieza el recorrido
     * @param fin nodo donde termina el recorrido
     * @return cual es el costo que tiene ir desde inicio a fin
     */
    private static int costoMinimo(Digraph g, int o, int d, boolean[] visitados) {
        visitados[o] = true;
        int costoMinimo = Integer.MAX_VALUE;
        int costoCamino = 0;
        if (o == d) {
            return costoMinimo;
        } else {
            ArrayList <Integer> hijos = g.getSuccessors(o);
            if(hijos != null){
                for (Integer hijo:hijos) {
                if (!visitados[hijo]) {
                    visitados[hijo] = true;
                    int re =costoMinimo(g, hijo,d, visitados);
                    if (re == Integer.MAX_VALUE){
                        costoCamino = re;
                    } else{
                        costoCamino = g.getWeight(o, d) + re;
                    }
                    if (costoCamino < costoMinimo){
                        costoMinimo = costoCamino;
                    }
                }
            }
            }
            
            return costoMinimo;
        }

    }

    /**
     * Metodo auxiliar que llama al metodo recorrido posterior con cada uno de
     * los vertices
     *
     * @param g grafo dado
     * @return cual es el costo que tiene
     */
    public static int recorrido(Digraph g, int NodeA, int NodeB) {
            boolean[] visitados = new boolean[g.size()];
            return costoMinimo(g, NodeA, NodeB, visitados);
	}

	/**
	* Metodo que recorre todo el grafo con la intencion de buscar un
	* camino que represente el menor costo pasando por todos los vertices exactamente
	* una vez y vuelva al nodo inicial
	* @param g grafo dado 
	* @param v vertice inicial
	* @param unvisited arreglo de nodos aun no visitados
	* @return cual es el costo que tiene
     *//*
	private static int recorrido(Digraph g, int v, int[] unvisited) {
		
	}


	/**
	* Metodo que dada un conjunto de costos en cada arco, encuentra el camino desde el nodo v
	* @param g grafo dado 
	* @param v vertice inicial
	* @param coso arreglo de valores que tiene de ir de un nodo a otro
	* 
     *//*
	private static void dfs(Digraph g, int v, int[] costo) {
		
	}*/


    public static void main(String[] args) {
        DigraphAL g = new DigraphAL(8);
        g.addArc(1, 5, 10);
        g.addArc(0, 1, 20);
        g.addArc(4, 1, 50);
        g.addArc(4, 6, 30);
        g.addArc(0, 6, 90);
        g.addArc(6, 0, 20);
        g.addArc(0, 3, 80);
        g.addArc(5, 3, 40);
        g.addArc(3, 6, 20);
        g.addArc(5, 2, 10);
        g.addArc(2, 5, 50);
        g.addArc(3, 2, 10);
        g.addArc(2, 3, 10);
        g.addArc(2, 7, 20);
        System.out.println(recorrido(g, 0, 6));

    }
}
