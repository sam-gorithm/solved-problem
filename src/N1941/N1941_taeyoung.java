package N1941;

import java.util.*;
import java.io.*;

public class N1941_taeyoung {
	static char[][] C = new char[5][5];
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int ans = 0;
	static int[] selected = new int[7];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 5; i++) {
			String S = br.readLine();
			for (int j = 0; j < 5; j++) {
				C[i][j] = S.charAt(j);
			}
		}

		bt(0, 0, 0);

		System.out.println(ans);
	}
	
	// 25명의 학생 중 7명 뽑는 백트래킹 함수
	static void bt(int n, int idx, int Y) { // n: 현재 뽑은 인원 수, idx: 뽑을 수 있는 학생 번호, Y: 임도연파 수
		if (Y > 3) // 임도연파 3명 넘어가면 리턴
			return;
		if (n == 7) { // 7명 다 채우면
			if (isConnected()) // 모두 연결되어있는지 확인
				ans++;
			return;
		}

		// 좌상단부터 0번 ~ 25번 가정(i)
		for (int i = idx; i < 25; i++) {
			int r = i / 5; // 번호에 해당하는 열
			int c = i % 5; // 번호에 해당하는 행
			selected[n] = i; // i번 뽑음
			// 다음 재귀 -> 인원 수 +1, 다음 번호부터 뽑을 수 있음, 임도연파인지 확인
			bt(n + 1, i + 1, Y + (C[r][c] == 'Y' ? 1 : 0));
		}
	}

	// 뽑힌 인원 모두 연결되어있는지 확인하는 함수 (BFS)
	static boolean isConnected() {
		boolean[][] visited = new boolean[5][5]; // 방문 체크
		Queue<Integer> Q = new ArrayDeque<>();
		Q.add(selected[0]); // 뽑힌 인원 한명 큐에 넣기
		int r0 = selected[0] / 5; // 열
		int c0 = selected[0] % 5; // 행
		visited[r0][c0] = true; // 방문 체크

		int cnt = 1; // 연결되어있는 인원 수

		while (!Q.isEmpty()) {
			int cur = Q.poll();
			int r = cur / 5;
			int c = cur % 5;

			for (int d = 0; d < 4; d++) {
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];

				// 인덱스, 방문여부 체크
				if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || visited[nr][nc])
					continue;

				int next = nr * 5 + nc; // 다음 번호

				for (int s : selected) {
					if (s == next) { // 다음 번호가 뽑힌 인원이라면
						visited[nr][nc] = true; // 방문 체크
						cnt++; // 인원 수
						Q.add(next); // 큐에 넣기
					}
				}
			}
		}
		return cnt == 7; // 7명이면 true 반환
	}
}
