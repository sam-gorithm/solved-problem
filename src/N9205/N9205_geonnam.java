package N9205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N9205_geonnam {
	static int[][] map; //좌표 저장할 배열
	static int result_x, result_y, cn; // 도착지 좌표, 배열 개수 정하려고 cn
	static boolean result; // 도착할 수 있는지
	static boolean[] visited; // 방문 배열
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc=0;tc<t;tc++) {
			result = false; // 탐색 전에는 false
			cn = Integer.parseInt(br.readLine());
			
			visited = new boolean[cn+2];
			map = new int[cn+2][2];
			
			for(int i=0;i<cn+2;i++) {
				st = new StringTokenizer(br.readLine());
				map[i][0] = Integer.parseInt(st.nextToken());
				map[i][1] = Integer.parseInt(st.nextToken());
			}
			
			//도착지 좌표
			result_x = map[cn+1][0];
			result_y = map[cn+1][1];
			
			//방문 처리하고 재귀
			visited[0] = true;
			find(map[0][0], map[0][1]);
			
			if(result) System.out.println("happy");
			else System.out.println("sad");
		}
	}
	
	//다음 이동할 곳 찾는 함수
	static void find(int x, int y) {
		if(x == result_x && y == result_y) {
			result = true;
			return;
		}// 도착지에 도달했으면 result 바꾸고 return
		
		for (int i=1 ;i<cn+2;i++) {
			if(visited[i] == true) continue;
			
			int[] is = map[i];
			
			//1000m 이내어야 갈 수 있음
			int length  = Math.abs(x-is[0]) + Math.abs(y-is[1]);
			
			//갈수 있는 곳일때 방문 처리하고 재귀
			if(length <= 1000) {
				visited[i] = true;
				find(is[0], is[1]);
			}
			if(result) return; //재귀 종료를 위해
		}
	}
}

