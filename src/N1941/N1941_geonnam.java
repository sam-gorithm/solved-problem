package N1941;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class N1941_geonnam {
	static int[] dr = {-1,1,0,0}; //4방향 탐색
	static int[] dc = {0,0,-1,1};
	static char[][] map = new char[5][5]; //주어진 입력값
	static int count; //결과 저장 변수
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		count = 0;
		
		for(int i=0;i<5;i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		} //입력 값 받기
		
		int[] sel = new int[7]; //선택할 7칸
		dfs(0,sel, 0,0);
		
		System.out.println(count);
	}
	
	static void dfs(int idx, int[] sel, int pCount, int yCount) {
		if(yCount > 3) return; //임도연파가 3명을 초과하면 안된다.
		
		if(pCount == 7) { //7명 다 채웠을 시
			if(connect(sel)) count++; //7명이 연결되어 있다면 count 증가
			return;
		}
		
		for(int i=idx;i<25;i++) {
			int r = i/5; //행
			int c = i%5; //열
			
			sel[pCount] = i; //조합처럼 선택
			
			if(map[r][c] == 'Y') //임도연파 명수 세기
				dfs(i+1,sel,pCount+1,yCount+1); 
			else
				dfs(i+1,sel,pCount+1,yCount);
		}
	}
	
	static boolean connect(int[] sel) { //7명이 연결되었는지 확인하는 함수
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[5][5]; //bfs 방문체크를 위해 
		boolean[][] selected = new boolean[5][5]; //선택된 7명 자리를 확인하기 위해
		int p = 1; // 이어진 사람 수
		
		for(int i=0;i<7;i++) {
			selected[sel[i]/5][sel[i]%5] = true;
		}//선택된 사람의 위치 확인
		
		q.add(new int[] {sel[0]/5, sel[0]%5});
		visited[sel[0]/5][sel[0]%5] = true;
		
		//bfs를 이용해 연결되어 있는지 확인
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int i=0;i<4;i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if(nr<0||nc<0||nr>=5||nc>=5) continue;
				if(!selected[nr][nc] || visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				q.add(new int[] {nr, nc});
				p++;
			}
		}
		if(p == 7) return true; //이어진 사람 수가 7명이면 모두 이어진 것이므로 true반환
		
		return false;
	}
}
