package N1932;

import java.util.*;
import java.io.*;

public class N1932_taeyoung {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		// 삼각형 배열
		int[][] A = new int[N][];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = new int[i+1];
			for (int j = 0; j <= i; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// dp 배열 -> dp[i][j] : (i, j) 위치로 내려오며 선택된 수의 합 최대 값
		int[][] dp = new int[N][];
		
		dp[0] = A[0]; // 맨 위 -> 삼각형과 동일
		
		for (int i = 1; i < N; i++) {
			dp[i] = new int[i+1];
			// 1. 삼각형 왼쪽 (j = 0)
			dp[i][0] = dp[i-1][0] + A[i][0];
			// 2. 삼각형 오른쪽 (j = i)
			dp[i][i] = dp[i-1][i-1] + A[i][i];
			// 3. 삼각형 안쪽 -> 왼쪽 대각선/ 오른쪽 대각선 두 경우 비교
			for (int j = 1; j < i; j++) {
				dp[i][j] = A[i][j] + Math.max(dp[i-1][j], dp[i-1][j-1]);
			}
		}
		// 정답
		int max = 0;
		
		// 마지막 행에서 가장 큰 값 찾기
		for (int i = 0; i < N; i++) {
			max = Math.max(max, dp[N-1][i]);
		}
		
		System.out.println(max);
	}
}
