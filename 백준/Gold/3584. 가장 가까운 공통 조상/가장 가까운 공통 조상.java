
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int dfs(int x) {
        if (parent[x] == 0) {
            return 0;
        }

        if (depth[x] != -1) {
            return depth[x]; // 이미 계산한 경우
        }

        depth[x] = dfs(parent[x]) + 1;

        return depth[x];
    }

    static int N, X, Y;
    static int[] parent, depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            parent = new int[N + 1];

            for (int i = 0; i < N - 1; i++) {
                String[] sp = br.readLine().split(" ");
                int A = Integer.parseInt(sp[0]);
                int B = Integer.parseInt(sp[1]);
                parent[B] = A; // A가 B의 부모
            }

            String[] sp = br.readLine().split(" ");
            X = Integer.parseInt(sp[0]);
            Y = Integer.parseInt(sp[1]);
            
            depth = new int[N + 1];
            Arrays.fill(depth, -1);
            int d1 = dfs(X);

            depth = new int[N + 1];
            Arrays.fill(depth, -1);
            int d2 = dfs(Y);

            while (d1 < d2) {
                Y = parent[Y];
                d2--;
            }
            while (d1 > d2) {
                X = parent[X];
                d1--;
            }
            while (X != Y) {
                X = parent[X];
                Y = parent[Y];
            }

            System.out.println(X);
        }
    }
}
