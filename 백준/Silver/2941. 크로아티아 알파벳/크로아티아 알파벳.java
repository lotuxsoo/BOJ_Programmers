
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
            if (i <= word.length() - 2) {
                String substr = word.substring(i, i + 2);
                if (alphabet.contains(substr)) {
                    result++;
                    i += 2;
                    continue;
                }
            }
            if (i <= word.length() - 3) {
                String substr = word.substring(i, i + 3);
                if (alphabet.contains(substr)) {
                    result++;
                    i += 3;
                    continue;
                }
            }
            result++;
            i++;
        }

        System.out.println(result);
    }
}
