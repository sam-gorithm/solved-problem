package 불;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class N5427_jeongun {
    static int H, W; // 세로, 가로
    static char[][] arr;
    static boolean[][] visited;
    static int dr[] = {0, 1, 0, -1};
    static int dc[] = {1, 0, -1, 0};
    static String result;
    static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {

            //메인 메서드에선 입력만 받고 bfs 메서드에서 다 처리
            W = sc.nextInt();
            H = sc.nextInt();

            arr = new char[H][W];
            visited = new boolean[H][W]; //지훈이용 -> 불은 그냥 F처리 해줘야함 (지훈이가 피해갈 수 있게)
            count = 0; // 테스트케이스마다 초기화

            for (int i = 0; i < H; i++) {
                String str = sc.next();
                for (int j = 0; j < W; j++) {
                    arr[i][j] = str.charAt(j);
                }
            } //입력 끝
            int result = bfs();

            if (result == -1) { //-1 반환 됐을 시 탈출 못한거
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(result);

            }
        }
    }


    static int bfs() { //불 큐, 지훈 큐 따로 만들기 -> 지훈이는 불 이동 영향 받으니 불 먼저
        Queue<int[]> fireQ = new ArrayDeque<>();
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < H; i++) { //불 시작점 찾고 큐에 넣기
            for (int j = 0; j < W; j++) {
                // 5427: 불은 '*'
                if (arr[i][j] == '*') {
                    fireQ.offer(new int[] {i, j});
                }
            }
        }

        for (int i = 0; i < H; i++) { //지훈 시작점 찾고 큐에 넣기
            for (int j = 0; j < W; j++) {
                if (arr[i][j] == '@') {
                    visited[i][j] = true;
                    q.offer(new int[] {i, j});
                }
            }
        }


        while (!q.isEmpty()) { //지훈이 이동할 때
            int qSizeF = fireQ.size(); //for문에 fireQ.size() 그대로 쓰면 실시간으로 크기 변경됨 --> 매 라운드 큐 사이즈 달라지니 while문 안에서 선언해야함 !!
            int qSize = q.size(); //무조건 바꿔줘야 고정된 크기임

            for (int i = 0; i < qSizeF; i++) { //불 bfs 먼저 시작
                int[] curr = fireQ.poll();
                int r = curr[0];
                int c = curr[1];

                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    if (nr < 0 || nc < 0 || nr >= H || nc >= W || arr[nr][nc] == '*' || arr[nr][nc] == '#') {
                        continue;

                    } else {
                        arr[nr][nc] = '*';
                        fireQ.offer(new int[] {nr, nc});
                    }
                }
            }

            for (int j = 0; j < qSize; j++) { //지훈 bfs
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];

                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    if (nr < 0 || nc < 0 || nr >= H || nc >= W) {
                        return count+1; //경계 걸쳤을 때 +1 해야 탈출하니까
                    } else {
                        if (arr[nr][nc] != '.' || visited[nr][nc]) {
                            continue;
                        }
                        visited[nr][nc] = true;
                        q.offer(new int[] {nr, nc});
                    }
                }
            }
            count++; //탐색 한 번 다 끝나고 count++ (중복 XX)
        }

        return -1; //탈출 못했으면 -1 반환하고
    }
}