
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int calculate(ArrayList<Integer> team1, ArrayList<Integer> team2) {
        int sum1 = 0;
        for (int i = 0; i < team1.size() - 1; i++) {
            for (int j = i + 1; j < team1.size(); j++) {
                int a = team1.get(i), b = team1.get(j);
                sum1 += S[a][b] + S[b][a];
            }
        }

        int sum2 = 0;
        for (int i = 0; i < team2.size() - 1; i++) {
            for (int j = i + 1; j < team2.size(); j++) {
                int a = team2.get(i), b = team2.get(j);
                sum2 += S[a][b] + S[b][a];
            }
        }

        return Math.abs(sum1 - sum2);
    }

    static void backtrack(int start, int n) {
        if (n == N / 2) {
            ArrayList<Integer> team1 = new ArrayList<>();
            ArrayList<Integer> team2 = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                if (visited[i]) {
                    team1.add(i);
                } else {
                    team2.add(i);
                }
            }
            minDiff = Math.min(minDiff, calculate(team1, team2));
            return;
        }

        for (int i = start; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtrack(i + 1, n + 1);
                visited[i] = false;
            }
        }
    }

    static int minDiff = Integer.MAX_VALUE;
    static int N;
    static int[][] S;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            String[] sp = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                S[i][j] = Integer.parseInt(sp[j - 1]);
            }
        }

        visited = new boolean[N + 1];
        backtrack(1, 0);

        System.out.println(minDiff);
    }
}
