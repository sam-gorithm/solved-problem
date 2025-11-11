package N14500;

import java.util.Scanner;

//	테트로미노 / 골드 4 / 876ms
//'ㅗ' 모양 제외 -> 한붓그리기 가능 -> DFS
//'ㅗ' 모양 직접 세기
public class N14500_jinhyuk {
	//static
    static int N, M;
    static int[][] map;
    static boolean[][] visited;	//DFS 체크
    static int max = 0;	//최대값
    //4방향탐색 (상하좌우)
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    
    //main
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();	//N x M
        M = sc.nextInt();

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }//입력 끝

        //DFS탐색
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                //'ㅗ' 모양 제외한 테트로미노 탐색
                visited[r][c] = true;
                dfs(r, c, 1, map[r][c]);	//(r,c) / 현재칸수 / 합
                visited[r][c] = false;	//다음 탐색 위해 복구

                //'ㅗ' 모양 4방향 직접 세기
                check(r, c);
            }
        }

        //결과출력
        System.out.println(max);
        
    }	//main

    
    //dfs
    static void dfs(int r, int c, int depth, int sum) {
        
    	//기저조건
        if (depth == 4) {
            max = Math.max(max, sum); //최대값 갱신 후 종료
            return;
        }

        //재귀파트
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            //유효범위체크
            if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

            //이미 방문했다면 (한붓그리기X)
            if (visited[nr][nc]) {
                continue;
            }

            //다음 칸
            visited[nr][nc] = true;
            dfs(nr, nc, depth + 1, sum + map[nr][nc]);
            visited[nr][nc] = false;	//다음 탐색 위해 복구

        }
    }	//dfs


    //check (ㅗㅓㅜㅏ)
    static void check(int r, int c) {
    	//'ㅜ'
        if (r + 1 < N && c - 1 >= 0 && c + 1 < M) {
            int sum = map[r][c] + map[r][c - 1] + map[r][c + 1] + map[r + 1][c];
            max = Math.max(max, sum);
        }

        //'ㅗ'
        if (r - 1 >= 0 && c - 1 >= 0 && c + 1 < M) {
            int sum = map[r][c] + map[r - 1][c] + map[r][c - 1] + map[r][c + 1];
            max = Math.max(max, sum);
        }

        //'ㅓ'
        if (r - 1 >= 0 && r + 1 < N && c - 1 >= 0) {
            int sum = map[r][c] + map[r - 1][c] + map[r + 1][c] + map[r][c - 1];
            max = Math.max(max, sum);
        }

        //'ㅏ'
        if (r - 1 >= 0 && r + 1 < N && c + 1 < M) {
            int sum = map[r][c] + map[r - 1][c] + map[r + 1][c] + map[r][c + 1];
            max = Math.max(max, sum);
        }
    }	//check
    
    
}	//class