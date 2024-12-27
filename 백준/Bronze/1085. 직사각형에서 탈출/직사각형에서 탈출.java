import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer> listX = new ArrayList<>();
    static ArrayList<Integer> listY = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        listX.add(0);
        listX.add(x);
        listX.add(w);
        listY.add(0);
        listY.add(y);
        listY.add(h);

        Collections.sort(listX);
        Collections.sort(listY);

        int minX = Math.min(listX.get(2) - listX.get(1), listX.get(1) - listX.get(0));
        int minY = Math.min(listY.get(2) - listY.get(1), listY.get(1) - listY.get(0));
        int ans = Math.min(minX, minY);
        System.out.println(ans);
    }

}