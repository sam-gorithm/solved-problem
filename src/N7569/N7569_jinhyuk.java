package N7569;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 토마토 / 골드 5 / 1376ms
public class N7569_jinhyuk {
	//static
	static int M, N, H;
	static int[][][] map;
	
	//4방향 델타 (상하좌우위아래)
	static int[] dr = {-1,1,0,0,0,0};
	static int[] dc = {0,0,-1,1,0,0};
	static int[] dh = {0,0,0,0,1,-1};
	
	//Pos
	static class Pos {
		int r;
		int c;
		int h;
		
		public Pos(int r, int c, int h) {
			this.r = r;
			this.c = c;
			this.h = h;
		}
	}

	
	//main
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();
		
		map = new int[N][M][H];
		Queue<Pos> q = new LinkedList<>();
		
		//1층 N x M 입력 후, 다음층
		for(int k=0; k<H; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					map[i][j][k] = sc.nextInt();
					if(map[i][j][k] == 1) {
						q.add(new Pos(i, j, k));
					}
				}
			}
		}//익은 토마토 지점 전부 q에 추가

		//bfs 실행
		bfs(q);
		
		//결과출력
		int ans = Integer.MIN_VALUE;
		for(int k=0; k<H; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j][k] == 0) {
						//안익은 토마토 있으면 '-1'출력 후 프로그램 종료.
						System.out.println(-1);
						return;
					}
					ans = Math.max(ans, map[i][j][k]);
				}
			}
		}
		//처음부터 다 익었을 경우 ans = 1 -> 0 출력
		//그 외 ans - 1
		System.out.println(ans - 1);
	}	//main


	//bfs
	private static void bfs(Queue<Pos> q) {
		while(!q.isEmpty()) {
			Pos now = q.poll();
			
			//6방향 탐색
			for(int i=0; i<6; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				int nh = now.h + dh[i];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= M || nh < 0 || nh >= H) continue;
				
				//'0'인 부분에 대해 bfs
				if(map[nr][nc][nh] == 0) {
					map[nr][nc][nh] = map[now.r][now.c][now.h] + 1;
					q.add(new Pos(nr, nc, nh));
				}
			}//6방향 탐색
		}//while
		
	}	//bfs

	
}	//class
