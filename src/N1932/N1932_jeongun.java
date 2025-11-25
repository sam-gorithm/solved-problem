import java.util.Scanner;

public class N1932_jeongun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        //파스칼 삼각형
        for (int i = 1; i < N; i++) {
            //왼쪽
            arr[i][0] = arr[i][0] + arr[i-1][0];

            //가운데
            for (int j = 1; j < i; j++) {
                int left = arr[i-1][j-1];
                int right = arr[i-1][j];
                if (left > right) {
                    arr[i][j] = arr[i][j] + left;
                }
                else arr[i][j] = arr[i][j] + right;
            }
            //오른쪽
            arr[i][i] = arr[i][i] + arr[i-1][i-1];
        }
        //마지막 줄 최댓값
        int result = arr[N-1][0];
        for (int j = 1; j < N; j++) {
            if (arr[N-1][j] > result) {
                result = arr[N-1][j];
            }
        }
        System.out.println(result);
    }
}