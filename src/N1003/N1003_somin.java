package N1003;

import java.util.Scanner;

public class N1003_somin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // DP 배열 생성
        int[][] dp = new int[41][2];
        
        // 초기값
        dp[0][0] = 1; // 0은 1번
        dp[0][1] = 0; // 1은 0번
        
        // f(1)일 때
        dp[1][0] = 0; // 0은 0번
        dp[1][1] = 1; // 1은 1번
        
        for (int i = 2; i <= 40; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-2][0];
            dp[i][1] = dp[i-1][1] + dp[i-2][1];
        }
        
        int T = sc.nextInt();
        
        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            System.out.println(dp[N][0] + " " + dp[N][1]);
        }
    }
}
