import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] tree;
    static boolean[] visited;
    static int[] answer;

    static void DFS(int x) {
        visited[x] = true;

        ArrayList<Integer> list = tree[x];
        for (int i : list) {
            if (!visited[i]) {
                visited[i] = true;
                answer[i] = x;
                DFS(i);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            tree[i] = new ArrayList<>(); // 초기화 필수
        }
        visited = new boolean[N + 1];
        answer = new int[N + 1];

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        DFS(1);

        for (int i = 2; i <= N; i++) {
            System.out.println(answer[i]);
        }

    }
}
