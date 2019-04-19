/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio4;

/**
 *
 * @author Daniel Felipe Gomez Martinez
 * @author Daniel Garcia Garcia
 * @author Cesar Andres Garcia Posada
 * 
 */

/**
 * Method Camino
 * @param g Digraph g: Grafo en el que los vertices representan los puntos de la ciudad
 * @return r Costo minimo del camino 
 * Funcionamiento del metodo:
 * 1) Se recibe un grafo
 * 2) Se tiene un arreglo de visitados, para no visitar un mismo vertice dos veces y evitar aumentar el costo
 * 3) Se crea la variable de costo minimo
 * 4) Se hace un recorrido del grafo (vertice a vertice)
 * 4.1) Se hace un recorrido de los vertices adyacentes a cada vertice (vertice a vertice)
 * 5) Se determina el costo de cada arco
 * 6) Se realiza la comparaciones necesarias
 * 7) Se asignan costos y vertices visitados
 * 7) Se inicalizna nuevamnete variables auxiliares 
 * 8) re retorna el costo minimo d eunv ertice a todos volviendo al vertice inicial
 **/
public class Camino {

    public static int recorrido(Digraph g) {
        boolean[] path = new boolean[g.size]; //Arreglo de visitados
        int pos = 0; // Variable auxiliar para determinar el vertice visitado
        int r = 0; // Variable para el costo (respuesta)
        int comp = Integer.MAX_VALUE; //Costo minimo de un vertice a otro, se inicializa en infinito para ir reduciendo cada vez más el costo
        for (int i = 0; i < g.size; i++) { // Recorrido de cada vertice del grafo
            for (int j = 0; j < g.getSuccessors(i).size(); j++) { // Recorrido de los sucesores de cada vertice del grafo
                /**
                 * Comparaciones:
                 * 1) Si se llego al ultimo vertice del grafo
                 * Condicion de parado
                 **/
                if (i + 1 == g.size) {
                    comp = g.getWeight(i, g.getSuccessors(i).get(0)); // Se calcula el costo entre le ultimo vertice encontrado y el vertice incial con la funcion getWeight.
                                                                        // Debido a que hay que llegar al vertice inicial
                /**
                 * 2) Si no se ha visitado el vertice analizado, si el costo encontrado es menor o igual al que se tiene y 
                 * si el vertice A, (vertice del primer ciclo) y el vertice B (vertice del segundo ciclo) son diferentes.
                 * Esto para evitar hacer un cilo en un mismo vertice (Sin embargo se sabe que el costo de ir de un vertice a el mismo es 0)
                 **/
                } else if (g.getWeight(i, g.getSuccessors(i).get(j)) <= comp && path[j] == false && i != g.getSuccessors(i).get(j)) {
                    // Si se cumplen las condiciones se determina el costo entre los vertices analizados con la funcion getWeight
                    comp = g.getWeight(i, g.getSuccessors(i).get(j));
                    //Se determina el vertice visitado en el arreglo de vertices visitados
                    if (g.getSuccessors(i).get(j) == 0) {
                        pos = 0;
                    } else {
                        pos = g.getSuccessors(i).get(j) - 1;
                    }
                }
            }
            // Se le asigna al vertice de visitados, el vertice que se visito, es decir, se coloca en true la posicionq ue corresponde al vertice visitado
            path[pos] = true;
            // Al costo total se de suma el costo calculado
            r += comp;
            // se vuelve a colocar el costo de un vertice a otro en infinito para nuevos calculos
            comp = Integer.MAX_VALUE;
        }
        // Se retorna el costo
        return r;
    }
}
