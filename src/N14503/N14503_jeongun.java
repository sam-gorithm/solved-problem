import java.util.*;

public class N14503_jeongun {
    static int N, M, r, c, d;
    static int[][] arr;
    static int count; //청소 칸
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();
        d = sc.nextInt();

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        count = 0;

        //청소 시작
        if (arr[r][c] == 0) {
            arr[r][c] = -1;
            count++;
        }

        while (true) {
            boolean check = false;

            for (int k = 0; k < 4; k++) {
                d = (d+3)%4; //좌회전
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    continue;
                }

                if (arr[nr][nc] == 0) { //청소 XX
                    r = nr; //좌표 갱신
                    c = nc;

                    arr[r][c] = -1; //청소
                    count++;

                    check = true;
                    break;
                }
            }

            if (check) {
                continue;
            }

            //청소 못하면 뒤로 가기
            int back = (d+2) % 4;
            int br = r + dr[back];
            int bc = c + dc[back];

            //벽 만나면 break
            if (br < 0 || bc < 0 || br >= N || bc >= M || arr[br][bc] == 1) {
                break;
            }

            //좌표 갱신
            r = br;
            c = bc;
        }
        System.out.println(count);
    }
}