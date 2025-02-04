import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] A;
    static int[] answer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        answer = new int[N];

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {

            while (!stack.isEmpty() && A[stack.peek()] < A[i]) {
                answer[stack.pop()] = A[i];
            }
            stack.push(i);

        }

        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }

        for (int x : answer) {
            sb.append(x).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
