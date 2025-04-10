
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int dfs(int a, int b, int cnt) {
        if (a == b) {
            return cnt;
        }

        visited[a] = true;

        for (int next : graph[a]) {
            if (!visited[next]) {
                int result = dfs(next, b, cnt + 1);
                if (result != -1) {
                    return result;
                }
            }
        }

        return -1;
    }

    static int n, m, a, b;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String[] sp = br.readLine().split(" ");
        a = Integer.parseInt(sp[0]);
        b = Integer.parseInt(sp[1]);

        graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            sp = br.readLine().split(" ");
            int x = Integer.parseInt(sp[0]);
            int y = Integer.parseInt(sp[1]);
            graph[x].add(y); // 부모-자식 양방향 저장
            graph[y].add(x);
        }

        visited = new boolean[n + 1];
        int result = dfs(a, b, 0);
        System.out.println(result);
    }
}
