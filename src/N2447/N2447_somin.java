import java.util.Arrays;
import java.util.Scanner;

public class N2447_somin {

    static char[][] board;

    public static void star(int n, int r, int c) {
        if (n == 1) {
            board[r][c] = '*';
            return;
        }

        int sub = n / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                star(sub, r + i * sub, c + j * sub);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], ' ');
        }

        star(N, 0, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(board[i]);
            sb.append('\n');
        }
        System.out.print(sb.toString());

    }
}