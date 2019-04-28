import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * @author Luis Palacio et al.
 */
public class Taller12 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(Arrays.toString(HillClimb(4)));
    }

    static class Pair<F, S> {

        public final F first;
        public final S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

    }

    public static int [] HillClimb(int n) {
        int com =Integer.MAX_VALUE;
        int [] tablero = new int [n];
        int [] min = new int [n];
        for (int i=0;i<n;i++){
            for (int j=0;j<n-1;j++){
                tablero[i]=j;
                int aux= reinasAtacandose(tablero);
                if (aux<com){
                    com=aux;
                    min= Array.c(tablero);
                }
            }
        }
        return min;
    }

    /**
     * Este metodo obtiene el numero de reinas que se atacan en el momento
     */
    public static int reinasAtacandose(int [] tablero) {
        boolean [] f = new boolean[tablero.length];
        boolean [] c = new boolean[tablero.length];
        int r =0;
        for(int i=0;i<tablero.length;i++){
            for(int j=0;j<tablero.length;j++){
                if (i!=j &&(tablero[i]==tablero[j] || Math.abs((i-j)/(tablero[i]-tablero[j]))==1) && f[i]==false && f[j]==false){
                    r++;
                }
            }
            f[i]=true;
        }
        return r;
    }
}