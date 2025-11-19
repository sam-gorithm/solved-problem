import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class N14502_jeongun {
	static int N, M, result;
	static int[][] arr;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		bfs(0);
		System.out.println(result);
	}

	static void bfs(int built) {
	    if (built == 3) { //벽 다 세웠을 때
	    	Queue<int[]> q = new ArrayDeque<int[]>();
	    	//복사 배열 만들고
	        int[][] arrCopy = new int[N][M];
	        //안전영역
	        int area = 0;
	        //바이러스 찾아서 큐에 넣기
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < M; j++) {
	                arrCopy[i][j] = arr[i][j];
	                if (arrCopy[i][j] == 2) {
	                    q.offer(new int[]{i, j});
	                } else if (arrCopy[i][j] == 0) {
	                    area++;                 //안전영역 세기
	                }
	            }
	        }

	        //감염 칸 확산 + 해당 칸 안전영역에서 빼기
	        while (!q.isEmpty()) {
	            int[] curr = q.poll();
	            int r = curr[0];
	            int c = curr[1];

	            for (int k = 0; k < 4; k++) {
	                int nr = r + dr[k];
	                int nc = c + dc[k];

	                if (nr < 0 || nc < 0 || nr >= N || nc >= M || arrCopy[nr][nc] != 0) {
	                	continue;
	                }

	                arrCopy[nr][nc] = 2;
	                q.offer(new int[]{nr, nc});
	                area--;  //감염되면 뺴주기
	            }
	        }
	        //최종값 갱신
	        result = Math.max(area, result);
	        return;
	    }
	    //벽 세우기
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < M; j++) {
	        	//빈 칸에만 세워야 하니까
	            if (arr[i][j] != 0) {
	            	continue;
	            }
	            arr[i][j] = 1; //고르고
	            bfs(built+1); //다음 선택
	            arr[i][j] = 0; //원복
	        }
	    }
	}
}