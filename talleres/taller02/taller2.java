import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @author Cesar Garcia Posada, Daniel Felipe Gomez martinez
 */

public class taller2 {

    /**
     * Auxiliary method that calls the subsequent combinations method.
     * @param  chain the set on which the combinations are made.
     */

    public static ArrayList<String> combinations(String chain) {
        ArrayList<String> r = new ArrayList<>();
        combinations("",chain,r);
        return r;
    }

    /**
     * Method to obtain the possible combinations that can be made
     * with the given elements.
     *
     * @param  index take the position to travel the set.
     * @param list the set that has all the combinations.
     * @param s the current chain.
     *
     */
    private static void combinations(String s, String index, ArrayList<String> list) {
        if (index.isEmpty()) {
            list.add(s);
        }else{
            combinations(s+index.charAt(0),index.substring(1),list);
            combinations(s,index.substring(1),list);
        }
    }

    /**
     * Auxiliary method that calls the subsequent permutations method.
     *
     * @param  s the chain to which the permutations are made.
     * @return an ArrayList that contains the permutations.
     */
    public static ArrayList<String> permutations(String s) {
            ArrayList<String> e =new ArrayList<>();
            permutations("",s,e);
        return e;
    }

    /**
     * Method to obtain the possible permutations that can be done with the characters of a given string,
     * remember that the letters are not repeated in this exercise.
     *
     * @param pre part of the chain that starts from 0 to i.
     * @param pos part of the chain that strats from i to n.
     * @param list the set that has all the permutations.
     *
     */
    private static void permutations(String pre, String pos, ArrayList<String> list) {
        int l = pos.length();
        if(pos.isEmpty()){
            list.add(pre);
            desencriptarArchivo(pre);
             return;
          }else{
             for(int i =0;i<l;i++){
                 permutations(pre+pos.charAt(i),pos.substring(0,i)+pos.substring(i+1),list);
             }
          }
    }

    /**
     * Method that prints on the screen how is the given board
     *
     * @param  tablero is an arrangement with the positions of a chess board
     */
    public static void imprimirTablero(int[] tablero) {
        int n = tablero.length;
        System.out.print("    ");
        for (int i = 0; i < n; ++i)
            System.out.print(i + " ");
            System.out.println("\n");
            for (int i = 0; i < n; ++i) {
                System.out.print(i + "   ");
                for (int j = 0; j < n; ++j)
                    System.out.print((tablero[i] == j ? "Q" : "#") + " ");
                System.out.println();
            }
            System.out.println();
    }

    /**
     * Auxiliary method verifies if the implemented board is correct
     * that is to say the queens are positioned correctly
     *
     * @param  tablero An arrangement with the positions of a chessboard.
     * @return true if it is true, false otherwise.
     */
    public static boolean esValido(int[] tablero) {

          return false;
    }

    /**
     * Method that shows the number of possible solutions to the problem
     *
     * @param  n number of queens
     * @return number of solutions
     */
    public static int queens(int n) {

        return n;
    }

    public static byte[] decrypt(byte[] cipherText, String password)
    {
        try{
            String key = "MZygpewJsCpR"+password;
            byte[] keyInBytes = key.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKey = new SecretKeySpec(keyInBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(cipherText);
        }
        catch(Exception e)
        {
            return new byte[0];
        }
    }

    /**
     * Decrypt the archive archivoEncriptado.txt with the password. If the password fails it returns an empty string.
     *
     * @param password The password to decrypt the file is a permutation of the ABCD chain.
     * @return Returns a string with the content of the decrypted file.
     */
    public static String desencriptarArchivo(String password) {
        try{
            Path path = Paths.get("archivoEncriptado.txt");
            byte[] archivoEncriptado = Files.readAllBytes(path);
            byte[] decryptedCipherText = decrypt(archivoEncriptado,password);
            return new String(decryptedCipherText);
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
            return  "";
        }
    }

    public static void main(String[] args) {
        permutations("abcd");
    }
}
