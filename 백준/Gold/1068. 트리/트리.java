import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static void dfs(int cur) {
        subtree[cur] = 1;

        for (int i = 0; i < N; i++) {
            if (subtree[i] == 0 && parent[i] == cur && i != remove) {
                dfs(i);
                subtree[cur] += subtree[i];
            }
        }
    }

    static int N, remove, root;
    static int[] parent;
    static int[] subtree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        parent = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
            if (parent[i] == -1) {
                root = i;
            }
        }
        remove = Integer.parseInt(br.readLine());
        if (remove == root) {
            System.out.println(0);
            return;
        }

        subtree = new int[N];
        dfs(root);

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (subtree[i] == 1) {
                count++;
            }
        }
        System.out.println(count);
    }
}
