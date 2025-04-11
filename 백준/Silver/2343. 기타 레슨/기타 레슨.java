
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] lecture;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lecture = new int[N];
        long maxSize = 0, minSize = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lecture[i] = Integer.parseInt(st.nextToken());
            minSize = Math.max(minSize, lecture[i]); // 가장 긴강의보다 짧을수없음
            maxSize += lecture[i];
        }

        // 강의 순서가 바뀌면 안되므로 정렬 X
        long left = minSize, right = maxSize, result = 0;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long size = mid, count = 1;

            for (int l : lecture) {
                if (size >= l) {
                    size -= l;
                } else {
                    count++;
                    size = mid - l;
                }
            }

            if (count <= M) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(result);
    }
}
