package BOJ_2206;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[][][] dist; //최단거리 저장용 + 벽 부순 상태 체크
    static boolean[][][] visited; // 벽 안 깸 0 깸 1
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N][M];
        dist = new int[N][M][2]; // 벽 상태 0 1 로만 체크할거니까 2개로 생성
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        } //입력 끝 + 정수 변환

       int result = bfs(0, 0);
        System.out.println(result);
    }

    static int bfs(int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        visited[i][j][0] = true; //시작좌표는 0이니까 벽 없는 상태 -> 안 깨도 됨 -> [][][0]
        q.offer(new int[]{i,j,0});
        dist[i][j][0] = 1; //최단 거리 1로 시작

        while(!q.isEmpty()) {
            int curr[] = q.poll();
            int r = curr[0];
            int c = curr[1];
            int b = curr[2];

            if(r == N-1 && c == M-1) {
                return dist[r][c][b];
            }

            for(int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if(nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    continue;
                }

                if(arr[nr][nc] == 0) { //벽 아닐 때 -> 그냥 탐색이랑 똑같음
                    if(!visited[nr][nc][b]) {
                        visited[nr][nc][b] = true;
                        dist[nr][nc][b] = dist[r][c][b]+1;
                        q.offer(new int[]{nr,nc,b});
                    }

                } else { //벽일 때
                    if(b == 0 && !visited[nr][nc][1]) { //b가 0 -> 벽 깰 수 있음 ! && ![][][1] 벽 깬 상태 아닐 떄 !
                        visited[nr][nc][1] = true;
                        dist[nr][nc][1] = dist[r][c][b]+1;
                        q.offer(new int[]{nr,nc,1});
                    }
                }
            }
        }
        return -1;
    }
}