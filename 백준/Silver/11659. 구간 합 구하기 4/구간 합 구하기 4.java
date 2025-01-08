import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[][] arr2 = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            arr2[i][0] = Integer.parseInt(st.nextToken()) - 1;
            arr2[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        // 부분합 구해놓기
        int[] sum = new int[N];
        sum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        // 구간합: S[j] - S[i-1]
        for (int[] arr1 : arr2) {
            int x;
            if (arr1[0] == 0) {
                x = sum[arr1[1]];
            } else {
                x = sum[arr1[1]] - sum[arr1[0] - 1];
            }
            System.out.println(x);
        }
    }
}
