import java.util.Arrays;

public class taller6 {

    public static int[] cambioGreedy(int n, int[] d) {
        int[] respuesta = new int[d.length];
        int[] denominaciones = new int[d.length];

        Arrays.sort(d);
        int m = d.length - 1;


        for(int i = m; i >= 0; i--){
            int tem = d[m-i];
            denominaciones[m-i] = d[i];
            denominaciones[i]= tem;
        }
        for (int i = 0; i < denominaciones.length; i++) {
            int cantidad = n / denominaciones[i];
            respuesta[i] = cantidad;
            n = n % denominaciones[i];
        }

        /* Método para mostrar*/
        for(int i = 0; i < respuesta.length; i++){
            if(respuesta[i] != 0){
                System.out.println(respuesta[i]);
            }
        }
        return respuesta;
    }

    public static int recorrido(Digraph g){
        boolean[] path= new boolean[g.size];
        int pos=0;
        int r =0;
        int comp=Integer.MAX_VALUE;
        for (int i=0;i<g.size;i++){
           for(int j=0;j<g.getSuccessors(i).size();j++){
               if(i+1==g.size){
                   comp=g.getWeight(i,g.getSuccessors(i).get(0));
               }
               else if(g.getWeight(i,g.getSuccessors(i).get(j))<=comp && path[j]==false && i!=g.getSuccessors(i).get(j)){
                   comp=g.getWeight(i,g.getSuccessors(i).get(j));
                   pos=g.getSuccessors(i).get(j);
               }
           }
           path[pos]=true;
           r+=comp;
           comp=Integer.MAX_VALUE;
        }
        System.out.println("Tamoño del garfo: "+g.size);
        System.out.println("Resultado: "+r);
        return r;
    }
}
