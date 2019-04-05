/*
 * Daniel Felipe Gomez Martinez, Daniel Garcia Garcia, Cesar Andres Garcia Posada
 */
package taller;

public class Taller{

	/**
	* Metodo que calcula la longitud de la subsecuencia mas larga en comun entre dos cadenas
	* 
	* @param x cadena de caracteres
	* @param y cadena de caracteres
	*/
    public static int lcs(String x, String y) {
        int [][] table = new int[x.length()+1][y.length()+1];
        for(int i = 0; i <= x.length(); i++){
            table[i][0] = 0;
        } 
        for(int j = 0; j <= y.length(); j++){
            table[0][j] = 0;
        }
        
        for(int i=1; i <= x.length();i++){
            for(int j=1;j <= y.length();j++){
                if(x.charAt(i-1)== y.charAt(j-1)) 
                table[i][j] = table[i-1][j-1]+1;
                else table[i][j] = Math.max(table[i-1][j],table[i][j-1]);
            }
        }
        return table[x.length()][y.length()];
    }
    
    public static void main (String[] args) {
        String cadenaA = "casa";
        String cadenaB = "cosa";
        System.out.println("La subsecuencia más larga entre las cadenas " + cadenaA + " y " + cadenaB + " es: " + lcs(cadenaA, cadenaB));
        
    }
}
