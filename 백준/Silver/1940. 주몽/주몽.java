import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int answer = 0;
        int left = 1;
        int right = N;

        while (left < right) { // 무조건 2개 선택
            if (arr[left] + arr[right] == M) {
                answer++;
                left++;
                right--;
            } else if (arr[left] + arr[right] > M) {
                right--;
            } else if (arr[left] + arr[right] < M) {
                left++;
            }
        }

        System.out.println(answer);
    }
}
