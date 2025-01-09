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

        int num = 1;
        boolean flag = false;

        for (int i = 0; i < n; i++) {
            if (num <= A[i]) {
                while (num <= A[i]) {
                    stack.push(num++);
                    list.add('+');
                }
                stack.pop();
                list.add('-');
            } else {
                if (!stack.isEmpty() && stack.peek() == A[i]) {
                    stack.pop();
                    list.add('-');
                } else {
                    flag = true;
                    break;
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