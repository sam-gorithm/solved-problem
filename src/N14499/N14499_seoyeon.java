package N14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N14499_seoyeon {

    static int M, N, K, x, y;
    static int[][] map = new int[20][20];
    // 주사위 정의(처음 0)
    // 윗면 북 동 서 남 아래
    static int[] dice = new int[7];
    // 동 서 북 남
    static int[] dr = {0, 0, 0, -1, 1};
    static int[] dc = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int inst = 0;

        while (st.hasMoreTokens()) {
            inst = Integer.parseInt(st.nextToken());
            solve(inst);
        }
    }

    private static void solve(int inst) {

        // 1. 다음 위치 계산:
        int nr = x + dr[inst];
        int nc = y + dc[inst];


        // 2. 경계 검사:
        if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
            return;
        }

        // 4. (경계를 벗어나지 않았으므로) 주사위를 굴립니다.
        //    각 case 문 안에서 값 교환(swap)에 필요한 임시 변수(temp)를 선언하고 사용합니다.

        switch (inst) {
            case 1: // 동
                int curr = dice[1];
                dice[1] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[3];
                dice[3] = curr;
                break;

            case 2: // 서
                curr = dice[1];
                dice[1] = dice[3];
                dice[3] = dice[6];
                dice[6] = dice[4];
                dice[4] = curr;
                break;

            case 3: // 북

                // 굴리기 전 윗면(1) 값을 임시 변수(temp)에 저장합니다.
                // 새 윗면(1)에 <- 현재 남쪽면(5) 값을 넣습니다.
                // 새 남쪽면(5)에 <- 현재 아랫면(6) 값을 넣습니다.
                // 새 아랫면(6)에 <- 현재 북쪽면(2) 값을 넣습니다.
                // 새 북쪽면(2)에 <- 임시 변수(temp)에 저장했던 (구)윗면 값을 넣습니다.
                curr = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[2];
                dice[2] = curr;
                break;

            case 4: // 남

                curr = dice[1];
                dice[1] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[5];
                dice[5] = curr;

                break;
        }

        // 5. (switch 문이 끝난 후) 지도와 상호작용합니다.
        //    이동한 새 위치(nx, ny)의 지도 칸(map[nx][ny]) 값을 확인합니다.
        //    (if 문 사용)
        if(map[nr][nc]==0){
            map[nr][nc]=dice[6];
        }
        else{
            dice[6]=map[nr][nc];
            map[nr][nc]=0;
        }

        x= nr;
        y= nc;

        System.out.println(dice[1]);
    }


}
