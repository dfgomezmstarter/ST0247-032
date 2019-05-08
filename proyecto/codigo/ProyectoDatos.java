import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
/**
 * Implementacion de un algoritmo para asignar vehiculos compartidos
 * Estructura de datos utilizada: Grafo con Matrices de Adyacencia
 * Complejidad: Peor Caso y Mejor Caso O(n*n)
 *
 * @author Mauricio Toro
 * @version 1
 */
public class ProyectoDatos
{
    /**
     * Metodo para leer un archivo con los duenos de vehiculos y la empresa
     * Complejidad: Mejor y peor caso es O(n*n), donde n es son los duenos de vehiculos y la empresa
     *
     * @param  numeroDePuntos  El numero de puntos es 1 de la empresa y n-1 de los duenos de vehiculos
     * @return un grafo completo con la distancia mas corta entre todos los vertices
     */
    public static DigraphAM leerArchivo(int numeroDePuntos, float p){
        //final String nombreDelArchivo = "dataset-ejemplo-U="+numeroDePuntos+"-p="+p+".txt";
        final String nombreDelArchivo = "numeroDepuntos2.txt";
        DigraphAM grafo = new DigraphAM(numeroDePuntos);
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreDelArchivo));
            String lineaActual = br.readLine();
            for (int i = 1; i <= 3; i++) // Descarta las primeras 3 lineas
                lineaActual = br.readLine();
            lineaActual = br.readLine();
            for (int i = 1; i <= numeroDePuntos; i++){ //Descarta los nombres y coordenadas de los vertices
                lineaActual = br.readLine();
            }
            for (int i = 1; i <= 3; i++) // Descarta las siguientes 3 lineas
                lineaActual = br.readLine();
            while (lineaActual != null){ // Mientras no llegue al fin del archivo. Lee la informacion de las aristas
                String [] cadenaParticionada = lineaActual.split(" ");
                grafo.addArc(Integer.parseInt(cadenaParticionada[0])-1, Integer.parseInt(cadenaParticionada[1])-1, Integer.parseInt(cadenaParticionada[2]));
                lineaActual = br.readLine();
            }
        }
        catch(IOException ioe) {
            System.out.println("Error leyendo el archivo de entrada: " + ioe.getMessage());
        }
        return grafo;
    }

    public static void sort(ArrayList<Integer> in, Graph grafo, int pos){
        if(in.size() <2) return; //do not need to sort
        int mid = in.size()/2;
        ArrayList<Integer> left = new ArrayList<>(mid);
        ArrayList<Integer> right = new ArrayList(in.size()-mid);
        for(int i=0; i<mid; i++){ //copy left
            left.add(i,in.get(i));
            //left.add(grafo.getWeight(pos,in.get(i)));
        }
        for(int i=0; i<in.size()-mid; i++){ //copy right
            right.add(i,in.get(mid+i));
            //right.add(grafo.getWeight(pos,in.get(mid+i)));
        }

        sort(left, grafo, pos);
        sort(right, grafo, pos);
        merge(left, right, in,grafo,pos);
    }

    private static void merge(ArrayList<Integer> a, ArrayList<Integer> b, ArrayList<Integer> all, Graph grafo, int pos){
        int i=0, j=0, k=0;
        while(i<a.size() && j<b.size()){ //merge back
            if(grafo.getWeight(pos,a.get(i))<grafo.getWeight(pos,b.get(j))){ // a.get(i) < b.get(j)
                all.set(k,a.get(i));//[k] = a[i];
                i++;
            }else{
                all.set(k,b.get(j));//[k] = b[j];
                j++;
            }
            k++;
        }
        while(i<a.size()){ //left remaining
            all.set(k++,a.get(i++)); //all[k++] = a[i++];
        }
        while(j<b.size()){ //right remaining
            all.set(k++,b.get(j++)); //all[k++] = b[j++];
        }
    }

    public static ArrayList<Float> comparacion(ArrayList<Integer> succesors,Graph grafo,float p){
        ArrayList<Float> com = new ArrayList<Float>();
        for(int i=0;i<succesors.size();i++){
            Float j = grafo.getWeight(0,succesors.get(i))*p;
            com.add(j);
        }
        return com;
    }

    public static LinkedList<LinkedList<Integer>> SolucionPreliminar(Graph grafo, ArrayList<Float> com,LinkedList<ArrayList<Integer>> ver, float p){
        LinkedList<LinkedList<Integer>> carrosCompartidos = new LinkedList<LinkedList<Integer>>();
        boolean[] visitados =new boolean[ver.size()];
        while(ver.get(0).size()>=1){//!ver.get(0).isEmpty()
            int mayor = ver.get(0).get(ver.get(0).size()- 1);
            carrosCompartidos.add(caminoposible(grafo, com, ver.get(mayor), mayor, visitados, ver));
            //carrosCompartidos.add(caminoposible2());
            for(int i=0;i<carrosCompartidos.get(carrosCompartidos.size()-1).size();i++){
                visitados[carrosCompartidos.get(0).get(i) - 1] = true;
                ver.get(0).remove(ver.get(0).indexOf(carrosCompartidos.get(carrosCompartidos.size() - 1).get(i) - 1));
            }
            com = comparacion(ver.get(0),grafo,p);
        }
        return carrosCompartidos;
    }

    public static LinkedList<Integer> caminoposible(Graph grafo, ArrayList<Float> com, ArrayList<Integer> hijos, int mayor, boolean[] visitados, LinkedList<ArrayList<Integer>> ver){
        LinkedList<Integer> camino = new LinkedList<Integer>();
        camino.add(mayor);
        visitados[mayor] = true;
        int menor = hijos.get(0);
        int i = 0;
        while(visitados[menor]==true&&i<hijos.size()-1){
            i++;
            menor = hijos.get(i);
        }
        if(i<hijos.size()-1){
            int peso = grafo.getWeight(mayor,menor);
            if(peso<com.get(com.size()-1)){
                camino.add(menor);
                hijos = ver.get(menor);
                return recorrido(grafo,com,hijos,visitados,ver,camino,menor,peso);
            }
        }
        return camino;
    }


    public static LinkedList<Integer> recorrido(Graph grafo, ArrayList<Float> com, ArrayList<Integer> hijos, boolean[] visitados, LinkedList<ArrayList<Integer>> ver, LinkedList<Integer> camino, int menor, int peso){
        peso = peso + grafo.getWeight(menor,hijos.get(0));
        if(camino.size()==5){
            return camino;
        }
        if(hijos.get(0)==0 && peso<com.get(com.size()-1)){
            menor = hijos.get(1);
            int i = 1;
            while(visitados[menor]==true&&i<hijos.size()-1){
                i++;
                menor = hijos.get(i);
            }
            if(i<hijos.size()-1){
                return recorrido(grafo,com,hijos,visitados,ver,camino,menor,peso);
            }
            camino.add(hijos.get(0));
            return camino;
        }

        if(peso<com.get(com.size()-1)||hijos.get(0)!=0){
            menor = hijos.get(0);
            int i = 0;
            while(visitados[menor]==true&&i<hijos.size()-1){
                i++;
                menor = hijos.get(i);
            }
            if(i<visitados.length-1){
                camino.add(menor);
                hijos = ver.get(menor);
                return recorrido(grafo,com,hijos,visitados,ver,camino,menor,peso);
            }else{
                return camino;
            }
        }
        return camino;
    }


    /**
     * Metodo para escribir un archivo con la respuesta
     * Complejidad: Mejor y peor caso es O(n), donde n son los duenos de vehiculo y la empresa
     *
     * @param  permutacionParaCadaSubconjunto es una lista de listas con la permutacion para cada subconjunto de la particion de duenos de vehiculo
     */
    public static void guardarArchivo(LinkedList<LinkedList<Integer>> permutacionParaCadaSubconjunto,int numeroDePuntos, float p){
        final String nombreDelArchivo = "respuesta-ejemplo-U="+numeroDePuntos+"-p="+p+".txt";
        try {
            PrintWriter escritor = new PrintWriter(nombreDelArchivo, "UTF-8");
            for (LinkedList<Integer> lista: permutacionParaCadaSubconjunto){
                for (Integer duenoDeVehiculo: lista)
                    escritor.print(duenoDeVehiculo + " ");
                escritor.println();
            }
            escritor.close();
        }
        catch(IOException ioe) {
            System.out.println("Error escribiendo el archivo de salida " + ioe.getMessage() );
        }
    }

    public static void main(String[] args){
        //Recibir el numero de duenos de vehiculo y la empresa, y el valor de p por el main
        final int numeroDePuntos = args.length == 0 ? 5 : Integer.parseInt(args[0]); //205
        final float p = args.length < 2 ? 1.2f : Float.parseFloat(args[1]);
        // Leer el archivo con las distancias entre los duenos de los vehiculo y la empresa
        DigraphAM grafo = leerArchivo(numeroDePuntos, p);
        LinkedList<ArrayList<Integer>> ver = new LinkedList<ArrayList<Integer>>();
        ArrayList<Float> com= new ArrayList<Float>();
        for (int i=0;i<grafo.size;i++){
            ArrayList<Integer> succesors = grafo.getSuccessors(i);
            sort(succesors,grafo,i);
            ver.add(succesors);
            if (i==0) {
                com = comparacion(succesors,grafo,p);
            }
        }
        System.out.println("p== "+p);
        for (int i = 0; i < com.size(); i++) {
            System.out.println("pos: "+com.get(i));
        }
        // Asignar los vehiculos compartidos
        long startTime = System.currentTimeMillis();
        LinkedList<LinkedList<Integer>> permutacionParaCadaSubconjunto = SolucionPreliminar(grafo,com,ver,p);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("El algoritmo tomo un tiempo de: "+estimatedTime+" ms");
        // Guardar en un archivo las abejas con riesgo de colision
        System.out.println("Memoria total consumida:" + Runtime.getRuntime().totalMemory()/1000000);
        guardarArchivo(permutacionParaCadaSubconjunto, numeroDePuntos, p);

    }
}