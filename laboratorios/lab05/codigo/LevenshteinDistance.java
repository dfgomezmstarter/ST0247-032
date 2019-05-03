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
public class LevenshteinDistance {
        /**
     * Metodo que pretende implementar el funcionamiento del algoritmo levenshtein
     * de dos cadenas a y b
     * @param a cadena de caracteres
     * @param b cadena de caracteres
     */
    
    /****
     * Tenemos una matriz, en la cual colocaremos horizontalmente una palabra y verticalmente otra palabra, esto en la primera fila y columna, adicionalmente al empezar cada palabra tendremos
     * un espacio en blanco para poder calcular la distancia Levenshtein
     * 
     */
    public static int levenshtein(String a, String b) {
        //Realizamos una matriz de tamaño n*m, donde n signfica el tamaño de la cadena b +1 y m significa el tamaño de la cadena a + 1 
        int [][] board = new int[b.length()+1][a.length()+1];
        //Llenamos la primera columna con el costo de pasar de una letra a un espacio en blanco, este costo sera
        // creciente de uno en uno, debido a que aumente la cadena, es decir, un caracter de la cadena, el costo 
        // de cambiar dicho caracter a un espacio en blanco, sera el tamaño de la subcadena leida hasta ese momento
        for (int i=1;i<board.length;i++){
            board[i][0]=i;
        }
        // Se procede allenar de igual forma la primera columna
        for (int i=1;i<board[0].length;i++){
            board[0][i]=i;
        }
        
        // Para llenar el resto de posiciones de la matriz realizamos un recorrido de cada posicion de la matriz
        for (int i=1;i<=b.length();i++){
            for (int j=1;j<board[0].length;j++){
                // Si las letras son las mismas, es decir, la letra que se esta evaluando en ese instante es la
                // misma para ambas cadenas, el costo va a ser igual al que se tenia anteriormente, es deicr
                // al costo en la posicion de la fila -1 y columna -1
                if(b.charAt(i-1)==a.charAt(j-1)){
                    board[i][j]=board[i-1][j-1];
                }else {
                    // Al no se la misma letra la distancia levenshtein entre las cadenas
                    // sera el minimo entre las distancias calculadas en las posiciones adyacentes, es decir,
                    // i, j-1 || i-1, j || i-1, j-1. Todo esto mas 1 que significa el cambio realizado
                    board[i][j]=Math.min(board[i-1][j],Math.min(board[i][j-1],board[i-1][j-1]))+1;
                }
            }
        }
        // Se retorna el costo total que corresponde a la matriz, en la posicion tamaño cadena A,tamaño cadena B 
        return board[b.length()][a.length()];
    }

    /****
     * Main  para ejecutar las pruebas
     * @param args 
     */
    public static void main(String[] args) {
        System.out.println("Levenshtein -> " + convert(testLevenshtein()));
    }

    
    /****
     * Metedo de prueba
     * @return verdadero si se encuentran las respuestas
     */
    static boolean testLevenshtein() {
        String[] wordlist = { "hash", "quantum", "fever", "bench", "long", "blade", "object", "orphanage", "flophouse",
                "fathead" };
        int[][] answers = { { 0, 6, 5, 4, 4, 4, 6, 7, 7, 5 }, { 6, 0, 7, 6, 6, 6, 7, 7, 8, 7 },
                { 5, 7, 0, 4, 5, 5, 5, 9, 8, 5 }, { 4, 6, 4, 0, 4, 4, 4, 8, 8, 7 }, { 4, 6, 5, 4, 0, 4, 6, 7, 7, 7 },
                { 4, 6, 5, 4, 4, 0, 5, 7, 7, 6 }, { 6, 7, 5, 4, 6, 5, 0, 8, 8, 6 }, { 7, 7, 9, 8, 7, 7, 8, 0, 7, 7 },
                { 7, 8, 8, 8, 7, 7, 8, 7, 0, 7 }, { 5, 7, 5, 7, 7, 6, 6, 7, 7, 0 } };
        int n = wordlist.length;
        for (int i = 0; i < n; ++i)
            for (int j = 1; j < n; ++j)
                if (LevenshteinDistance.levenshtein(wordlist[i], wordlist[j]) != answers[i][j])
                    return false;
        return true;
    }

    static String convert(boolean b) {
        return b ? "correcta" : "incorrecta";
    }
    
}
