
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int E, S, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int value = 1;
        while (true) {
            int e = (value % 15 == 0) ? 15 : value % 15;
            int s = (value % 28 == 0) ? 28 : value % 28;
            int m = (value % 19 == 0) ? 19 : value % 19;
            if ((e == E) && (s == S) && (m == M)) {
                break;
            }
            value++;
        }

        System.out.println(value);
    }
}
