
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int x, y, score;

        Node(int x, int y, int score) {
            this.x = x;
            this.y = y;
            this.score = score;
        }
    }

    static int N;
    static int[][] map;
    static int MIN_VAL = Integer.MAX_VALUE;
    static int MAX_VAL = Integer.MIN_VALUE;
    static int[] dy = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] min = new int[3];
        int[] max = new int[3];
        Arrays.fill(min, Integer.MAX_VALUE);
        Arrays.fill(max, Integer.MIN_VALUE);

        min[0] = map[N - 1][0];
        max[0] = map[N - 1][0];

        min[1] = map[N - 1][1];
        max[1] = map[N - 1][1];

        min[2] = map[N - 1][2];
        max[2] = map[N - 1][2];

        for (int i = N - 2; i >= 0; i--) {
            int[] curMin = new int[3];
            int[] curMax = new int[3];
            Arrays.fill(curMin, Integer.MAX_VALUE);
            Arrays.fill(curMax, Integer.MIN_VALUE);
            
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    int nj = j + dy[k];
                    if (!(0 <= nj && nj < 3)) {
                        continue;
                    }
                    curMin[j] = Math.min(curMin[j], min[nj] + map[i][j]);
                    curMax[j] = Math.max(curMax[j], max[nj] + map[i][j]);
                }
            }
            
            min = curMin;
            max = curMax;
        }

        for (int i = 0; i < 3; i++) {
            MIN_VAL = Math.min(MIN_VAL, min[i]);
            MAX_VAL = Math.max(MAX_VAL, max[i]);
        }
        System.out.println(MAX_VAL + " " + MIN_VAL);
    }
}
