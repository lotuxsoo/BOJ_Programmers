
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static void dfs(int x) {
        visited[x] = true;

        int next = arr[x];
        if (!visited[next]) {
            dfs(next);
        }
    }

    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            arr = new int[N + 1];

            String[] sp = br.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                arr[i + 1] = Integer.parseInt(sp[i]);
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
