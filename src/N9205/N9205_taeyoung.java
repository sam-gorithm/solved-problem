package N9205;

import java.io.*;
import java.util.*;

public class N9205_taeyoung {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		next: for (int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine());

			// 집
			int[] home = new int[2];
			st = new StringTokenizer(br.readLine());
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());

			// 편의점
			int[][] cs = new int[n][2];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				cs[i][0] = Integer.parseInt(st.nextToken());
				cs[i][1] = Integer.parseInt(st.nextToken());
			}

			// 락페스티벌
			int[] rock = new int[2];
			st = new StringTokenizer(br.readLine());
			rock[0] = Integer.parseInt(st.nextToken());
			rock[1] = Integer.parseInt(st.nextToken());

			int b = 20; // 맥주

			boolean[] visited = new boolean[n];

			Deque<int[]> Q = new ArrayDeque<>();
			Q.offer(home);

			while (!Q.isEmpty()) {
				int[] now = Q.poll();

				if (Math.abs(rock[0] - now[0]) + Math.abs(rock[1] - now[1]) <= 1000) {
					System.out.println("happy");
					continue next;
				}

				for (int i = 0; i < n; i++) {
					if (visited[i])
						continue;

					if (Math.abs(now[0] - cs[i][0]) + Math.abs(now[1] - cs[i][1]) <= 1000) {
						visited[i] = true;
						Q.offer(cs[i]);
					}

				}
			}
			System.out.println("sad");
		}

	}
}
