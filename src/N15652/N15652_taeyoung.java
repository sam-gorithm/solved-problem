package N15652;

import java.io.*;
import java.util.*;

public class N15652_taeyoung {
	static int[] A; // 수열
	static int N, M;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 길이 M인 수열
		A = new int[M];

		backtracking(0);
		
		System.out.println(sb);
	}
	
	// 백트래킹 함수
	static void backtracking(int x) { // A[x] 채우기
		if (x == M) { // 수열 M개 다 채웠으면
			for (int a : A) {
				sb.append(a).append(' ');
			}
			sb.append('\n');
			return;
		}

		// 1부터 N까지 자연수
		for (int i = 1; i <= N; i++) {
			// 처음엔 바로 채우고, 이후에는 이전 값과 비교한다(비내림차순)
			if (x == 0 || A[x-1] <= i) {
				A[x] = i;
				backtracking(x + 1);
			}
		}
	}
}
