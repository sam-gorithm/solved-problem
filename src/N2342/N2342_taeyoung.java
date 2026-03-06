package N2342;

import java.util.*;
import java.io.*;

public class N2342_taeyoung {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 수열 리스트에 입력
		List<Integer> A = new ArrayList<>();
		while (true) {
			int N = Integer.parseInt(st.nextToken());
			if (N == 0)
				break;
			A.add(N);
		}

		int l = A.size();

		// dp[i][L][R] : i번째 지시까지 처리했을 때, 왼발 L / 오른발 R 위치에서의 최소 힘
		int[][][] dp = new int[l + 1][5][5];

		// 아직 도달하지 못한 상태를 INF로 초기화
		int INF = 1000000000;
		for (int i = 0; i <= l; i++) {
			for (int a = 0; a < 5; a++) {
				Arrays.fill(dp[i][a], INF);
			}
		}
		// 초기 상태는 0
		dp[0][0][0] = 0;

		for (int i = 0; i < l; i++) {
			int x = A.get(i); // 다음에 눌러야 하는 위치
			for (int L = 0; L < 5; L++) {
				for (int R = 0; R < 5; R++) {
					int cur = dp[i][L][R];

					// 아직 도달하지 못한 상태(INF)는 스킵
					if (cur == INF) continue;
					
					// 왼발로 x 누르기 -> 다음 상태 (x, R)
					dp[i + 1][x][R] = Math.min(dp[i + 1][x][R], cur + cost(L, x));
					
					// 오른발로 x 누르기 -> 다음 상태 (L, x)
					dp[i + 1][L][x] = Math.min(dp[i + 1][L][x], cur + cost(R, x));
				}
			}
		}

		int ans = INF;
		for (int L = 0; L < 5; L++) {
			for (int R = 0; R < 5; R++) {
				ans = Math.min(ans, dp[l][L][R]);
			}
		}

		System.out.println(ans);
	}
	
	// now 위치에 있던 발을 next로 옮겨 누를 때 드는 힘
	static int cost(int now, int next) {
		if (now == 0) return 2;
		if (now == next) return 1;
		if (Math.abs(next - now) == 2) return 4;
		return 3;

	}
}
