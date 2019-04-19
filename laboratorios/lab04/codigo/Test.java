/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio4;


/**
 * Clase test para hacer una prueba
 **/
public class Test {

    public static void main(String[] args) {
        DigraphAL g1 = new DigraphAL(4);
        g1.addArc(0, 1, 1);
        g1.addArc(0, 2, 15);
        g1.addArc(0, 3, 6);
        g1.addArc(1, 0, 2);
        g1.addArc(1, 2, 7);
        g1.addArc(1, 3, 3);
        g1.addArc(2, 0, 9);
        g1.addArc(2, 1, 6);
        g1.addArc(2, 3, 12);
        g1.addArc(3, 0, 10);
        g1.addArc(3, 1, 4);
        g1.addArc(3, 2, 18);
        System.out.println("El camino más corto es: " + Camino.recorrido(g1));
    }
}
