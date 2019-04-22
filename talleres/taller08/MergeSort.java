/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller8;


import java.util.Arrays;

/**
 *
 * @author Daniel Felipe Gomez martinez
 * @author Daniel Garcia Garcia
 * @author Cesar Andres Garcia Posada
 */
public class MergeSort {
    /**
     * *
     * Método auxiliar que llama al metodo que realiza la funcion de MergeSort
     *
     * @param arreglo arreglo de enteros que se recibe
     */
    public static void mergeSortAux(int[] arreglo) {
        mergeSort(arreglo, 0, arreglo.length-1);
    }

    /**
     * *
     * Metodo que realiza la siguientes funciones: 1) Divide el arreglo en dos
     * subarreglos 1.1) cada mitad la vuelve a dividir hasta el maximo 2) Llena
     * cada mitad con el valor correspondiente del arreglo original 3) Realiza
     * las comparaciones y une los arreglos
     *
     * @param arreglo arreglo de enteros que se recibe
     * @param posInicio posicion de inicio de ordenamiento de cada submitad
     * @param posFinal posicion final de ordenamiento de cada submitad
     */
    public static void mergeSort(int[] arreglo, int posInicio, int posFinal) {
        if (posInicio < posFinal) {
            int mitad = (posInicio + posFinal) / 2;
            mergeSort(arreglo, posInicio, mitad);
            mergeSort(arreglo, mitad + 1, posFinal);
            mergeFinaly(arreglo, posInicio, mitad, posFinal);
            
        }
    }

    /**
     * *
     *
     * @param arreglo arreglo que se recibe
     * @param posInicio posicion Inicial de ordenamiento de cada subarreglo
     * @param mitad Donde termina el primer subarreglo, es decir, la parte
     * izquierda, de igual forma este valor más uno (+1) es donde comienza el
     * otro subarreglo, es decir, la parte derecha
     * @param posFinal posicion final de ordenamiento
     */
    public static void mergeFinaly(int[] arreglo, int posInicio, int mitad, int posFinal) {
        int tamA = mitad - posInicio + 1; //Se determia el tamaño del arreglo de la parte izquierda
        int tamB = posFinal - mitad; // Se determina el mañano del arreglo de la parte derecha
        int[] left = new int[tamA]; // Se crean el arreglo de la parte izquierda
        int[] right = new int[tamB]; // Se crea el arreglo de la parte derecha

        // Se llena el arreglo de la parte izquierda correspondiente a los valores del arreglo original que van hasta el tamaño de este arreglo
        for(int i = 0; i < tamA; i++){
            left[i] = arreglo[posInicio + i];
        }
        
        // Se llena el arreglo de la parte derecha correspondiente a los valores del arreglo original que van hasta el tamaño de este arreglo
        for(int i = 0; i < tamB; i++){
            right[i] = arreglo[mitad + 1 + i];
        }
        // Variables para controlar la unión de los arreglos para formar el arreglo ordenado
        int i = 0;
        int j = 0;

        while (i < tamA && j < tamB) {
            // comparaciones:
            // Si el valor de la posicion a en el arreglo de la izquierda es menor al valor en la posicion b en el arreglo de la derecha
            // entonces el valor a se coloca de primero en la posicion c del arreglo original
            // de otro modo se agrega el valor b en la posicion c del arreglo original
            if (left[i] <= right[j]) {
                arreglo[posInicio] = left[i];
                i++;
            } else {
                arreglo[posInicio] = right[j];
                j++;
            }
            posInicio++;
        }

        // una vez terminadas las comparaciones, puede que queden elementos sin comparar, esto debido a tamaños impares del arreglo original
        // Por ende se colocan los elementos sobrantes de cada sub arreglo
        while (i < tamA) {
            arreglo[posInicio] = left[i];
            i++;
            posInicio++;
        }

        while (j < tamB) {
            arreglo[posInicio] = right[j];
            j++;
            posInicio++;
        }

    }

    
    
    
    
    
    
    
    /**
     * Metodo auxiliar que llama al método posterior
     *
     * @param a un arreglo con elementos
     */
    public static void quicksort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    /**
     * Metodo que pretende implementar el funcionamiento del algoritmo MergeSort
     * de un conjunto de elementos
     *
     * @param a un arreglo con elementos
     *
     * para mas informacion ver
     * @see <a href="https://www.youtube.com/watch?v=PgBzjlCcFvc">
     *
     */
    public static void quickSort(int a[], int l, int r) {
        if (l < r) {
            int index = findIndex(a, l, r);
            quickSort(a, l, index - 1);
            quickSort(a, index + 1, r);
        }
    }

    /**
     * Metodo que toma el último elemento como indice y cambia todos los
     * elementos menores a una posición antes de este elemento y posiciona los
     * elementos mayores después de ese índice
     *
     * @param arr un arreglo de elementos
     * @param l indice inicial del subconjunto que se quiere particionar
     * @param r indice final del subconjunto que se quiere partcionar
     * @return el índice donde se parte el conjunto de números
     */
    private static int findIndex(int arr[], int l, int r) {
        int index = arr[r];
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (arr[j] <= index) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[r];
        arr[r] = temp;

        return i + 1;
    }

    public static void main(String args[]) {
        int arreglo[] = {12, 11, 13, 5, 6, 7};
        System.out.println("Arreglo orignal");
        System.out.println(Arrays.toString(arreglo));
        System.out.println("Arreglo ordenado por medio de MergeSort");
        mergeSortAux(arreglo);
        System.out.println(Arrays.toString(arreglo));
    }
}