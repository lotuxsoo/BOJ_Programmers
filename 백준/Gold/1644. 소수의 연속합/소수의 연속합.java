
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static void generatePrime(int N) {
        boolean[] isNotPrime = new boolean[N + 1];

        for (int i = 2; i * i <= N; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        // 소수는 2~N 사이에 존재
        for (int i = 2; i <= N; i++) {
            if (!isNotPrime[i]) {
                primes.add(i);
            }
        }
    }

    static ArrayList<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        generatePrime(N);

        int left = 0, right = 0, sum = 0, result = 0;

        while (left <= right) {
            if (sum >= N) {
                if (sum == N) {
                    result++;
                }
                sum -= primes.get(left);
                left++;
            } else if (sum < N) {
                if (right == primes.size()) {
                    break;
                }
                sum += primes.get(right);
                right++;
            }
        }

        System.out.println(result);
    }
}
