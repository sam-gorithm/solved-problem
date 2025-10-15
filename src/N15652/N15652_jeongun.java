package N15652;

import java.util.Scanner;

public class N15652_jeongun {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[M];
        dfs(1, 0);
    }

    static void dfs(int start, int num) {
        if (num == M) {
            for (int i = 0; i < M; i++) {
                System.out.print(arr[i]);
                if (i < M-1) {
                    System.out.print(' ');
                }
            }
            System.out.println();
            return;
        }
        for (int i = start; i <= N; i++) {
            arr[num] = i;
            dfs(i, num+1);
        }
    }
}
