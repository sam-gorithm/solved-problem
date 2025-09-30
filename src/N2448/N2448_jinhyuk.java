package N2448;

import java.util.Scanner;

//	별 찍기 - 11 / 골드 4 / 484ms
public class N2448_jinhyuk {
    //static
    static char[][] map;

    
    //main
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();	//N = 3, 6, 12, 24, 48, ... (= 3x2^k, k=0~10)

        map = new char[N][2 * N - 1];	//높이 : N, 너비 : 2*N-1

        //우선 전체를 공백으로 채움
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                map[i][j] = ' ';
            }
        }//입력

        //재귀호출
        star(0, N - 1, N);	//(시작행, 시작열, 높이)

        //결과출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
        
    }	//main

    
    //star
    private static void star(int r, int c, int N) {
        
    	////기저조건
    	//가장 작은 삼각형(높이 N=3)일 때 별을 직접 찍는다.
        if (N == 3) {
            //제일 위에 줄 (r, c) = (0, N-1)
            map[r][c] = '*';
            
            //가운데 줄 (가운데 비우고 양 옆에 별 찍기)
            map[r + 1][c - 1] = '*';
            map[r + 1][c + 1] = '*';
            
            //제일 아래 줄 (N=3 -> 너비=N*2-1 만큼 별 찍기)
            for (int i = 0; i < 5; i++) {
                map[r + 2][c - 2 + i] = '*';
            }
            
            return;
        }

        
        ////재귀 파트: N/2 크기의 삼각형 3개로 나누어 호출
        int nextSize = N/2;
        
        //위쪽 삼각형
        star(r, c, nextSize);						//(시작행, 시작열, 높이)
        
        //아래에서 왼쪽 삼각형
        star(r + nextSize, c - nextSize, nextSize);	//(시작행, 시작열, 높이)
        
        //아래에서 오른쪽 삼각형
        star(r + nextSize, c + nextSize, nextSize);	//(시작행, 시작열, 높이)
        
        
    }	//star
       
}	//class