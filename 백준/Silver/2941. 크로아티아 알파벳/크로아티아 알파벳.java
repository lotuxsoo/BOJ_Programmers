
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

public class Main {

    static Set<String> alphabet = Set.of("c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=");

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        int result = 0;
        int i = 0;
        while (i < word.length()) {
            if ((i <= word.length() - 3) && alphabet.contains(word.substring(i, i + 3))) {
                i += 3;
            } else if ((i <= word.length() - 2) && alphabet.contains(word.substring(i, i + 2))) {
                i += 2;
            } else {
                i++;
            }
            result++;
        }

        System.out.println(result);
    }
}
