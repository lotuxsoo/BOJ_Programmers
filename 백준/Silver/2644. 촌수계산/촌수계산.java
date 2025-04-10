
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int dfs(int cur, int count) {
        visited[cur] = true;

        if (cur == y) {
            return count;
        }

        for (int next : graph[cur]) {
            if (!visited[next]) {
                int result = dfs(next, count + 1);
                if (result != -1) {
                    return result;
                }
            }
        }
        return -1;
    }

    static int n;
    static int x, y;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String[] sp = br.readLine().split(" ");
        x = Integer.parseInt(sp[0]);
        y = Integer.parseInt(sp[1]);

        graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            sp = br.readLine().split(" ");
            int parent = Integer.parseInt(sp[0]);
            int child = Integer.parseInt(sp[1]);
            graph[parent].add(child);
            graph[child].add(parent);
        }

        visited = new boolean[n + 1];
        int count = dfs(x, 0);
        System.out.println(count);
    }
}
