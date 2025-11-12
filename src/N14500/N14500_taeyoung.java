package N14500;

import java.util.*;
import java.io.*;

public class N14500_taeyoung {
	static int ans, N, M;
	static int[][] A;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N][M];
		A = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, A[i][j]); // (i, j)를 시작으로 4개 이어서 정답 갱신
				visited[i][j] = false;
				
				sumT(i,j);
			}
		}
		
		System.out.println(ans);
	}

	// dfs -> 4개 연결해서 정답 갱신 (T 모양 불가능)
	static void dfs(int x, int y, int d, int sum) {
		if (d == 4) {
			ans = Math.max(ans, sum);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny])
				continue;

			visited[nx][ny] = true;
			dfs(nx, ny, d + 1, sum + A[nx][ny]);
			visited[nx][ny] = false;
		}
	}
	
	// (x,y)를 중심으로 하는 T 합 구하기
	static void sumT(int x, int y) { 
		int sum = A[x][y];
		int min = Integer.MAX_VALUE;
		int cnt = 0;
		
		// 중심 기준으로 4방향 체크
		for (int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M)
				continue;

			sum += A[nx][ny];
			min = Math.min(min, A[nx][ny]);
			cnt++;
		}
		// cnt -> 2 ~ 4 
		if (cnt < 3) // T자 불가능
			return;
		if (cnt == 4) // 모든 방향 가능(+) -> 가장 작은 값 제거
			sum -= min; 
		ans = Math.max(ans, sum);
	}
}
