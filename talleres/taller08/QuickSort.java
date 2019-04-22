/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller8;

import java.util.Arrays;

/**
 *
 * @author Daniel Felipe Gomez Martinez
 * @author Daniel Garcia Garcia
 * @author Cesar Andres Garcia Posada
 */
public class QuickSort {

    /**
     * ***
     * Metodo auxiliar que llama al metodo quickSortBegin que realiza el
     * ordenamiento del arreglo orginal mediante dividir el arregl tantas veces
     * sea necesario
     *
     * @param arreglo arreglo dado
     */
    public static void quickSortAux(int[] arreglo) {
        quickSortBegin(arreglo, 0, arreglo.length - 1);
    }

    /**
     * ***
     * Metodo que encuentra el pivote, es decir un elemento donde se partira el
     * arreglo para poder ordenarlo
     *
     * @param arreglo arreglo dado
     * @param posInicio posicion donde inicia el ordenamiento del subaregglo
     * extraido
     * @param posFinal posicion donde finaiza el rdenamiento del subarreglo
     * extradido
     */
    public static void quickSortBegin(int[] arreglo, int posInicio, int posFinal) {
        if (posInicio < posFinal) {
            int indexParticion = particion(arreglo, posInicio, posFinal);
            quickSortBegin(arreglo, posInicio, indexParticion - 1);
            quickSortBegin(arreglo, indexParticion + 1, posFinal);

        }
    }

    
    /*****
     * Metodo que encuentra el pivote correcto para realizar el ordenamiento
     * El pivote en este caso va a ser siempre el elemento en la ultima posicion
     * Se realiza el algoritmo del quickSort en el cual:
     * 1) Se elige el pivote
     * 2) Se evalua cada element dela rreglo cmparandolo con el pivote, si el elemento es menor al pivote se ubica a la izquierda de este
     * 3) de lo contrario, no realiza ada y al encontrar otro elemento enor, hace un swamp entre dichos elementos, (aux) 
     * @param arreglo Arregl dado
     * @param posInicio posicion inicial de ordenamiento
     * @param posFinal posicion final de ordenamiento
     * @return 
     */
    public static int particion(int[] arreglo, int posInicio, int posFinal) {
        int pivote = arreglo[posFinal];
        int i = (posInicio - 1);

        for (int j = posInicio; j < posFinal; j++) {
            if (arreglo[j] <= pivote) {
                i++;
                int aux = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = aux;
            }
        }
        int aux = arreglo[i + 1];
        arreglo[i + 1] = arreglo[posFinal];
        arreglo[posFinal] = aux;
        return i + 1;
    }
    
     public static void main(String args[]) {
        int arreglo [] = {17,0,1,89,100,84,2,4,7};
        System.out.println("Arreglo Original");
        System.out.println(Arrays.toString(arreglo));
        quickSortAux(arreglo);
        System.out.println("Arreglo ordenado por medio del QuickSort");
        System.out.println(Arrays.toString(arreglo));
    }

}
