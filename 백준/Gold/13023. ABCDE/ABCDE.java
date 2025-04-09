
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static boolean dfs(int cnt, int x) {
        visited[x] = true;
        if (cnt == 5) {
            return true;
        }

        for (int next : graph[x]) {
            if (!visited[next]) {
                if (dfs(cnt + 1, next)) {
                    return true;
                }
            }
        }

        visited[x] = false;
        return false;
    }

    static int N, M;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        visited = new boolean[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 0; i <= N - 1; i++) {
            if (dfs(1, i)) {
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);
    }
}
