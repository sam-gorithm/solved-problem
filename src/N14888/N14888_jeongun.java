import java.util.Scanner;

public class N14888_jeongun {
    static int max, min, N;
    static int[] arr = new int[4];
    static int[] num;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        num = new int[N];
        int i;
        
        for (i = 0; i < N; i++) {
            num[i] = sc.nextInt();
        }
        for (i = 0; i < 4; i++) {
            arr[i] = sc.nextInt();
        }
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        dfs(num[0],1);

        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int n, int idx) {
        if (idx == N) {
            if (n > max) {
                max = n;
            }
            if (n < min) {
                min = n;
            }
            return;
        }
        int i;
        for (i = 0; i < 4; i++) {
            if (arr[i] > 0) {
                arr[i]--;
                if (i == 0) {                 //+
                    dfs(n+num[idx], idx+1);
                } else if (i == 1) {          //-
                    dfs(n-num[idx], idx+1);
                } else if (i == 2) {          //*
                    dfs(n*num[idx], idx+1);
                } else if (i == 3) {          ///
                    dfs(n/num[idx], idx+1);
                }
                arr[i]++; //원복
            }
        }
    }
}
