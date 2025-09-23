package BOJ_2573;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class N2573_jeognun {
    static int N, M;
    static int arr[][];
    static int meltArr[][]; //녹은 빙하 저장용
    static boolean[][] visited;
    static int dr[] = {0, 0, 1, -1};
    static int dc[] = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = sc.nextInt();
            }
        }//입력 끝

        int time = 0; //시간 세기
        while (true) {//while 돌리다 빙산 > 1 되면 끊어줌
            meltArr = new int[N][M];
            visited = new boolean[N][M];

            int count = 0; //빙산 세기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] != 0 && !visited[i][j]) { //빙산 발견하면 bfs 시작
                        bfs(i, j);
                        count++; //빙산++
                    }
                }
            }

            if (count == 0) { //다 녹으면 0 출력
                System.out.println(0);
                return;
            }
            if (count > 1) { //두 개 이상 분리되면 끊기
                break;
            }

            // 다 세고 1년 뒤 한 번에 녹이기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] > 0) {
                        arr[i][j] -= meltArr[i][j]; //bfs에서 인접 바다 저장 후 녹임
                        if (arr[i][j] <= 0) {
                            arr[i][j] = 0;
                        }
                    }
                }
            }
            time++; // 1년 경과
        }

        System.out.println(time);

    }

    static int bfs(int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        visited[i][j] = true;
        q.offer(new int[] {i, j});

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int sea = 0; //인접 바다 세는 용도

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    continue;
                }
                if (arr[nr][nc] == 0) { //0이면 바다니까 count++
                    sea++;
                }
            }
            meltArr[r][c] = sea; //바다 개수 저장하고 메인 메서드에서 빼주기

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] || arr[nr][nc] == 0) {
                    continue;
                }
                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc});
            }
        }

        return 0;
    }
}
