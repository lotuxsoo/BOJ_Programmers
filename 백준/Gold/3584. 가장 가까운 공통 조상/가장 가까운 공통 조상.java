
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static int N;
    static int[] parent;
    static int x, y;

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
                parent[B] = A; // 무조건 부모노드는 1개
            }
            String[] sp = br.readLine().split(" ");
            x = Integer.parseInt(sp[0]);
            y = Integer.parseInt(sp[1]);

            Set<Integer> ancestor = new HashSet<>();

            while (x != 0) {
                ancestor.add(x);
                x = parent[x];
            }

            while (y != 0) {
                if (ancestor.contains(y)) {
                    System.out.println(y);
                    break;
                }
                y = parent[y];
            }

        }
    }
}
