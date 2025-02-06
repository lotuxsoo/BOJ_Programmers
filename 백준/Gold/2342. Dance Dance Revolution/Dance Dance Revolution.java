import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class State {
        int idx, left, right; // 큐에 넣을 상태 정의

        State(int idx, int left, int right) {
            this.idx = idx;
            this.left = left;
            this.right = right;
        }
    }

    static int getCost(int from, int to) {
        if (from == 0) {
            return 2;
        }
        if (Math.abs(from - to) == 2) {
            return 4;
        }
        if (from == to) {
            return 1;
        }
        return 3;
    }

    static List<Integer> commands = new ArrayList<>();
    static int[][][] dp;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (true) {
            int t = Integer.parseInt(st.nextToken());
            if (t == 0) {
                break;
            }
            commands.add(t);
        }

        // i번째 지시, (x,y)에서의 최소 힘
        dp = new int[commands.size() + 1][5][5];

        // INF로 초기화
        for (int i = 0; i <= commands.size(); i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        // 초기값 지정
        dp[0][0][0] = 0;

        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0, 0, 0));

        int ans = INF;

        // 가중치 BFS 탐색 (업데이트 o)
        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (cur.idx == commands.size()) {
                ans = Math.min(ans, dp[cur.idx][cur.left][cur.right]);
                continue;
            }

            int nextPos = commands.get(cur.idx);

            if (cur.left != nextPos) { // right로 이동 가능
                if (dp[cur.idx + 1][cur.left][nextPos] > dp[cur.idx][cur.left][cur.right] + getCost(cur.right,
                        nextPos)) { // 갱신 가능한지
                    dp[cur.idx + 1][cur.left][nextPos] = dp[cur.idx][cur.left][cur.right] + getCost(cur.right, nextPos);
                    queue.add(new State(cur.idx + 1, cur.left, nextPos));
                }
            }

            if (cur.right != nextPos) {
                if (dp[cur.idx + 1][nextPos][cur.right] > dp[cur.idx][cur.left][cur.right] + getCost(cur.left,
                        nextPos)) {
                    dp[cur.idx + 1][nextPos][cur.right] = dp[cur.idx][cur.left][cur.right] + getCost(cur.left, nextPos);
                    queue.add(new State(cur.idx + 1, nextPos, cur.right));
                }
            }
        }

        System.out.println(ans);
    }
}
