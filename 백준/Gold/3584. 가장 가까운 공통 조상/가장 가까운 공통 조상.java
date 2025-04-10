
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static int N;
    static int[] parent; // 부모노드는 반드시 1개

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            // 부모 배열 저장
            parent = new int[N + 1];
            for (int i = 0; i < N - 1; i++) {
                String[] sp = br.readLine().split(" ");
                int a = Integer.parseInt(sp[0]);
                int b = Integer.parseInt(sp[1]);
                parent[b] = a; // 자식->부모 단방향 배열
            }
            String[] sp = br.readLine().split(" ");
            int x = Integer.parseInt(sp[0]);
            int y = Integer.parseInt(sp[1]);

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
