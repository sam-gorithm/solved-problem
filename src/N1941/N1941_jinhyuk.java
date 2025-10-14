package N1941;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//	소문난 칠공주 / 골드 3 / 368ms
public class N1941_jinhyuk {
	//static
	static int result;
	static int[] selected = new int[7];
	static int[][] map = new int[5][5];
	static boolean[][] visited;
	
	//4방향탐색 (상하좌우)
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1}; 
	
	//Point 클래스
	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
 
	
	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i < 5; i++) {
			String line = sc.next();
			for(int j = 0; j < 5; j++) {
				if(line.charAt(j) == 'S') {
					map[i][j] = 1; // 이다솜파
				} else {
					map[i][j] = 0; // 임도연파
				}
			}
		}
		
		//재귀
		comb(0, 0);
		
		//결과출력
		System.out.println(result);
		
	}	//main
 
	
	//comb
	private static void comb(int start, int depth) {
		
		////기저조건
		if(depth == 7) {
			// 일단 뽑은 아이들 체크
			int lee = 0;
			int yim = 0; // 이다솜파, 임도연파
			visited = new boolean [5][5];
			for(int i = 0; i < 7; i++) {
				int r = selected[i] / 5;
				int c = selected[i] % 5;
				
				if(map[r][c] == 1) lee++;
				else			   yim++;
				visited[r][c] = true;
			}
			
			// 이다솜파가 4명 이상인 경우에만 연결 체크
			if(lee >= 4) {
				if(isConnected()) {
					result++;
				}
			}
			return;
		}
		
		////재귀파트
		//5 x 5 인덱스를 학생들 번호로 지정
		//7가지 숫자 조합만들고 해당 번호 학생들 중 이다솜파가 4명 이상인지 / 모두 인접했는지 판단
		for(int i = start; i < 25; i++) {
			selected[depth] = i;
			comb(i + 1, depth + 1);
		}
		
	}	//comb
	
	//isConnected (연결여부확인하는 bfs)
	private static boolean isConnected() {
		//bfs를 위한 Queue 생성
		Queue<Point> Q = new LinkedList<>();
		//첫 번째 학생부터 bfs
		int r = selected[0] / 5;
		int c = selected[0] % 5;
		Q.add(new Point(r, c));
		visited[r][c] = false; // 선택 해제
		
		int cnt = 0;	//인접한 학생 수
		while(!Q.isEmpty()) {
			Point now = Q.poll();
			cnt++;
			
			//4방향탐색
			for(int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				
                //유효범위체크
				if(nr < 0 || nc < 0 || nr >= 5 || nc >= 5|| !visited[nr][nc]) continue;
				
				Q.add(new Point(nr, nc));
				visited[nr][nc] = false; // 선택 해제
			}//4방향탐색
		}//while
		
		if(cnt == 7) return true;
		else		 return false;
		
	}	//isConnected
	
	
}	//class