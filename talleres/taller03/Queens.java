/**
 * @author Daniel García García - Cesar Andres Garcia Posada - Daniel Felipe Gómez Martínez
 */
public class Queens {

   public static void nQueens(int n, int[] board, int colum){
       if (colum==n){
           if (CollisionQueens(board, colum)) {
               printBoard(board);
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

   public static void printBoard(int[] board){
       System.out.print("[");
       for (int i=0;i<board.length;i++)
           System.out.print(board[i]);
       System.out.println("]");
   }

   public static void nQueens(int n){
       nQueens(n,new int [n],0);
   }

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

    public static void main(String[] args) {
        nQueens(4);
    }
}