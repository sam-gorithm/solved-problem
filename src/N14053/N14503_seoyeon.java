package N14053;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class N14503_seoyeon {

    static int N, M;
    static int[][] map;
    static int currR, currC, currD; // 로봇의 최초 좌표와 방향

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        currR = Integer.parseInt(st.nextToken());
        currC = Integer.parseInt(st.nextToken());
        currD = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean();

        bw.write(Integer.toString(cnt));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void clean() {

        while (true) {
            // 현재 좌표가 청소가 되지 않았을 경우
            if (map[currR][currC] == 0) {
                map[currR][currC] = -1;
                cnt++;
            }

            // 청소가 되어있을 경우 -> 주위 동서남북 탐색
            boolean isDirty4 = false;
            for (int i = 0; i < dr.length; i++) {
                int nr = currR + dr[i];
                int nc = currC + dc[i];
                // 배열 범위 유효한지 확인
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    continue;
                }
                // 청소할 곳이 있으면
                if (map[nr][nc] == 0) {
                    isDirty4 = true;
                    break;
                }
            } // 4

            // 주변 4칸 청소가 안 되었을 경우
            if (isDirty4) {
                for (int i = 0; i < 4; i++) {
                    // 현재 방향에서 90도 회전
                    mv();
                    int frontR = currR + dr[currD];
                    int frontC = currC + dc[currD];

                    // 배열 범위 및 청소할 곳인지 확인
                    if ((frontR >= 0 && frontR < N && frontC >= 0 && frontC < M) && map[frontR][frontC] == 0 ) {
                        currR = frontR;
                        currC = frontC;
                        break;
                    } else {
                        continue;
                    }

                }

            }

            // 주변 4칸 청소가 되어있을 경우
            else {

                int backDir = (currD + 2) % 4;
                int backR = currR + dr[backDir];
                int backC = currC + dc[backDir];

                // 벽이 아닌지 배열 범위를 초과하지 않는지 확인
                if (backR < 0 || backR >= N || backC < 0 || backC >= M || map[backR][backC] == 1) {
                    return;
                }
                // 벽이 아니면 바로 뒤로 후진
                else {
                    // 후진
                    currR = backR;
                    currC = backC;
                }
            }
        } // while

    }


    // 인접한 4방향 중 청소가 안 되어있을 때 90도 방향을 바꾸는 함수
    private static void mv() {
        switch (currD) {
            case 0:
                currD = 3;
                break;
            case 1:
                currD = 0;
                break;
            case 2:
                currD = 1;
                break;
            case 3:
                currD = 2;
                break;
        }
    }
}
