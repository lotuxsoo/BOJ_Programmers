import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static void DFS(int x) {
        visited[x] = true;

        for (int next : graph[x]) {
            if (!visited[next]) {
                check[next] = (check[x] + 1) % 2;
                DFS(next);
            } else {
                if (check[next] == check[x]) {
                    found = true;
                    return;
                }
            }
        }
    }

    static ArrayList<Integer>[] graph;
    static int[] check;
    static boolean[] visited;
    static int V, E;
    static boolean found;

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

            check = new int[V + 1];
            visited = new boolean[V+1];

            E = Integer.parseInt(st.nextToken());
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            found = false;
            
            // 그래프가 모두 연결돼있다는 보장이 없으므로 모든 노드에서 수행
            for (int j = 1; j <= V; j++) {
                if (!found) {
                    DFS(j);
                } else {
                    System.out.println("NO");
                    break;
                }
            }

            if (!found) {
                System.out.println("YES");
            }

        }
    }
}
