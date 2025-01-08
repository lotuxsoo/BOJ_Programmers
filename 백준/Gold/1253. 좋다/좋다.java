import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr); // 배열 정렬

        int answer = 0;

        for (int i = 0; i < N; i++) { // i는 현재 확인 중인 "좋은 수"
            int left = 0;
            int right = N - 1;

            while (left < right) {
                if (left == i) { // left가 i와 같으면 스킵
                    left++;
                    continue;
                }
                if (right == i) { // right가 i와 같으면 스킵
                    right--;
                    continue;
                }

                int sum = arr[left] + arr[right];

                if (sum == arr[i]) { // "좋은 수" 조건 만족
                    answer++;
                    break; // "좋은 수"를 찾았으므로 종료
                } else if (sum < arr[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(answer);
    }
}