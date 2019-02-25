/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;


/**
 * Ejecucion y test
 *
* @autor Daniel Garcia Garcia, Daniel Felipe Gomez Martinez, Cesar Andres Garcia Posada
 **/
public class Lab2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Hamilton ob = new Hamilton();
        ob.AddNodes();
        ob.addEdge(0, 1, 2, false);
        ob.addEdge(0, 2, 3, false);
        ob.addEdge(0, 3, 4, false);
        ob.addEdge(1, 4, 5, false);
        ob.addEdge(4, 2, 6, false);
        ob.addEdge(2, 3, 7, false);
        ob.addEdge(3, 1, 8, false);
        ob.addEdge(3, 4, 9, false);
        ob.addEdge(3, 0, 10, false);
        ob.addEdge(4, 0, 11, true);
    }
    
}
