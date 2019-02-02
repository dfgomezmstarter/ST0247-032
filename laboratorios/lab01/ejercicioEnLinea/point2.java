import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class point2 {
    private int vertex, arc;
    private boolean answer;
    public Vertex[] points;
    private String file;

    public point2(boolean answer,String file){
        this.answer = answer;
        this.file = file;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public int getVertex() {
        return vertex;
    }

    public void setVertex(int vertex) {
        this.vertex = vertex;
    }

    public int getArc() {
        return arc;
    }

    public void setArc(int arc) {
        this.arc = arc;
    }

    public void readfile(){
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
                    if (getArc() == 0) {
                        setArc(Integer.parseInt(datos[0]));
                    }
                    if (cadena.length()>1){
                        for(int i=0;i<cadena.length()-1;i++){
                            createdVertexarc(i,i+1);
                        }
                    }
                }
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createdVertex(int a){
        Vertex sond;
        for(int i=0;i<a;i++){
            sond = new Vertex(Color.NINGUNO);
            points[i]= sond;
        }
    }

    public void createdVertexarc(int point1, int point2){
        for (int i =0;i<points.length;i++){
            if (i==point1) {
              for (int j =0;j<points.length;j++){
                  if (j==point2){
                      possibleColor(points[i],points[j]);
                  }
              }
            }
        }
    }

    public void possibleColor(Vertex a,Vertex b){
        if(a.getBrother()==null && b.getBrother()==null){
          a.setColor(Color.BLANCO);
          b.setColor(Color.NEGRO);
          a.setBrother(b);
        }
        else if (a.getColor()!=Color.NINGUNO && b.getColor()==Color.NINGUNO){
            if (a.getColor()==Color.BLANCO){
                b.setColor(Color.NEGRO);
            }else {
                b.setColor(Color.BLANCO);
            }
            a.setBrother(b);
            b.setBrother(a);
        }else if (b.getColor()!=Color.NINGUNO && a.getColor()==Color.NINGUNO){
            if (b.getColor()==Color.BLANCO){
                a.setColor(Color.NEGRO);
            }else{
                a.setColor(Color.BLANCO);
            }
            a.setBrother(b);
            b.setBrother(a);
        }else {
            this.setAnswer(false);
        }
    }

    public static void main(String[] args) {
        String file = "";
        point2 a = new point2(true,file);
        a.readfile();
        if (a.isAnswer()==true){
            System.out.println("BICOLORABLE.");
        }else {
            System.out.println("NOT BICOLORABLE.");
        }
    }
}
