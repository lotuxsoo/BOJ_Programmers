import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static void DFS(int x, int nowColor) {
        if (found) {
            return;
        }

        color[x] = nowColor;

        for (int next : graph[x]) {
            if (color[next] == 0) {
                DFS(next, -nowColor);
            } else {
                if (color[next] == color[x]) {
                    found = true;
                    return;
                }
            }
        }
    }

    static ArrayList<Integer>[] graph;
    static int[] color;
    static boolean found; // 이분그래프 아닌걸 찾음
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = new ArrayList[V + 1];
            for (int i = 0; i < V + 1; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            color = new int[V + 1]; // 각 정점의 색을 구분
            found = false;

            for (int i = 1; i <= V; i++) {
                if (found) {
                    break;
                }
                if (color[i] == 0) {
                    DFS(i, 1);
                }
            }

            if (found) {
                sb.append("NO\n");
            } else {
                sb.append("YES\n");
            }
        }
        System.out.println(sb.toString());
    }
}
