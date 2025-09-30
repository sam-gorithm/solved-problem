package N2573;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//	빙산 / 골드 4 / 932ms
public class N2573_jinhyuk {
	// static
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int cnt;

	// 4방향탐색 (상하좌우)
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	// Point
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	
	// main
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		} // 입력

		int year = 0;
		while (true) {

			// 매 년 방문체크배열 초기화
			visited = new boolean[N][M];
			cnt = 0;

			///////////////////
			// 빙산 덩어리 개수 세기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 빙산 두 덩이 이상이면 종료.
					if (cnt > 1) {
						System.out.println(year);
						return;
					}
					// 빙산/방문X
					if (map[i][j] != 0 && !visited[i][j]) {
						bfs(i, j);
						cnt++;
					}
				}
			} // 빙하 덩어리 개수 세기

			//빙산이 하나도 없다면 (다 녹을 때까지 두 덩어리 이상으로 분리X)
			if (cnt == 0) {
				System.out.println(0);
				return;
			}

			////////////////////
			// 빙하 녹이기
			int[][] meltMap = new int[N][M]; // 녹을 높이 저장
			// 녹을 높이 구하기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 0) {
						meltMap[i][j] = melting(i, j);
					}
				}
			} // 녹을 높이 구하기

			// 녹이기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] -= meltMap[i][j];
					if (map[i][j] <= 0) {
						map[i][j] = 0;
					}
				}
			} // 녹이기

			//다음 년도로
			year++;
		} // while

		
	} // main

	
	// melting
	private static int melting(int r, int c) {
		Point now = new Point(r, c);
		
		int melt = 0;
		for (int i = 0; i < 4; i++) {
			int nr = now.r + dr[i];
			int nc = now.c + dc[i];
			if (map[nr][nc] == 0) {
				melt++;
			}
		}

		return melt;

	} // melting

	
	// bfs
	private static void bfs(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c));
		visited[r][c] = true;

		while (!q.isEmpty()) {
			Point now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;
				if (map[nr][nc] != 0 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new Point(nr, nc));
				}
			} // for
		} // while

	} // bfs

	
} // class
