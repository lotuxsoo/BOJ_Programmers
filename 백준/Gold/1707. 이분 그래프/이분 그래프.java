
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static boolean dfs(int x, boolean flag) {
        if (!flag) {
            return false;
        }

        for (int next : graph[x]) {
            if (checked[next] == 0) {
                checked[next] = -checked[x];
                flag = dfs(next, flag);
            } else if (checked[next] == checked[x]) {
                return false;
            }
        }

        return flag;
    }

    static ArrayList<Integer>[] graph;
    static int[] checked;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
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

            checked = new int[V + 1];
            boolean flag = true;
            for (int i = 1; i <= V; i++) {
                if (checked[i] == 0) {
                    checked[i] = 1;
                    flag = dfs(i, true); // 이분그래프 아니면 false
                    if (!flag) {
                        sb.append("NO\n");
                        break;
                    }
                }
            }

            if (flag) {
                sb.append("YES\n");
            }
        }
        System.out.println(sb.toString());
    }
}
