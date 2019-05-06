import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Cesar Andres García Posada.
 * @author Daniel García García.
 * @author Daniel Felipe Gómez Martínez.
 */
public class ejercicioenLinea {
    public static void leerArchivo(String txt){
        HashMap<Integer,int[]> board = new HashMap<Integer,int []>();
        final String nombreDelArchivo = txt+".txt";
        int e=1;
        int pos=0;
        int[] karolina = new int[2];
        int toxic=0;
        int posarr=0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreDelArchivo));
            String lineaActual = br.readLine();
            while (lineaActual != null && e>0){
                String [] cadenaParticionada = lineaActual.split(" ");
                if(pos==0){
                    e=Integer.parseInt(cadenaParticionada[0]);
                    lineaActual=br.readLine();
                    ++pos;
                }
                else if(pos==1){
                    karolina[0]=Integer.parseInt(cadenaParticionada[0]);
                    karolina[1]=Integer.parseInt(cadenaParticionada[1]);
                    lineaActual=br.readLine();
                    toxic=Integer.parseInt(lineaActual);
                    ++pos;
                }
                else if(toxic>0){
                    int[] pos1= {Integer.parseInt(cadenaParticionada[0]),Integer.parseInt(cadenaParticionada[1])};
                    board.put(posarr,pos1);
                    ++posarr;
                    --toxic;
                    if (toxic==0){
                        HashMap<Integer, int[]> t = new HashMap<Integer, int[]>();
                        System.out.println(DynamicProgramming(board, karolina, t, toxic, 0));
                        pos = 1;
                        posarr=0;
                        --e;
                        board.clear();
                        t.clear();
                        lineaActual=br.readLine();
                }
                }
                lineaActual=br.readLine();
            }
        }
        catch(IOException ioe) {
            System.out.println("Error leyendo el archivo de entrada: " + ioe.getMessage());
        }
    }

    public static int DynamicProgramming(HashMap<Integer,int []> board,int [] karolina, HashMap<Integer,int []> t, int toxic,int numtoxic){
        int[] minKey;
        int r=0;
        int[] karolina2= Arrays.copyOf(karolina,karolina.length);
        boolean[] v=new boolean[board.size()];
        for (int i=0;i<board.size();++i){
            minKey=encontrarmenor(board,karolina,karolina2,v);
            t.put(i,minKey);
            r+=(Math.abs(minKey[0]-karolina[0])+Math.abs(minKey[1]-karolina[1]));
            karolina[0]=minKey[0];
            karolina[1]=minKey[1];

        }
        r+=(Math.abs(t.get(t.size()-1)[0]-karolina2[0])+Math.abs(t.get(t.size()-1)[1]-karolina2[1]));
        return r;
    }

    public static int[] encontrarmenor(HashMap<Integer,int []> board,int[] com,int []karolina2,boolean[] v){
        int[] minKey={Integer.MAX_VALUE,Integer.MAX_VALUE};
        int min= Integer.MAX_VALUE;
        int p=0;
         for(int i =0;i<board.size();++i){
            if ((Math.abs(board.get(i)[0]-com[0])+Math.abs(board.get(i)[1]-com[1]))==min){
                if(Math.abs(board.get(i)[0]-karolina2[0])+Math.abs(board.get(i)[1]-karolina2[1])==(Math.abs(minKey[0]-karolina2[0])+Math.abs(minKey[1]-karolina2[1]))){
                    if(Math.abs(board.get(i)[0]-karolina2[0])<Math.abs(minKey[0]-karolina2[0])){
                        minKey[0]=board.get(i)[0];
                        minKey[1]=board.get(i)[1];
                    }
                }
            }
            if((Math.abs(board.get(i)[0]-com[0])+Math.abs(board.get(i)[1]-com[1]))<min
                && v[i]==false){
                minKey[0]=board.get(i)[0];
                minKey[1]=board.get(i)[1];
                min=(Math.abs(board.get(i)[0]-com[0])+Math.abs(board.get(i)[1]-com[1]));
                p=i;
            }
        }
        v[p]=true;
        return minKey;
    }

    public static void main(String[] args) {
        String test = "test";
        leerArchivo(test);
    }
}