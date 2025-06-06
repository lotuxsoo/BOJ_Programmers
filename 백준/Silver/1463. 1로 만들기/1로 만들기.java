
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{n, 0});

        boolean[] visited = new boolean[n + 1];
        visited[n] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == 1) {
                System.out.println(cur[1]);
                break;
            }

            if (cur[0] % 3 == 0) {
                int x = cur[0] / 3;
                if (!visited[x]) {
                    visited[x] = true;
                    queue.add(new int[]{x, cur[1] + 1});
                }
            }
            if (cur[0] % 2 == 0) {
                int x = cur[0] / 2;
                if (!visited[x]) {
                    visited[x] = true;
                    queue.add(new int[]{x, cur[1] + 1});
                }
            }
            if (cur[0] - 1 >= 1) {
                int x = cur[0] - 1;
                if (!visited[x]) {
                    visited[x] = true;
                    queue.add(new int[]{x, cur[1] + 1});
                }
            }
        }
    }
}
