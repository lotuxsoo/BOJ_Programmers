import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] miro;
    static int[] dx = {-1, 1, 0, 0}; // x 방향배열-상하
    static int[] dy = {0, 0, -1, 1}; // y 방향배열-좌우
    static int answer = 0;
    static boolean[][] check;

    static void bfs(int x, int y) {
        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int[] {x, y});
        check[x][y] = true;
        while (!Q.isEmpty()) {
            int[] now = Q.poll();
            int nowx = now[0];
            int nowy = now[1];

            for (int i = 0; i < 4; i++) {
                int nextx = nowx + dx[i];
                int nexty = nowy + dy[i];
                if (nextx < 0 || nexty < 0 || nextx > N || nexty > M)
                    continue;
                if (check[nextx][nexty] || miro[nextx][nexty] == 0)
                    continue;
                
                Q.add(new int[] {nextx, nexty});
                check[nextx][nexty] = true;
                miro[nextx][nexty] = miro[nowx][nowy]+1;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        miro = new int[N + 1][M + 1];
        check = new boolean[N + 1][M + 1];
        for (int i = 1; i < N + 1; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                miro[i][j + 1] = Integer.parseInt(str.substring(j, j + 1));
            }
        }
        bfs(1, 1);
        System.out.println(miro[N][M]);
    }
}
