import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;

/**
 * @author Cesar Andres Garcia Posada and Daniel Felipe Gómez Martínez.
 */

public class point2 {
    private int vertex, arc;
    private boolean answer;
    public List<Vertex> points = new ArrayList<>();
    private String file;

    /**
     * Constructor of the classes.
     *
     * @param answer
     * @param file
     */
    public point2(boolean answer,String file){
        this.answer = answer;
        this.file = file;
    }

    /**
     * @return boolean (whether the connection is possible or not).
     */
    public boolean isAnswer() {
        return answer;
    }

    /**
     * @param answer .
     */
    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    /**
     * @return int (Vertex number)
     */
    public int getVertex() {
        return vertex;
    }

    /**
     * @param vertex .
     */
    public void setVertex(int vertex) {
        this.vertex = vertex;
    }

    /**
     * @return int (number of arcs)
     */
    public int getArc() {
        return arc;
    }

    /**
     * @param arc .
     */
    public void setArc(int arc) {
        this.arc = arc;
    }

    /**
     * Principal method of reading and executing the code,
     * in this method the vertices are saved and
     * the connections of the arcs are executed and
     * finally prints if it is "BICOROLEABLE OR NOT BICOROLEABLE."
     *
     * The method is in the best case is O (1) and at worst is O (n).
     */
    public void readfile(){
        int acum = 0;
        try{
            String cadena ;
            FileReader f = new FileReader(this.file);
            BufferedReader b = new BufferedReader(f);
            while((cadena = b.readLine())!=null) {
                String[] datos = cadena.split(" ");
                    if (getVertex() == 0) {
                        setVertex(Integer.parseInt(datos[0]));
                        createdVertex(getVertex());
                    }
                    else if (getArc() == 0) {
                        setArc(Integer.parseInt(datos[0]));
                    }
                    else if (datos.length>1){
                        for(int i=0;i<datos.length-1;i++){
                            possibleColor(points.get(Integer.parseInt(datos[i])),points.get(Integer.parseInt(datos[i+1])));
                            acum++;
                        }
                        if (acum==getArc()){
                            setVertex(0);
                            setArc(0);
                            acum=0;
                            points = new ArrayList<>();
                            if (isAnswer()==true){
                                System.out.println("BICOLORABLE.");
                            }else {
                                System.out.println("NOT BICOLORABLE.");
                            }
                        }
                    }
                }
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * In this method, the vertices are created,
     * Vertices are an object of the Vertex class.
     *
     * @param a .
     */
    public void createdVertex(int a){
        for(int i=0;i<a;i++){
            Vertex sond = new Vertex(Color.NONE);
            points.add(sond);
        }
    }

    /**
     * In this method the colors are assigned to the vertices,
     * return true  if possible otherwise false.
     *
     * @param a (initial vertex).
     * @param b (reach vertex).
     */
    public void possibleColor(Vertex a,Vertex b){
        this.setAnswer(true);
        if(a.getBrother()==null && b.getBrother()==null){
          a.setColor(Color.WHITE);
          b.setColor(Color.BLACK);
          a.setBrother(b);
          b.setBrother(a);
        }
        else if (a.getColor()!=Color.NONE && b.getColor()==Color.NONE){
            if (a.getColor()==Color.WHITE){
                b.setColor(Color.BLACK);
            }else {
                b.setColor(Color.WHITE);
            }
            a.setBrother(b);
            b.setBrother(a);
        }else if (b.getColor()!=Color.NONE && a.getColor()==Color.NONE){
            if (b.getColor()==Color.WHITE){
                a.setColor(Color.BLACK);
            }else{
                a.setColor(Color.WHITE);
            }
            a.setBrother(b);
            b.setBrother(a);
        }else  if (a.getColor()!=b.getColor()){
            a.setBrother(b);
            b.setBrother(a);
        } else{
            this.setAnswer(false);
        }
    }

    /**
     * Method main.
     * @param args
     */
    public static void main(String[] args) {
        String file = "test.txt";
        point2 a = new point2(true,file);
        a.readfile();
    }
}