
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static void DFS(int x) {
        visited[x] = true;

        for (int next : A[x]) {
            if (!visited[next]) {
                DFS(next);
            }
        }
    }

    static ArrayList<Integer>[] A;
    static int N, M;
    static int[] route;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        StringTokenizer st;

        // 인접행렬 -> 인접리스트
        A = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    A[i].add(j);
                    A[j].add(i); // 양방향 연결
                }
            }
        }

        route = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N + 1];
        int start = route[0];
        DFS(start);

        for (int i = 0; i < M; i++) {
            if (!visited[route[i]]) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }
}
