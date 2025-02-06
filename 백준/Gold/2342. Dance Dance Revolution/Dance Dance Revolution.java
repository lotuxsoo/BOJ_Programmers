import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class State {
        int index, left, right, cost;

        State(int index, int left, int right, int cost) {
            this.index = index;
            this.left = left;
            this.right = right;
            this.cost = cost;
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

    static List<Integer> instruction = new ArrayList<>();
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int x = Integer.parseInt(st.nextToken());
            if (x == 0) {
                break;
            }
            instruction.add(x);
        }

        // dp[i][left][right]: 이 상태에서 달성하 ㄴ최소 힘
        dp = new int[instruction.size() + 1][5][5];

        // 메모 초기화
        for (int i = 0; i <= instruction.size(); i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        // 반복문 풀이

        Queue<State> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        queue.add(new State(0, 0, 0, 0));
        dp[0][0][0] = 0;

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (cur.index == instruction.size()) {
                break;
            }

            if (cur.cost > dp[cur.index][cur.left][cur.right]) {
                continue;
            }

            // 다음 명령어 처리
            int nextPos = instruction.get(cur.index);

            if (nextPos != cur.left) {
                int nextCost = cur.cost + getCost(cur.right, nextPos);
                if (nextCost < dp[cur.index + 1][cur.left][nextPos]) {
                    dp[cur.index + 1][cur.left][nextPos] = nextCost;
                    queue.add(new State(cur.index + 1, cur.left, nextPos, nextCost)); // 비용 갱신
                }
            }

            if (nextPos != cur.right) {
                int nextCost = cur.cost + getCost(cur.left, nextPos);
                if (nextCost < dp[cur.index + 1][nextPos][cur.right]) {
                    dp[cur.index + 1][nextPos][cur.right] = nextCost;
                    queue.add(new State(cur.index + 1, nextPos, cur.right, nextCost));
                }
            }
        }

        int bestCost = Integer.MAX_VALUE;

        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                bestCost = Math.min(bestCost, dp[instruction.size()][j][k]);
            }
        }

        System.out.println(bestCost);
    }
}
