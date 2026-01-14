package N14501;

import java.util.*;
import java.io.*;

public class N14501_taeyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] T = new int[N];
		int[] P = new int[N];

		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N + 1];

		for (int i = N - 1; i >= 0; i--) {
			if (i + T[i] <= N) {
				dp[i] = Math.max(dp[i + T[i]] + P[i], dp[i + 1]) ;
			}
			else {
				dp[i] = dp[i + 1];
			}
		}
		System.out.println(dp[0]);

	}
}

