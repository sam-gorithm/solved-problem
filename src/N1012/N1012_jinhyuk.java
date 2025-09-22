package N1012;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

//	유기농 배추 / 실버 2 / 232ms
public class N1012_jinhyuk {
	//static
	static int N, M;
	static int[][] map;
	
	//4방향 탐색 (상하좌우)
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	//Pos
	static class Pos {
		int r;
		int c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	
	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		//tc
		for(int tc=1; tc<=T; tc++) {
			
			M = sc.nextInt();	//가로길이
			N = sc.nextInt();	//세로길이
			int K = sc.nextInt();	//좌표개수
			
			map = new int[N][M];
			for(int i=0; i<K; i++) {
				int x = sc.nextInt();	//열
				int y = sc.nextInt();	//행
				map[y][x] = 1;
			}
			
			int cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 1) {
						//배추 재배하는 지점마다 bfs, 지렁이수 추가
						bfs(i,j);
						cnt++;
					}
				}
			}
			
			//결과출력
			System.out.println(cnt);
		}	//tc
		
	}	//main
	
	
	//bfs
	private static void bfs(int r, int c) {
		Queue<Pos> q = new LinkedList<>();
		Pos p = new Pos(r, c);
		q.add(p);
		map[r][c] = 0;
		
		while(!q.isEmpty()) {
			Pos now = q.poll();
			//4방향 탐색
			for(int i=0; i<4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				if(nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] == 1) {
					Pos p2 = new Pos(nr, nc);
					q.add(p2);
					map[nr][nc] = 0;
				}
			}//4방향 탐색
		}//while
		
		return;
	}	//bfs

	
}	//class
