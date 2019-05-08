import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Implementacion de un algoritmo para asignar vehiculos compartidos
 * Estructura de datos utilizada: Grafo con Matrices de Adyacencia
 * Complejidad: Peor Caso y Mejor Caso O(n*n)
 *
 * @author Mauricio Toro
 * @version 1
 */
public class VehiculosCompartidos {
//
//
//    /**
//     * Metodo para leer un archivo con los duenos de vehiculos y la empresa
//     * Complejidad: Mejor y peor caso es O(n*n), donde n es son los duenos de vehiculos y la empresa
//     *
//     * @param numeroDePuntos El numero de puntos es 1 de la empresa y n-1 de los duenos de vehiculos
//     * @return un grafo completo con la distancia mas corta entre todos los vertices
//     */
//    public static DigraphAM leerArchivo(int numeroDePuntos, float p) {
//        //final String nombreDelArchivo = "dataset-ejemplo-U="+numeroDePuntos+"-p="+p+".txt";
//        final String nombreDelArchivo = "numeroDePuntos.txt";
//        DigraphAM grafo = new DigraphAM(numeroDePuntos);
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(nombreDelArchivo));
//            String lineaActual = br.readLine();
//            for (int i = 1; i <= 3; i++) // Descarta las primeras 3 lineas
//                lineaActual = br.readLine();
//            lineaActual = br.readLine();
//            for (int i = 1; i <= numeroDePuntos; i++) { //Descarta los nombres y coordenadas de los vertices
//                lineaActual = br.readLine();
//            }
//            for (int i = 1; i <= 3; i++) // Descarta las siguientes 3 lineas
//                lineaActual = br.readLine();
//            while (lineaActual != null) { // Mientras no llegue al fin del archivo. Lee la informacion de las aristas
//                String[] cadenaParticionada = lineaActual.split(" ");
//                grafo.addArc(Integer.parseInt(cadenaParticionada[0]) - 1, Integer.parseInt(cadenaParticionada[1]) - 1, Integer.parseInt(cadenaParticionada[2]));
//                lineaActual = br.readLine();
//            }
//        } catch (IOException ioe) {
//            System.out.println("Error leyendo el archivo de entrada: " + ioe.getMessage());
//        }
//        return grafo;
//    }
//
//    public static void sort(ArrayList<Integer> in, Graph grafo, int pos) {
//        if (in.size() < 2) return; //do not need to sort
//        int mid = in.size() / 2;
//        ArrayList<Integer> left = new ArrayList<>(mid);
//        ArrayList<Integer> right = new ArrayList(in.size() - mid);
//        for (int i = 0; i < mid; i++) { //copy left
//            left.add(i, in.get(i));
//            //left.add(grafo.getWeight(pos,in.get(i)));
//        }
//        for (int i = 0; i < in.size() - mid; i++) { //copy right
//            right.add(i, in.get(mid + i));
//            //right.add(grafo.getWeight(pos,in.get(mid+i)));
//        }
//
//        sort(left, grafo, pos);
//        sort(right, grafo, pos);
//        merge(left, right, in, grafo, pos);
//    }
//
//    private static void merge(ArrayList<Integer> a, ArrayList<Integer> b, ArrayList<Integer> all, Graph grafo, int pos) {
//        int i = 0, j = 0, k = 0;
//        while (i < a.size() && j < b.size()) { //merge back
//            if (grafo.getWeight(pos, a.get(i)) < grafo.getWeight(pos, b.get(j))) { // a.get(i) < b.get(j)
//                all.set(k, a.get(i));//[k] = a[i];
//                i++;
//            } else {
//                all.set(k, b.get(j));//[k] = b[j];
//                j++;
//            }
//            k++;
//        }
//        while (i < a.size()) { //left remaining
//            all.set(k++, a.get(i++)); //all[k++] = a[i++];
//        }
//        while (j < b.size()) { //right remaining
//            all.set(k++, b.get(j++)); //all[k++] = b[j++];
//        }
//    }
//
//    public static int[] comparacion(ArrayList<Integer> succesors, Graph grafo, float p) {
//        int[] com = new int[succesors.size()];
//        int c = (int) (p - 1) * 100;
//        for (int i = 0; i < succesors.size(); i++) {
//            com[i] = ((grafo.getWeight(0, i) * c) / 100) + grafo.getWeight(0, i);
//        }
//        return com;
//    }
//
//    public static LinkedList<LinkedList<Integer>> SolucionPreliminar(Graph grafo, int[] com, LinkedList<ArrayList<Integer>> ver) {
//        LinkedList<LinkedList<Integer>> carrosCompartidos = new LinkedList<LinkedList<Integer>>();
//        boolean[] visitados = new boolean[ver.size()];
//        while (ver.get(0).size() > 1) {//!ver.get(0).isEmpty()
//            if (ver.get(0).size() == 5) {
//                carrosCompartidos.add(caminoposible(grafo, com, grafo.getSuccessors(ver.get(0).get(ver.get(0).size() - 1)), visitados));
//            } else {
//                LinkedList<Integer> r = new LinkedList<>();
//                r.add(5);
//                carrosCompartidos.add(r);
//            }
//            for (int i = 0; i < carrosCompartidos.get(carrosCompartidos.size() - 1).size(); i++) {
//                //System.out.println(ver.get(0).indexOf(carrosCompartidos.get(0).get(i)-1));
//                //System.out.println("valor: " + (carrosCompartidos.get(0).get(i)));
//                //System.out.println(ver.get(0).indexOf(carrosCompartidos.get(carrosCompartidos.size()-1).get(i)-1));
//                visitados[carrosCompartidos.get(0).get(i) - 1] = true;
//                //visitados[ver.get(0).indexOf(carrosCompartidos.get(0).get(i)-1)] = true;
//                ver.get(0).remove(ver.get(0).indexOf(carrosCompartidos.get(carrosCompartidos.size() - 1).get(i) - 1));
//                //System.out.println(ver.get(0).indexOf(carrosCompartidos.get(0).get(i)-1));
//
//                //visitados[carrosCompartidos.get(carrosCompartidos.size()-1).get(i)]=true;
//            }
//        }
//        return carrosCompartidos;
//    }
//
//    public static LinkedList<Integer> caminoposible(Graph grafo, int[] com, ArrayList<Integer> hijos, boolean[] visitados) {
//        LinkedList<Integer> r = new LinkedList<>();
//        r.add(4);
//        r.add(3);
//        r.add(2);
//        return r;
//    }
//
//    /**
//     * Algoritmo para asignar vehiculos compartidos (No tiene en cuenta la restriccion que hay en el problema)
//     * Lo que hace es agrupar los duenos de vehiculos en permutaciones de maximo 5 elementos
//     * Complejidad: Mejor y Peor Caso O(n), donde n son los duenos de vehiculos y la empresa
//     *
//     * @param grafo Un grafo que puede estar implementado con matrices o con listas de adyacencia
//     * @return una lista de listas con la permutacion para cada subconjunto de la particion de duenos de vehiculo
//     */
//    public static LinkedList<LinkedList<Integer>> asignarVehiculos(Graph grafo, float p) {
//        LinkedList<LinkedList<Integer>> permutacionParaCadaSubconjunto = new LinkedList<LinkedList<Integer>>();
//        int dueno = 2; // Empieza en 2 porque 1 es la empresa
//        int contador = 1;
//        LinkedList<Integer> permutacion = new LinkedList<Integer>();
//        while (dueno <= grafo.size()) {
//            if (contador == 1) { // Si el contador es 1, crear una nueva permutacion
//                permutacion = new LinkedList<Integer>();
//                permutacion.add(dueno);
//                dueno++;
//                contador++;
//            } else { // Sino, seguir insertando en la permutacion actual
//                permutacion.add(dueno);
//                dueno++;
//                contador++;
//                if (contador == 6 || dueno == grafo.size()) {  //Si esto se cumple, agregar la permutacion a la respuesta
//                    contador = 1;
//                    permutacionParaCadaSubconjunto.add(permutacion);
//                }
//            }
//        }
//        return permutacionParaCadaSubconjunto;
//    }
//
//    /**
//     * Metodo para escribir un archivo con la respuesta
//     * Complejidad: Mejor y peor caso es O(n), donde n son los duenos de vehiculo y la empresa
//     *
//     * @param permutacionParaCadaSubconjunto es una lista de listas con la permutacion para cada subconjunto de la particion de duenos de vehiculo
//     */
//    public static void guardarArchivo(LinkedList<LinkedList<Integer>> permutacionParaCadaSubconjunto, int numeroDePuntos, float p) {
//        final String nombreDelArchivo = "respuesta-ejemplo-U=" + numeroDePuntos + "-p=" + p + ".txt";
//        try {
//            PrintWriter escritor = new PrintWriter(nombreDelArchivo, "UTF-8");
//            for (LinkedList<Integer> lista : permutacionParaCadaSubconjunto) {
//                for (Integer duenoDeVehiculo : lista)
//                    escritor.print(duenoDeVehiculo + " ");
//                escritor.println();
//            }
//            escritor.close();
//        } catch (IOException ioe) {
//            System.out.println("Error escribiendo el archivo de salida " + ioe.getMessage());
//        }
//    }
//
//
//    public static void main(String[] args) {
//        //Recibir el numero de duenos de vehiculo y la empresa, y el valor de p por el main
//        final int numeroDePuntos = args.length == 0 ? 205 : Integer.parseInt(args[0]); //205
//        final float p = args.length < 2 ? 1.1f : Float.parseFloat(args[1]);
//        // Leer el archivo con las distancias entre los duenos de los vehiculos y la empresa
//        DigraphAM grafo = leerArchivo(numeroDePuntos, p);
//        LinkedList<ArrayList<Integer>> ver = new LinkedList<ArrayList<Integer>>();
//        int[] com = new int[numeroDePuntos];
//        for (int i = 0; i < grafo.size; i++) {
//            ArrayList<Integer> succesors = grafo.getSuccessors(i);
//            sort(succesors, grafo, i);
//            ver.add(succesors);
//            if (i == 0) {
//                com = comparacion(succesors, grafo, p);
//            }
//        }
//        // Asignar los vehiculos compartidos
//        System.out.println("Free memory (bytes): " +
//                Runtime.getRuntime().freeMemory());
//        long startTime = System.currentTimeMillis();
//        LinkedList<LinkedList<Integer>> permutacionParaCadaSubconjunto = SolucionPreliminar(grafo, com, ver);
//        long estimatedTime = System.currentTimeMillis() - startTime;
//        System.out.println("El algoritmo tomo un tiempo de: " + estimatedTime + " ms");
//        System.out.println("Total memory available to JVM (bytes): " +
//                Runtime.getRuntime().totalMemory()/100+000);
//        // Guardar en un archivo las abejas con riesgo de colision
//        guardarArchivo(permutacionParaCadaSubconjunto, numeroDePuntos, p);
//
//    }
}