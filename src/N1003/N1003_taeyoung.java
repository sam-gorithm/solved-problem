package N1003;

import java.util.*;
import java.io.*;

public class N1003_taeyoung {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// dp 배열 -> {0  출력 횟수, 1 출력 횟수}
		int[][] dp = new int[41][2];
		dp[0] = new int[] {1, 0};
		dp[1] = new int[] {0, 1};
		
		int T = Integer.parseInt(br.readLine());
		
		int idx = 2; // 현재 dp가 계산되어 있는 최대 인덱스 + 1
		
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			// 아직 dp 계산하지 않은 경우
			if (N >= idx) {
				for (int i = idx; i <= N; i++) {
					// dp 배열 채우기
					dp[i][0] = dp[i-1][0] + dp[i-2][0];
					dp[i][1] = dp[i-1][1] + dp[i-2][1];
				}
				idx = N + 1; // 인덱스 갱신
			}
			
			sb.append(dp[N][0]).append(' ').append(dp[N][1]).append('\n');
		}
		System.out.println(sb);
	}
}
