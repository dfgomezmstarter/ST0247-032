/**
 * Clase en la cual se implementan los metodos del Taller 5.
 * @author Daniel García García , Cesar Andres Garcia Posada  y Daniel Felipe Gómez Martínez.
 */
public class Taller5VersionCorta {

    /**
     * Método que dado un grafo y un numero m, verifica que el grafo se pueda colorear
     * con los colores ingresasdos.
     *
     * @param g grafo dado
     * @param m numero de colores
     * @return true si es posible, false de lo contrario
     */
    public static boolean mColoring(Digraph g, int m) {
        for (int i=0;i<g.size;i++){
         if(m<g.getSuccessors(i).size()){
             return false;
         }
        }
        return true;
    }
}