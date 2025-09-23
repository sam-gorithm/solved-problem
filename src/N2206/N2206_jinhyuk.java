package N2206;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//	벽 부수고 이동하기 / 골드 3 / 764ms
public class N2206_jinhyuk {
	// static
	static int N, M;	//행, 열
	static int[][] map;
	static int[][][] visited;

	// 4방향탐색 (상하좌우)
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	// Point
	static class Point {
		int r, c;
		int isBroken;

		public Point(int r, int c, int isBroken) {
			this.r = r;
			this.c = c;
			this.isBroken = isBroken;
		}
	} // Point

	// main
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		visited = new int[N][M][2];

		for (int i = 0; i < N; i++) {
			String line = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		} // 입력

		bfs(0,0,0);

		
	} // main

	
	//bfs
	private static void bfs(int r, int c, int isBroken) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0, 0));
		visited[0][0][0] = 1;
		
		while (!q.isEmpty()) {
			Point now = q.poll();
			if (now.r == N - 1 && now.c == M - 1) {
				System.out.println(visited[now.r][now.c][now.isBroken]);
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				if (visited[nr][nc][now.isBroken] == 0) {
					// 일반적인 상황의 탐색
					if (map[nr][nc] == 0) {
						visited[nr][nc][now.isBroken] = visited[now.r][now.c][now.isBroken] + 1;
						q.add(new Point(nr, nc, now.isBroken));
					}
					// 벽을 부술수있다면 부수고 가보는 탐색
					else if (map[nr][nc] == 1 && now.isBroken == 0) {
						visited[nr][nc][1] = visited[now.r][now.c][now.isBroken] + 1;
						q.add(new Point(nr, nc, 1));
					}
				}
			} // for
		} // while
		
		//Queue가 빌 때까지 도착X (불가능)
		System.out.println(-1);
	}	//bfs

} // class
