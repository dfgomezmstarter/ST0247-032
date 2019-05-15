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
 * @author Cesar Andres García - Daniel García García - Daniel Felipe Gómez Martínez
 * @version 2.3
 *
 */
public class ProyectoDatos
{
    /**
     * Metodo para leer un archivo con los duenos de vehiculos y la empresa
     * Complejidad: Mejor y peor caso es O(n*n), donde n es son los duenos de vehiculos y la empresa
     *
     * @param  numeroDePuntos  El numero de puntos es 1 de la empresa y n-1 de los duenos de vehiculos
     * @return Un grafo completo con la distancia mas corta entre todos los vertices.
     *
     */
    public static DigraphAM leerArchivo(int numeroDePuntos, float p){
        final String nombreDelArchivo = "dataset-ejemplo-U=205-p=1.3.txt";
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

    /**
     * Primera parte del método merge sort el cual se implemetara para ordenar el grafo, a través de los pesos de los arcos
     * de cada sucesor con el punto "pos".
     *
     * @param in Sucesores de un verice.
     * @param grafo Grafo completo con la distancia mas corta entre todos los vertices.
     * @param pos Verice origen o referencia.
     *
     */
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

    /**
     * Segunda parte del método merge sort en el cual divide el arreglo de sucesores un dos arreglos hasta que los vaya
     * ubicando de menor a mayor.
     *
     * @param a
     * @param b
     * @param all
     * @param grafo
     * @param pos
     *
     */
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

    public static float tiempoTope(Graph grafo,int ver,float p){
        return grafo.getWeight(0,ver)*p;
    }

    /**
     * Método que encuentra el número minimo de carros, partiendo desde el principio de que se va a tratar de encontrar
     * un camino más corto al vertice que este más alejado de la empresa a través de otros vértices; una vez agrupado
     * los vértice se eliminan del arreglo de los vertices de la empresa por lo que la complejidad en el peor de los
     * casos es de O(n).
     *
     * @param grafo Grafo completo con la distancia mas corta entre todos los vertices.
     * @param com ArrayList en el cual está almacenado el tiempo de espera máximo de cada dueno.
     * @param ListaDeVerticesOrdenados Lista de ArrayList en los cual contienen cada uno de sus arcos ordenados de menor a mayor.
     * @return Una lista con las listas con la minima cantidad de carros.
     *
     */
    public static LinkedList<LinkedList<Integer>> SolucionPreliminar(Graph grafo, ArrayList<Float> com,LinkedList<ArrayList<Integer>> ListaDeVerticesOrdenados, float p){
        LinkedList<LinkedList<Integer>> carrosCompartidos = new LinkedList<LinkedList<Integer>>();
        boolean[] visitados =new boolean[ListaDeVerticesOrdenados.size()];
        int costoDelRecorrido =0;
        while(ListaDeVerticesOrdenados.get(0).size()>1){ // Lista de vertices que lleagn a la empresa ordenandos de menor a mayor.
            int mayor = ListaDeVerticesOrdenados.get(0).get(ListaDeVerticesOrdenados.get(0).size()- 1); // Vértice mas alejado de la empresa.
            /**
             * Agrega la lista del posible vehiculo compartido con otros vertices partiendo del vertice más lejos de la empresa
             */
            carrosCompartidos.add(caminoposible(grafo, com, mayor, visitados, ListaDeVerticesOrdenados,costoDelRecorrido,p));
            /**
             * Elimina los vertices que fueron utilizados en el vehiculo anterior de la lista ListaDeVerticesOrdenados con el finalidad de disminur el ciclo.
             */
            for(int i=0;i<carrosCompartidos.get(carrosCompartidos.size()-1).size();i++){
                ListaDeVerticesOrdenados.get(0).remove(ListaDeVerticesOrdenados.get(0).indexOf(carrosCompartidos.get(carrosCompartidos.size() - 1).get(i)));
            }
        }
        return carrosCompartidos;
    }

    /**
     * Método que agrupa los vértices que poseen el menor costo hacia otros vértices hasta que legue al tope maximo(5)
     * o hasta que llegue a la univerisada sin contemplar el carro.
     *
     * @param grafo Grafo completo con la distancia mas corta entre todos los vertices.
     * @param com ArrayList en el cual está almacenado el tiempo de espera máximo de cada dueno.
     * @param mayor Vértice mas alejado de la empresa.
     * @param visitados Arreglo de booleanos(true-> si ya fue visitado o false-> si no ha sido visitado).
     * @param ListaDeVerticesOrdenados Lista de ArrayList en los cual contienen cada uno de sus arcos ordenados de menor a mayor.
     * @param costoDelRecorrido
     * @return Una lista de vertices
     */
    public static LinkedList<Integer> caminoposible(Graph grafo, ArrayList<Float> com, int mayor, boolean[] visitados, LinkedList<ArrayList<Integer>> ListaDeVerticesOrdenados, int costoDelRecorrido,float p){
        LinkedList<Integer> camino = new LinkedList<Integer>();
        int pos=1;
        int fin=0;
        float recorridoConPDeE_dueno=tiempoTope(grafo,ListaDeVerticesOrdenados.get(0).get(ListaDeVerticesOrdenados.get(0).size()-1),p);
        float origenMenor=recorridoConPDeE_dueno;
        int recorridoHaciaElMayor=0;
        int Mayor_origen=0;
        for(int i = 0; i < ListaDeVerticesOrdenados.get(0).size(); i++){
            float recorridoConPDeE_Mayor=tiempoTope(grafo,mayor,p);
            if (camino.size()==5 && costoDelRecorrido<=recorridoConPDeE_dueno ){
                return camino;
            }else {
                recorridoHaciaElMayor=grafo.getWeight(mayor,ListaDeVerticesOrdenados.get(mayor).get(pos));
                costoDelRecorrido+=recorridoHaciaElMayor;
                Mayor_origen=grafo.getWeight(0,ListaDeVerticesOrdenados.get(mayor).get(pos)) +recorridoHaciaElMayor;
                if(mayor!=ListaDeVerticesOrdenados.get(mayor).get(pos) && // para que no se vaya a si mismo
                        costoDelRecorrido<=recorridoConPDeE_dueno && //para que no se pase del tiempo maximo del primer vertice
                        !visitados[ListaDeVerticesOrdenados.get(mayor).get(pos)] && //para que no vuelva a vertices ya seleccionados
                        0!=ListaDeVerticesOrdenados.get(mayor).get(pos) && //para que no anote que puede llegar a la  universidad
                        Mayor_origen<=origenMenor
                        //recorridoHaciaElMayor<=origenMenor
                        //costoDelRecorrido<=recorridoConPDeE_Mayor //para que no se pase del tiempo maximo del vertice visitado
                ) {
                    camino.add(mayor);
                    visitados[mayor] = true;
                    i=0; // reiniciar el ciclo.
                    mayor = ListaDeVerticesOrdenados.get(mayor).get(pos);
                    recorridoConPDeE_Mayor=tiempoTope(grafo,mayor,p);
                    origenMenor=Math.min(origenMenor-grafo.getWeight(mayor,ListaDeVerticesOrdenados.get(mayor).get(pos)),recorridoConPDeE_Mayor);
                    fin = mayor;
                    pos=1; //reiniciar la busqueda desde el menor.
                }else{
                    costoDelRecorrido-=grafo.getWeight(mayor,ListaDeVerticesOrdenados.get(mayor).get(pos));
                    ++pos;
                }
            }
        }
        costoDelRecorrido+=grafo.getWeight(0,fin);
        if (!visitados[mayor]&&costoDelRecorrido<=recorridoConPDeE_dueno) {
            camino.add(mayor);
            visitados[mayor] = true;
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
                    escritor.print((duenoDeVehiculo + 1) + " ");
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
        final int numeroDePuntos = args.length == 0 ? 205 : Integer.parseInt(args[0]); //205
        final float p = args.length < 2 ? 1.3f : Float.parseFloat(args[1]);
        // Leer el archivo con las distancias entre los duenos de los vehiculo y la empresa
        DigraphAM grafo = leerArchivo(numeroDePuntos, p);
        LinkedList<ArrayList<Integer>> ListaDeVerticesOrdenados = new LinkedList<ArrayList<Integer>>();
        ArrayList<Float> com= new ArrayList<Float>();
        for (int i=0;i<grafo.size;i++){
            ArrayList<Integer> succesors = grafo.getSuccessors(i);
            sort(succesors,grafo,i);
            ListaDeVerticesOrdenados.add(succesors);
        }
        // Asignar los vehiculos compartidos
        long startTime = System.currentTimeMillis();
        LinkedList<LinkedList<Integer>> permutacionParaCadaSubconjunto = SolucionPreliminar(grafo,com,ListaDeVerticesOrdenados,p);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("El algoritmo tomo un tiempo de: "+estimatedTime+" ms");
        // Guardar en un archivo las abejas con riesgo de colision
        System.out.println("Memoria total consumida:" + Runtime.getRuntime().totalMemory()/1000000);
        guardarArchivo(permutacionParaCadaSubconjunto, numeroDePuntos, p);

    }
}