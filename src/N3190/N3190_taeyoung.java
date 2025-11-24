package N3190;

import java.util.*;
import java.io.*;

public class N3190_taeyoung {
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // 우 하 좌 상

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] board = new int[N][N];
		board[0][0] = 2; // 뱀

		int K = Integer.parseInt(br.readLine());

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			board[r][c] = 1; // 사과
		}

		int L = Integer.parseInt(br.readLine());
		
		// 뱀 방향 변환 정보
		int[] X = new int[L];
		String[] C = new String[L];
		int ti = 0; // 인덱스

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			String c = st.nextToken();
			X[i] = x;
			C[i] = c;
		}

		int ans = 0;

		Deque<int[]> snake = new ArrayDeque<>(); // 데크 -> 맨 앞 머리, 맨 뒤 꼬리
		snake.offer(new int[] { 0, 0 }); // 머리 
		
		int d = 0; // 이동 방향

		while (true) {
			ans++;
			int[] head = snake.getFirst();
			// 다음 머리 위치
			int hr = head[0] + dir[d][0];
			int hc = head[1] + dir[d][1];
			if (hr < 0 || hr >= N || hc < 0 || hc >= N || board[hr][hc] == 2) // 벽이나 자기자신
				break;

			if (board[hr][hc] == 0) { // 사과 없으면
				// 꼬리 이동
				int[] tail = snake.pollLast(); // 데크에서 꼬리 제거
				int tr = tail[0];
				int tc = tail[1];
				board[tr][tc] = 0; // 꼬리 있던 위치 보드에 표시
			}
			board[hr][hc] = 2; // 뱀 머리 위치 보드에 저장
			snake.offerFirst(new int[] { hr, hc }); // 뱀 머리 데크 맨 앞에 넣기

			// 방향 변환
			if (ti < L && ans == X[ti]) {
				if (C[ti].equals("D")) { // 오른쪽 90도
					d = (d + 1) % 4;
				} else { // 왼쪽 90도
					d = (d + 3) % 4;
				}
				ti++;
			}
		}
		System.out.println(ans);
	}
}

