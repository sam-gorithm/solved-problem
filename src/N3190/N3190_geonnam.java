package N3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class N3190_geonnam {
	static int[] dr = {-1,0,1,0}; // 상, 우, 하, 좌
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			
			map[x][y] = 2; //사과의 위치
		}
		
		int L = Integer.parseInt(br.readLine());
		int[] time = new int[L];
		char[] dir = new char[L];
		
		
		for(int i=0;i<L;i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			dir[i] = st.nextToken().charAt(0);
		}
		
		map[0][0] = 1;
		
		int t = 0; //시간
		int index = 0 ; //방향 변경 인덱스
		int r = 0; // 뱀의 처음 위치
		int c = 0; // 뱀의 처음 위치
		int dire = 1; //오른쪽 방향
		
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {0,0});
		
		while(true) {
			//방향을 변경해야할 때면 방향 변경
			if (index < L && time[index] == t) {
				if(dir[index] == 'L') {
					dire = (dire + 3)%4;
				}else {
					dire = (dire + 1)%4;
				}
				index++;
			}
			
			//뱀 이동
			t++;
			int nr = r + dr[dire];
			int nc = c + dc[dire];
			
			if(nr<0||nc<0||nr>=N||nc>=N) break; //벽에 부딪힐 때
			if(map[nr][nc] == 1) break; // 자기 자신과 부딪힐 때
			
			//빈 칸인 경우
			if(map[nr][nc] == 0) { 
				map[nr][nc] = 1;
				
				//꼬리 제거
				int[] del = q.poll();
				map[del[0]][del[1]] = 0;
				
			}
			//사과를 먹은 경우
			else if(map[nr][nc] == 2) {
				map[nr][nc] = 1;
			}
			
			r = nr;
			c = nc;
			q.add(new int[] {r,c});
			
		}
		
		System.out.println(t);
	}
}
