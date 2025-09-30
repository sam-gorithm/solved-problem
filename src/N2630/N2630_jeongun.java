import java.util.Scanner;

public class N2630_jeongun {
    static int N;
    static int[][] arr;
    static int white = 0;
    static int blue = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        cut(0, 0, N);

        System.out.println(white);
        System.out.println(blue);
    }

    static void cut(int r, int c, int size) {
        if (check(r,c,size)) {
            if (arr[r][c] == 0) {
                white++;
            }
            else blue++;
            return;
        }

        int next = size/2;
        cut(r,c,next);               // 좌상
        cut(r,c+next,next);          // 우상
        cut(r+next,c,next);          // 좌하
        cut(r+next,c+next,next);     // 우하
    }

    static boolean check(int r, int c, int size) {
        int k = arr[r][c];
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (arr[i][j] != k) return false;
            }
        }
        return true;
    }
}
