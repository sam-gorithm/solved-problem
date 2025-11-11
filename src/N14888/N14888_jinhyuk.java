package N14888;

import java.util.Scanner;

//	연산자 끼워넣기 / 실버 1 / 96ms
//N개의 숫자 고정, N-1개의 연산자
//연산자 우선순위 무시, 앞에서부터 계산
//나누기는 몫만 음수 나누기는 음수 몫만
//=> 최대, 최소 구하기
public class N14888_jinhyuk {
	// static
	static int N; // N개 숫자
	static int[] num; // N개 배열
	static int[] opts = new int[4]; // 연산자 개수 [+, -, *, /]
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	// main
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		num = new int[N];

		// N개 숫자 고정
		for (int i = 0; i < N; i++) {
			num[i] = sc.nextInt();
		}

		// 연산자 개수 입력
		for (int i = 0; i < 4; i++) {
			opts[i] = sc.nextInt();
		}

		// dfs(현재까지연산결과, 다음숫자)
		dfs(num[0], 1);

		// 결과출력
		System.out.println(max);
		System.out.println(min);

	} // main

	// dfs
	public static void dfs(int curr, int depth) {

		// 기저조건
		if (depth == N) {
			// 최대값, 최소값 갱신 후 종료
			max = Math.max(max, curr);
			min = Math.min(min, curr);
			return;
		}

		// 재귀파트 : 연산자 별로 한 개씩 넣어보며 다음 숫자 계산 재귀
		// 덧셈 연산자
		if (opts[0] > 0) {
			opts[0]--; // 덧셈 연산자 1개 사용
			dfs(curr + num[depth], depth + 1); // 다음 숫자 연산 재귀
			opts[0]++; // 다음 경우의 수 위해 복구
		}

		// 뺄셈 연산자
		if (opts[1] > 0) {
			opts[1]--; // 뺄셈 연산자 1개 사용
			dfs(curr - num[depth], depth + 1); // 다음 숫자 연산 재귀
			opts[1]++; // 다음 경우의 수 위해 복구
		}

		// 곱셈 연산자
		if (opts[2] > 0) {
			opts[2]--; // 곱셈 연산자 1개 사용
			dfs(curr * num[depth], depth + 1); // 다음 숫자 연산 재귀
			opts[2]++; // 다음 경우의 수 위해 복구
		}

		// 나눗셈 연산자
		if (opts[3] > 0) {
			opts[3]--;	// 나눗셈 연산자 1개 사용
			dfs(curr / num[depth], depth + 1);	// 다음 숫자 연산 재귀
			opts[3]++; // 다음 경우의 수 위해 복구
		}
		
	} // dfs

	
} // class