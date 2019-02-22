
package taller5;

/**
 *Clase de test y de Ejecución
 * 
 * @author Daniel Goméz Martinéz
 * @author Daniel Goméz Goméz
 * @author Cesar Andres Garcia Posada
 */
public class Taller5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("M Coloring -> " + convert(Check()));
        
    }
    
    public static boolean Check(){
        Graph objeto = new Graph();
        return objeto.AddNodes();
        
    }
    
    static String convert(boolean b) {
		return b ? "correcta" : "incorrecta";
	}
    
}
