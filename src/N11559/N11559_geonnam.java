package N11559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class N11559_geonnam {
	static char[][] map = new char[12][6];
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0;i<12;i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		} //입력값 채우기
		
		int result = 0;
		boolean flag = true; //블록을 부쉈는지 확인하기 위한 변수
		
		while(flag) {
			flag = false;
			for(int i=0;i<12;i++) {
				for(int j=0;j<6;j++) {
					if(map[i][j] == '.') continue; //색깔이 아니면 검사할 필요X
					if(check(i,j)) { //부술 수 있으면
						puyo(i,j); //부수기
						flag = true;
					}
				}
			}
			if(flag) {
				down(); //부순 공간에 색깔 내리기
				result++;
			}
		}
		
		System.out.println(result);
	
	}
	
	// 부수는 함수
	static void puyo(int r, int c) {
		visited = new boolean[12][6];
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {r,c});
		visited[r][c] = true;
		
		char color = map[r][c];
		map[r][c] = '.'; //부수기
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int i=0;i<4;i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				
				if(nr<0||nc<0||nr>=12||nc>=6) continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc] != color) continue;
				
				visited[nr][nc] = true;
				map[nr][nc] = '.'; //부수기
				q.add(new int[] {nr,nc});
			}
		}
	}
	
	//부숴야하는지 체크하는 함수
	static boolean check(int r, int c) {
		visited = new boolean[12][6];
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {r,c});
		visited[r][c] = true;
		
		char color = map[r][c];
		int count = 1; //연결 개수 확인하는 변수
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int i=0;i<4;i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				
				if(nr<0||nc<0||nr>=12||nc>=6) continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc] != color) continue;
				
				visited[nr][nc] = true;
				count++; 
				q.add(new int[] {nr,nc});
			}
		}
		
		if(count >= 4) return true; //4개 이상 연결되어 있는지 확인
		return false;
	}
	
	//빈공간 내리는 함수
	static void down() {
		for(int i=11;i>=0;i--) {
			for(int j=5;j>=0;j--) {
				if(map[i][j] == '.') {
					for(int k=i-1;k>=0;k--) {
						if(map[k][j] != '.') {
							map[i][j] = map[k][j];
							map[k][j] = '.';
							break;
						}
					}
				}
			}
		}
	}
}
