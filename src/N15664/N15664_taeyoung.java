package N15664;

import java.io.*;
import java.util.*;

public class N15664_taeyoung {
	static int[] A, nums; // nums: 주어진 자연수 배열
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	static boolean[] checked;
	
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
		checked = new boolean[N];

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
			// 중복되는 수 존재 -> 사용 체크 필요 
			// 중복 수열 출력 X -> prev와 같은 값은 넘어간다
			// 처음엔 바로 채우고, 이후에는 이전 값과 비교한다(비내림차순)
			if (!checked[i] && nums[i] != prev && (x == 0 || A[x - 1] <= nums[i])) {
				A[x] = nums[i];
				checked[i] = true;
				backtracking(x + 1);
				checked[i] = false;
				prev = nums[i];
			}
		}
	}
}	
