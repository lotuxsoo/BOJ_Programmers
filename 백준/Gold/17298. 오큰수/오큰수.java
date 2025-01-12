import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int[] result = new int[N];

        for (int i = 0; i < N; i++) {
            // 스택안에 있는거는 아직 result가 확정되지 않은 숫자
            while (!stack.isEmpty() && A[stack.peek()] < A[i]) {
                int index = stack.pop();
                result[index] = A[i];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int index = stack.pop();
            result[index] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
}
