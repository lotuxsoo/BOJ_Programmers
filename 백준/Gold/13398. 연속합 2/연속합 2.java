
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] L = new int[N]; // L[i]: 왼쪽에서부터 i를 포함한 연속 최대합
        int[] R = new int[N]; // R[i]: 오른쪽에서부터 i를 포함한 연속 최대합

        L[0] = A[0];
        R[N - 1] = A[N - 1];
        int maxVal = L[0];

        for (int i = 1; i < N; i++) {
            L[i] = Math.max(L[i - 1] + A[i], A[i]);
            maxVal = Math.max(maxVal, L[i]);
        }
        for (int i = N - 2; i >= 0; i--) {
            R[i] = Math.max(R[i + 1] + A[i], A[i]);
        }

        for (int i = 1; i < N - 1; i++) {
            maxVal = Math.max(maxVal, L[i - 1] + R[i + 1]);
        }
        System.out.println(maxVal);
    }
}
