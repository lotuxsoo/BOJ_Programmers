import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] D = new int[15][15];
        for (int i = 0; i <= 14; i++) {
            D[0][i] = i;
            D[i][1] = 1;
        }

        for (int i = 1; i <= 14; i++) {
            for (int j = 1; j <= 14; j++) {
                D[i][j] = D[i][j - 1] + D[i - 1][j];
            }
        }

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            // k층 n호에 몇명이 살고 있는지
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());
            System.out.println(D[k][n]);
        }
    }
}
