import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr1 = br.readLine().toCharArray();
        char[] arr2 = br.readLine().toCharArray();

        // 두 수열이 주어졌을때, 모두의 부분수열 중 가장 긴 것을 찾기
        int[][] dp = new int[arr1.length + 1][arr2.length + 1];
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1; // 문자가 같을 경우
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]); // 문자가 다를 경우
                }
            }
        }
        System.out.println(dp[arr1.length][arr2.length]);
    }
}