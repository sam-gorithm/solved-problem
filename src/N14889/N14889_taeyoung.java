package N14889;

import java.util.*;
import java.io.*;

public class N14889_taeyoung {
	static int N, ans;
	static int[][] A;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		A = new int[N][N];
		visited = new boolean[N];
		
		StringTokenizer st;
		for (int i = 0; i < N;  i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = Integer.MAX_VALUE;
		
		bt(0, 0);
        System.out.println(ans);
	}
	
	// 백트래킹 -> 팀 나누기 -> true:스타트팀
	static void bt(int idx, int cnt) {
		if (cnt == N / 2) {
			cal();
			return;
		}
		
		for (int i = idx; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				bt(i + 1, cnt + 1);
				visited[i] = false;
			}
		}
	}
	
	// 팀 능력치 차이 계산 
	static void cal() {
		int s = 0; // 스타트팀 능력치
		int l = 0; // 링크팀 능력치
		
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (visited[i] && visited[j]) { // i,j -> 스타트팀
					s += A[i][j] + A[j][i];
				} else if (!visited[i] && !visited[j]) { // i,j -> 링크팀
					l += A[i][j] + A[j][i];
				}
			}
		}
		// 정답 갱신
		ans = Math.min(ans, Math.abs(s - l));
	}
}

