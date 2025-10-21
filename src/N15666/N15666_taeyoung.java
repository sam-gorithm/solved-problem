package N15666;

import java.io.*;
import java.util.*;

public class N15666_taeyoung {
	static int[] A, nums; // nums: 주어진 자연수 배열
	static int N, M;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// 사전 순 -> 정렬
		Arrays.sort(nums);

		A = new int[M];

		backtracking(0);

		System.out.println(sb);
	}

	// 백트래킹 함수
	static void backtracking(int x) {
		if (x == M) {
			for (int a : A) {
				sb.append(a).append(' ');
			}
			sb.append('\n');
			return;
		}

		// 주어진 수에 중복되는 수 존재
		// 현재 깊이에서 같은 수를 고르면 안된다
		int prev = -1;
		for (int i = 0; i < N; i++) {
			// 중복 수열 출력 X -> prev와 같은 값은 넘어간다
			// 같은 수 여러 번 골라도 됨 -> 사용 체크 필요 없음
			// 처음엔 바로 채우고, 이후에는 이전 값과 비교한다(비내림차순)
			if (nums[i] != prev && (x == 0 || A[x - 1] <= nums[i])) {
				A[x] = nums[i];
				backtracking(x + 1);
				prev = nums[i];
			}
		}
	}
}
