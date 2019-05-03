/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

/**
 *
 * @author Daniel Felipe Gomez Martinez
 * @author Daniel Garcia Garcia
 * @author Cesar Andres Garcia Posada
 */
public class Secuencia {
    /**
	* Metodo que calcula la longitud de la subsecuencia mas larga en comun entre dos cadenas
	* 
	* @param x cadena de caracteres
	* @param y cadena de caracteres
	*/
    public static int lcs(String x, String y) {
        // Tenemos una matriz de tamaño: n*m donde n significa el tamaño de la primera cadena + 1 y m significa el tamaño de la segunda cadena +1 
        int [][] table = new int[x.length()+1][y.length()+1];
        
        //Llenamos la primera columna de 0
        for(int i = 0; i <= x.length(); i++){
            table[i][0] = 0;
        } 
        //Llenamos la primera fila de 0
        for(int j = 0; j <= y.length(); j++){
            table[0][j] = 0;
        }
        
        // Hacemos un recorrido de la matriz
        for(int i=1; i <= x.length();i++){
            for(int j=1;j <= y.length();j++){
                //Si ambas letras son e l tamaño de la subsecuencia mas larga es el encontrado en la posicion diagonal anterior + 1
                if(x.charAt(i-1)== y.charAt(j-1)) 
                table[i][j] = table[i-1][j-1]+1;
                // De lo contrario se coje el maximo entre el tamaño de la subcadena encontrada hasta el momento
                // Esto es las posiciones anteriores a exepcion la diagonal 
                else table[i][j] = Math.max(table[i-1][j],table[i][j-1]);
            }
        }
        // Se retorna el tamaño de la subcadena mas larga encontrada que corresponde al valor de la ultima fila * la ultima columna
        return table[x.length()][y.length()];
    }
    
    // Metodo de test
    public static void main (String[] args) {
        String cadenaA = "casa";
        String cadenaB = "cosa";
        System.out.println("La subsecuencia más larga entre las cadenas " + cadenaA + " y " + cadenaB + " es: " + lcs(cadenaA, cadenaB));
        
    }
}
