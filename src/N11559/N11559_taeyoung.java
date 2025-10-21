package N11559;

import java.util.*;
import java.io.*;

public class N11559_taeyoung {
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static char[][] C = new char[12][6]; // 초기 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 배열 입력
		for (int i = 0; i < 12; i++) {
			String S = br.readLine();
			for (int j = 0; j < 6; j++) {
				C[i][j] = S.charAt(j);
			}
		}
		
		// 정답 변수
		int ans = 0;

		while (popPuyos()) { // 터트릴게 있다면 -> 터트리고 떨어진다 -> 정답 1 증가
			dropPuyos();
			ans++;
		}

		System.out.println(ans);
	}
	
	// 터트릴 수 있는지 확인
	static boolean popPuyos() { // 터질게 있으면 터트리고 true, 없으면 false
		boolean popped = false; // 터졌는지 확인

		boolean[][] checked = new boolean[12][6]; // 방문 체크
		
		// 모든 위치 탐색
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (C[i][j] != '.' && !checked[i][j]) { // 빈 공간 아니고, 아직 방문 안함
					char now = C[i][j]; // 현재 색
					
					Deque<int[]> Q = new ArrayDeque<>(); // BFS를 위한 큐
					
					List<int[]> L = new ArrayList<>(); // 연결된 같은 색 좌표 입력 리스트 

					checked[i][j] = true; // 방문 체크
					Q.offer(new int[] { i, j }); // 큐에 추가
					L.add(new int[] { i, j }); // 리스트에 추가

					// BFS
					while (!Q.isEmpty()) {
						int[] X = Q.poll();
						int r = X[0];
						int c = X[1];

						for (int d = 0; d < 4; d++) {
							int nr = r + dir[d][0];
							int nc = c + dir[d][1];

							// 인덱스, 방문 체크
							if (nr < 0 || nr >= 12 || nc < 0 || nc >= 6 || checked[nr][nc]) {
								continue;
							}

							// 같은 색이면
							if (C[nr][nc] == now) {
								checked[nr][nc] = true;
								Q.offer(new int[] { nr, nc });
								L.add(new int[] { nr, nc });
							}
						}
					}
					
					// 연결된 같은 색이 4개 이상 -> 터진다
					if (L.size() >= 4) {
						for (int[] l : L) {
							C[l[0]][l[1]] = '.';
						}
						popped = true; // 터짐
					}
				}
			}
		}
		return popped;
	}

	// 터트린 후 떨어뜨리는 함수
	static void dropPuyos() {
		for (int j = 0; j < 6; j++) {
			Deque<Character> Q = new ArrayDeque<>();
			// 아래에서부터 뿌요 큐에 넣기
			for (int i = 11; i >= 0; i--) {
				if (C[i][j] != '.') {
					Q.offer(C[i][j]);
				}
			}
			// 아래에서부터 큐가 빌때까지 뿌요 쌓고, 나머지는 빈 칸
			for (int i = 11; i >= 0; i--) {
				if (!Q.isEmpty())
					C[i][j] = Q.poll();
				else
					C[i][j] = '.';
			}
		}
	}
}
