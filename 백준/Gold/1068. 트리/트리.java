import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] T;
    static boolean[] visited;
    static int answer = 0;

    static void DFS(int x, int del) {
        visited[x] = true;
        int node = 0;

        ArrayList<Integer> list = T[x];
        for (int i : T[x]) {
            if (!visited[i] && i != del) {
                node++;
                DFS(i, del);
            }
        }

        if (node == 0) {
            answer++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        T = new ArrayList[N + 1]; // 0~N번 노드까지 인접리스트 저장
        for (int i = 0; i < N; i++) {
            T[i] = new ArrayList<>(); // ArrayList[] 초기화
        }
        visited = new boolean[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int root = 0;
        for (int i = 0; i < N; i++) { // 0~N-1번까지 부모노드
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
                continue;
            }
            T[parent].add(i);
            T[i].add(parent);
        }
        int del = Integer.parseInt(br.readLine());
        if (root == del) {
            System.out.println(0);
            return;
        }

        DFS(root, del);

        System.out.println(answer);
    }
}
