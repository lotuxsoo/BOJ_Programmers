
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < N; k++) {
            for (int s = 0; s < N; s++) {
                for (int e = 0; e < N; e++) {
                    if (dist[s][k] == 1 && dist[k][e] == 1) {
                        dist[s][e] = 1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(dist[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
