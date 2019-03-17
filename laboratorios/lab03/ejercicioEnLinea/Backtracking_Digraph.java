import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Cesar Andres Garcia Posada - Daniel Felipe Gomez Martinez - Daniel Garcia Garcia.
 */

public class Backtracking_Digraph {
    private int com=Integer.MAX_VALUE;
    private int[] road;

    /**
     * Source method where the parameters are established bases for
     * the elaboration of the DFS path with backtracking.
     *
     * @param g Directed graph.
     */
    public void travel(Digraph g){
        int [] r= new int [g.size()-1];
        r[0]=1;
        road=new int[g.size()-1];
        findroad(r,g,g.getSuccessors(1),g.size,1);
        print(this.getRoad());
    }

    /**
     * Recursive method that looks for a shorter path from
     * point of origin 1 to vertex n, implementing a
     * path type DFS (Depth-Frist Search).
     *
     * @param i Array in which are the vertices leading to n.
     * @param g Directed graph.
     * @param pos Array in which are the connections of a vertex n (its successors).
     * @param arrival vertex of arrival.
     * @param colum position of the column where you want to put the vertex.
     * @return Array of connections from 1 to the vertex of arrival.
     */
    private int[] findroad(int[] i, Digraph g, ArrayList<Integer> pos, int arrival,int colum){
        for(int j=0;j<pos.size();j++){
            if(pos.get(j) == arrival){
                i[colum]=pos.get(j);
                int compr = comprobar(i,g,colum);
                if(compr<getCom()){
                    this.setCom(compr);
                    this.setRoad(Arrays.copyOf(i,i.length));
                }
            }else{
                i[colum]=pos.get(j);
                if(comprobar(i,g,colum)<getCom()){
                    colum++;
                    findroad(i,g,g.getSuccessors(pos.get(j)),arrival,colum);
                    colum--;
                }
            }
        }
        return i;
    }

    /**
     * Method where the total route of a valid array is counted.
     *
     * @param r Array in which are the vertices leading to n.
     * @param g Directed graph.
     * @param top Position where the last vertex was added.
     * @return
     */
    public int comprobar(int [] r,Digraph g,int top){
        int min=0;
        for (int i=0;i<top;i++){
            min+=g.getWeight(r[i],r[i+1]);
        }
        return min;
    }

    /**
     * Method that prints the Array.
     * @param r Array.
     */
    public void print(int [] r){
        int fin=0;
        System.out.print("[");
        for (int i=0; i<r.length;i++){
            if(r[i]==r.length+1&&  fin==0){
                System.out.print(r[i]);
                fin++;
            }else if(r[i]==r.length+1){
                System.out.print(0);
            }else {
                System.out.print(r[i]);
            }
        }
        System.out.println("]");
    }

    /**
     * Method of reading and opening the code.
     *
     * @param text file
     */
    private void readtext(String text){
        Digraph l =new DigraphAL(0);
        try {
            FileReader txt = new FileReader(text+".txt");
            BufferedReader read = new BufferedReader(txt);
            String letter = read.readLine();
            while(letter!=null){
                String [] values = letter.split(" ");
                int [] values1 = new int[values.length];
                for (int i=0;i<values.length;i++){
                    values1[i]=Integer.parseInt(values[i]);
                }
                if(values1.length==2){
                    Digraph g =new DigraphAL(values1[0]);
                    l=g;
                }else{
                    l.addArc(values1[0], values1[1], values1[2]);
                }
                letter = read.readLine();
            }
            travel(l);
        }catch (Exception e){
            System.out.println("Se ha genreado un Error en la lectura de tipo:"+e);
        }
    }

    public int getCom() {
        return com;
    }

    public void setCom(int com) {
        this.com = com;
    }

    public int[] getRoad() {
        return road;
    }

    public void setRoad(int[] road) {
        this.road = road;
    }

    public static void main(String[] args) {
        Backtracking_Digraph n = new Backtracking_Digraph();
        n.readtext("prueba");
    }
}
