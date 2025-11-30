package N7795;

import java.util.Scanner;
import java.util.Arrays;

public class N7795_somin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
            }

            int[] B = new int[M];
            for (int i = 0; i < M; i++) {
                B[i] = sc.nextInt();
            }

            // 5. 정렬
            Arrays.sort(A);
            Arrays.sort(B);

            // 6. 투 포인터 
            int pointA = N - 1; // A의 가장 큰 값 인덱스
            int pointB = M - 1; // B의 가장 큰 값 인덱스
            int sum = 0;     

            // 범위를 벗어나지 않을 때까지 반복
            while (pointA >= 0 && pointB >= 0) {
                // A가 B보다 클 경우 -> B와 그보다 작은 모든 B를 먹을 수 있음
                if (A[pointA] > B[pointB]) {
                    sum += (pointB + 1); // 0번부터 pointB번까지
                    pointA--;            
                } else {
                    // A가 B보다 작거나 같을 경우 -> B를 더 작은 걸로 교체
                    pointB--; 
                }
            }

            System.out.println(sum);
        }
    }
}
