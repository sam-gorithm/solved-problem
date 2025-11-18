package N3190;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class N3190_seoyeon {
    // 배열 크기
    static int N;
    // 뱀의 이동과 사과가 있는 곳을 저장할 게임 지도 생성
    static int[][] game;
    // 사과 갯수, 뱀 방향
    static int K, L;
    // 뱀이 이동할 시간과 방향을 담아 놓을 맵
    static Map<Integer, String> move = new HashMap<>();
    // 시간을 측정할 변수
    static int time = 0;
    // 뱀 이동 방향 정의 상우하좌
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    // 뱀 이동 좌표 저장
    static Deque<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        game = new int[N + 1][N + 1];

        K = Integer.parseInt(br.readLine());

        int k = K;
        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // 사과 위치 기록
            game[r][c] = 1;
        }

        L = Integer.parseInt(br.readLine());
        int l = L;
        while (l-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            move.put(sec, dir);
        }
        // 뱀 몸을 추가
        queue.add(new int[] {1, 1});

        // 뱀 초기 위치
        game[1][1] = 2;
        move();

        bw.write(Integer.toString(time));
        bw.flush();
        bw.close();
        br.close();

    }

    // 뱀이 이동하는 메소드
    private static void move() {
        int snakeR = 1; // 현재 뱀의 r 좌표
        int snakeC = 1; // 현재 뱀의 c 좌표
        int snakeD = 1; // 현재 뱀의 방향 (오른쪽)

        while (true) {
            // 시간 증가
            time++;


            int nr = snakeR + dr[snakeD];
            int nc = snakeC + dc[snakeD];

            // 벽 부딪히면
            if (nr <= 0 || nr >= N + 1 || nc <= 0 || nc >= N + 1) {
                return;
            }

            // 몸 충돌
            // game 배열에서 뱀이 위치하고 있는 곳 다음에 방문하게 되면 바로 return
            if (game[nr][nc] == 2) {
                return;
            }

            // 사과가 있는 경우
            if (game[nr][nc] == 1) {
                // 사과 먹기
                game[nr][nc] = 0;
            }
            // 사과가 없는 경우
            else if (game[nr][nc] == 0) {
                // 꼬리 줄이기
                int[] removeTail = queue.removeFirst();
                game[removeTail[0]][removeTail[1]] = 0;
            }
            // 머리 이동
            queue.add(new int[] {nr, nc});
            game[nr][nc] = 2;
            snakeR = nr;
            snakeC = nc;

            // 방향 전환
            if (move.containsKey(time)) {
                snakeD = moveDir(snakeD);
            }


        } // while

    }

    // 뱀의 방향을 바꾸는 함수
    // 상우하좌
    private static int moveDir(int dir) {
        String currD = move.get(time);
        // 왼쪽으로 90
        if (currD.equals("L")) {
            switch (dir) {
                case 0:
                    return 3;
                case 1:
                    return 0;
                case 2:
                    return 1;
                case 3:
                    return 2;
            }
        }
        // 오른쪽으로 90
        else {
            switch (dir) {
                case 0:
                    return 1;
                case 1:
                    return 2;
                case 2:
                    return 3;
                case 3:
                    return 0;
            }

        }

        return dir;
    }

}
