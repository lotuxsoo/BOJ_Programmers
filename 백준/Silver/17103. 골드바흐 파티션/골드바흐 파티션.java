
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static void generatePrime() {
        // 에라토스테네스의 체
        for (int i = 2; i * i <= 1000000; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= 1000000; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        for (int i = 2; i <= 1000000; i++) {
            if (!isNotPrime[i]) {
                primes.add(i);
            }
        }

        Collections.sort(primes);
    }

    static void solve(int N) {
        int count = 0;

        for (int prime : primes) {
            if (prime > N / 2) {
                break;
            }
            int complement = N - prime;
            if (!isNotPrime[complement]) {
                count++;
            }
        }

        sb.append(count).append("\n");
    }

    static ArrayList<Integer> primes = new ArrayList<>();
    static boolean[] isNotPrime = new boolean[1000001];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        generatePrime();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            solve(N);
        }
        System.out.println(sb.toString().trim());
    }
}
