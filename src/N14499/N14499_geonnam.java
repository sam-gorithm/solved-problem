package N14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N14499_geonnam {
	static int[][] map;
	static int[] dice = {0,0,0,0,0,0,0}; //주사위 배열 0번 인덱스는 사용하지 않음
	static int[] dr = {0,0,-1,1}; // 동서북남
	static int[] dc = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 세로
		int M = Integer.parseInt(st.nextToken()); // 가로
		int x = Integer.parseInt(st.nextToken()); // 주사위 좌표 x
		int y = Integer.parseInt(st.nextToken()); // 주사위 좌표 y
		int K = Integer.parseInt(st.nextToken()); // 명령어 수
		
		map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//map 입력 받기
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<K;i++) {
			//1:동, 2:서, 3:북, 4:남
			int dir = Integer.parseInt(st.nextToken());
			x = x+dr[dir-1];
			y = y+dc[dir-1];
			
			if(x<0||y<0||x>=N||y>=M) {
				//범위에 벗어나면 그 쪽으로 움직이지 않으므로 상태 복구
				x = x-dr[dir-1];
				y = y-dc[dir-1];
				continue;
			}
			
			roll(x,y,dir);
			sb.append(dice[6]).append("\n"); //주사위 윗면 출력
		}
		System.out.println(sb);
	}
	
	static void roll(int nr, int nc, int dir) {
		if(dir == 1) {
			//동쪽으로
			int tmp = dice[1];
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = tmp;
		}else if(dir == 2) {
			//서쪽으로
			int tmp = dice[1];
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = tmp;
		}else if(dir == 3) {
			//북쪽으로
			int tmp = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = tmp;
		}else if(dir == 4) {
			//남쪽으로
			int tmp = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[2];
			dice[2] = tmp;
		}
		
		if(map[nr][nc] == 0) {
			//칸이 0이면 주사위 아랫면 수를 map에 복사
			map[nr][nc] = dice[1]; 
		}else {
			//칸의 수가 0이 아니면 주사위에 복사 후 칸의 수 지우기
			dice[1] = map[nr][nc];
			map[nr][nc] = 0;
		}
	}
}
