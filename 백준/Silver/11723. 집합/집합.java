
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static void calc(String operator, int x) {
        if (operator.equals("add")) {
            set.add(x);
        } else if (operator.equals("remove")) {
            if (set.contains(x)) {
                set.remove(x);
            }
        } else if (operator.equals("toggle")) {
            if (set.contains(x)) {
                set.remove(x);
            } else {
                set.add(x);
            }
        } else if (operator.equals("all")) {
            set.clear();
            for (int i = 1; i <= 20; i++) {
                set.add(i);
            }
        } else if (operator.equals("empty")) {
            set.clear();
        }
    }

    static int binSearch(int target) {
        ArrayList<Integer> list = new ArrayList<>(set);
        int left = 0, right = list.size() - 1;
        Collections.sort(list);

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) == target) {
                return 1;
            } else if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return 0;
    }

    static int M;
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String[] splits = br.readLine().split(" ");
            String operator = splits[0];
            if (splits.length == 1) {
                calc(operator, 0);
            } else {
                int x = Integer.parseInt(splits[1]);
                if (operator.equals("check")) {
                    sb.append(binSearch(x)).append("\n");
                } else {
                    calc(operator, x);
                }
            }
        }
        System.out.println(sb.toString());
    }
}
