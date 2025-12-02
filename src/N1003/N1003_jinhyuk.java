package N1003;

import java.util.Scanner;

//	피보나치 함수 / 실버 3 / 100ms
public class N1003_jinhyuk {
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        //DP (N : 0 ~ 40)
        int[] dp0 = new int[41];
        int[] dp1 = new int[41];

        //N = 0
        dp0[0] = 1;
        dp1[0] = 0;

        //N = 1
        dp0[1] = 0;
        dp1[1] = 1;

        //N = 2 ~ 40
        for (int i = 2; i <= 40; i++) {
            dp0[i] = dp0[i-1] + dp0[i-2];
            dp1[i] = dp1[i-1] + dp1[i-2];
        }

        //결과출력
        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            System.out.println(dp0[N] + " " + dp1[N]);
        }

    }	//main
    
    
}	//class