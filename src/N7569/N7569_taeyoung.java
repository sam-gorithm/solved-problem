package N7569;

import java.io.*;
import java.util.*;

public class N7569_taeyoung {
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int[][][] tomato = new int[H][M][N]; // 토마토 창고 3차원 배열

		Deque<int[]> Q = new ArrayDeque<>();

		int cnt = 0; // 아직 안익은 토마토

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					tomato[i][j][k] = Integer.parseInt(st.nextToken());
					if (tomato[i][j][k] == 1) { // 익은 토마토
						Q.push(new int[] { i, j, k, 1 }); // 토마토 좌표와 일자를 배열로 큐에 넣는다
					} else if (tomato[i][j][k] == 0) { // 안익은 토마토
						cnt++; // 개수 세기
					}
				}
			}
		}

		if (cnt == 0) { // 모든 토마토가 익어있는 상태
			System.out.println(0);
			return;
		}

		int[][] dir = { { -1, 0, 0 }, { 1, 0, 0 }, { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 } }; // 3차원 6방향 탐색

		while (!Q.isEmpty()) { // BFS
			int[] X = Q.poll();

			int h = X[0]; 
			int r = X[1];
			int c = X[2];
			int d = X[3];

			for (int i = 0; i < 6; i++) { // 6방향 탐색
				int nh = h + dir[i][0];
				int nr = r + dir[i][1];
				int nc = c + dir[i][2];
				// 인덱스 벗어나면 넘어가기
				if (nh < 0 || nh >= H || nr < 0 || nr >= M || nc < 0 || nc >= N) {
					continue;
				}
				// 토마토 없는 경우도 넘어가기
				if (tomato[nh][nr][nc] == -1) {
					continue;
				}

				if (tomato[nh][nr][nc] == 0) { // 안익은 토마토라면
					tomato[nh][nr][nc] = 1; // 익은걸로 변경
					cnt--; // 안익은 토마토 개수 1 감소
					if (cnt == 0) { // 모든 토마토 익었다면
						System.out.println(d); // 며칠 걸렸는지 출력
						return;
					}
					Q.offer(new int[] { nh, nr, nc, d + 1 }); // 새로 익은 토마토 좌표와 1일 증가시켜 큐에 넣기
				}
			}
		}
		// BFS 완료 후에도 안익은 토마토 남음
		System.out.println(-1); 
	}
}

  
