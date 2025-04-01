
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static void dfs(String str, String path, int left, int right) {
        if (left == 0 && right == target.length() - 1) {
            set.add(path);
            return;
        }

        if (left - 1 >= 0) {
            dfs(target.charAt(left - 1) + str, path + " " + target.charAt(left - 1) + str, left - 1, right);
        }
        if (right + 1 < target.length()) {
            dfs(str + target.charAt(right + 1), path + " " + str + target.charAt(right + 1), left, right + 1);
        }
    }

    static String target;
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        target = String.valueOf(N);

        for (int i = 0; i < target.length(); i++) {
            dfs("" + target.charAt(i), "" + target.charAt(i), i, i);
        }

        System.out.println(set.size());
    }
}
