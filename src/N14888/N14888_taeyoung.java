package N14888;

import java.util.*;
import java.io.*;

public class N14888_taeyoung {
	static int N, max, min;
	static int[] A, OP;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		OP = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			OP[i] = Integer.parseInt(st.nextToken());
		}

		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		bt(A[0], 1);

		System.out.println(max);
		System.out.println(min);
	}

	// 백트래킹 -> 연산자 사용해서 순차적 계산
	static void bt(int X, int idx) {
		if (idx == N) { // 모든 수 계산 완료
			max = Math.max(max, X);
			min = Math.min(min, X);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (OP[i] > 0) { // 해당 연산자 남아있으면
				OP[i]--; // 사용 -> 1감소
				bt(cal(X, A[idx], i), idx + 1); // 계산하여 다음 단계로 재귀
				OP[i]++; // 되돌리기
			}
		}
	}

	// 연산자 사용하여 계산하는 함수
	static int cal(int x, int y, int op) {
		switch (op) {
		case 0:
			return x + y;
		case 1:
			return x - y;
		case 2:
			return x * y;
		case 3:
			return x / y;
		}
		return 0;
	}
}

