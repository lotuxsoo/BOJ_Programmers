import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            boolean group = true;
            char[] chars = br.readLine().toCharArray();
            boolean[] visited = new boolean[26]; // a:0 ~ z:25
            char first = chars[0];
            visited[(int) first - 'a'] = true;
            for (int j = 1; j < chars.length; j++) {
                char now = chars[j];
                if (first != now && visited[(int) now - 'a']) {
                    group = false;
                    break;
                }
                visited[(int) now - 'a'] = true;
                first = now;
            }

            if (group) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
