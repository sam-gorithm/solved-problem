package N1932;

import java.util.Scanner;

//	정수 삼각형 / 실버 1 / 644ms
public class N1932_jinhyuk {
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();	//1 ~ 500

        int[][] tri = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                tri[i][j] = sc.nextInt();
            }
        }//입력

        //0 ~ n-1까지 중 n-2부터 거꾸로
        for (int i = n - 2; i >= 0; i--) {
        	//열 별로 아래 칸(i+1) 좌우 열(j, j+1) 중 더 큰 수 더하기
            for (int j = 0; j <= i; j++) {
                tri[i][j] += Math.max(tri[i + 1][j], tri[i + 1][j + 1]);
            }
        }

        //결과출력
        System.out.println(tri[0][0]);	//제일 위 꼭짓점값이 경로의 최대합
        
    }	//main
    
    
}	//class