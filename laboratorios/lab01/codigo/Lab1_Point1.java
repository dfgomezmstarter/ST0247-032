import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.util.Pair;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Daniel Felipe Gomez Martinez and Cesar Andres Garcia Posada.
 */
public class Lab1_Point1{
    
    private static Hashtable<Long,HashSet<Pair<Long,Double>>> Map = new Hashtable<Long,HashSet<Pair<Long,Double>>>();
    private static String File;
    
    
    /**
        * Method to read the file type .txt
        * In this method the file is read and the HashTable key is determined.
        * The HashTable key is each nodes and the HashTable values are neighbor nodes
        * These neighbor Nodes is seave in a HashSet
	* 
	* @param  archive File to read
	* 
	*/
    public static void Node(String archive) throws FileNotFoundException, IOException {
        String cadena;
        long NodeA;
        Long NodeB;
        double Distance;
        //int contador = 0;
        boolean check = false;
        FileReader f = new FileReader(archive);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!= null) {
            if(cadena.contains("Vertices. ")){
                cadena = null;
            }else if(cadena.contains("Arcos. ")){
                cadena = null;
                check = true;
            }else if(cadena.length() != 0 && check == true){
                NodeA = Long.parseLong(cadena.substring(0, cadena.indexOf(" ")));
                if(Map.containsKey(NodeA)){
                    cadena = cadena.substring(cadena.indexOf(" ")+1, cadena.length());
                    NodeB = Long.parseLong(cadena.substring(0, cadena.indexOf(" ")));
                    cadena = cadena.substring(cadena.indexOf(" ")+1, cadena.length());
                    Distance = Double.parseDouble(cadena.substring(0, cadena.indexOf(" ")));
                    Map.get(NodeA).add(new Pair(NodeB,Distance));
                }
            }else if (cadena.length() != 0){
                cadena = cadena.substring(0, cadena.indexOf(" "));
                if(!Map.containsKey(cadena)){
                    Map.put(Long.parseLong(cadena), new HashSet<Pair<Long,Double>>());
                }
            }    
        }
        b.close();
    }
    
    
    
    /**
        * Method to show the neighbor nodes in a specific node
	* 
	* @param  value Node to show the neighbor nodes
	* 
	*/
    public static void ShowNode(long value){
        System.out.println();
        String cadena = null; 
        System.out.print("[ " + value + " ] -> " );
        Iterator iterator = Map.get(value).iterator(); 
        if(iterator.hasNext()){
            while (iterator.hasNext()) {
                cadena = cadena + "[ "+iterator.next().toString() + " ] -> ";
            }
            System.out.print(cadena.substring(4,cadena.length()-3)); 
        }else{
            cadena = null;
            System.out.println(cadena);
        }
    }
    
    /**
        * Method to show the neighbor nodes to each node of a file
	* 
	* @param  archivo File to read to show the neighbor nodes to each node
	* 
	*/
    
    public static void ShowNodes(String archive) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archive);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!= null) {
            if(cadena.contains("Vertices. ")){
                cadena = null;
            }else if(cadena.contains("Arcos. ")){
                cadena = null;
                break;
            }else if(cadena.length() != 0){
                cadena = cadena.substring(0, cadena.indexOf(" "));
                ShowNode(Long.parseLong(cadena));
            }  
        }
        b.close();
    }
    
    /**
        * Method to give the user options to interact with the algorithm
	*/
    
    public static int  Menu(){
        Scanner read = new Scanner(System.in);
        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println("------------------ Menu -------------------");
        System.out.println("------------ 1) Determine Nodes -----------");
        System.out.println("-- 2) Determine nodes with specific file --");
        System.out.println("---------- 3) Show specific Node ----------");
        System.out.println("---------- 4) Show neighbor Nodes ---------");
        System.out.println("----------------- 5) Exit -----------------");
        System.out.println("-------------------------------------------");
        System.out.println();
        System.out.println("--------- What do you want to do? ---------");
        int option = read.nextInt();
        return option;
    }
    
    /**
        * Method to execute the options, the read the file and the determination of nodes of the Graph
	*/
    
    public static void Run() throws IOException{
        Scanner read = new Scanner(System.in);
        int option = Menu();
        switch(option){
            case 1:
                Node("medellin_colombia-grande.txt");
                File = "medellin_colombia-grande.txt";
                Run();
                break;
            case 2:
                System.out.println("Write the file name");
                String value = read.nextLine();
                Node(value);
                File = value;
                Run();
                break;
            case 3:
                System.out.println("Write the node you want to see");
                String node = read.nextLine();
                ShowNode(Long.parseLong(node));
                Run();
                break;
            case 4:
                ShowNodes(File);
                Run();
                break;
            case 5:
                System.exit(0);
                break;
                
        }
    }
    
    /**
        * Method main
	*/

    public static void main(String[] args) throws IOException {
        Run();
    }

}