package N16985;

import java.io.*;
import java.util.*;

public class N16985_taeyoung {
	static int[][][] plate = new int[5][5][5]; // 초기 주어진 판 5개
	static int[][][] maze = new int[5][5][5]; // 만들어진 미로 배열
	static int[][] dir = { { -1, 0, 0 }, { 1, 0, 0 }, { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 } }; // 6방향 델타 탐색
	static boolean[] checked = new boolean[5]; // 판 사용 체크
	static int ans = Integer.MAX_VALUE; // 정답

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 판 5개 입력
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 5; k++) {
					plate[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}

		makeMaze(0);
		
		// 탈출 불가한 경우 -1
		if (ans == Integer.MAX_VALUE)
			ans = -1;

		System.out.println(ans);
	}

	// 미로 만들고 탐색까지 (백트래킹)
	static void makeMaze(int n) { 
		if (n == 5) { // 판 5개 배치 완료
			if (maze[0][0][0] == 0 || maze[4][4][4] == 0) // 입구 출구 갈 수 없음
				return;
			bfs(maze); // 현재 미로 탈출 경로 탐색
			return;
		}
		for (int i = 0; i < 5; i++) {
			if (checked[i]) // i 판 사용했으면
				continue;
			
			checked[i] = true; // 사용 체크
			
			// 회전 상태 반영하여 4가지 경우로 재귀
			for (int r = 0; r < 4; r++) {
				maze[n] = rotate(plate[i], r);
				makeMaze(n + 1);
			}
			checked[i] = false;
		}
	}

	// 판 r번 회전시키는 함수
	static int[][] rotate(int[][] board, int r) {
		int[][] tmp = new int[5][5];
		switch (r) {
		case 0: // 회전 X
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					tmp[i][j] = board[i][j];
				}
			}
			break;
		case 1: // 90도 회전
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					tmp[i][j] = board[4 - j][i];
				}
			}
			break;
		case 2: // 180도 회전
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					tmp[i][j] = board[4 - i][4 - j];
				}
			}
			break;
		case 3: // 270도 회전
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					tmp[i][j] = board[j][4 - i];
				}
			}
			break;
		}
		return tmp;
	}

	// 미로 최단 경로 찾기
	static void bfs(int[][][] maze) {
		boolean[][][] visited = new boolean[5][5][5];
		Deque<int[]> Q = new ArrayDeque<>();

		Q.offer(new int[] { 0, 0, 0, 0 }); // 좌표와 깊이
		visited[0][0][0] = true; // 방문 체크

		while (!Q.isEmpty()) {
			int[] tmp = Q.poll();
			int x = tmp[0];
			int y = tmp[1];
			int z = tmp[2];
			int l = tmp[3];

			if (l >= ans) // 현재 최단 경로보다 더 길면 탐색 중단
				return;

			if (x == 4 && y == 4 && z == 4) { // 출구 도착
				ans = l;
				return;
			}

			for (int d = 0; d < 6; d++) {
				int nx = x + dir[d][0];
				int ny = y + dir[d][1];
				int nz = z + dir[d][2];

				// 인덱스, 방문 체크
				if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || nz < 0 || nz >= 5 || visited[nx][ny][nz])
					continue;

				if (maze[nx][ny][nz] == 1) {
					Q.offer(new int[] { nx, ny, nz, l + 1 });
					visited[nx][ny][nz] = true;
				}

			}
		}
	}
}
