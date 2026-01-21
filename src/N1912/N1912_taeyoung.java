package N1912;

import java.util.*;
import java.io.*;

public class N1912_taeyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] A = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[n];
		dp[0] = A[0];
		
		int max = dp[0];
		
		for (int i = 1; i < n; i++) {
			dp[i] = Math.max(dp[i-1], 0) + A[i];
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}
}
