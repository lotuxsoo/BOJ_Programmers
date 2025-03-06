
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

    static int D(int n) {
        return n * 2 > 9999 ? n * 2 % 10000 : n * 2;
    }

    static int S(int n) {
        return n == 0 ? 9999 : n - 1;
    }

    static int L(int n) {
        return (n % 1000) * 10 + n / 1000;
    }

    static int R(int n) {
        return (n / 10) + (n % 10) * 1000;
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

            queue.add(new Register(D(val), order + "D"));

            queue.add(new Register(S(val), order + "S"));

            queue.add(new Register(L(val), order + "L"));

            queue.add(new Register(R(val), order + "R"));
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
