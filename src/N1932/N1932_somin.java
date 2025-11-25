package N1932;

import java.util.Scanner;

public class N1932_somin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] a = new int[n + 1][n + 1];
        int[][] d = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                a[i][j] = sc.nextInt();
            }
        }

        // 초기값
        d[1][1] = a[1][1];

        // DP 
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                d[i][j] = Math.max(d[i - 1][j - 1], d[i - 1][j]) + a[i][j];
            }
        }

        // 최댓값 
        int result = 0;
        for (int j = 1; j <= n; j++) {
            result = Math.max(result, d[n][j]);
        }

        System.out.println(result);
    }
}
