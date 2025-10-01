import java.util.Arrays;
import java.util.Scanner;

public class N2448_somin {

    static char[][] board;

    public static void star(int r, int c) {
        // 맨 위 
        board[r][c] = '*';
        
        // 중간
        board[r + 1][c - 1] = '*';
        board[r + 1][c + 1] = '*';

        // 맨 아래 
        for (int i = 0; i < 5; i++) {
            board[r + 2][c - 2 + i] = '*';
        }
    }

    public static void solve(int n, int r, int c) {
        if (n == 3) {
            star(r, c);
            return;
        }

        int sub = n / 2;
        
        solve(sub, r, c);
        solve(sub, r + sub, c - sub);
        solve(sub, r + sub, c + sub);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        board = new char[N][2 * N - 1];

        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], ' ');
        }

        solve(N, 0, N - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(board[i]);
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}