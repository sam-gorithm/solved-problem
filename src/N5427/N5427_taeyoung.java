package N5427;

import java.io.*;
import java.util.*;

public class N5427_taeyoung  {
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		tc: for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			char[][] C = new char[h][w];

			// 상근이 시작 위치
			int r0 = -1;
			int c0 = -1;

			Queue<int[]> Q = new ArrayDeque<>();

			for (int i = 0; i < h; i++) {
				String S = br.readLine();
				for (int j = 0; j < w; j++) {
					C[i][j] = S.charAt(j);
					if (C[i][j] == '@') { // 상근이
						r0 = i;
						c0 = j;
					} else if (C[i][j] == '*') {
						Q.offer(new int[] { i, j, 0, 0 }); // {행, 열, (불:0, 상근이:1), 이동 거리}
					}
				}
			}
			Q.offer(new int[] { r0, c0, 1, 0 }); // 상근이

			boolean[][] visited = new boolean[h][w]; // 상근이 방문 체크

			while (!Q.isEmpty()) {
				int[] X = Q.poll();
				int r = X[0]; // 행
				int c = X[1]; // 열
				int z = X[2]; // 타입 (불 or 상근이)
				int d = X[3]; // 이동 횟수
				if (z == 0) { // 불
					for (int i = 0; i < 4; i++) { // 4방향 확인
						int nr = r + dir[i][0];
						int nc = c + dir[i][1];
						if (nr < 0 || nr >= h || nc < 0 || nc >= w) // 인덱스 확인
							continue;
						if (C[nr][nc] == '#' || C[nr][nc] == '*') // 벽이나 불이면 넘어가기
							continue;
						C[nr][nc] = '*'; // 다음 위치 불로 바꾸기
						Q.offer(new int[] { nr, nc, z, d + 1 });
					}
				} else { // 상근이
					for (int i = 0; i < 4; i++) { // 4방향 확인
						int nr = r + dir[i][0];
						int nc = c + dir[i][1];
						if (nr < 0 || nr >= h || nc < 0 || nc >= w) { // 인덱스 벗어남 -> 다음 이동에서 탈출 성공
							sb.append(d + 1).append("\n"); // d+1을 출력
							continue tc; // 다음 테케로 넘어가기
						}
						// 방문했거나, 벽이거나, 불이면 넘어가기
						if (visited[nr][nc] || C[nr][nc] == '#' || C[nr][nc] == '*')
							continue;
						visited[nr][nc] = true; // 방문 체크
						Q.offer(new int[] { nr, nc, z, d + 1 });
					}
				}
			}
			// 큐가 비었으면 탈출 불가
			sb.append("IMPOSSIBLE\n");
		}
		System.out.println(sb);
	}
}
