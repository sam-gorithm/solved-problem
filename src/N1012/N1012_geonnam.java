package N1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N1012_geonnam {
	static boolean[][] visited; 
	static int[][] map;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int M,N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for(int tc=0;tc<T;tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); //가로
			N = Integer.parseInt(st.nextToken()); //세로
			int K = Integer.parseInt(st.nextToken()); //배추 수
			
			map = new int[N][M]; //배추 밭
			visited = new boolean[N][M];
			
			for(int i=0;i<K;i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				map[r][c] = 1;
			}//배추 심어져있는 곳 표시
			
			int count = 0; // 흰지렁이 개수
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(visited[i][j] || map[i][j] == 0) continue;
					check(i,j); //방문하지 않고 배추가 심어져 있는 곳 check
					count++;
				}
			}
			System.out.println(count);
		}
	}
	
	static void check(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r,c});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int row = cur[0];
			int col = cur[1];
			
			//배추가 이어져있는지 탐색
			for(int i=0;i<4;i++) {
				int nr = row+dr[i];
				int nc = col+dc[i];
				
				if(nr<0 || nc<0 || nr>=N || nc>=M) continue;
				if(visited[nr][nc] || map[nr][nc] == 0) continue;
				
				//이어져 있다면 그 부분을 기준으로 다시 탐색
				visited[nr][nc] = true;
				q.add(new int[] {nr,nc});
			}
		}
	}
}
