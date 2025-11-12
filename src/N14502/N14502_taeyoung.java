package N14502;

import java.util.*;
import java.io.*;

public class N14502_taeyoung {
	static int N, M, ans;
	static int[][] board;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ans = 0;

		dfs(0, 0);

		System.out.println(ans);

	}

	// 벽 3개 세우기
	static void dfs(int d, int X) {
		if (d == 3) {
			ans = Math.max(ans, count(bfs(copy(board))));
			return;
		}

		// 벽 세우기
		while (X < N * M) {
			int r = X / M;
			int c = X % M;
			if (board[r][c] == 0) {
				board[r][c] = 1;
				dfs(d + 1, X + 1);
				board[r][c] = 0;
			}
			X++;
		}

	}

	// 배열 복사
	static int[][] copy(int[][] board) {
		int[][] A = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				A[i][j] = board[i][j];
			}
		}
		return A;
	}

	// 바이러스 퍼짐
	static int[][] bfs(int[][] A) {
		Deque<int[]> Q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (A[i][j] == 2) {
					Q.offer(new int[] { i, j });
				}
			}
		}

		while (!Q.isEmpty()) {
			int[] X = Q.poll();
			int r = X[0];
			int c = X[1];

			for (int d = 0; d < 4; d++) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M || A[nr][nc] != 0)
					continue;

				A[nr][nc] = 2;
				Q.offer(new int[] { nr, nc });
			}
		}

		return A;
	}

	// 안전구역 카운트
	static int count(int[][] A) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (A[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}

