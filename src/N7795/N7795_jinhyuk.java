package N7795;

import java.util.Arrays;
import java.util.Scanner;

//	먹을 것인가 먹힐 것인가 / 실버 3 / 848ms
public class N7795_jinhyuk {
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        //테스트 케이스
        for (int tc = 1; tc <= T; tc++) {
            
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[] A = new int[N];
            int[] B = new int[M];

            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
            }//A입력

            for (int i = 0; i < M; i++) {
                B[i] = sc.nextInt();
            }//B입력

            //정렬
            Arrays.sort(A);
            Arrays.sort(B);

            int count = 0;	//정답 쌍 개수
            int idx = 0; 	//비교할 B 인덱스 위치

            //A[i]보다 작은 B의 원소 개수 세기
            for (int i = 0; i < N; i++) {
            	
                while (idx < M && A[i] > B[idx]) {
                	//A[i] > B[idx] 이고, idx가 M 이내라면 증가
                    idx++;
                }
                
                count += idx;	//idx가 정답
            }//개수 세기

            //결과 출력
            System.out.println(count);
        }	//tc

    }	//main
    
    
}	//class