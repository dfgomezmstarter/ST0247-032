import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Daniel Felipe Gomez Martinez
 * @author Daniel Garcia Garcia
 * @author Cesar Andres Garcia Posada
 *
 * Clase en la que se implementan los metodos correspondientes al laboratorio 3
 */
public class Point2 {

    private int com=Integer.MAX_VALUE;
    private ArrayList<Integer> road;

    public void HayCaminos(Digraph g, int begin, int destination){
        boolean [] visit = new boolean[g.size()];
        road=new ArrayList();
        for (int i=1;i<g.size+1;i++){
            road.add(i);
        }
        visit[begin-1]=true;
        hayCamino(road,g,g.getSuccessors(begin),destination,1, visit);
        System.out.println("El coto mínimo fue de : "+com);
    }

    private ArrayList<Integer> hayCamino(ArrayList<Integer> i, Digraph g, ArrayList<Integer> pos, int arrival, int colum, boolean[] visit){
        for(int j=0;j<pos.size();j++){
            if(pos.get(j) == arrival){
                i.set(colum,pos.get(j));
                visit[pos.get(j)-1]= true;
                int compr = comprobar(i,g,colum);
                if(compr<getCom()){
                    this.setCom(compr);
                    this.setRoad((ArrayList<Integer>)i.clone());
                }
            }else{
                i.set(colum,pos.get(j));
                visit[pos.get(j)-1]= true;
                if(comprobar(i,g,colum)<getCom()){
                    colum++;
                    hayCamino(i,g,g.getSuccessors(pos.get(j)),arrival,colum,visit);
                    visit[pos.get(j)-1]= false;
                    colum--;
                }
            }
        }
        return i;
    }

    public int comprobar(ArrayList<Integer> r,Digraph g,int top){
        int min=0;
        for (int i=0;i<top;i++){
            min+=g.getWeight(r.get(i),r.get(i+1));
        }
        return min;
    }

    public void Mostrar(ArrayList<Integer> j) {
        int r=0;
        for (int i = 0; i < j.size(); i++) {
            r+=j.get(i);
        }

    }

    public void readtext(String text){
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
            HayCaminos(l,1,5);
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

    public ArrayList<Integer> getRoad() {
        return road;
    }

    public void setRoad(ArrayList<Integer> road) {
        this.road = road;
    }
}