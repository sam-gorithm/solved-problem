package N7562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N7562_geonnam {
	static int N;
	static boolean[][] visited; //방문 체크 배열
	static int eX, eY; //도착지
	static int[] dr = {-2,-2,-1,1,2,2,1,-1}; //나이트 이동 지점
	static int[] dc = {-1,1,2,2,1,-1,-2,-2};
	static int result; //이동 횟수
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=t;tc++) {
			N = Integer.parseInt(br.readLine());
			visited = new boolean[N][N];
			
			st = new StringTokenizer(br.readLine());
			int sX = Integer.parseInt(st.nextToken()); //시작 지점
			int sY = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			eX = Integer.parseInt(st.nextToken()); //도착 지점
			eY = Integer.parseInt(st.nextToken());
			
			result = 0;
			bfs(sX,sY,0);
			System.out.println(result);
		}
	}
	
	static void bfs(int r, int c, int count) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r,c,count});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int row = cur[0];
			int col = cur[1];
			int count2 = cur[2];
			
			//종료 조건 - 도착지 도달
			if(row == eX && col == eY) {
				result = count2;
				return;
			}
			
			for(int i=0;i<8;i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];
				
				if(nr<0||nc<0||nr>=N||nc>=N) continue;
				if(visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				q.add(new int[] {nr,nc,count2+1});
			}
		}
	}
}
