package N14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class N14502_geonnam {
	static int N,M, result;
	static int[][] map;
	static List<int[]> empty, virus; //빈 칸, 바이러스 저장 배열
	static int[] dr = {0,0,-1,1};
	static int[] dc = {-1,1,0,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //세로
		M = Integer.parseInt(st.nextToken()); //가로
		
		map = new int[N][M];
		empty = new ArrayList<>(); //빈 칸을 저장할 리스트 (순회가 편하기 위함)
		virus = new ArrayList<>(); //바이러스를 저장할 리스트 (순회가 편하기 위함)
		result = Integer.MIN_VALUE; //안전구역 수
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) empty.add(new int[] {i,j}); //빈칸 추가
				else if(map[i][j] == 2) virus.add(new int[] {i,j}); //바이러스 추가
			}
		}
		
		wall(0,0);
		System.out.println(result);
	}
	
	// 벽을 세우는 모든 경우의 수
	static void wall(int idx, int count) {
		//벽 3개 세웠으면 bfs
		if(count == 3) {
			bfs();
			return;
		}
		
		//벽을 3개 미만으로 세웠을 때
		for(int i=idx;i<empty.size();i++) {
			int[] cur = empty.get(i); // 다음 빈 칸을 가져온다.
			map[cur[0]][cur[1]] = 1; //빈 칸을 벽으로 만든다.
			wall(i+1, count+1); // 재귀
			map[cur[0]][cur[1]] = 0; // 다시 돌려놓기
		}
	}
	
	//바이러스 퍼뜨리기
	static void bfs() {
		int[][] copy = new int[N][M]; //map을 복사했다.
		for(int i=0;i<N;i++) {
			copy[i] = map[i].clone(); //clone()을 안하면 얕은 복사가 됨
		}
		
		Queue<int[]> q = new ArrayDeque<>();
		
		for(int i=0;i<virus.size();i++) {
			int[] cur = virus.get(i); //바이러스를 q에 추가한다.
			q.add(new int[] {cur[0], cur[1]});
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			//바이러스 퍼뜨리기
			for(int i=0;i<4;i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if(nr<0||nc<0||nr>=N||nc>=M) continue;
				if(copy[nr][nc] == 0) {
					copy[nr][nc] = 2;
					q.add(new int[] {nr,nc});
				}
			}
		}
		//안전 구역 수 확인
		check(copy);
	}
	//안전 구역 수를 확인하는 함수
	static void check(int[][] copy) {
		int count = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(copy[i][j] == 0) count++; 
			}
		}
		if(result <count) result = count;
	}
}
