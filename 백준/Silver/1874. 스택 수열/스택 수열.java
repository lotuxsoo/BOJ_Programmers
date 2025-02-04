import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    static int N;
    static int[] S;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N];
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(br.readLine());
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int idx = 0;

        for (int num = 1; num <= N; num++) {

            stack.push(num); // 순서대로 반드시 push
            sb.append("+\n");

            while (!stack.isEmpty() && stack.peek() == S[idx]) {
                stack.pop();
                sb.append("-\n");
                idx++;
            }
        }

        if (idx == N) {
            System.out.println(sb.toString());
        } else {
            System.out.println("NO");
        }
    }
}
