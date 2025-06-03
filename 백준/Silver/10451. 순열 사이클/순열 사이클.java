
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static void dfs(int x) {
        visited[x] = true;

        for (int next : graph[x]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            graph = new ArrayList[N + 1];
            for (int i = 0; i < N + 1; i++) {
                graph[i] = new ArrayList<>();
            }
            String[] sp = br.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(sp[i]);
                graph[i + 1].add(x); // 단방향 그래프
            }

            // 순열 사이클 dfs로 구하기
            int answer = 0;
            visited = new boolean[N + 1];
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    answer++;
                    dfs(i);
                }
            }
            System.out.println(answer);
        }
    }
}
