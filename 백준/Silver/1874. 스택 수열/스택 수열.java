import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        Deque<Integer> stack = new ArrayDeque<>();
        ArrayList<Character> list = new ArrayList<>();

        int number = 1;
        boolean flag = false;

        for (int i = 0; i < n; i++) {
            if (number <= A[i]) {
                while (number <= A[i]) {
                    stack.push(number++);
                    list.add('+');
                }
                stack.pop(); // 마지막 한번만 pop
                list.add('-');
            } else {
                if (stack.isEmpty() || stack.peek() != A[i]) {
                    flag = true;
                    break;
                } else {
                    stack.pop();
                    list.add('-');
                }
            }
        }

        if (flag) {
            System.out.println("NO");
        } else {
            for (char c : list) {
                System.out.println(c);
            }
        }
    }
}