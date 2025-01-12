import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            deque.addLast(i);
        }

        while (deque.size() >= 2) {
            deque.pollFirst(); // 제일 위에 있는 카드를 버린다.

            deque.addLast(deque.pollFirst()); // 제일 위에 있는 카드를 제일 밑으로 옮긴다.
        }

        System.out.println(deque.poll());
    }
}
