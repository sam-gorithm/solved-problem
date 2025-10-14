package N15655;

import java.io.*;
import java.util.*;

public class N15655_taeyoung {
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

		// nums 배열의 수 사용
		for (int i = 0; i < N; i++) {
			// 처음엔 바로 채우고, 이후에는 이전 값과 비교한다(오름차순)
			if (x == 0 || A[x - 1] < nums[i]) {
				A[x] = nums[i];
				backtracking(x + 1);
			}
		}
	}
}
