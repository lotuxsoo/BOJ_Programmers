
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int x, int y) {
        int root1 = find(x);
        int root2 = find(y);
        if (root1 != root2) {
            if (rank[root1] < rank[root2]) {
                parent[root1] = root2;
            } else if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
            } else {
                parent[root2] = root1;
                rank[root1]++;
            }
        }
    }

    static int N, M;
    static int[] rank, parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    if (find(i) != find(j)) {
                        union(i, j);
                    }
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int prev = -1;
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(st.nextToken()) - 1;
            if (prev != -1) {
                if (prev != find(x)) {
                    System.out.println("NO");
                    return;
                }
            } else {
                prev = find(x);
            }
        }
        
        System.out.println("YES");
    }
}
