public class T9 {
    /**
     * Metodo que pretende implementar el funcionamiento del algoritmo levenshtein
     * de dos cadenas a y b
     * @param a cadena de caracteres
     * @param b cadena de caracteres
     * para mas informacion ver
     * @see <a href="https://people.cs.pitt.edu/~kirk/cs1501/Pruhs/Spring2006/assignments/editdistance/Levenshtein%20Distance.htm">
     *
     */
    public static int levenshtein(String a, String b) {
        int [][] board = new int[b.length()+1][a.length()+1];
        for (int i=1;i<board.length;i++){
            board[i][0]=i;
        }
        for (int i=1;i<board[0].length;i++){
            board[0][i]=i;
        }
        for (int i=1;i<=b.length();i++){
            for (int j=1;j<board[0].length;j++){
                if(b.charAt(i-1)==a.charAt(j-1)){
                    board[i][j]=board[i-1][j-1];
                }else {
                    board[i][j]=Math.min(board[i-1][j],Math.min(board[i][j-1],board[i-1][j-1]))+1;
                }
            }
        }
        return board[b.length()][a.length()];
    }

    public static void main(String[] args) {
        System.out.println("Levenshtein -> " + convert(testLevenshtein()));
    }

    static boolean testLevenshtein() {
        String[] wordlist = { "hash", "quantum", "fever", "bench", "long", "blade", "object", "orphanage", "flophouse",
                "fathead" };
        int[][] answers = { { 0, 6, 5, 4, 4, 4, 6, 7, 7, 5 }, { 6, 0, 7, 6, 6, 6, 7, 7, 8, 7 },
                { 5, 7, 0, 4, 5, 5, 5, 9, 8, 5 }, { 4, 6, 4, 0, 4, 4, 4, 8, 8, 7 }, { 4, 6, 5, 4, 0, 4, 6, 7, 7, 7 },
                { 4, 6, 5, 4, 4, 0, 5, 7, 7, 6 }, { 6, 7, 5, 4, 6, 5, 0, 8, 8, 6 }, { 7, 7, 9, 8, 7, 7, 8, 0, 7, 7 },
                { 7, 8, 8, 8, 7, 7, 8, 7, 0, 7 }, { 5, 7, 5, 7, 7, 6, 6, 7, 7, 0 } };
        int n = wordlist.length;
        for (int i = 0; i < n; ++i)
            for (int j = 1; j < n; ++j)
                if (T9.levenshtein(wordlist[i], wordlist[j]) != answers[i][j])
                    return false;
        return true;
    }

    static String convert(boolean b) {
        return b ? "correcta" : "incorrecta";
    }
}
