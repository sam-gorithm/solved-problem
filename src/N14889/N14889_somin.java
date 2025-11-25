import java.util.Scanner;
 
public class N14889_somin {
	
	static int N;
	static int[][] map;
	static boolean[] visit;
	
	static int Min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
    
		Scanner in = new Scanner(System.in);
 
		N = in.nextInt();
 
		map = new int[N][N];
		visit = new boolean[N];
 
 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = in.nextInt();
			}
		}
 
		team(0, 0);
		System.out.println(Min);
 
	}
 
	static void team(int idx, int count) {
		// 팀 조합 완성 
		if(count == N / 2) {
			diff();
			return;
		}
 
		for(int i = idx; i < N; i++) {
			// 방문하지 않았을 경우 
			if(!visit[i]) {
				visit[i] = true;	// 방문처리 
				team(i + 1, count + 1);
				visit[i] = false;	// 재귀 끝난 후 되돌림 
			}
		}
	}
 
	// 두 팀의 능력치 차이 
	static void diff() {
		int team_start = 0;
		int team_link = 0;
 
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (visit[i] == true && visit[j] == true) {
					team_start += map[i][j];
					team_start += map[j][i];
				} 
				else if (visit[i] == false && visit[j] == false) {
					team_link += map[i][j];
					team_link += map[j][i];
				}
			}
		}

		int val = Math.abs(team_start - team_link);
		
		if (val == 0) {
			System.out.println(val);
			System.exit(0);
		}
		
		Min = Math.min(val, Min);
			
	}
 
} 