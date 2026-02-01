package N10429;

import java.util.*;
import java.io.*;

public class N10429_taeyoung {
	static int N, M;
	static char[][] A;
	static boolean found;
	static StringBuilder ans;
	static boolean[][] visited;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new char[3][3];
		for (int i = 0; i < 3; i++) {
			String s = br.readLine();
			for (int j = 0; j < 3; j++) {
				A[i][j] = s.charAt(j);
			}
		}

		found = false;
		ans = new StringBuilder();
		visited = new boolean[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if ((i + j) % 2 == 1)
					continue;
				visited[i][j] = true;
				List<int[]> L = new ArrayList<>();
				L.add(new int[] { i, j });
				StringBuilder sb = new StringBuilder();
                sb.append(A[i][j]);
				dfs(i, j, L, sb);
				visited[i][j] = false;
			}
		}

		if (!found) {
			ans.append(0);
		}

		System.out.println(ans);
	}

	static void dfs(int r, int c, List<int[]> use, StringBuilder sb) {
		if (found || sb.length() > 2 * M - 1)
			return;
		if (sb.length() == 2 * M - 1 && calc(sb.toString()) == N) {
			ans.append(1);
			for (int[] x : use)
				ans.append("\n").append(x[0]).append(' ').append(x[1]);
			found = true;
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			if (nr < 0 || nr >= 3 || nc < 0 || nc >= 3 || visited[nr][nc])
				continue;
			visited[nr][nc] = true;
			use.add(new int[] { nr, nc });
			sb.append(A[nr][nc]);
			dfs(nr, nc, use, sb);
			visited[nr][nc] = false;
			use.remove(use.size()-1);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	static int calc(String S) {
		int X = S.charAt(0) - '0';
		for (int i = 1; i < S.length(); i += 2) {
			char c = S.charAt(i);
			int n = S.charAt(i + 1) - '0';
			if (c == '+')
				X += n;
			else
				X -= n;
		}
		return X;
	}
}
