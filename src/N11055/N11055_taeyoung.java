package N11055;

import java.util.*;
import java.io.*;

public class N11055_taeyoung {

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
		
		int max = A[0];
		
		for (int i = 1; i < n; i++) {
			dp[i] = A[i];
			for (int j = i - 1; j >= 0; j--) {
				if(A[i] > A[j]) {
					dp[i] = Math.max(dp[j] + A[i], dp[i]);
				}
			}
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}
}

