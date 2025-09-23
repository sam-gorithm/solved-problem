package N10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class N10026_geonnam {
	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); 
		map = new char[N][N];
		
		for(int i=0;i<N;i++) {
			String text = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = text.charAt(j);
			}
		}//map 채우기
		
		int nCount = 0; // 적록색맹이 아닌 사람
		int cCount = 0; // 적록색맹
		
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(visited[i][j]) continue;
				normal(i,j);
				nCount++;
			}
		}//적록 색맹이 아닌 사람 그룹 수 세기
		
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(visited[i][j]) continue;
				color(i,j);
				cCount++;
			}
		}//적록 색맹 그룹수 세기
		
		System.out.println(nCount+" "+cCount);
	}
	
	static void normal(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r,c});
		visited[r][c] = true;
		char ch = map[r][c];
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int row = cur[0];
			int col = cur[1];
			
			for(int i=0;i<4;i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];
				
				if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
				if(visited[nr][nc] || ch != map[nr][nc]) continue;
				
				//방문하지 않았고 색깔이 처음과 같을 때
				visited[nr][nc] = true;
				q.add(new int[] {nr,nc});
			}
		}
	}
	
	static void color(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r,c});
		visited[r][c] = true;
		char ch = map[r][c];
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int row = cur[0];
			int col = cur[1];
			
			for(int i=0;i<4;i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];
				
				if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
				if(visited[nr][nc]) continue;
				//R이나 G는 B만을 무시한다.
				if(ch == 'R' && map[nr][nc] == 'B' || ch == 'G' && map[nr][nc] == 'B')
					continue;
				if(ch == 'B' && ch != map[nr][nc]) continue;
				
				//방문하지 않고 색깔이 서로 맞을 때
				visited[nr][nc] = true;
				q.add(new int[] {nr,nc});
			}
		}
	}
}
