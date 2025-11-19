import java.util.Scanner;

public class N14500_jeongun {
	static int N, M, result;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i,j,1,arr[i][j]);
				visited[i][j] = false;
				
				check(i,j); //ㅗ 체크
			}
		}
		
		System.out.println(result);
	}
	//ㅗ 제외 dfs
    static void dfs(int r, int c, int num, int sum) {
        if (num == 4) { //4칸 완성하면 반환
            result = Math.max(result, sum);
            return;
        }
            
        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            
            if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc]) {
            	continue;
            }
            
            visited[nr][nc] = true;
            dfs(nr, nc, num+1, sum+arr[nr][nc]);
            //원상복구
            visited[nr][nc] = false;
        	}
        }
	

    //ㅗ ㅓ ㅏ ㅜ 체크
    static void check(int r, int c) {
    	int sum = 0;
    	//ㅗ 현 좌표 기준 좌 상 우 필요
    	if(r > 0 && c > 0 && c < M-1) {
    		sum = arr[r][c] + arr[r-1][c] + arr[r][c-1] + arr[r][c+1];
    		result = Math.max(result, sum);
    	}
    	//ㅜ 좌 우 하 팔요
    	if(r < N-1 && c > 0 && c < M-1) {
    		sum = arr[r][c] + arr[r+1][c] + arr[r][c-1] + arr[r][c+1];
    		result = Math.max(result, sum);
    		
    	}
    	//ㅓ 좌 상 하 필요
    	if(c > 0 && r > 0 && r < N-1) {
    		sum = arr[r][c] + arr[r][c-1] + arr[r-1][c] + arr[r+1][c];
    		result = Math.max(result, sum);
    	}    	
    	//ㅏ 상 우 하 필요
    	if(c < M-1 && r > 0 && r < N-1) {
    		sum = arr[r][c] + arr[r-1][c] + arr[r+1][c] + arr[r][c+1];
    		result = Math.max(result, sum);
    	}
    }   
}