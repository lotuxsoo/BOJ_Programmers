
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    
    static int getMaxDist(ArrayList<Integer> shelters) {
        int maxDist = 0;

        for (int i = 0; i < houses.size(); i++) {
            int x1 = houses.get(i)[0], y1 = houses.get(i)[1];
            int minDist = Integer.MAX_VALUE;
            // 집과 가장 가까운 대피소 거리 찾기
            for (int j = 0; j < shelters.size(); j++) {
                int idx = shelters.get(j);
                int distance = Math.abs(x1 - houses.get(idx)[0]) + Math.abs(y1 - houses.get(idx)[1]);
                minDist = Math.min(minDist, distance);
            }
            if (minDist != Integer.MAX_VALUE) {
                maxDist = Math.max(maxDist, minDist);
            }
        }
        return maxDist;
    }

    static void backtrack(int idx, ArrayList<Integer> shelters) {
        if (shelters.size() == K) {
            MIN_DIST = Math.min(MIN_DIST, getMaxDist(new ArrayList<>(shelters)));
            return;
        }

        for (int i = idx; i < houses.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                shelters.add(i);
                backtrack(i + 1, shelters);
                shelters.remove(shelters.size() - 1);
                visited[i] = false;
            }
        }
    }

    static int N, K;
    static ArrayList<int[]> houses = new ArrayList<>();
    static boolean[] visited;
    static int MIN_DIST = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            houses.add(new int[]{x, y});
        }

        visited = new boolean[N];

//        for (int i = 0; i < N; i++) {
//            backtrack(i, new ArrayList<>());
//        }

        backtrack(0, new ArrayList<>());

        System.out.println(MIN_DIST);
    }
}
