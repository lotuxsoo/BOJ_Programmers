
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static void generatePrimes() {
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

    static void solve(int n) {

        for (int prime : primes) {
            if (prime > n / 2) {
                break;
            }
            int complement = n - prime;
            if (!isNotPrime[complement]) {
                sb.append(n + " = " + prime + " + " + complement + "\n");
                return;
            }
        }

        sb.append("Goldbach's conjecture is wrong.\n");
    }

    static ArrayList<Integer> primes = new ArrayList<>();
    static boolean[] isNotPrime = new boolean[1000001];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());

        generatePrimes();

        while (input != 0) {
            solve(input);
            input = Integer.parseInt(br.readLine());
        }
        System.out.println(sb.toString().trim());
    }
}
