import java.io.*;
import java.util.*;

public class Main
{
	
	static int n;
	static int[][] map;
	
	static int[][][] blocks = {
			{{0,0}, {0,1}, {0,2}, {0,3}},
			{{0,0}, {1,0}, {2,0}, {3,0}},
			{{0,0}, {0,1}, {1,1}, {1,2}},
			{{0,0}, {1,0}, {1,-1}, {2,-1}},
			{{0,0}, {0,1}, {0,2}, {1,2}},
			{{0,0}, {1,0}, {2,0}, {2,-1}},
			{{0,0}, {1,0}, {1,1}, {1,2}},
			{{0,0}, {0,1}, {1,0}, {2,0}},
			{{0,0}, {0,1}, {0,2}, {1,1}},
			{{0,0}, {1,0}, {1,-1}, {2,0}},
			{{0,0}, {1,0}, {1,-1}, {1,1}},
			{{0,0}, {1,0}, {1,1}, {2,0}},
			{{0,0}, {1,0}, {0,1}, {1,1}}
	};
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test_case = 1;
		
		while (true) {
			n = Integer.parseInt(br.readLine().trim()); // trim하지 않으면 NumberFormatException 발생
			
			if (n == 0) break;
			
			map = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim()); // trim
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken().trim()); // trim
				}
			}
			int maxSum = calMaxSum();
			
			sb.append(test_case++).append(". ").append(maxSum).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int calMaxSum() {
		int max = Integer.MIN_VALUE; // 입력값이 음수인 경우 고려
		
		for (int idx = 0; idx < blocks.length; idx++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int sum = calSum(idx, i, j);
					max = Math.max(sum, max);
				}
			}
		}
		return max;
	}
	
	static int calSum(int idx, int r, int c) {
		int sum = 0;
		for (int i = 0; i < blocks[idx].length; i++) {
			int nr = r + blocks[idx][i][0];
			int nc = c + blocks[idx][i][1];
			
			if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
				return Integer.MIN_VALUE;
			}
			
			sum += map[nr][nc];
		}
		
		return sum;
	}
}