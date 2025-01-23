import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int s, e, w;

        Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 도시 개수
        int M = Integer.parseInt(st.nextToken()); // 에지 개수

        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(s, e, w));
        }

        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M; j++) {
                Edge edge = edges.get(j);

                if (dist[edge.s] != Long.MAX_VALUE && dist[edge.e] > dist[edge.s] + edge.w) {
                    dist[edge.e] = dist[edge.s] + edge.w;
                }
            }
        }

        boolean mCycle = false;
        for (int i = 0; i < M; i++) {
            Edge edge = edges.get(i);

            if (dist[edge.s] != Long.MAX_VALUE && dist[edge.e] > dist[edge.s] + edge.w) {
                mCycle = true;
                break;
            }
        }

        if (!mCycle) {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i < N + 1; i++) {
                sb.append(dist[i] == Long.MAX_VALUE ? -1 : dist[i]).append("\n");
            }
            System.out.print(sb.toString());
        } else {
            System.out.println(-1);
        }
    }
}
