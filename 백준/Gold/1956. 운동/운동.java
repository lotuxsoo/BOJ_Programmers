
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static int[][] dist;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        dist = new int[V + 1][V + 1];
        for (int i = 0; i < V + 1; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dist[a][b] = c;
        }

        for (int k = 1; k <= V; k++) {
            for (int s = 1; s <= V; s++) {
                for (int e = 1; e <= V; e++) {
                    if (dist[s][k] != INF && dist[k][e] != INF) {
                        dist[s][e] = Math.min(dist[s][e], dist[s][k] + dist[k][e]);
                    }
                }
            }
        }

        int minCycle = INF;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j) {
                    continue;
                }
                if (dist[i][j] != INF && dist[j][i] != INF) {
                    minCycle = Math.min(minCycle, dist[i][j] + dist[j][i]);
                }
            }
        }

        System.out.println(minCycle == INF ? -1 : minCycle);
    }
}
