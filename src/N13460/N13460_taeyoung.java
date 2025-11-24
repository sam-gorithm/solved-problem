package N13460;

import java.util.*;
import java.io.*;

public class N13460_taeyoung {
	static int N, M;
	static char[][] board;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[] hole;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new char[N][M];

		int[] B = new int[2]; // 빨간 구슬 초기 위치
		int[] R = new int[2]; // 파란 구슬 초기 위치
		hole = new int[2]; // 구멍 위치

		// board -> 구슬 제외한 상태로 입력
		for (int i = 0; i < N; i++) {
			String S = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = S.charAt(j);
				if (board[i][j] == 'B') {
					B = new int[] { i, j };
					board[i][j] = '.';
				} else if (board[i][j] == 'R') {
					R = new int[] { i, j };
					board[i][j] = '.';
				} else if (board[i][j] == 'O') {
					hole = new int[] { i, j };
				}
			}
		}
		
		// BFS
		Deque<int[][]> Q = new ArrayDeque<>();
		
		// 구슬 좌표와 이동 횟수를 2차원 배열로
		Q.offer(new int[][] { B, R, {0} }); 

		// 방문 체크
		boolean[][][][] visited = new boolean[N][M][N][M];
		visited[B[0]][B[1]][R[0]][R[1]] = true;

		while (!Q.isEmpty()) {
			int[][] cur = Q.poll();
			int[] blue = cur[0];
			int[] red = cur[1];
			int move = cur[2][0];
			
			// 10번 이하로 움직여야 함
			if (move >= 10)
				continue;
			
			for (int d = 0; d < 4; d++) {
				int[][] next = finalLocation(blue, red, d);
				
				int[] nb = next[0];
				int[] nr = next[1];
				
				// 파랑이 구멍에 들어오면 실패 -> 넘어간다
				if (nb[0] == hole[0] && nb[1] == hole[1]) 
					continue;
				
				// 빨강만 구멍에 들어옴 -> 다음 이동에 성공
				if (nr[0] == hole[0] && nr[1] == hole[1]) {
					System.out.println(move+1);
					return;
				}
				
				// 현재 위치 방문 여부
				if (visited[nb[0]][nb[1]][nr[0]][nr[1]]) 				
					continue;
				
				visited[nb[0]][nb[1]][nr[0]][nr[1]] = true;
				Q.offer(new int[][] {nb, nr, {move + 1}});
			}
		}
		
		// 10번 이내 불가능
		System.out.println(-1);
	}

	// 구슬 이동시키는 함수. 구슬끼리 겹치는건 여기서는 고려 안함
	static int[] move(int[] ball, int d) {
		int r = ball[0];
		int c = ball[1];
		
		int nr = r + dir[d][0];
		int nc = c + dir[d][1];
		
		// 벽이 아닌 위치까지 계속 이동
		while(board[nr][nc] != '#') {
			// 구멍 만나면 이동 종료
			if (board[nr][nc] == 'O') {
				return new int[] {nr, nc};
			}
			// 1칸 이동
			nr += dir[d][0];
			nc += dir[d][1];
			
		}
		r = nr - dir[d][0];
		c = nc - dir[d][1];
		
		return new int[] {r, c};
	}
	
	// 구슬이 겹치는 경우 고려한 최종 위치
	static int[][] finalLocation(int[] blue, int[] red, int d){
		int[] nb = move(blue, d);
		int[] nr = move(red, d);
		
		// 두 구슬 같은 위치 -> 늦게 온 구슬 한칸 이동 
		// 구멍이면 같은 위치 가능
		if (nb[0] == nr[0] && nb[1] == nr[1] && !(nr[0] == hole[0] && nr[1] == hole[1])) { 
			if (d == 0) { // 위로 기울임
				if (blue[0] > red[0]) { // 파랑이 아래에 있었으면
					nb[0]++; // 파랑 한칸 아래로
				} else { // 빨강이 아래에 있었으면
					nr[0]++; // 빨강 한칸 아래로
				}
			} else if (d == 1) { // 아래로 기울임
				if (blue[0] < red[0]) { // 파랑이 위에 있었으면
					nb[0]--; // 파랑 한칸 위로
				} else { // 빨강이 위에 있었으면
					nr[0]--; // 빨강 한칸 위로
				}
			} else if (d == 2) { // 왼쪽으로 기울임
				if (blue[1] > red[1]) { // 파랑이 우측에 있었으면
					nb[1]++; // 파랑 한칸 우측
				} else { // 빨강이 우측에 있었으면
					nr[1]++; // 빨강 한칸 우측
				}
			} else { // 오른쪽으로 기울임
				if (blue[1] < red[1]) { // 파랑이 좌측에 있었으면
					nb[1]--; // 파랑 한칸 좌측
				} else { // 빨강이 좌측에 있었으면
					nr[1]--; // 빨강 한칸 좌측
				}
			}
		}
		return new int[][] {nb, nr};
	}
}

