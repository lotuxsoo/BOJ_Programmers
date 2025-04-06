
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static ArrayList<Integer> generatePrime(int N) {
        boolean[] isNotPrime = new boolean[N + 1];

        for (int i = 2; i * i <= N; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (!isNotPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> primes = generatePrime(N);

        // 투포인터로 연속된 소수의 합 구하기
        int left = 0, right = 0, sum = 0, result = 0;
        while (true) {

            if (sum >= N) {
                if (sum == N) {
                    result++;
                }
                sum -= primes.get(left);
                left++;
            } else if (right == primes.size()) {
                break;
            } else {
                sum += primes.get(right);
                right++;
            }
        }

        System.out.println(result);
    }
}
