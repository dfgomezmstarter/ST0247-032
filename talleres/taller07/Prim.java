import java.util.Arrays;

public class Prim {

    public static int prim(Digraph g){

        int r=0;
        int [] rm = FullMatrix(g.size);
        boolean [] br = new boolean[g.size];
        int temp=0;

        for (int i=0; i<rm.length; i++){
            for (int j=0; j<rm.length; j++){
                if(rm[i]>g.getWeight(i,j) && (br[i]==false || br[j]==false) && i!=j){
                    rm[i]=g.getWeight(i,j);
                    temp=j;
                    }
            }
            br[i]=true;
            br[temp]=true;
            temp=0;
        }

        for (int i=0;i<rm.length; i++){
            if(rm[i]!=Integer.MAX_VALUE)
            r+=rm[i];
        }

        System.out.println("La respuesta es: "+r);
        return r;
    }

    private static int[] FullMatrix(int n){
        int[] a = new int[n];
        Arrays.fill(a, Integer.MAX_VALUE);
        return a;
    }
}
