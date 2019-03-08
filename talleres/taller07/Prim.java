public class Prim {

    public int[] prim(Digraph g){

        int [] r = new int [g.size];
        boolean [] br = new boolean[g.size];
        int [][] tabla= FullMatrix(g);
        for (int i=0; i<tabla.length; i++){
            for (int j=0; j<tabla.length; j++){
                if(tabla[i][j]<g.getWeight(i,j) && (br[i]==false || br[j]==false)){
                    r[i]=g.getWeight(i,j);
                }
            }
        }
        return r;
    }

    private int[][] FullMatrix(Digraph g){
        int [][] r = new int[g.size][g.size];
        for (int i=0; i<g.size; i++){
            for (int j=0; j<g.size; j++){
                if(r[i][j]==0){
                    r[i][j]=Integer.MAX_VALUE;
                }
            }
        }
        return r;
    }

}
