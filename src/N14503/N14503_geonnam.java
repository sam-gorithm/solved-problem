package N14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N14503_geonnam {
	static int[] dr = {-1,0,1,0}; //북서남동
	static int[] dc = {0,-1,0,1};
	static int[][] map;
	static boolean[][] visited;
	static int N,M;
	static int count;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//방
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//로봇
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());
		
		//방향 정의를 잘못해놔서 추가함
		if(dir == 1) {
			dir = 3;
		}else if(dir ==3) {
			dir = 1;
		}
		
		map = new int[N][M];
		visited = new boolean[N][M];
		count = 0;
		
		//방 정보 입력 받기
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		clean(r,c,dir);
		System.out.println(count);
	}
	
	//청소하기
	static void clean(int r, int c, int dir) {
		visited[r][c] = true; //현재 위치 청소
		count++;
		
		while(true) {
			//주변 4칸 중 청소할 칸이 있는지 확인
			if(check(r,c)) {
				for(int i=0;i<4;i++) {
					//반시계 회전
					dir = (dir+1)%4;
					int nr = r+dr[dir];
					int nc = c+dc[dir];
					
					if(nr<0||nc<0||nr>=N||nc>=M) continue;
					if(map[nr][nc] == 1 || visited[nr][nc]) continue;
					
					visited[nr][nc] = true;
					count++; //청소
					
					//현재 위치 갱신
					r = nr;
					c = nc;
					break;
				}
			}
			//청소할 곳이 없어서 후진해야할 때
			else {
				int nd = (dir+2)%4;
				int nr = r+dr[nd];
				int nc = c+dc[nd];
				
				if(map[nr][nc] == 1) return;
				
				r = nr;
				c = nc;
			}
		}
	}
	
	//주변 4칸 중 청소할 칸이 있는지 확인하는 함수
	static boolean check(int r, int c) {
		for(int i=0;i<4;i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			
			if(nr<0||nc<0||nr>=N||nc>=M) continue;
			if(map[nr][nc] == 1 || visited[nr][nc]) continue;
			
			return true;
		}
		return false;
	}
}
