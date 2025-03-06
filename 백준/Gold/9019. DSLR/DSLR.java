
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Register {
        int val;
        String order;

        Register(int val, String order) {
            this.val = val;
            this.order = order;
        }
    }

    static String bfs(int A, int B) {
        Queue<Register> queue = new LinkedList<>();
        queue.add(new Register(A, ""));
        boolean[] visited = new boolean[10000];

        while (!queue.isEmpty()) {
            Register cur = queue.poll();
            int val = cur.val;
            String order = cur.order;

            if (val == B) {
                return order;
            }

            if (visited[val]) {
                continue;
            }
            visited[val] = true;

            // D
            int v1 = val * 2;
            if (v1 > 9999) {
                v1 %= 10000;
            }
            queue.add(new Register(v1, order + "D"));

            // S
            int v2 = val - 1;
            if (val == 0) {
                v2 = 9999;
            }
            queue.add(new Register(v2, order + "S"));

            // L
            int v3 = (val % 1000) * 10 + val / 1000;
            queue.add(new Register(v3, order + "L"));

            // R
            int v4 = (val / 10) + (val % 10) * 1000;
            queue.add(new Register(v4, order + "R"));
        }
        return "";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            System.out.println(bfs(A, B));
        }
    }
}
