
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static void dfs(int cur) {

        for (int next : graph[cur]) {
            compliment[next] += compliment[cur];
            dfs(next);
        }
    }

    static int n, m;
    static int[] parent;
    static int[] compliment;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
        }

        compliment = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            compliment[idx] += w;
        }

        graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        int x = n;
        while (x > 1) {
            int p = parent[x];
            graph[p].add(x); // 부모 -> 자식 방향 그래프
            x--;
        }

        dfs(1);

        for (int i = 1; i <= n; i++) {
            System.out.print(compliment[i] + " ");
        }
    }
}
