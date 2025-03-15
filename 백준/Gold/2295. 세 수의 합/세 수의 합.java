
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] U = new int[N];
        for (int i = 0; i < N; i++) {
            U[i] = Integer.parseInt(br.readLine());
        }

        Set<Integer> sumSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sumSet.add(U[i] + U[j]);
            }
        }

        int maxSum = Integer.MIN_VALUE;

        // x+y = d-z
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (sumSet.contains(U[i] - U[j])) {
                    maxSum = Math.max(maxSum, U[i]);
                }
            }
        }

        System.out.println(maxSum);
    }
}
