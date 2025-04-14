
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[101][101];
        for (int t = 0; t < 4; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = 100 - Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = 100 - Integer.parseInt(st.nextToken());

            for (int i = y2; i < y1; i++) {
                for (int j = x1; j < x2; j++) {
                    map[i][j]++;
                }
            }
        }

        int c = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (map[i][j] > 0) {
                    c++;
                }
            }
        }

        System.out.println(c);
    }
}
