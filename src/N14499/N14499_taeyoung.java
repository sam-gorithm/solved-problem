package N14499;

import java.util.*;
import java.io.*;

public class N14499_taeyoung {
	static int[][] dice = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } }; // 주사위 전개도

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M]; // 지도

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		int[][] dir = { {}, { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } }; // 주사위 이동 방향 (동 서 북 남)

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < K; i++) {
			int a = Integer.parseInt(st.nextToken()); // 주사위 이동 방향
			// 다음 좌표
			int nx = x + dir[a][0];
			int ny = y + dir[a][1];
			
			// 이동할 수 없으면
			if (nx < 0 || nx >= N || ny < 0 || ny >= M) 
				continue;
			
			// 좌표 이동 및 주사위 회전
			x = nx;
			y = ny;
			roll(a);
			
			if (map[x][y] == 0) { // 이동한 칸 수 0이면
				map[x][y] = dice[3][1]; // 바닥면에 있는 수 지도에 복사
			} else { // 0 아니면
				dice[3][1] = map[x][y];  // 지도 수 주사위 바닥에 복사
				map[x][y] = 0; // 해당 칸 0
			}
			
			System.out.println(dice[1][1]); // 주사위 상단 수 출력
		}
	}
	
	// 주사위 굴리기
	static void roll(int a) { // a: 방향
		int tmp;
		switch (a) {
		case 1: // 동
			tmp = dice[1][2];
			dice[1][2] = dice[1][1];
			dice[1][1] = dice[1][0];
			dice[1][0] = dice[3][1];
			dice[3][1] = tmp;
			break;
		case 2: // 서
			tmp = dice[1][0];
			dice[1][0] = dice[1][1];
			dice[1][1] = dice[1][2];
			dice[1][2] = dice[3][1];
			dice[3][1] = tmp;
			break;
		case 3: // 북
			tmp = dice[0][1];
			dice[0][1] = dice[1][1];
			dice[1][1] = dice[2][1];
			dice[2][1] = dice[3][1];
			dice[3][1] = tmp;
			break;
		case 4: // 남
			tmp = dice[3][1];
			dice[3][1] = dice[2][1];
			dice[2][1] = dice[1][1];
			dice[1][1] = dice[0][1];
			dice[0][1] = tmp;
			break;
		}
		return;
	}
}
