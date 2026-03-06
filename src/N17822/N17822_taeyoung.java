package N17822;

import java.util.*;
import java.io.*;

public class N17822_taeyoung {
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int N, M, T;
	static int[][] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		A = new int[N + 1][M];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			// 1. 회전
			for (int j = x; j <= N; j += x) {
				A[j] = rotate(A[j], d, k);
			}
			
			// 2. bfs -> true(제거함) 경우에 넘어가기
			if (bfs())
				continue;

			// 3. 제거하지 않았으면 보정
			avgAdjust();
		}
		// 정답 출력
		System.out.println(sumAll());
	}

	// 배열 회전 함수
	static int[] rotate(int[] now, int d, int k) {
		int[] next = new int[M];
		k %= M;
		if (d == 1) // 반시계인 경우 -k로 인덱스 이동
			k *= -1;
		for (int i = 0; i < M; i++) {
			int idx = (i + k) % M; // 원래 i에 있던 값 -> 회전 후 idx로
			if (idx < 0) // 인덱스 음수면 배열 크기만큼 더하기
				idx += M;
			next[idx] = now[i];
		}
		return next; // 회전 완료한 배열 반환
	}

	// bfs 함수 -> 제거할 수가 있으면 true
	static boolean bfs() {
		boolean remove = false; // 제거 가능 여부

		boolean[][] visited = new boolean[N + 1][M]; // 방문 체크

		Queue<int[]> Q = new ArrayDeque<>();

		for (int r = 1; r <= N; r++) {
			for (int c = 0; c < M; c++) {
				if (A[r][c] == 0 || visited[r][c]) // 이전에 이미 제거했거나, 방문했으면 넘어가기
					continue;

				int val = A[r][c]; // 비교를 위한 값
				List<int[]> comp = new ArrayList<>(); // 같은 수 좌표를 저장하기 위한 리스트

				visited[r][c] = true;
				Q.offer(new int[] { r, c });
				comp.add(new int[] { r, c });

				while (!Q.isEmpty()) {
					int[] cur = Q.poll();
					int r0 = cur[0];
					int c0 = cur[1];

					// 델타 탐색
					for (int d = 0; d < 4; d++) {
						int nr = r0 + dir[d][0];
						int nc = c0 + dir[d][1];

						// 원 -> 인덱스 조정
						if (nc < 0)
							nc += M;
						if (nc >= M)
							nc -= M;

						// 행 인덱스 넘어가거나, 방문했거나, 다른 수면 넘어간다.
						if (nr < 1 || nr > N || visited[nr][nc] || A[nr][nc] != val)
							continue;

						visited[nr][nc] = true;
						Q.offer(new int[] { nr, nc });
						comp.add(new int[] { nr, nc });
					}
				}
				// 같은 수 2개 이상 -> 제거 가능
				if (comp.size() >= 2) {
					remove = true; // 제거 가능으로 변경
					for (int[] p : comp) // 0으로 바꿔주기
						A[p[0]][p[1]] = 0;
				}
			}
		}
		return remove;
	}

	// 지울 수 없을 때 -> 평균과 비교해서 보정
	static void avgAdjust() {
		long sum = 0;
		int cnt = 0;

		// 평균 구하기
		for (int i = 1; i <= N; i++)
			for (int j = 0; j < M; j++)
				if (A[i][j] != 0) {
					sum += A[i][j];
					cnt++;
				}

		if (cnt == 0)
			return;

		double avg = (double) sum / cnt;

		// 평균과 비교해서 보정하는 과정
		for (int i = 1; i <= N; i++)
			for (int j = 0; j < M; j++)
				if (A[i][j] != 0) {
					if (A[i][j] > avg)
						A[i][j]--;
					else if (A[i][j] < avg)
						A[i][j]++;
				}
	}

	// 정답 -> 모든 수 더하기
	static int sumAll() {
		int s = 0;
		for (int i = 1; i <= N; i++)
			for (int j = 0; j < M; j++)
				s += A[i][j];
		return s;
	}
}

