package N16987;

import java.util.*;
import java.io.*;

public class N16987_taeyoung {
	static int N; // 계란 수
	static int ans = 0; // 정답
	static int[] S, W; // 내구도, 무게 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		S = new int[N];
		W = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			W[i] = Integer.parseInt(st.nextToken());
		}

		egg(0);

		System.out.println(ans);
	}

	// 계란 치는 과정 -> 백트래킹
	static void egg(int x) {
		if (x == N) { // 종료 조건 -> 모든 계란 확인 완료
			int cnt = 0; // 깨진 계란 수 세기
			for (int i = 0; i < N; i++) {
				if (S[i] <= 0) { // 내구도 0 이하 -> 깨짐
					cnt++;
				}
			}
			ans = Math.max(ans, cnt); // 정답 갱신
			return;
		}

		if (S[x] <= 0) { // 이미 깨진 계란이면
			egg(x + 1); // 다음 계란으로
		}
		
		else {
			boolean flag = false; // 칠 수 있는 계란이 있는지 여부

			for (int i = 0; i < N; i++) {
				if (i == x) // 자기 자신 -> 넘어가기
					continue;
				if (S[i] <= 0) // 이미 깨진 계란 -> 넘어가기
					continue;
				flag = true; // 칠 수 있는 계란 존재
				S[x] -= W[i]; // 들고있는 계란 내구도 감소
				S[i] -= W[x]; // 친 계란 내구도 감소
				egg(x + 1); // 다음 계란으로 재귀
				// 내구도 다시 복구
				S[x] += W[i];
				S[i] += W[x];
			}
			
			if (!flag) // 칠 수 있는 계란 없다 -> 현재 계란 제외 모두 깨짐
				egg(N); // 종료 조건으로 넘긴다
		}
	}
}
