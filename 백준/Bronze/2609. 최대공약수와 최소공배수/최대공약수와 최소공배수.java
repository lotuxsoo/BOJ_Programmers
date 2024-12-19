import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int gcd(int a, int b) {
        while (b != 0) {
            int mod = a % b;
            a = b;
            b = mod;
        }
        return a;
    }

    public static int lcm(int a, int b) {
        int gcd = gcd(a, b);
        return Math.abs(a * b) / gcd;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int a = Integer.parseInt(inputs[0]);
        int b = Integer.parseInt(inputs[1]);
        
        System.out.println(gcd(a, b));
        System.out.println(lcm(a, b));
    }
}