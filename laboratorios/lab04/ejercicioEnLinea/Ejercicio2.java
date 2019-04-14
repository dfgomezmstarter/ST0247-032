import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Daniel Felipe Gomez Martinez
 * @author Daniel Garcia Garcia
 * @author Cesar Andres Garcia Posada
 *
 * Laboratory online exercise 4.
 */

public class Ejercicio2 {

    /**
     * Method of reading the file.
     *
     * @param txt nema of file.
     */
    public static void leerArchivo(String txt){
        final String nombreDelArchivo = txt+".txt";
        int r=0;
        int d=0;
        int pos=0;
        int maxD=0;
        int maxN=0;
        int[] exit=new int[3];
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreDelArchivo));
            String lineaActual = br.readLine();
            while (lineaActual != null){
                String [] cadenaParticionada = lineaActual.split(" ");
                int [] numbers = new int [cadenaParticionada.length];
                for (int i =0;i<cadenaParticionada.length;i++){
                    numbers[i]= Integer.parseInt(cadenaParticionada[i]);
                }
                if(Arrays.equals(exit, numbers)){
                    System.exit(0);
                } else {
                    if(pos==0){
                        d=numbers[1];
                        r=numbers[2];
                        pos++;
                    }else {
                        if (pos==1) {
                            maxD = encontrarMax(numbers);
                            pos++;
                        }
                        else {
                            maxN = encontrarMax(numbers);
                            solucion(maxD,maxN,r,d);
                            pos=0;
                        }
                    }
                }
                lineaActual = br.readLine();
            }
        }
        catch(IOException ioe) {
            System.out.println("Error leyendo el archivo de entrada: " + ioe.getMessage());
        }
    }

    /**
     * Method that finds the duration of the most delayed route.
     *
     * @param numbers Arrays with routes.
     * @return r most delayed route.
     */
    public static  int encontrarMax(int [] numbers){
        int max=0;
        for (int i=0;i<numbers.length;i++){
            if (numbers[i]>max) max=numbers[i];
        }
        return max;
    }

    /**
     * Method that prints the problem solution.
     *
     * @param maxD most delayed route in the morning.
     * @param maxN most delayed route in the afternoon.
     * @param r value of overtime.
     * @param d duration of route per day.
     */
    public static void solucion(int maxD, int maxN, int r, int d){
        if ((maxD+maxN)<=d){
            System.out.println(0);
        }else {
            System.out.println(Math.abs((maxD+maxN)-d)*r);
        }
    }

    public static void main(String[] args) {
        leerArchivo("test");
    }
}