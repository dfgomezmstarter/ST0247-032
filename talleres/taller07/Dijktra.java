import java.util.Arrays;
import java.util.ArrayList;

public class Dijktra {

    public int[] dijkstra(Digraph g, int v){
        int[] tabla = llenarUnArregloConInfinitos(g.size());
        int actual = v;
        boolean[] visitados = new boolean[g.size()];
        for (int i = 0; i < g.size(); i++) {
            actual = elMasPequenoNoVisitado(g, visitados, tabla);
            visitados[actual] = true;
            actualizarLosPesosDeLaTablaConElValorActual(g, actual, tabla);
        }
        return tabla;
    }
    private int[] llenarUnArregloConInfinitos(int n){
        int[] a = new int[n];
        Arrays.fill(a, Integer.MAX_VALUE);
        return a;
    }

    private int elMasPequenoNoVisitado(Digraph g, boolean[] visitados, int[] tabla){
        int min=Integer.MAX_VALUE;
        for(int i =0;i<visitados.length;i++){
            if(visitados[i]==false && min>tabla[i]){
                min=tabla[i];
            }
        }
        return min;
    }

    private void actualizarLosPesosDeLaTablaConElValorActual(Digraph g, int actual, int[] tabla){
        if((tabla[actual] != Integer.MAX_VALUE)){
            ArrayList<Integer> arr = g.getSuccessors(actual);
            for(int i = 0; i < arr.size(); i++){
                if(actual != i && g.getWeight(actual,i) != Integer.MAX_VALUE && (g.getWeight(actual,i)+tabla[actual])<tabla[i]){
                    tabla[i] = tabla[actual] + g.getWeight(actual,i);
                }
            }
        }
    }
}
