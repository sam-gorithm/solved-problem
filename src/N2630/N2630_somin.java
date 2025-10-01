import java.util.Scanner;

public class N2630_somin {

    public static int[][] board;
    // 하얀색과 파란색 색종이의 개수를 저장
    public static int wCnt = 0;
    public static int bCnt = 0;

    public static void solve(int x, int y, int n) {
        // 모두 같은 색인지 확인
        if (check(x, y, n)) {
            // 영역이 모두 같은 색일 경우 
            if (board[x][y] == 0) {
                wCnt++;
            } else {
                bCnt++;
            }
        } else {
            int newN = n / 2;

            solve(x, y, newN);                   // 좌상
            solve(x, y + newN, newN);            // 우상
            solve(x + newN, y, newN);            // 좌하
            solve(x + newN, y + newN, newN);     // 우하
        }
    }

    public static boolean check(int x, int y, int n) {
        int color = board[x][y]; // 영역의 첫 번째 칸 색상

        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                // 색이 다른 칸이 있으면 false 반환
                if (board[i][j] != color) {
                    return false;
                }
            }
        }
        // 모든 칸의 색이 동일하면 true 반환
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        board = new int[N][N];

        // N x N 입력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        // 전체 종이
        solve(0, 0, N);

        System.out.println(wCnt);
        System.out.println(bCnt);

    }
}