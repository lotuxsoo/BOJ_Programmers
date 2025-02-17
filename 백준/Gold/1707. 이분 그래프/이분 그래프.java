import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean BFS(int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        check[x] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph[cur]) {
                if (check[next] == 0) {
                    check[next] = -check[cur];
                    queue.add(next);
                } else {
                    if (check[next] == check[cur]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static ArrayList<Integer>[] graph;
    static int[] check;
    static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());

            // 인접리스트 초기화 (양방향)
            graph = new ArrayList[V + 1];
            for (int j = 0; j < V + 1; j++) {
                graph[j] = new ArrayList<>();
            }

            E = Integer.parseInt(st.nextToken());
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            check = new int[V + 1]; // 0 초기화 상태
            boolean found = true;

            for (int j = 1; j <= V; j++) {
                if (check[j] == 0) {
                    if (!BFS(j)) {
                        found = false;
                        break;
                    }
                }
            }

            System.out.println(found ? "YES" : "NO");
        }
    }
}
