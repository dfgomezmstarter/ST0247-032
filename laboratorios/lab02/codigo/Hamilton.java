
package lab2;

/**
 * Implementación encontrar el costo minimo de un grafo completo, de tal manera que se pueda realizar un circutio de Hamilton
 *
* @autor Daniel Garcia Garcia, Daniel Felipe Gomez Martinez, Cesar Andres Garcia Posada
 **/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import javafx.util.Pair;

public class Hamilton {

    
    /**
     * Estructuras de datos necesarias
     * HashMap y LinkedList para el grafo
     * ArrayList para un arreglo de costos de todos los caminos posibles
     */
    
    private HashMap<Integer, LinkedList<Pair<Integer, Integer>>> Graph;
    private LinkedList<Integer> Nodes;
    private int CostoMinimo = 0;
    private ArrayList<Integer> Costos;

    /**
     * Metodo constructor
     */
    
    public Hamilton() {
        Graph = new HashMap();
        Nodes = new LinkedList();
        Costos = new ArrayList();
    }

    /**
     * Metodo para agregar nodos Metodo opcinal
     */
    
    public void AddNodes() {
        for (int i = 0; i < 5; i++) {
            if (!Graph.containsKey(i)) {
                Graph.put(i, new LinkedList());
                Nodes.add(i);
            }
        }
    }
    
    /**
     * Metodo para crear arcos
     * @param node1 Nodo de donde sale el arco
     * @param node2 Nodo a donde llega el arco
     * @param value peso o costo del trayecto
     * @param verificar para comenzar a evaluar el costo minimo
     */

    public void addEdge(int node1, int node2, int value, boolean verificar) {
        if (Graph.containsKey(node1) && Graph.containsKey(node2)) {
            Graph.get(node1).add(new Pair(node2, value));
        } else {
            Graph.put(node1, new LinkedList());
            Graph.put(node2, new LinkedList());
            Graph.get(node1).add(new Pair(node2, value));
            Nodes.add(node1);
            Nodes.add(node1);
        }
        if (verificar) {
            int valor = Graph.size();
            String[] permutaciones = new String[valor];
            Permutations(Nodes, "", valor, valor, permutaciones, Costos);
            System.out.println("El costo minimo es de: " + MostrarCosto());
        }
    }
    
    
    /**
     * Metodo permutations. Calcula todas ls posibles permutaciones, teniendo en cuenta los arcos del grafo
     * @param Nodes LinkedList de los nodos del grafo
     * @param act Genera una cadena con la permutacion encontrada
     * @param n Cantidad de elementos al calcular la permutacion 
     * @param r Cantidad de elementos que se toman para la permutacion 
     * Los paramentos n y r son el total de los nodos del grafo, para poder encontrar todas las posibles permutaciones 
     * @param permutaciones Arreglo de permutaciones validad, es decir, permutaciones encontradas en donde se tienen en cuenta los
     * arcos del grafo
     * @param costos ArrayList de los costos de cada uno de los caminos (permutaciones) de los nodos del grafo
     */

    private void Permutations(LinkedList<Integer> Nodes, String act, int n, int r, String[] permutaciones, ArrayList<Integer> costos) {
        if (n == 0) {
            permutaciones = act.split(",");
            if (Hamiltoniano(permutaciones)) {
                costos.add(Costo(permutaciones));
            }
        } else {
            for (int i = 0; i < r; i++) {
                if (!act.contains(Integer.toString(Nodes.get(i)))) {
                    if (act.length() == 0) {
                        Permutations(Nodes, act + Nodes.get(i) + ",", n - 1, r, permutaciones, costos);
                    } else {
                        permutaciones = act.split(",");
                        int Node = Integer.parseInt(permutaciones[permutaciones.length - 1]);
                        for (int j = 0; j < Graph.get(Node).size(); j++) {
                            if (Graph.get(Node).get(j).getKey() == Nodes.get(i)) {
                                String val = act + Nodes.get(i);
                                Permutations(Nodes, act + Nodes.get(i) + ",", n - 1, r, permutaciones, costos);
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Metodo permutations. para determinar si se realiza un cicuito Hamiltoniano
     * @param permutation Arreglo de los valores de una permutaciones, es decir, de los nodos del recorrido creado
     * @return true si se puede realizar la tarea, de lo contrario false
     */

    private boolean Hamiltoniano(String[] permutation) {
        boolean verificar = false;
        for (int i = 0; i < Graph.get(Integer.parseInt(permutation[permutation.length - 1])).size(); i++) {
            if (Graph.get(Integer.parseInt(permutation[permutation.length - 1])).get(i).getKey() == Integer.parseInt(permutation[0])) {
                verificar = true;
                break;
            } else {
                verificar = false;
            }
        }
        return verificar;
    }
    
    /**
     * Metodo Costo. Metodo para determinar el costo de un camino analizado
     * @param permutation Arreglo de los valores de una permutaciones, es decir, de los nodos del recorrido creado
     * @return el costo del camino
     */

    private int Costo(String[] permutation) {
        int costo = 0;

        for (int i = 0; i < permutation.length - 1; i++) {
            for (int j = 0; j < Graph.get(Integer.parseInt(permutation[i])).size(); j++) {
                if (Integer.parseInt(permutation[i + 1]) == Graph.get(Integer.parseInt(permutation[i])).get(j).getKey()) {
                    costo = costo + Graph.get(Integer.parseInt(permutation[i])).get(j).getValue();
                }
            }
        }
        return costo;
    }
    
    /**
     * Metodo MostrarCosto. Para determinar el costo minimo de un camino de un grafo completo
     * @return el costo minimo
     */

    public int MostrarCosto() {
        CostoMinimo = Costos.get(0);
        for (int i = 0; i < Costos.size(); i++) {
            if(Costos.get(i) <= CostoMinimo){
                CostoMinimo = Costos.get(i);
            }
        }
        return this.CostoMinimo;
    }
}

