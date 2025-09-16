package N7562;

import java.io.*;
import java.util.*;

public class N7562_taeyoung {
	static int[][] dir = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } }; // 나이트 이동 방향

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		tc: for (int t = 0; t < T; t++) {
			int l = Integer.parseInt(br.readLine());

      // 나이트 현재 위치
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

      // 목표 위치
			st = new StringTokenizer(br.readLine());
			int tx = Integer.parseInt(st.nextToken());
			int ty = Integer.parseInt(st.nextToken());

			boolean[][] checked = new boolean[l][l]; // 방문 체크 배열

			Queue<int[]> Q = new ArrayDeque<>();

			Q.offer(new int[] { x, y, 0 }); // 시작 좌표와 이동 횟수를 배열로 큐에 넣기
			checked[x][y] = true; // 방문 체크
			while (!Q.isEmpty()) {
				int[] A = Q.poll();
				int r = A[0];
				int c = A[1];
				int d = A[2];
				if (r == tx && c == ty) { // 목표 위치에 도달하면
					System.out.println(d); // 이동 횟수 출력
					continue tc; // 다음 테케로 넘어가기
				}
				for (int i = 0; i < 8; i++) { // 나이트 이동 가능한 8방향 탐색
					int nr = r + dir[i][0];
					int nc = c + dir[i][1];
					// 인덱스 벗어나거나, 이미 방문한 곳 넘어가기
					if (nr < 0 || nr >= l || nc < 0 || nc >= l || checked[nr][nc])
						continue;
					
					Q.offer(new int[] { nr, nc, d + 1 }); // 이동 후 좌표와 횟수 1 증가시킨 배열 큐에 넣기
					checked[nr][nc] = true; // 방문 체크
				}
			}
		}
	}
}
