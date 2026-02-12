package N7579;

import java.util.*;
import java.io.*;

public class N7579_taeyoung {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] m = new int[N + 1];
		int[] c = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			m[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int maxCost = 0; // 최대 비용 -> 모든 앱 비활성화
		for (int i = 1; i <= N; i++) {
			c[i] = Integer.parseInt(st.nextToken());
			maxCost += c[i];
		}

		// dp[i][cost] -> i번째 앱까지 고려했을 때, 비용이 cost일 때 확보 가능한 최대 메모리
		int[][] dp = new int[N + 1][maxCost + 1];

		for (int i = 1; i <= N; i++) {
			for (int cost = 0; cost <= maxCost; cost++) {
				dp[i][cost] = dp[i - 1][cost];
				// 만약 i번째 앱을 비활성화하려면 -> 지금 사용 가능한 cost가 비활성화 비용 이상이어야 함
				if (cost >= c[i]) {
					// 비활성화 여부에 따라 더 큰 값을 갱신
					dp[i][cost] = Math.max(dp[i][cost], dp[i - 1][cost - c[i]] + m[i]);
				}
			}
		}

		for (int cost = 0; cost <= maxCost; cost++) {
			// M 바이트 확보가 가능하면 출력
			if (dp[N][cost] >= M) {
				System.out.println(cost);
				break;
			}
		}
	}

}

