/**
 * Clase en la que se implementan los metodos del Taller
 * 
 * @author Daniel Goméz Martinéz
 * @author Daniel Goméz Goméz
 * @author Cesar Andres Garcia Posada
 */


package taller5;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;

public class Graph {

    private HashMap<NodeGraph, LinkedList<NodeGraph>> Graph;

    public Graph() {
        Graph = new HashMap<>();
    }
    
    /**
	* Metodo que añade un nodo al grafo
	* @param Node1 Nodo 1 
	* @param Node2 Nodo 2 al que llega el arco del Nodo 1
	*
	*/

    public void AddNode(NodeGraph Node1, NodeGraph Node2) {
        if (!Graph.containsKey(Node1)) {
            Graph.put(Node1, new LinkedList<>());
            Graph.get(Node1).add(Node2);
        } else {
            Graph.get(Node1).add(Node2);
        }
    }
    
    /**
	* Metodo que muestra los colores con los que quedan los diferentes nodos del grafo
	* 
	*/

    public void Show() {
        if (Graph.isEmpty()) {
            System.out.println("Empty graph");
        } else {
            Iterator it = Graph.keySet().iterator();
            while (it.hasNext()) {
                NodeGraph key = (NodeGraph) it.next();
                System.out.print("* Clave: " + key.GetValue() + " Color: " + key.GetColor());
                for (int i = 0; i < Graph.get(key).size(); i++) {
                    System.out.print(" Node 2: " + Graph.get(key).get(i).GetValue() + " Color: " + Graph.get(key).get(i).GetColor());
                    System.out.println();
                }
            }
        }
    }
    
    /**
	* Metodo que determina un arreglo de los colores 
	* @param n número de colores
        * @return int[] Colores: arreglo de colores
	*/

    public int[] DefineColors(int n) {
        int[] Colors = new int[n];
        for (int i = 0; i < n; i++) {
            Colors[i] = i;
        }
        return Colors;
    }
    
    /**
	* Metodo que determina si es posible colorear un grafo con una cantidad fija de colores, con la
        * condicion de que dos nodos adyasentes no tengan el mismo color
	* @param n número de colores 
	* @return true si es posible, false de lo contrario
	*/

    public boolean Colors(int n) {
        boolean verificar = false;
        int[] Colors = DefineColors(n);
        int color = 0;
        Iterator IteratorGraph = Graph.keySet().iterator();
        while (IteratorGraph.hasNext()) {
            NodeGraph key = (NodeGraph) IteratorGraph.next();
            if (key.GetColor() == -1) {
                key.SetColor(color);
                color++;
            }
            if (n < Graph.get(key).size()) {
                verificar = false;
                break;
            } else {
                verificar = true;
                for (int i = 0; i < Graph.get(key).size(); i++) {
                    if (key.GetColor() == color) {
                        color++;
                        if (Graph.get(key).get(i).GetColor() == -1) {
                            Graph.get(key).get(i).SetColor(color);
                        }
                    } else {
                        if (Graph.get(key).get(i).GetColor() == -1) {
                            Graph.get(key).get(i).SetColor(color);
                        }
                    }
                    color++;
                    if (color == n - 1) {
                        color = 0;
                    }
                }
            }
            /*if (verificar) {
                Show();
            }*/
        }
        
        //System.out.println("Verdad " + verificar);
        
        /*if(verificar)
            return true;
        return false;*/
        return verificar;

    }
    
    /**
	* Metodo que crea nodos
        * 
	*/

    public boolean AddNodes() {
        NodeGraph Node0 = new NodeGraph(0);
        NodeGraph Node1 = new NodeGraph(1);
        NodeGraph Node2 = new NodeGraph(2);
        NodeGraph Node3 = new NodeGraph(3);
        NodeGraph Node4 = new NodeGraph(4);
        NodeGraph Node5 = new NodeGraph(5);
        NodeGraph Node6 = new NodeGraph(6);
        NodeGraph Node7 = new NodeGraph(7);
        NodeGraph Node8 = new NodeGraph(8);
        NodeGraph Node9 = new NodeGraph(9);
        AddNode(Node0, Node1);
        AddNode(Node0, Node2);
        AddNode(Node0, Node5);
        AddNode(Node2, Node3);
        AddNode(Node2, Node8);
        AddNode(Node8, Node6);
        AddNode(Node8, Node9);
        AddNode(Node9, Node7);
        AddNode(Node9, Node5);
        AddNode(Node5, Node4);
        AddNode(Node3, Node4);
        AddNode(Node3, Node7);
        AddNode(Node4, Node6);
        AddNode(Node1, Node6);
        AddNode(Node1, Node7);
        
        
        /*
        AddNode(Node1, Node2);
        AddNode(Node1, Node3);
        AddNode(Node1, Node4);
        AddNode(Node1, Node5);
        AddNode(Node2, Node4);
        AddNode(Node2, Node5);
        AddNode(Node2, Node3);
        AddNode(Node3, Node2);
        AddNode(Node4, Node2);
        AddNode(Node4, Node5);
        AddNode(Node5, Node1);
        AddNode(Node5, Node3);*/
        return Colors(5);
    }
}
