package N16974;

import java.util.*;
import java.io.*;

public class N16974_taeyoung {
	static long[] L, P;
	static long ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		long X = Long.parseLong(st.nextToken());

		// L : 버거 길이 , P : 버거의 패티 수
		L = new long[N + 1];
		P = new long[N + 1];

		L[0] = 1;
		P[0] = 1;
		ans = 0;

		// 점화식을 통해 배열 채우기
		for (int i = 1; i <= N; i++) {
			L[i] = 2 * L[i - 1] + 3;
			P[i] = 2 * P[i - 1] + 1;
		}

		sol(N, X);

		System.out.println(ans);
	}

	// 먹은 패티의 수 구하는 함수
	static void sol(int n, long x) {
		// 버거를 먹지 않으면
		if (x <= 0)
			return;

		// 레벨-0 버거 -> 패티 1개 먹음
		if (n == 0) {
			ans += 1;
			return;
		}
		
		// 레벨 0이 아닌 버거 -> 맨 앞은 번
		if (x == 1)
			return;

		// x가 버거 길이 이상이면 -> 전체 다 먹음
		if (x >= L[n]) {
			ans += P[n];
			return;
		}
		
		// 버거 중앙 패티 위치
		long mid = L[n] / 2 + 1;

		// 먹어야 할 양이 중간 위치라면
		if (x == mid) {
			// 이전 레벨 버거에 패티 하나 더 먹음
			ans += P[n - 1] + 1;
		} 
		// 중간보다 더 많으면
		else if (x > mid) {
			// 이전 레벨 버거에 패티 하나 더 먹음
			ans += P[n - 1] + 1;
			// 남은 양 계산(번(1) + 왼쪽버거(L[n-1]) + 패티(1) 만큼 제외)
			sol(n - 1, x - L[n - 1] - 2);
		} 
		// 중간보다 적으면
		else {
			// 맨 앞 번 빼고 다시 계산
			sol(n - 1, x - 1);
		}
	}
}

