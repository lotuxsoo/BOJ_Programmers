
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static void backtrack(char[] ch, StringBuilder temp) {
        if (temp.length() == ch.length) {
            if (!set.contains(temp.toString())) {
                set.add(temp.toString());
                sb.append(temp).append("\n");
            }
            return;
        }

        for (int i = 0; i < ch.length; i++) {
            if (!visited[i]) {
                if (i > 0 && (ch[i - 1] == ch[i]) && !visited[i - 1]) {
                    continue;
                }

                int size = temp.length();
                temp.append(ch[i]);
                visited[i] = true;
                backtrack(ch, temp);
                visited[i] = false;
                temp.setLength(size);
            }
        }
    }

    static boolean[] visited;
    static Set<String> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            char[] ch = br.readLine().toCharArray();
            Arrays.sort(ch);
            visited = new boolean[ch.length];
            backtrack(ch, new StringBuilder());
        }

        System.out.println(sb.toString());
    }
}
