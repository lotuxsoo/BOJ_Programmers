
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int calculate() {
        int result = 0;

        for (int i = 0; i < houses.size(); i++) {
            int[] house = houses.get(i);
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < chickens.size(); j++) {
                if (visited[j]) {
                    int dist = Math.abs(house[0] - chickens.get(j)[0]) + Math.abs(house[1] - chickens.get(j)[1]);
                    min = Math.min(min, dist);
                }
            }
            result += min;
        }
        return result;
    }

    static void backtrack(int start, int n) {
        if (n == M) {
            // 치킨거리 계산
            minDist = Math.min(minDist, calculate());
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtrack(i + 1, n + 1);
                visited[i] = false;
            }
        }
    }

    static int minDist = Integer.MAX_VALUE;
    static int N, M;
    static int[][] map;
    static boolean[] visited;
    static ArrayList<int[]> houses = new ArrayList<>();
    static ArrayList<int[]> chickens = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            sp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(sp[j]);
                if (map[i][j] == 1) {
                    houses.add(new int[]{i, j});
                } else if (map[i][j] == 2) {
                    chickens.add(new int[]{i, j});
                }
            }
        }

        visited = new boolean[chickens.size()];
        backtrack(0, 0);

        System.out.println(minDist);
    }
}
