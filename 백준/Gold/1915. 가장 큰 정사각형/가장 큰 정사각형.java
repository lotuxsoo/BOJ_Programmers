import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] D = new int[1001][1001];
        int MAX_VAL = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                D[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
                if (D[i][j] == 1 && i > 0 && j > 0) {
                    D[i][j] = Math.min(D[i - 1][j - 1], Math.min(D[i][j - 1], D[i - 1][j])) + 1;
                }
                MAX_VAL = Math.max(MAX_VAL, D[i][j]);
            }
        }

        System.out.println(MAX_VAL * MAX_VAL);
    }
}
