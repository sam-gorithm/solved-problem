package 나이트이동;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class N7562_jeongun {
    static int T, I, startR, startC, endR, endC;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dr = {-2,-2,-1,-1,1,1,2,2};
    static int[] dc = {-1,1,-2,2,-2,2,-1,1};
    static int[][] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for(int tc = 1; tc <= T; tc++) {
            I = sc.nextInt();
            arr = new int[I][I];
            visited = new boolean[I][I];
            dist = new int[I][I];

            startR = sc.nextInt();
            startC = sc.nextInt();
            endR = sc.nextInt();
            endC = sc.nextInt();

            bfs(startR,startC);

            //bfs에서 dist배열에 거리 저장해놨으니까
            System.out.println(dist[endR][endC]);
        }
    }

    static void bfs(int i, int j) {
        Queue<int []> q = new ArrayDeque<>();
        q.offer(new int[] {i,j});
        visited[i][j] = true;
        dist[i][j] = 0;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            if(r == endR && c == endC) {
                return;
            }

            for(int k = 0; k < 8; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if(nr < 0 || nc < 0 || nr >= I || nc >= I) {
                    continue;
                }
                if(!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    dist[nr][nc] = dist[r][c]+1;
                    q.offer(new int[] {nr,nc});
                }
            }
        }
    }
}
