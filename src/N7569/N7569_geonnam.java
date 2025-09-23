package N7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N7569_geonnam {
	static int[][][] map,day;
	static int[] dr = {-1,1,0,0,0,0};
	static int[] dc = {0,0,-1,1,0,0};
	static int[] dh = {0,0,0,0,-1,1};
	static int M,N,H;
	static Queue<int[]> q;
	static int max;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); //가로
		N = Integer.parseInt(st.nextToken()); //세로
		H = Integer.parseInt(st.nextToken()); //높이
		
		map = new int[N][M][H];
		day = new int[N][M][H];
		q = new LinkedList<>();
		max = 0;
		
		
		for(int i=0;i<H;i++) { //높이
			for(int j=0;j<N;j++) { //세로
				st = new StringTokenizer(br.readLine());
				for(int k=0;k<M;k++) { // 가로
					map[j][k][i] = Integer.parseInt(st.nextToken());
					if(map[j][k][i] == 1) {
						day[j][k][i] = 1; 
						q.add(new int[] {j,k,i});
					}
				}
			}
		}
		
		bfs();
		
		for(int i=0;i<H;i++) { //높이
			for(int j=0;j<N;j++) { //세로
				for(int k=0;k<M;k++) { // 가로
					if(map[j][k][i] == 0) { //익지 않은 토마토가 남아있을 때
						System.out.println(-1);
						return;
					}
				}
			}
		}
		if(max == 0) System.out.println(0); //토마토가 처음부터 다 익어있을 때
		else System.out.println(max - 1);//day 시작을 1로 했으므로 -1
	}
	
	static void bfs() {
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int row = cur[0];
			int col = cur[1];
			int height = cur[2];
			
			for(int i=0;i<6;i++) { //6방향 탐색
				int nr = row + dr[i];
				int nc = col + dc[i];
				int nh = height + dh[i];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M || nh<0 || nh>=H) continue;
				
				if(map[nr][nc][nh] == 0) { //익지 않은 토마토가 있을 때
					map[nr][nc][nh] = 1;
					max = day[nr][nc][nh] = day[row][col][height] + 1; //day 최댓값 계산
					q.add(new int[] {nr,nc,nh});
				}
			}
		}
	}
}
