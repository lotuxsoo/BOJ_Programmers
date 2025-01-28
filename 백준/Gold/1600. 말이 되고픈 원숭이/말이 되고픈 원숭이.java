import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int x, y, k, dist;
        Node(int x, int y, int k, int dist) {
            this.x = x;
            this.y = y;
            this.k = k;    // 말처럼 이동할 수 있는 남은 횟수
            this.dist = dist;  // 현재까지의 이동 횟수
        }
    }

    static int K, W, H;
    static int[][] map;
    static int[][][] visited;  // [x][y][k]: k번 말처럼 이동한 상태로 (x,y)에 도달한 최소 이동 횟수

    static int BFS() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, K, 0));
        visited[0][0][K] = 0;

        // 상하좌우 이동
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // 말처럼 이동
        int[] hdx = {-2, -1, 2, 1, -2, -1, 2, 1};
        int[] hdy = {1, 2, 1, 2, -1, -2, -1, -2};

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.x == H-1 && cur.y == W-1) {  // 도착점에 도달
                return cur.dist;
            }

            // 1. 상하좌우 이동
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if (nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == 1) continue;
                
                if (visited[nx][ny][cur.k] == -1) {
                    visited[nx][ny][cur.k] = cur.dist + 1;
                    queue.add(new Node(nx, ny, cur.k, cur.dist + 1));
                }
            }

            // 2. 말처럼 이동 (k > 0일 때만)
            if (cur.k > 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = cur.x + hdx[i];
                    int ny = cur.y + hdy[i];
                    
                    if (nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == 1) continue;
                    
                    if (visited[nx][ny][cur.k-1] == -1) {
                        visited[nx][ny][cur.k-1] = cur.dist + 1;
                        queue.add(new Node(nx, ny, cur.k-1, cur.dist + 1));
                    }
                }
            }
        }
        return -1;  // 도착점에 도달할 수 없는 경우
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        map = new int[H][W];
        visited = new int[H][W][K+1];
        
        // 맵 입력
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // visited 배열 초기화
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Arrays.fill(visited[i][j], -1);
            }
        }

        System.out.println(BFS());
    }
}