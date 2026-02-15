package N16234;

import java.util.*;
import java.io.*;

public class N16234_taeyoung {
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int[][] A = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;

		while (true) {
			boolean moved = false;

			boolean[][] visited = new boolean[N][N];

			Queue<int[]> Q = new ArrayDeque<>();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					List<int[]> list = new ArrayList<>();
					int sum = 0;

					if (visited[i][j])
						continue;
					visited[i][j] = true;
					Q.offer(new int[] { i, j });
					list.add(new int[] { i, j });
					sum += A[i][j];
					
					while (!Q.isEmpty()) {
						int[] now = Q.poll();
						int r = now[0];
						int c = now[1];
						for (int d = 0; d < 4; d++) {
							int nr = r + dir[d][0];
							int nc = c + dir[d][1];
							if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc])
								continue;
							int dif = Math.abs(A[nr][nc] - A[r][c]);
							if (dif >= L && dif <= R) {
								visited[nr][nc] = true;
								Q.offer(new int[] { nr, nc });
								list.add(new int[] { nr, nc });
								sum += A[nr][nc];
							}
						}
					}
					if (list.size() > 1) {
						int p = sum / list.size();
						for (int[] tmp : list) {
							A[tmp[0]][tmp[1]] = p;
						}
						moved = true;
					}
				}
			}
			if (!moved)
				break;
			ans++;
		}

		System.out.println(ans);
	}
}

