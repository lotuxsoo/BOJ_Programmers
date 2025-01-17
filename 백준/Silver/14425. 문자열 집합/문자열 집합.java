import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        Node[] next = new Node[26];
        boolean isEnd;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Node root = new Node();

        for (int i = 0; i < N; i++) {
            String text = br.readLine();
            Node now = root;
            for (int j = 0; j < text.length(); j++) {
                char c = text.charAt(j);
                if (now.next[c - 'a'] == null) {
                    now.next[c - 'a'] = new Node();
                }
                now = now.next[c - 'a'];

                if (j == text.length() - 1) {
                    now.isEnd = true;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < M; i++) {
            String text = br.readLine();
            Node now = root;
            for (int j = 0; j < text.length(); j++) {
                char c = text.charAt(j);
                if (now.next[c - 'a'] == null) {
                    break;
                }
                now = now.next[c - 'a'];

                if (j == text.length() - 1 && now.isEnd) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
