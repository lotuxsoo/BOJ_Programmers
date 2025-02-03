import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] ch = br.readLine().toCharArray();

        int cnt = 0;

        for (char c : ch) {
            if (c == 'L') {
                cnt++;
            }
        }

        System.out.println(Math.min(N, N + 1 - cnt / 2));

    }
}
