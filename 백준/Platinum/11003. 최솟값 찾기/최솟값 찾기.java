import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int index;
        int number;

        Node(int index, int number) {
            this.index = index;
            this.number = number;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Node> deque = new ArrayDeque<>();
        int[] result = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            while (!deque.isEmpty() && deque.getLast().number > A[i]) {
                deque.removeLast();
            }
            deque.addLast(new Node(i, A[i]));

            if (deque.getFirst().index < i - L + 1) {
                deque.removeFirst();
            }

            result[i] = deque.getFirst().number;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}
