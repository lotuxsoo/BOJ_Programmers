import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // N개의 수
        int M = Integer.parseInt(st.nextToken()); // 수행 명령 수

        // 세그먼트 트리 생성
        int leafStart = 1;
        while (leafStart < N) {
            leafStart *= 2;
        }
        long[] tree = new long[2 * leafStart];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 0) { // Sum(b,c)
                long sum = 0;
                int s_idx = 0, e_idx = 0;
                if (b <= c) {
                    s_idx = b + leafStart - 1;
                    e_idx = c + leafStart - 1;
                } else {
                    s_idx = c + leafStart - 1;
                    e_idx = b + leafStart - 1;
                }

                while (s_idx <= e_idx) {
                    if (s_idx % 2 == 1) {
                        sum += tree[s_idx];
                        s_idx++;
                    }
                    if (e_idx % 2 == 0) {
                        sum += tree[e_idx];
                        e_idx--;
                    }
                    s_idx /= 2;
                    e_idx /= 2;
                }
                System.out.println(sum);
            } else { // Modify(b,c)
                int treeIdx = b + leafStart - 1;
                tree[treeIdx] = c;

                while (treeIdx > 0) {
                    treeIdx /= 2;
                    tree[treeIdx] = tree[treeIdx * 2] + tree[treeIdx * 2 + 1];
                }
            }
        }
    }
}
