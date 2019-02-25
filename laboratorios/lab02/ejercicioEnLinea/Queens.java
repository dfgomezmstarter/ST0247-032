import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel García García - Cesar Andres Garcia Posada - Daniel Felipe Gómez Martínez
 */
public class Queens {

    private List<int[]> boards = new ArrayList();
    private List<int[]> arrayasterisk =new ArrayList();
    private int iterations=0;
    private int cases=0;
    private int r=0;
    private boolean asterisk;
    private int row=0;

    /**
     * Method thats find the number of solutions the problem has
     *
     * The method is in the best case is O (n) and at worst is O (n!).
     *
     * @param n number of queens.
     * @param board array with the positions of the queens.
     * @param colum positions of the queens.
     */
    private void nQueens(int n, int[] board, int colum){

       if (colum==n){
           if (CollisionQueens(board, colum)){
               int [] boardaux = new int[board.length];
               for (int j=0;j<boardaux.length;j++){
                   boardaux[j]=board[j];
               }
               this.getBoards().add(boardaux);
               this.setR(this.getR()+1);
           }
       }else{
           for (int i=0;i<n;i++) {
               board[colum]=i;
               if (CollisionQueens(board, colum)) {
                   nQueens(n, board, colum+1);
               }
           }
       }

   }

    /**
     * This method is the one that writes the answer of a board
     * and cleans the class for the following boards.
     *
     */
   private void printr(){
        if (this.getArrayasterisk().size()==0) {
            System.out.println("Case " + cases + ":" + r);
            this.setR(0);
            this.getBoards().removeAll(this.getBoards());
        }else{
            this.deliteCondision();
            this.printr();
        }

   }

    /**
     *In this method, the boards that do not comply
     * with the conditions given in the exercise are eliminated,
     * as are the blank spaces in the board '*'.
     *
     * The method is in the best case is O (n) and at worst is O (n^2).
     */
   private void deliteCondision(){
        for (int i=0;i<this.getBoards().size();i++){
            int[] board = ((int[])this.getBoards().get(i));

            for (int j=0;j<this.getArrayasterisk().size();j++){
                int[] asterisk = ((int[])this.getArrayasterisk().get(j));

                for (int b=0;b<board.length;b++){
                    if(board[b]==asterisk[b]){
                        this.setR(this.getR()-1);
                    }
                }
            }
        }
       this.getArrayasterisk().removeAll(this.getArrayasterisk());
   }

    /**
     * Auxiliary method to call the subsequent method
     *
     * @param n
     */
   private void nQueens(int n){

       nQueens(n,new int [n],0);

   }

    /**
     * Method that returns if the queen's position on the board is correct or not
     *
     * The method is in the best case is O (1) and at worst is O (n).
     *
     * @param board array with the positions of the queens.
     * @param pos position of the queen.
     * @return booolean.
     */
   private static boolean CollisionQueens(int[] board,int pos){

       for (int i=0;i<pos-1;i++){
           for (int j=i+1;j<pos;j++){
               if (Math.abs(board[i] - board[j]) == Math.abs(i - j) || board[i] == board[j]){
                   return false;
               }
           }
       }
       return true;

   }

    /**
     * In this method, the file is read and the anilicis
     * of the file and the solutions for the boards are started.
     *
     * The method is in the best case is O (1) and at worst is O (n).
     *
     * @param text text where the data are.
     */
   private void readtext(String text){

       try {
           FileReader txt = new FileReader(text+".txt");
           BufferedReader read = new BufferedReader(txt);
           String letter = read.readLine();
           while(letter!=null && !letter.equalsIgnoreCase("0")){

               String [] values = letter.split("");
               int [] values1 = new int [values.length];

               for (int i=0;i<values.length;i++){
                   if(values[i].equals("*")){
                       values[i]=""+this.getRow();
                       this.setAsterisk(true);
                   }else if(values[i].equals(".")){
                       values[i]=""+values.length;
                   }else if(values.length==1 && values[i]!="0" && this.getBoards().size()!=0){
                       this.printr();
                       this.setR(cases++);
                       nQueens(Integer.parseInt(values[i]));
                       this.setRow(Integer.parseInt(values[i]));
                       this.setIterations(this.getIterations()+1);
                       this.setR(this.getR()-this.getIterations());
                   }else{
                       this.setR(cases++);
                       nQueens(Integer.parseInt(values[i]));
                       this.setRow(Integer.parseInt(values[i]));
                   }
               }
               this.setRow(this.getRow()-1);

               if(values.length>2&& this.getAsterisk()){
                   for (int j=0;j<values.length;j++){
                       values1[j]=Integer.parseInt(values[j]);
                   }
                   this.getArrayasterisk().add(values1);
                   this.setAsterisk(false);
               }
               letter=read.readLine();
           }
       }catch (Exception e){
           System.out.println("Se ha genreado un Error en la lectura de tipo:"+e);
       }

   }

    public List getBoards() {
        return boards;
    }

    public List getArrayasterisk() {
        return arrayasterisk;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public boolean getAsterisk() {
        return asterisk;
    }

    public void setAsterisk(boolean asterisk) {
        this.asterisk = asterisk;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public static void main(String[] args) {
        Queens n = new Queens();
        n.readtext("prueba");
        n.printr();
    }
}