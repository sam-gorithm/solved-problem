package N15656;

import java.util.Arrays;
import java.util.Scanner;

//	N과 M (7) / 실버 3 / 632ms
public class N15656_jinhyuk {
	//static
	static int N, M;
    static int[] num;
    static int[] arr;
    static boolean[] check;
    static StringBuilder sb = new StringBuilder();

    
    //main
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();

        num = new int[N];
        arr = new int[M];
        check = new boolean[N];
        for (int i = 0; i < N; i++) {
            num[i] = sc.nextInt();
        }
        Arrays.sort(num);
        func(0);
        System.out.println(sb);
    }	//main
    

    //func
    public static void func(int depth) {
    	
    	//기저조건
        if (depth == M) {
            for(int i=0; i<M; i++) {
            	sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        //재귀파트
        for (int i = 0; i < N; i++) {
            arr[depth] = num[i];
            func(depth + 1);
        }
    }
    
}	//class